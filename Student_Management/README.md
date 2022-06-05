# student management
In this project I try to use Spring and Spring Boot framework to create a web application for managing student.

# Spring projects used in this application

>- Spring Data JPA
>- Spring Security
>- Spring MVC
>- validation using Spring boot validation
# Template engine
>- Thymeleaf
# Style
>- Tailwind css

# run the project using docker-compose :
- Build the project
```java
mvn package
```
- run containers
```shell
cd project_directoryproject_directory_path
docker-compose up --build -d
```
- access to project
```
phpMyAdmin : http://localhost:8090/
wab application : http://localhost:8083/
```
# run the project in your computer :
- inside the resources folder put uncomment propreties inside application.properties file
```properties
Spring.datasource.url=jdbc:mysql://localhost:3306/STD_DB?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.jpa.hibernate.ddl-auto=create
```
- start you database service
- run the project.
- access to project from : http://localhost:8080/
# login
- admin
> username : admin
>
> password : 1234
- user
> username : fouad
>
> password : 1234
# interfaces
- Home page

![Screenshot 2022-04-29 at 19-27-40 Students](https://user-images.githubusercontent.com/70094556/166003083-c85ca8fe-ed69-417d-bb23-c7a8796f22f0.png)

- Simple user page

![image](https://user-images.githubusercontent.com/70094556/166003721-4f470223-3375-4b2a-b49c-5ed88e78c1d9.png)

- Admin page

![image](https://user-images.githubusercontent.com/70094556/166004028-b9f8ec41-1d0e-4f3f-93c2-0b7fda8eddbc.png)

- Add Student formular

![image](https://user-images.githubusercontent.com/70094556/166004422-940a34e1-a3aa-4a17-8b0d-64556bcc5f78.png)

