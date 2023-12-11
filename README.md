THIS PROJECT IS STRUCTURED IN JAVA 17 WITH SPRING 3.2.0.

Before init:

 - In Project Structure > SDK Version: make sure it's poiting to 17;
 - In Maven Config > Bytecode Version: make sure it's poiting to 17;
 - Check if you've MySQL installed in your machine;
 - Set properties of your database in "aplication.properties".

Create a new folder in your dev environment and run "git clone https://github.com/joosilva/digimon-api.git" command.
After that open the project in an IDE and run the application.

This app is prepared to run just once. If you rerun, the application will have unexpected behavior, so open MySQL Workbench and drop the "digimon" schema.

Frameworks and Librarys in this project:

 - FlyWay: make the contact with your database and create tables by reading local scripts;
 - Lombok: helps us by avoiding the need to generate getters, setters, hash codes, among other facilities;
 - ModelMapper: map the classes properties and conver to another classes allowed we manipulate the data 'how we want'. 

Note: This app is designed for dev environment, so the feign is being consumed directly in "DigimonApiApplication". The ideal would be to create configuration classes for feign. 

Time spent: ~9h.
