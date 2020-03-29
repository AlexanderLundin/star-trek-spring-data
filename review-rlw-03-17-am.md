# Review Summary
- Rob Wing 3/17/2020 am

# General notes
- Your windows box is not setting the execute bit properly for gradlew.  This will be required in a prod environment as you 
checkin and deploy to unix environments.  It might be worth while to set it manually with the following code (run in cmd 
shell from the project root)...
```
$ git update-index --chmod=+x gradlew
```
- Check your package naming.  There is a wierd package in test/java com.galvanize.repositories.***JpaOfficerDaoTest.java***.JpaOfficerDAOTest
    - Due to the anonymous class in the file.  
    - should have been implemented with  an anonymous inner class, OR, the better option would have been to use a lambda (see code)
    - The pre work has a very good section on lambdas.  It may help.
- Naming: Class names begin with capital letter.  Everything else, except for CONSTANTS should begin with lower case letter.
- JPA - With Jpa, you only need to declare an interface, which is what makes it so popular.  Spring & Hibernate take
care of all of the implementations.  Make an appointment with me if you this is not clear.  I am happy to clarify it for you.
 