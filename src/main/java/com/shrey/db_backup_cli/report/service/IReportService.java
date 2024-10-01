package com.shrey.db_backup_cli.report.service;

import com.shrey.db_backup_cli.entity.TableStructureEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IReportService {
    String generateTableStructure(
            String tableName,
            List<String> primaryKeyColumns,
            List<TableStructureEntity> tableStructure
    );

    String generateTableDataInsert(
            String tableName,
            List<Map<String, Object>> tableData
    );
}
