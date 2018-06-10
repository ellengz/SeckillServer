# Spring Boot Server for Seckill



### Design
- Use Hibernate JPA and MySQL
- Return a Result (code, message, data) for every request
- Use XXStateEnum to list all identified states around topic XX
- Use XXException for error states from the list mentioned above
- Use ResultUtil to encapsulate a Result with null, object (data to be returned, e.g. API key), StateEnum or Exception as the input 
- Use ExceptionUtil (@ControllerAdvice) to capture Exceptions then call ResultUtil


### Features
- User Register
    - BCrypt (Spring Security) for password encryption
    - Unique username
- User Login
    - UUID as API key
- Seckill (TBC)
