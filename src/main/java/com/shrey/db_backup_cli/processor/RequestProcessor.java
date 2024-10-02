package com.shrey.db_backup_cli.processor;

import com.shrey.db_backup_cli.database.service.IDataSourceService;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.ResponseEntity;
import com.shrey.db_backup_cli.entity.TableStructureEntity;
import com.shrey.db_backup_cli.report.service.IReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("requestProcessor")
public class RequestProcessor implements
        IRequestProcessor<RequestEntity, ResponseEntity> {

    @Autowired
    @Qualifier("dataSourceService")
    private IDataSourceService dataSourceService;

    @Autowired
    @Qualifier("reportService")
    private IReportService reportService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProcessor.class);


    @Override
    public ResponseEntity process(RequestEntity request) {

        final ExecutorService service = Executors.newFixedThreadPool(
                Math.min(64, Runtime.getRuntime().availableProcessors() * 8)
        );

        dataSourceService
                .initializeDataSource(request);

        // fetch tableNames
        List<String> tables = dataSourceService
                .getTableNames(request);

        tables.forEach(table -> {
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


            // ------------------- generators -------------------
            String generatedQuery = reportService
                    .generateTotalQuery(
                            table,
                            primaryKeyColumns,
                            tableStructure,
                            tableData
                    );

            LOGGER.info("{}", generatedQuery);
            // --------------------------------------------------
        });

        return null;
    }
}
