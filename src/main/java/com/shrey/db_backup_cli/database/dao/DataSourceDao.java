package com.shrey.db_backup_cli.database.dao;

import com.shrey.db_backup_cli.database.mapper.PrimaryKeyColumnMapper;
import com.shrey.db_backup_cli.database.mapper.TableDataRowMapper;
import com.shrey.db_backup_cli.database.mapper.TableNameRowMapper;
import com.shrey.db_backup_cli.database.mapper.TableStructureRowMapper;
import com.shrey.db_backup_cli.entity.RequestEntity;
import com.shrey.db_backup_cli.entity.TableStructureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static com.shrey.db_backup_cli.constants.db.*;

@Repository("dataSourceDao")
public class DataSourceDao
        implements IDataSourceDao {

    @Override
    public List<String> fetchTableNames(
            DataSource dataSource,
            String query
    ) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate
                .query(query, new TableNameRowMapper());
    }

    @Override
    public List<String> fetchPrimaryKeyColumns(
            DataSource dataSource,
            String query
    ) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate
                .query(query, new PrimaryKeyColumnMapper());
    }

    @Override
    public List<TableStructureEntity> fetchTableStructure(
            DataSource dataSource,
            String query
    ) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate
                .query(query, new TableStructureRowMapper());
    }

    @Override
    public List<Map<String, Object>> fetchTableData(
            DataSource dataSource,
            String query
    ) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate
                .query(query, new TableDataRowMapper());
    }
}
