# Example Spring Boot project using multiple databases

When I had to implement multiple databases of different database types in a Spring Boot application, I could not find an
example project. I found a nice project showing how to implement multiple databases of the same type (MySQL), which this 
project is based on:
https://github.com/spring-framework-guru/sfg-blog-posts/tree/master/multiple-data-sources

The project also has a blog post:
https://springframework.guru/how-to-configure-multiple-data-sources-in-a-spring-boot-application/

## Prepare to run the project 
To run this project you first have to start the 3 Docker containers with the MySQL, Postgres and MS-SQL database:
```
cd into src/main/resources/docker
docker-compose build
docker-compose up
```

Wait about a minute for all databases to start. The databases should be created automatically, although I had some
problems with the MS-SQL database. If it is not created automatically, please do the following:

```
docker exec -it CONTAINER_NAME bash
cd /usr/config
. ./configure-db.sh
```

## Run the project
* Execute MultipledatasourcesApplication
  * this creates the empty tables
* Execute MultipledatasourcesApplicationTest
  * this inserts one row into each table (if all is well)

## Connect to the different databases with a database tool:

### MS-SQL
* URL: jdbc:sqlserver://localhost:11433
* DbUser: sa
* DbPassword: Secret+01

### MySQL
* URL: jdbc:mysql://localhost:13306
* DbUser: root
* DbPassword: secret

### Postgres
* URL: jdbc:postgresql://localhost:15432/carddb
* DbUser: testuser
* DbPassword: secret


## Some additional links which were helpful in setting up this project

* Customize MS-SQL Docker container:
https://github.com/microsoft/mssql-docker/tree/master/linux/preview/examples/mssql-customize
* Possible Env variables for MS-SQL:
https://learn.microsoft.com/en-us/sql/linux/sql-server-linux-configure-environment-variables?view=sql-server-2017