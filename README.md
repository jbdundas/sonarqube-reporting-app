# sonarqube-reporting-app
This is a Spring boot Java application to give a UI page where the user can submit their SonarQube project key to get their XLS report on their last SonarQube analysis of the given project. We plan to add more reports and features down the line for downloads.

In order to make it run on your local, do the following:-
1) Download the project and via Maven, run the mvn eclipse:eclipse command from your root of this project.
2) Next, import this project into your Eclipse.
3) To build this project, type mvn clean install.
4) To run it, you have to pass 3 spring params:- sonar.server.url,sonar.server.username and sonar.server.password as command line arguments.
5) Ensure that the project is run using the mvn spring-boot:run command with the above params as command line arguments..They correspond to the SonarQube server URL, the SonarQube Admin user Login and SonarQube Admin password. 
6) This project is still not complete but majority of the code is in place. Please use this at your own risk and understanding. I am not unable to commit to fixes due to other priorities on my end. 

Command To Run:- mvn clean install spring-boot:run -Drun.arguments="--sonar.server.url=http://localhost:9000,--sonar.server.username=admin,--sonar.server.password=admin"

You may need to tweak the above command a bit to make it run as per your environment's needs. The properties passed are needed for making the connection to the SonarQube server and fetching the API data.
