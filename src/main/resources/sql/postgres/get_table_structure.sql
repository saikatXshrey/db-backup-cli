SELECT
    column_name,
    data_type,
	column_default,
    character_maximum_length,
    is_nullable
FROM information_schema.columns
WHERE table_name = '$TABLE'