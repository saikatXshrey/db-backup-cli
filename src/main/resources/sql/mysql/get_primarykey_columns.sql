SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = '$TABLE'
AND COLUMN_KEY = 'PRI';