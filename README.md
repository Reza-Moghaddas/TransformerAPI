# TransformerAPI
This RESTful API is covering the CRUD functionality of the sample Transformer and simulates a simple battle game between Autobots and Decepticons!

This project is based on Spring Boot freamework and to run the API, You need to create a maven based project from spring starter or Spring integrated project creatin
in your IDE and import existing source into it and replace the pom.xml file to add the dependencies needed to run the API.

After creating the project just Run the TransformerapiApplication class.

The project integarted to swagger and you can run each method in swagger ui using : http://localhost:8080/swagger-ui.html
To test the methods you also can use the tools like SwaggerUI or Postman.

# How To Build and create JAR package

Build:

./mvnw clean install

Build & JAR package:

./mvnw clean package

# How to Run the App:
After clean build and JAR packaging, You can go to the /target folder and check if the JAR file is created. You can run the JAR file, as follows:

java -jar target/transformerapi-0.0.1-SNAPSHOT.jar





