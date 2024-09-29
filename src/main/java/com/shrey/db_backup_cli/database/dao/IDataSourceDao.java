package com.shrey.db_backup_cli.database.dao;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public interface IDataSourceDao {

    List<String> fetchTableNames(
            DataSource dataSource,
            String query
    );

    List<Map<String, Object>> fetchTableData(
            DataSource dataSource,
            String query
    );
}
