--Go to new workbench query and type in command "CREATE DATABASE musedatabase;" and execute  
  (under the username and password you will be using for mysql and our project) 

-- Then pull the db dump set up branch from github, 
-- run this code in terminal. 
-- "mysql -u <A> -p <B> < your/current/directory/path/backend/dbdumps/musedatabase_dump.sql"
      -- the u- option here means username
      -- the -p option here means it will require your password entered after command is           entered

        -- "A" here would be your username for your local mysql instance
        
        -- "B"  here would be what the .sql dump file will be mapped to (Use                     "musedatabase" which is what youve already created in first step). Basically its         mapping the database structure that trey has already created using hibernate in           java file which will populate our 'musedatabase' holder with the sql                   information from the dump

-- go to application.properties and find your local database. On mine, it is "localhost" with port 8080.
-- Yours may be different perhaps in "db"

--Go to src/main/resources/application.properties in IDE.
Update the spring.datasource.url to match your local MySQL:

      --below is mine for example--
      spring.datasource.url=jdbc:mysql://localhost:3306/musedatabase

-- Update the spring.datasource.username and spring.datasource.password with your MySQL credentials.

-- When we start getting some good dummy info, we can update the repo with a new sql dump, which is a different mysql command 


December 15th, 2024, My app is not running as it seems some of the methods havent been fully built out, however i can see that
the "musedatabase" is populating with the correct tables so I think the database is set up correctly.
