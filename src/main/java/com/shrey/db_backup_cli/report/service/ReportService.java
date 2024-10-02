package com.shrey.db_backup_cli.report.service;

import com.shrey.db_backup_cli.database.service.IDataSourceService;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.TableStructureEntity;
import com.shrey.db_backup_cli.util.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("reportService")
public class ReportService
        implements IReportService {

    @Override
    public String generateTableStructure(
            String tableName,
            List<String> primaryKeyColumns,
            List<TableStructureEntity> tableStructure
    ) {
        StringBuilder builder = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS")
                .append("\t")
                .append(tableName)
                .append("\t")
                .append("(")
                .append(System.lineSeparator());

        tableStructure
                .forEach(ListUtil
                        .withCounter((index, structure) -> {
                    builder.append("\t")
                            .append(structure.getColumn_name())
                            .append("\t")
                            .append(structure.getData_type().toUpperCase());

                    if (structure.getCharacter_maximum_length() > 0) {
                        builder.append("(")
                                .append(structure.getCharacter_maximum_length())
                                .append(")")
                                .append("\t");
                    }

                    if (Objects.nonNull(structure.getColumn_default())) {
                        builder.append("DEFAULT")
                                .append("\t");

                        if (StringUtils.isAlphaSpace(structure.getColumn_default())) {
                            builder.append("'")
                                    .append(structure.getColumn_default())
                                    .append("'")
                                    .append("\t");
                        } else {
                            builder.append(structure.getColumn_default())
                                    .append("\t");
                        }
                    }

                    if (!structure.getIs_nullable()) {
                        builder.append("\t")
                                .append("NOT NULL")
                                .append(",")
                                .append(System.lineSeparator());
                    } else {
                        if (primaryKeyColumns.size() > 0) {
                            builder.append(",")
                                    .append(System.lineSeparator());
                        } else {
                            builder.append(System.lineSeparator());
                        }
                    }
                }));

        if (primaryKeyColumns.size() > 0) {
            builder.append("\t")
                    .append("PRIMARY KEY (");

            primaryKeyColumns
                    .forEach(ListUtil
                        .withCounter((index, column) -> {
                            if ((index + 1) == primaryKeyColumns.size()) {
                                builder.append(column);
                            } else {
                                builder.append(column)
                                        .append(",")
                                        .append(" ");
                            }
                        }));

            builder.append(")");
        }


        builder.append(System.lineSeparator())
                .append(")");

        return builder
                .toString();
    }

    @Override
    public String generateTableDataInsert(
            String tableName,
            List<Map<String, Object>> tableData
    ) {
        StringBuilder builder = new StringBuilder();

        tableData.forEach(ListUtil
                .withCounter((index, data) -> {
                    builder.append("INSERT INTO")
                            .append("\t")
                            .append(tableName)
                            .append("\t")
                            .append("(");

                    // insert column names
                    data.forEach((key, value) -> {
                        builder.append(key)
                                .append(",")
                                .append(" ");
                    });

                    // remove last instance
                    builder
                        .setLength(builder.length() - 2);

                    builder.append(")")
                            .append("\t")
                            .append("VALUES")
                            .append("\t")
                            .append("(");

                    // insert value
                    data.forEach((key, value) -> {
                        builder.append("'")
                                .append(value)
                                .append("'")
                                .append(",")
                                .append(" ");
                    });

                    // remove last instance
                    builder
                        .setLength(builder.length() - 2);

                    builder.append(")")
                            .append(System.lineSeparator());
                }));

        return builder
                .toString();
    }

    @Override
    public String generateTotalQuery(
            IDataSourceService dataSourceService,
            RequestEntity request,
            String table
    ) {
        // --------------------- loaders -------------------
        // fetch primaryKey columns
        List<String> primaryKeyColumns = dataSourceService
                .getPrimaryKeyColumns(request, table);

        // fetch table-structure
        List<TableStructureEntity> tableStructure = dataSourceService
                .getTableStructure(request, table);

        // fetch table-table
        List<Map<String, Object>> tableData = dataSourceService
                .getTableData(table);
        // --------------------------------------------------


        // --------------------- generator -------------------
        String creationQuery = generateTableStructure(table, primaryKeyColumns, tableStructure);
        String insertQuery = generateTableDataInsert(table, tableData);

        StringBuilder builder = new StringBuilder()
                .append(System.lineSeparator())
                .append(creationQuery)
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(insertQuery);
        // ----------------------------------------------------

        return builder
                .toString();
    }
}
