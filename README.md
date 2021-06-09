# Oneworld-Accuracy

# Assessment Test
This is the REST API backend with spring boot

# Dependencies
## Requirements
*  Java 8 or later
*  STS , IntelliJ , Eclipse
*  Maven 4 and later

## Prerequisites
REST API using spring boot is as follows:

*  Create a User
*  Get the list of Users 
*  Verify the User 
*  Deactivate the User 
*  Update the User 
*  Notification for a validated and deactivated user (Using a Scheduler)


## Configuration
* Seed data is provided prior when deployment is ongoing either to create a Role as an Admin or User.
* Swagger is provded for this project .( http://localhost:8085/swagger-ui/#/ )



## Methods
#### Create User
* A user is created
* A POST RequestMethod is used with the payload below
* using the url :  localhost:8085/api/user

 ```payload
        {
 
  "email": "string",
  "firstname": "string",
  "lastname": "string",
  "mobile": "string",
  "password": "string",
  "roles": ["user"],
  "status": "string",
  "title": "string",
}
 ```



#### Get Users
* This Gets the list of all users by pagination
* A GET RequestMethod is used
* using the url :  localhost:8085/api/users?limit=1



#### Verify Users
* Upon Successful signup or creation of user, a response is returned which contains the verification url
* A GET RequestMethod is used 
* using the url :  localhost:8085/api/verify?email=test@yahoo.com
* On a successful verification means user is VERIFIED.
* A scheduler is running in this service that checks for VERIFIED users in every 30secs
* A Notification is sent to all VERIFIED users.



#### Deactivate Users
* Users can be deactivated by their Id.
* A DELETE RequestMethod is used 
* using the url :  localhost:8085/api/user/{id}
* A scheduler is running in this service that checks  for DEACTIVATED users in every 30secs
* A Notification is sent to all DEACTIVATED users.




#### Update User
* An user can update is data 
* A PUT RequestMethod is used with the payload below
* using the url :  localhost:8085/api/user/{id}

 ```payload
        {
 
  "firstname": "string",
  "lastname": "string",
  "mobile": "string",
  "password": "string",
  "title": "string",
}
 ```







