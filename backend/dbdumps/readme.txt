-- After pulling the db dump set up branch, 
-- run this code in terminal. 
-- mysql -u <A> -p <B> < your/current/directory/path/backend/dbdumps/musedatabase_dump.sql
  -- the u- option here means username
  -- the -p option here means it will require your password entered after command is entered

-- "A" here would be your username for your local mysql instance

-- "B"  here would be what the .sql dump file will be mapped to (Use "musedatabase"). Basically its creating a new database "musedatabase" and uploading it with table structure using hibernate SQL commands that have already been started through treys crud operations.

-- go to application.properties and find your local database. On mine, it is "localhost" with port 8080.
-- Yours may be different perhaps in "db"

--Go to src/main/resources/application.properties.
Update the spring.datasource.url to match your local MySQL:

--below is mine for example--
spring.datasource.url=jdbc:mysql://localhost:3306/musedatabase

Update the spring.datasource.username and spring.datasource.password with your MySQL credentials.


December 15th, My app is not running as it seems some of the methods havent been fully built out, however i can see that
the "musedatabase" is populating with the correct tables so I think the database is set up correctly.
