package com.shrey.db_backup_cli.constants;

public interface file {

    // -------- fetch table-names ---------
    String POSTGRES_TABLE_NAME_PATH = "classpath:sql/postgres/get_table_names.sql";
    String MYSQL_TABLE_NAME_PATH = "classpath:sql/mysql/get_table_names.sql";
    String ORACLE_TABLE_NAME_PATH = "classpath:sql/oracle/get_table_names.sql";
    String MSSQL_TABLE_NAME_PATH = "classpath:sql/mssql/get_table_names.sql";
    // ------------------------------


    // -------- fetch primary-key columns --------
    String POSTGRES_PRIMARYKEY_PATH = "classpath:sql/postgres/get_primarykey_columns.sql";
    String MYSQL_PRIMARYKEY_PATH = "classpath:sql/mysql/get_primarykey_columns.sql";
    String ORACLE_PRIMARYKEY_PATH = "classpath:sql/oracle/get_primarykey_columns.sql";
    String MSSQL_PRIMARYKEY_PATH = "classpath:sql/mssql/get_primarykey_columns.sql";
    // --------------------------------------------


    // --------- fetch table-structure ------------
    String POSTGRES_TABLE_STRUCTURE_PATH = "classpath:sql/postgres/get_table_structure.sql";
    String MYSQL_TABLE_STRUCTURE_PATH = "classpath:sql/mysql/get_table_structure.sql";
    String ORACLE_TABLE_STRUCTURE_PATH = "classpath:sql/oracle/get_table_structure.sql";
    String MSSQL_TABLE_STRUCTURE_PATH = "classpath:sql/mssql/get_table_structure.sql";
    // --------------------------------------------
}
