# SCCartApp

Data Feed :

                 Go to the 'SCartServices' folder 
                 run the build command (mvn clean install)
                 Start the SpringBoot application (java -jar target/SCartServices-0.0.1-SNAPSHOT.jar)

       Create Users :

               Perform the Create User API-Post operation to create new user..

               Url : http://localhost:8080/sccart/user
               Method: POST
               Header : { Content-Type : application/json }
                Body :
                             {
                                "userId": 2,
                                 "name": "test",
                                 "password": "test123",
                                 "roleId": 7
                             }

          Create Product:

                     Perform the Create product API-Post operation to create new user..

               Url : http://localhost:8080/sccart/user
               Method: POST
                Header : { Content-Type : application/json }
                Body :
                             {
                               
                                    "price": 12,
                                    "name": "p7",
                                    "description": "description about product",
                                    "productId": 7,
                                    "ingredients":
                                        [
                                                   {
                                                   "percentage": 12,
                                                     "name": "ing1"
                                                 }
                                       ]
                            }

             

Running Application :

                 Go to the 'SCartServices' folder 
                 run the build command (sbt compilel)
                 Start the Web  application (sbt run)
                 Open the url (http://localhost:9000/)
                  Enter login credentials (Create the user before login.)
                  Browse the application..


Technologies:

                      Java, Spring Boot, MongoDB
                      Play framework, Scala, Sbt 


S/Ws to be Installed:
                                 
                                MongoDB,JAVA 8, SCALA
