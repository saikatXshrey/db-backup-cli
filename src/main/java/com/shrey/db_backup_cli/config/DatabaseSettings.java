package com.shrey.db_backup_cli.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
public class DatabaseSettings {

    private DatabaseDrivers drivers;
    private DatabaseUrls urls;

    @Configuration
    @Getter
    @Setter
    public static class DatabaseDrivers {
        private String postgres;
        private String mysql;
        private String oracle;
        private String mssql;
    }

    @Configuration
    @Getter
    @Setter
    public static class DatabaseUrls {
        private String postgres;
        private String mysql;
        private String oracle;
        private String mssql;
    }
}
