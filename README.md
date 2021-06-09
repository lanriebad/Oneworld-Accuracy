# Oneworld-Accuracy

This is the REST API backend with spring boot

Dependencies
## Requirements
1.Java 8 or later
2.STS , IntelliJ , Eclipse
3.Maven 4 and later

## Prerequisites
REST API using spring boot is as follows:


1.Create a User
2.Get the list of Users 
3.Verify the User 
4.Deactivate the User 
5.Update the User 
6.Notification for a validated and deactivated user (Using a Scheduler)



## Configuration
1.Seed data is provided prior when deployment is ongoing either to create a Role as an Admin or User.
2.Swagger is provded for this project .( http://localhost:8085/swagger-ui/#/ )



## Methods
#### Create User
1.A user is created
2.A POST RequestMethod is used with the payload below
3.using the url :  localhost:8085/api/user

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



## Get Users
1.This Gets the list of all users by pagination
2.A GET RequestMethod is used
3.using the url :  localhost:8085/api/users?limit=1



## Verify Users
1.Upon Successful signup or creation of user, a response is returned which contains the verification url
2.A GET RequestMethod is used 
3.using the url :  localhost:8085/api/verify?email=test@yahoo.com
4.On a successful verification means user is VERIFIED.
5.A scheduler is running in this service that checks for VERIFIED users in every 30secs
6.A Notification is sent to all VERIFIED users.



## Deactivate Users
1.Users can be deactivated by their Id.
2.A DELETE RequestMethod is used 
3.Using the url :  localhost:8085/api/user/{id}
4.A scheduler is running in this service that checks  for DEACTIVATED users in every 30secs
5.A Notification is sent to all DEACTIVATED users.



## Update User
1.An user can update is data 
2.A PUT RequestMethod is used with the payload below
3.using the url :  localhost:8085/api/user/{id}

 ```payload
        {
 
  "firstname": "string",
  "lastname": "string",
  "mobile": "string",
  "password": "string",
  "title": "string",
}
 ```







