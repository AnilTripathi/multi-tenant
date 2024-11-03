## Connect With Multi schema using PostgresSQL Database

### Below Procedure needs to create in Public Schema

```
CREATE OR REPLACE PROCEDURE create_schema(schema_name varchar(255))
LANGUAGE plpgsql
AS $$
BEGIN
    EXECUTE format('CREATE SCHEMA IF NOT EXISTS %I', schema_name);
EXCEPTION
    WHEN duplicate_schema THEN
        RAISE NOTICE 'Schema % already exists.', schema_name;  
END;
$$
```