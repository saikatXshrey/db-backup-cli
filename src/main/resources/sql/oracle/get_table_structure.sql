SELECT
    column_name,
    data_type,
    data_default as column_default,
    data_length as character_maximum_length,
    nullable as is_nullable
FROM
    user_tab_columns
WHERE
    table_name = '$TABLE'