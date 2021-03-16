# Godel Technologies Test Task
***
## Project description
***
This is the REST Web Application serves as library that allows to add, get, edit and delete information about books and their authors.
Main goals of this task are described in [Mogilev Mastery 2021 Task.pdf](https://github.com/Pavel-Kuropatin/GodelTestTask/blob/main/Mogilev%20Mastery%202021%20Task.pdf) located in the project root.

### Application features:
1. Create books that contain following information: book name, year of publication, authors, publisher.
2. A book can have one or more authors. You can addThe book must have at least one author on creation/editing.
3. The book must have at least one author so when author is deleted all his books aldo deleted.
4. Create authors that contain following information: first name, last name, date of birth, sex.
5. If author
6. The author can have any number of books under his own authorship, including 0.
7. Ability to get/add/edit/delete the Book and the Author including adding and removing the author of the book.
8. Validation upon add/edit operation.
9. The application provides the ability to search criteria for books (including their combinations):
   * By name _(including the first letters)_
   * By year of publication _(in range)_
   * By the name of the publisher _(including the first letters)_
   * By the first name / last name of the author _(including the first letters)_
   * By sex
   * By year of birth _(in range)_
10. Application contains an SQL script to initialize data on application startup.
   
### Technology stack
* Java 11
* Maven
* Spring Web MVC
* Spring JDBC
* Thymeleaf
* PostgreSQL
* Git
* SonarCloud _via GitHub Actions_

### Known issues:
1. There is still some logic in controllers that must be moved to service classes
2. Vulnerability issue in <span style="color:green">SearchRepositoryImpl.findBooks()</span> method. SQL query must be changed.
3. Date of birth stored as string. Must be stored and processed as SQL Data

### TODO:
1. Fix known issues
2. Add tests
3. Add multiple selection of authors on book creation and adding/removing authors to book
4. Add admin/user functionality
5. Visually improve web forms by adding design

***
## Instructions
***
### To launch this project you will need:
* IntellijIDEA _CE or EE_
* JDK 11
* Apache Maven 3.6.3 _or higher_
* Apache Tomcat 9.0.41 _or higher_
* PostgreSQL 13.1 _or higher_

### How to:

1. Download project from github or clone using commands
   
        git clone https://github.com/Pavel-Kuropatin/GodelTestTask.git
        cd GodelTestTask
   
2. Create a database with a name <span style="color:green">library</span>, create user with a name <span style="color:green">username</span> and password <span style="color:green">password</span>.
You can also use different database name and user. To do so, you must specify parameters it in <span style="color:green">SpringConfig.java</span> in this method.
   
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/library);
            dataSource.setUsername("username");
            dataSource.setPassword("password");
            return dataSource;
        }
3. If you're using IntellijIDEA Community Edition you should go to _File -> Settings-> Plugins_ and install <span style="color:green">Smart Tomcat</span> plugin

![Smart Tomcat Plugin](images/Smart%20Tomcat%20Plugin.png)

4. Press <span style="color:green">Alt+Shift+F10</span> then <span style="color:green">0</span> to open Run/Debug Configuration then press <span style="color:green">Alt+Insert</span> select <span style="color:green">Smart Tomcat</span> and configure Run/Debug Configuration as follows. Make sure that _Context Path_ field is empty. Deployment directory root may be different.

![Smart Tomcat Plugin](images/Create%20Run-Debug%20Configuration.png)

In <span style="color:green">Before Launch</span> tab add <span style="color:green">Run Maven Goal</span> task with empty command line. That allows maven to use default goal specified in pom.xml

![Smart Tomcat Plugin](images/Select%20Maven%20Goal.png)

5. IntellijIDEA Enterprise Edition has inbuilt Tomcat Server, so you need to select _Tomcat Server -> Local_ as Run/Debug Configuration and configure it same as above. In addition, you should press the <span style="color:green">Fix</span> button and select <span style="color:green">GodelTestTask:war exploded</span>. _Application Context_ field must be empty.


6. If you did everything right you can see start page at [http://localhost:8080/](http://localhost:8080/)