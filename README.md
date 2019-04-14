# Selenium-Cucumber-Java

This is a simple example project which uses Selenium Webdriver and Cucumber. It is written in Java and is designed to run with Mac OSX but can be extended
 to run on other platforms easily.

There is a single feature file with one scenario. The scenario has two steps, see if you can extend the functionality to complete a journey.

## Get the code

Git:

    git clone https://github.com/paddyfox/selenium-cucumber-java.git
    cd selenium-cucumber-java


Or simply [download a zip](https://github.com/paddyfox/selenium-cucumber-java/archive/master.zip) file.

## Use Maven

Open a command window and run:

    mvn clean verify -Dcucumber.options="--tags @regression"

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `Specifications`
class tells JUnit to kick off Cucumber.
