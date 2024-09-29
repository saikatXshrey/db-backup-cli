package com.shrey.db_backup_cli.database.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TableDataRowMapper
        implements RowMapper<Map<String, Object>> {

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Object> row = new HashMap<>(columnCount);

        for (int index = 1; index <= columnCount; index++) {
            String columnName = metaData.getColumnName(index);
            Object columnValue = rs.getObject(index);
            row.put(columnName, columnValue);
        }

        return row;
    }
}
