SELECT
    column_name,
    data_type,
    column_default,
    character_maximum_length,
    is_nullable
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = '$TABLE'