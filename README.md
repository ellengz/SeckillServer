# Spring Boot Server for Seckill



### Design
#### General
- Use Hibernate JPA and MySQL
- Return a Result (code, msg, data) for every request
- Use XXStateEnum to list all identified states around topic XX
- Use XXException for error states from the list mentioned above
- Use ResultUtil to encapsulate a Result with null, object (data to be returned, e.g. API key), StateEnum or Exception as the input 
- Use ExceptionUtil (@ControllerAdvice) to capture Exceptions then call ResultUtil

#### API
Method | URL | Action | Param (required)
------ | --- | -----  | ---
POST | /user | Create a new user | User (username, encrypPassword) 
POST | /login | User login | User (username, encrypPassword)
GET | /seckill | List all seckill products |
POST | /seckill | Create a seckill product | SeckillProduct (title, number, startTime, endTime)
GET | /seckill/id | Get product details by id |
POST | /seckill/id | Buy this product | ?


### Features
- User Register
    - BCrypt (Spring Security) for password encryption
    - Unique username
- User Login
    - UUID as API key
- Seckill (TBC)
