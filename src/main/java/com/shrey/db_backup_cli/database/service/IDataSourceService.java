package com.shrey.db_backup_cli.database.service;

import com.shrey.db_backup_cli.entity.RequestEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public interface IDataSourceService {

    void initializeDataSource(
            RequestEntity request
    );

    void getTableNames(
            RequestEntity request
    );
}
