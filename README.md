
# Auction App - REST API testing

### Test automation suite for Auction App  

## General info

Auction App is a website for auction where users can bid on a product by placing a bid. 
A user needs to create an account and log in and then they can put and sell their product and bid on other products.

## Test suite
Test automation suite for 'Auction App' web application written in Java using Cucumber & Serenity BDD framework libraries.

Tests are broken down into reusable steps: In the test/java/steps package are two main classes which call methods from other helper classes (test/java/sub_steps package). All helper classes contain methods which are just for a particular page of the app.  

For post, put and delete requests, payloads(body) are created into the main/java/company package following the Builder pattern.

After running tests, Serenity report is generated and possible to see from target/serenity/site/index.html.


### Smoke test suite
 Contains E2E smoke test for core functionality & feature file is in the test/java/resources/features package.


### Regression test suite
Contains tests separated into classes by their functionalities & feature files are in the test/java/resources/features package. 



## Environment
Homepage: [Auction App](http://ec2-3-67-80-227.eu-central-1.compute.amazonaws.com:8090/ "Auction App")

Java Version 17.0.5

Google Chrome Version 108

## Deployment

To run smoke_api.feature :

```mvn verify -Dcucumber.filter.tags='@Smoke'```

To run regression test suite:

```mvn verify -Dcucumber.filter.tags='@Regression'```

To run all tests:

```mvn verify```

To run particular test, in tags instead of 'TEST' put their name:

```mvn verify -Dcucumber.filter.tags='@TEST'```



## Status
This test contains smoke test and regression tests. While application is in the progress, more tests would be added in regression package.
