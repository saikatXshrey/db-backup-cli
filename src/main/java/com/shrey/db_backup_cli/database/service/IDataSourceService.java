package com.shrey.db_backup_cli.database.service;

import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.TableStructureEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public interface IDataSourceService {
    void initializeDataSource(RequestEntity request);
    List<String> getTableNames(RequestEntity request);
    List<String> getPrimaryKeyColumns(RequestEntity request, String tableName);
    List<TableStructureEntity> getTableStructure(RequestEntity request, String tableName);
}
