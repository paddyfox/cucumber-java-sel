# Selenium-Cucumber-Java

This is a simple example project which uses Selenium Webdriver and Cucumber. 
Change the config in serenity.properties file to suit browser under test and other settings.
NOTE: You need to have the Chromedriver binary downloaded and setup in your PATH.  

There is a single feature file with one scenario. The scenario has two steps, see if you can extend the functionality to complete a journey.

## Get the code

Git:

    git clone https://github.com/paddyfox/selenium-cucumber-java.git
    cd selenium-cucumber-java


Or simply [download a zip](https://github.com/paddyfox/selenium-cucumber-java/archive/master.zip) file.

## Use Maven

Open a command window and run:

    mvn clean verify -Dtags="regression" serenity:aggregate

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `Specifications` class tells JUnit to kick off Cucumber.
