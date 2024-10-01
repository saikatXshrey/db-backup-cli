package com.shrey.db_backup_cli.report.service;

import com.shrey.db_backup_cli.entity.TableStructureEntity;
import com.shrey.db_backup_cli.util.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
                                builder.append(column);
                            }));

            builder.append(")");
        }


        builder.append(System.lineSeparator())
                .append(")");

        return builder
                .toString();
    }
}
