# Spring Boot Server for Seckill



## Design
#### General
- Use Hibernate JPA and MySQL
- Return a Result (code, msg, data) for every request
- Use XXStateEnum to list all identified states around topic XX
- Use XXException for error states from the list mentioned above
- Use ResultUtil to encapsulate a Result with null, object (data to be returned, e.g. API key), StateEnum or Exception as the input 
- Use ExceptionUtil (@ControllerAdvice) to capture Exceptions then call ResultUtil

#### API
Method | Path | Action | Params (required) | Return Data
------ | --- | -----  | --- | ---
POST | /user | Create a new user | User (username, encryptPassword) |
POST | /login | User login | User (username, encrypPassword) | apiKey
GET | /product | List all seckill products | | 
POST | /product/{productId} | Get product details by id | apiKey | product info + secret key/system time 
POST | /product/{productId}/execution | Buy the product | apiKey, secretKey | order

### State
Code | Type
---- | ----
200 | Success
40X | Identified Error
-1 | Unidentified Error

## Features
- User Register
    - BCrypt (Spring Security) for password encryption
    - Unique username
- User Login
    - UUID as API key
- Seckill
    - Get product list
    - When go to the detail page of a product, product info will be returned as well as
        - if current time is valid, a secretPath
        - otherwise, system time
        - **Redis** is used in this process to decrease pressure on DB
    - Use secretPath to seckill a product
    - TBC
