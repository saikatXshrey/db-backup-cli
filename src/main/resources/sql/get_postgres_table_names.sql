SELECT *
  FROM information_schema.tables
 WHERE (
	   table_catalog = '$DATABASE'
   AND table_schema = '$SCHEMA'
   AND table_type='BASE TABLE'
)