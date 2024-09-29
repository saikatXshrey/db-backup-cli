package com.shrey.db_backup_cli.database.config;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @SneakyThrows
    public boolean isConnectionValid(
            DataSource dataSource
    ) {
        return dataSource
                .getConnection()
                .isValid(3000);
    }

//    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(
            String url,
            String username,
            String password,
            String driver
    ) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        LOGGER.warn("Database connection valid : [{}]",
                isConnectionValid(dataSource));

        return dataSource;
    }
}
