@ValidateMorbidityModule
Feature: Morbidity Module

  #@TC01
  #Scenario: Validate Dietician can retrieve all morbidity details
    #Given User is logged in as Dietician with Token
    #And Dietician sends GET request to get morbidities
    #Then Dietician receives all morbidity details

  @TC01
  Scenario Outline: Validate Dietician can retrieve all morbidity details
  Given User is logged in as Dietician with Token
  When Dietician sends GET request to get morbidities
  Then Dietician receives 200 for "<endpointType>"
  
  Examples:
  | endpointType   |
  | AllMorbidities |
  
   @TC02
  Scenario Outline: Validate Dietician can retrieve morbidity details by Test Name
  Given User is logged in as Dietician with Token
  When Dietician sends GET request to get morbidities by Test Name
  Then Dietician receives 200 for "<endpointType>"
  
  Examples:
  | endpointType   |
  | TestName       |
  
  @TC03
  Scenario: Validate Dietician gets 405 status code for invalid method
    Given User is logged in as Dietician with Token
    And Dietician creates POST request for morbidity
    Then Dietician gets 405 method not allowed

  @TC04
  Scenario: Validate Dietician gets 404 status code for invalid endpoint
    Given User is logged in as Dietician with Token
    And Dietician creates GET request for morbidity with invalid endpoint
    Then Dietician should get 404 not found

  @TC05
  Scenario: Validate Dietician gets 405 status code for invalid method by Test Name
    Given User is logged in as Dietician with Token
    When Dietician sends POST request with morbidity by Test Name endpoint
    Then Dietician gets 405 method not allowed

  @TC06 @RerunForDefect
  Scenario: Validate Dietician gets 404 status code for invalid endpoint by Test Name
    Given User is logged in as Dietician with Token
    And GET request for morbidity by Test Name with invalid endpoint
    Then Dietician should get 404 not found

  @TC07
  Scenario: Validate Dietician gets 404 status code for invalid Test Name
    Given User is logged in as Dietician with Token
    And Dietician GET request for morbidity having invalid test name
    Then Dietician should get 404 not found

  @TC08
  Scenario: Validate Dietician gets 401 status code for No Auth Token
    Given Dietician sends GET request to get morbidities with NO Auth Token
    Then Dietician receives 401 unauthorized

  @TC09
  Scenario: Validate Dietician gets 401 unauthorized for No Auth Token -morbidity By Test name
    Given Dietician sends GET request having Test Name with NO Auth Token
    Then Dietician receives 401 unauthorized

  @TC10
  Scenario: Validate Patient gets 403 forbidden for Morbidities with Patient Token
    Given User is logged in as Patient with Patient Token
    When Patient sends GET request to get morbidities
    Then Patient receives 403 forbidden

  @TC11
  Scenario: Validate Patient gets 403 forbidden for Morbidity Test name with Patient Token
    Given User is logged in as Patient with Patient Token
    When Patient sends GET request to get morbidities having Test Name
    Then Patient receives 403 forbidden

  @TC12
  Scenario: Validate Admin gets all morbidities with token
    Given User is logged in as Admin with valid Token
    And Admin sends GET request to get morbidities
    Then Admin receives all morbidity details

  @TC13
  Scenario: Validate Admin gets 405 status code for invalid method
    Given User is logged in as Admin with valid Token
    And Admin creates POST request for morbidity
    Then Admin gets 405 method not allowed

  @TC14
  Scenario: Validate Admin gets 404 status code for invalid endpoint
    Given User is logged in as Admin with valid Token
    And Admin creates GET request for morbidity with invalid endpoint
    Then Admin should get 404 not found

  @TC15
  Scenario: Validate Admin can retrieve morbidity details with Test Name
    Given User is logged in as Admin with valid Token
    And Admin user sends GET request with endpoint having Test Name
    Then Admin receives morbidity details with Test Name

  @TC16
  Scenario: Validate Admin gets 405 status code for invalid method by Test Name
    Given User is logged in as Admin with valid Token
    When Admin sends POST request with morbidity by Test Name endpoint
    Then Admin gets 405 method not allowed

  @TC17
  Scenario: Validate Admin gets 404 status code for invalid endpoint by Test Name
    Given User is logged in as Admin with valid Token
    And Admin GET request for morbidity by Test Name with invalid endpoint
    Then Admin should get 404 not found

  @TC18
  Scenario: Validate Admin gets 404 status code for invalid Test Name
    Given User is logged in as Admin with valid Token
    And Admin GET request for morbidity having invalid test name
    Then Admin should get 404 not found
