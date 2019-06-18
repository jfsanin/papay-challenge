# Papay-challenge

## 1) Implementing an immutable queue:

The key point here is realize that a queue can be seen as a reverse stack, with this in mind I defined two stacks, one for saving the information in a push operation and the other one for reverse the previous stack and return the elements in a peek or in a pop operation.

the project was done using maven 3.5 and Java 8.

## 2) Design A Google Analytic like Backend System:

There are two main scenarios to keep in mind, when an application send information to the backend and when the user want to get some statitics about the information.

In the first case, the client send the information in asyncronous way to an API Gateway that validates that the app is allowed to send information, then a lambda function is created for mapping the information to a specific format and send this information to a event queue, in this case I chose Kafka. The information is taken by a server using Spark and put into a HBase database.


In the second case, an user ask for some statistics and an API Gateway validates that the user is valid, then a lambda function is created and this lambda ask the query to Apache Hive. Apache Hive takes the information from Hbase and create the statistics needed and return them to the user.

this is the diagram:



