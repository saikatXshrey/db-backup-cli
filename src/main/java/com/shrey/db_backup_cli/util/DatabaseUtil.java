package com.shrey.db_backup_cli.util;

import com.shrey.db_backup_cli.config.DatabaseSettings;
import com.shrey.db_backup_cli.entity.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.shrey.db_backup_cli.constants.db.*;
import static com.shrey.db_backup_cli.constants.file.*;

public class DatabaseUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    public static String configureDriver(
            DatabaseSettings settings,
            RequestEntity request
    ) {
        switch (request.getType()) {
            case POSTGRES -> {
                return settings.getDrivers().getPostgres();
            }

            case MYSQL -> {
                return settings.getDrivers().getMysql();
            }

            case ORACLE -> {
                return settings.getDrivers().getOracle();
            }

            case MSSQL -> {
                return settings.getDrivers().getMssql();
            }

            default -> {
                LOGGER.warn("Invalid Database Type!");
                return null;
            }
        }
    }

    public static String configureUrl(
            DatabaseSettings settings,
            RequestEntity request
    ) {
        switch (request.getType()) {
            case POSTGRES -> {
                return settings.getUrls().getPostgres()
                        .replaceAll("<host>", request.getHost())
                        .replaceAll("<port>", Integer.toString(request.getPort()))
                        .replaceAll("<database>", request.getDatabase())
                        .replaceAll("<schema>", request.getSchema());
            }

            case MYSQL -> {
                return settings.getUrls().getMysql()
                        .replaceAll("<host>", request.getHost())
                        .replaceAll("<port>", Integer.toString(request.getPort()))
                        .replaceAll("<database>", request.getDatabase())
                        .replaceAll("<schema>", request.getSchema());
            }

            case ORACLE -> {
                return settings.getUrls().getOracle()
                        .replaceAll("<host>", request.getHost())
                        .replaceAll("<port>", Integer.toString(request.getPort()))
                        .replaceAll("<database>", request.getDatabase())
                        .replaceAll("<schema>", request.getSchema());
            }

            case MSSQL -> {
                return settings.getUrls().getMssql()
                        .replaceAll("<host>", request.getHost())
                        .replaceAll("<port>", Integer.toString(request.getPort()))
                        .replaceAll("<database>", request.getDatabase())
                        .replaceAll("<schema>", request.getSchema());
            }

            default -> {
                LOGGER.warn("Invalid Database Type!");
                return null;
            }
        }
    }

    public static String configureTableNameQuery(
            RequestEntity request
    ) {
        switch (request.getType()) {
            case POSTGRES -> {
                return ResourceUtil
                        .readFileToString(POSTGRES_TABLE_NAME_PATH)
                        .replace("$DATABASE", request.getDatabase())
                        .replace("$SCHEMA", request.getSchema());
            }

            case MYSQL -> {
                return ResourceUtil
                        .readFileToString(MYSQL_TABLE_NAME_PATH)
                        .replace("$DATABASE", request.getDatabase())
                        .replace("$SCHEMA", request.getSchema());
            }

            case ORACLE -> {
                return ResourceUtil
                        .readFileToString(ORACLE_TABLE_NAME_PATH);
            }

            case MSSQL -> {
                return ResourceUtil
                        .readFileToString(MSSQL_TABLE_NAME_PATH)
                        .replace("$DATABASE", request.getDatabase())
                        .replace("$SCHEMA", request.getSchema());
            }

            default -> {
                LOGGER.warn("Invalid Database Type!");
                return null;
            }
        }
    }

    public static String configurePrimaryKeyQuery(
            RequestEntity request,
            String tableName
    ) {
        switch (request.getType()) {
            case POSTGRES -> {
                return ResourceUtil
                        .readFileToString(POSTGRES_PRIMARYKEY_PATH)
                        .replace("$TABLE", tableName);
            }

            case MYSQL -> {
                return ResourceUtil
                        .readFileToString(MYSQL_PRIMARYKEY_PATH)
                        .replace("$TABLE", tableName);
            }

            case ORACLE -> {
                return ResourceUtil
                        .readFileToString(ORACLE_PRIMARYKEY_PATH)
                        .replace("$TABLE", tableName);
            }

            case MSSQL -> {
                return ResourceUtil
                        .readFileToString(MSSQL_PRIMARYKEY_PATH)
                        .replace("$TABLE", tableName);
            }

            default -> {
                LOGGER.warn("Invalid Database Type!");
                return null;
            }
        }
    }

    public static String configureTableStructureQuery(
            RequestEntity request,
            String tableName
    ) {
        switch (request.getType()) {
            case POSTGRES -> {
                return ResourceUtil
                        .readFileToString(POSTGRES_TABLE_STRUCTURE_PATH)
                        .replace("$TABLE", tableName);
            }

            case MYSQL -> {
                return ResourceUtil
                        .readFileToString(MYSQL_TABLE_STRUCTURE_PATH)
                        .replace("$TABLE", tableName);
            }

            case ORACLE -> {
                return ResourceUtil
                        .readFileToString(ORACLE_TABLE_STRUCTURE_PATH)
                        .replace("$TABLE", tableName);
            }

            case MSSQL -> {
                return ResourceUtil
                        .readFileToString(MSSQL_TABLE_STRUCTURE_PATH)
                        .replace("$TABLE", tableName);
            }

            default -> {
                LOGGER.warn("Invalid Database Type!");
                return null;
            }
        }
    }
}
