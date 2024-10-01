package com.shrey.db_backup_cli.database.mapper;

import com.shrey.db_backup_cli.entity.TableStructureEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableStructureRowMapper
        implements RowMapper<TableStructureEntity> {

    @Override
    public TableStructureEntity mapRow(ResultSet rs, int rowNum)
            throws SQLException {

        return TableStructureEntity
                .builder()
                .column_name(rs.getString("column_name"))
                .data_type(rs.getString("data_type"))
                .column_default(rs.getString("column_default"))
                .character_maximum_length(rs.getInt("character_maximum_length"))
                .is_nullable(rs.getString("is_nullable").equalsIgnoreCase("YES"))
                .build();
    }
}
