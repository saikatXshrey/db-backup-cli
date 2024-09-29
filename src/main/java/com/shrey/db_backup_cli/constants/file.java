package com.shrey.db_backup_cli.constants;

public interface file {
    String POSTGRES_TABLE_NAME_PATH = "classpath:sql/get_postgres_table_names.sql";
    String MYSQL_TABLE_NAME_PATH = "classpath:sql/get_mysql_table_names.sql";
    String ORACLE_TABLE_NAME_PATH = "classpath:sql/get_oracle_table_names.sql";
    String MSSQL_TABLE_NAME_PATH = "classpath:sql/get_mssql_table_names.sql";
}
