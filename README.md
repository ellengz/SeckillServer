# Spring Boot Server for Seckill

## Design
#### General
- Use Hibernate JPA, MySQL and Redis
- Return a Result (code, msg, data) for every request
- Use XXStateEnum to list all identified states around topic XX (User, Seckill, etc.)
- Use XXException for error states from the list mentioned above
- Use ResultUtil to encapsulate a Result with null, object (data to be returned, e.g. user token), StateEnum or Exception as the input 
- Use ExceptionUtil (@ControllerAdvice) to capture Exceptions then call ResultUtil
- Use Jedis as Redis Client
- Use Redis to store product info to face high concurrency issue
- Use Protostuff to serialize/deserialize objects

#### API
Method | Path | Action | Params (required) | Return Data
------ | --- | -----  | --- | ---
POST | /user | Create a new user | User | userToken
POST | /login | User login | User (username, encrypPassword) | 
GET | /product | List all seckill products | | list of products
POST | /product/{productId} | Get product details by id | user token | product info + secreKey/systemTime (not start) 
POST | /product/{productId}/execution | Buy the product | user token, secretKey | order
POST | /order | List all orders | username | list of orders
POST | /order/{prodcutId} | Get specific order by order id | username | order

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
    - UUID as user token
    - Support third party user token (now: Facebook)
- User Login
- Seckill Product
    - Get product list
    - When go to the detail page of a product, product info will be returned as well as
        - if current time is valid && user token is valid, a secretKey
        - otherwise, system time
        - **Redis** is used in this process to store product info, timeout is set as 30mins
    - secretPath is needed in order to seckill a product
    - When stock is detected as 0 during execution, Redis will delete the product info to ensure consistency 
    - Return an order when seckill-execution succeeded
- Seckill Order
    - Create an order when seckill-execution succeeded
        - productId and username is combined as embeddedId for an order so that one user can only seckill a product once
    - Get order list
    - Get a specific order
