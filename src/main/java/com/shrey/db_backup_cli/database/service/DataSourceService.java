package com.shrey.db_backup_cli.database.service;

import com.shrey.db_backup_cli.config.DatabaseSettings;
import com.shrey.db_backup_cli.database.config.DataSourceConfiguration;
import com.shrey.db_backup_cli.database.dao.DataSourceDao;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service("dataSourceService")
public class DataSourceService
        implements IDataSourceService {

    @Autowired
    private DatabaseSettings databaseSettings;

    @Autowired
    private DataSourceConfiguration dataSourceConfiguration;

    @Autowired
    @Qualifier("dataSourceDao")
    private DataSourceDao dataSourceDao;

    private DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceService.class);

    @Override
    public void initializeDataSource(
            RequestEntity request
    ) {
        String driverClassName = DatabaseUtil
                .configureDriver(databaseSettings, request.getType());
        this.dataSource = dataSourceConfiguration
                .dynamicDataSource(
                        request.getUrl(),
                        request.getUsername(),
                        request.getPassword(),
                        driverClassName
                );
    }

    @Override
    public void getTableNames(
            RequestEntity request
    ) {
        String query = DatabaseUtil
                .configureTableNameQuery(request);

        LOGGER.info("Start fetching table-names");
        List<String> tableNames = dataSourceDao
                .fetchTableNames(dataSource, query);
        LOGGER.info("Table-Names fetched of size : [{}]",
                tableNames.size());
    }
}
