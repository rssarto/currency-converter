# currency converter
This project represents the back-end app that serves currency-converter-web (https://github.com/rssarto/currency-converter-web) app.

This applicaton is built upon Spring boot v1.5.9. and the concepts below were applied:
<ul>
  <li>Rest API;</li>
  <li>Spring Security customization for authentication and autorization using Jwt - Json Web Tokens;</li>
  <li>Cors - Cross origin source sharing;</li>
  <li>Spring Data for persistence usig Repository concept;</li>
  <li>Travis-ci for continous integration using Heroku PaaS.</li>
</ul>

# Build
After cloning the repository go to the repository folder and open the project folder with the command below:<br/>
<ul>
  <li>cd currency-converter-app</li>
</ul>
  
Since inside the project folder execute the command below to package the application ina runnable jar.<br/>
<ul>
  <li>mvn package</li>
</ul>  

# Running the application
In the project folder execute the command below:<br/>
<ul>
  <li>java -jar ./target/currency-converter-app-0.0.1-SNAPSHOT.jar</li>
</ul>  
  
  
# External Dependencies
The application expects you to have a PostgreSql server up and running on port 5432.
  
