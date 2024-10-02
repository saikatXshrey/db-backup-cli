package com.shrey.db_backup_cli.processor;

import com.shrey.db_backup_cli.database.service.IDataSourceService;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.ResponseEntity;
import com.shrey.db_backup_cli.entity.TableStructureEntity;
import com.shrey.db_backup_cli.report.service.IReportService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
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
    @SneakyThrows
    public ResponseEntity process(RequestEntity request) {

        final ExecutorService executor = Executors.newFixedThreadPool(
                Math.min(64, Runtime.getRuntime().availableProcessors() * 8)
        );

        final CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        dataSourceService
                .initializeDataSource(request);

        // fetch tableNames
        List<String> tables = dataSourceService
                .getTableNames(request);

        tables.forEach(table -> completionService
                .submit(() -> reportService
                    .generateTotalQuery(
                            dataSourceService,
                            request,
                            table
                    )));

        //  resolve waiting CompletionService
        final List<String>
                reportResponses = new ArrayList<>(tables.size());

        while (reportResponses.size() < tables.size()) {
            LOGGER.info("Waiting for all responses to come back. Currently received [{}] responses out of [{}]",
                    reportResponses.size(),
                    tables.size()
            );

            reportResponses.add(completionService.take().get());
        }

        LOGGER.info("query : {}", reportResponses);

        return null;
    }
}
