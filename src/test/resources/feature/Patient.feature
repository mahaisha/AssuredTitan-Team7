#Divya
@getPatientPositive
Feature: Get Operation [Get all Patients]
Background:
    Given Admin logs in with valid credentials and receives a Admin token
   # And Admin creates a Dietician
    And Dietician logs in and receives a Dietician token
    #And Dietician creates a Patient
    And Patient logs in receives a Patient token
  
@getAllPatientsPositive
  Scenario Outline: Check dietician able to retrieve all patients
  	Given "Dietician" create "GET" request 
    When "Dietician" send "GET" http request with "<endpointType>" 
    Then "Dietician" recieves 200 ok with response body for "<endpointType>"
   Examples: 
     | endpointType |
     | AllPatients |
    
@getPatientByIdPositiveByDietician
  Scenario Outline: Check dietician able to retrieve patients morbidity details by patient ID
    Given "Dietician" create "GET" request
    When "Dietician" send "GET" http request with "<endpointType>"
    Then "Dietician" recieves 200 ok with response body for "<endpointType>"
    
     Examples: 
     |  endpointType|
     |  PatientID |
     
@getPatientByIdPositiveByPatient
  Scenario Outline: Check patient able to retrieve patients morbidity details by patient ID
    Given "Patient" create "GET" request
    When "Patient" send "GET" http request with "<endpointType>"
    Then "Patient" recieves 200 ok with response body for "<endpointType>"
    
     Examples: 
  			 |  endpointType|
     		 |  PatientID |
     
@getPatientByFileIdPositiveByDietician
  Scenario Outline: Check dietician able to retrieve patients file by patient FileId
    Given "Dietician" create "GET" request
    When "Dietician" send "GET" http request with "<endpointType>"
    Then "Dietician" recieves 200 ok with response body for "<endpointType>"
    Examples: 
     |  endpointType  |
     |  FileID |
    
@getPatientByFileIdPositiveByPatient
  Scenario Outline: Check patient able to retrieve patients file by patient FileId
    Given "Patient" create "GET" request
    When "Patient" send "GET" http request with "<endpointType>"
    Then "Patient" recieves 200 ok with response body for "<endpointType>"
    Examples: 
      |  endpointType  |
      |  FileID |
       
   #@deletePatientByPatientIdPositive
  #Scenario Outline: Check dietician able to delete patient by ID
   #Given "Dietician" create "DELETE" request  
    #When "Dietician" send "DELETE" http request with "<endpointType>"
    #Then "Dietician" recieves 200 ok with response body for "<endpointType>"
    #Examples: 
     #|  endpointType  |
     #|  DeleteEndpoint |
     
#Negative Sceanrios

@getPatientNegativeNoAuth
  Scenario Outline: Check dietician able to retrieve all patients without Auth
  	Given "Dietician" create "GET" request with No Auth
    When "Dietician" send "GET" http request with "<endpointType>" 
    Then "Dietician" recieves 401 unauthorized
   Examples: 
      | endpointType |
      | AllPatients  |
      | PatientID    |
      | FileID       |

@getpatientNegativeAdminToken
Scenario Outline: Check Admin able to retrieve all patients with Admin Token
  	Given "Admin" create "GET" request 
    When "Admin" send "GET" http request with "<endpointType>" 
    Then "Admin" recieves 403 Forbidden
   Examples: 
      | endpointType |
      | AllPatients  |
      | PatientID    |
      | FileID       |

@getpatientNegativePatientToken
Scenario Outline: Check Patient able to retrieve all patients with Patient Token
  	Given "Patient" create "GET" request 
    When "Patient" send "GET" http request with "<endpointType>" 
    Then "Patient" recieves 403 Forbidden
   Examples: 
     | endpointType |
     | AllPatients |
     
@getpatientWithInvalidMethod1
  Scenario Outline: Check Dietician able to retrieve patients Morbidity/File with Invalid Method
  	Given "Dietician" create "POST" request 
    When "Dietician" send "POST" http request with "<endpointType>" 
    Then "Dietician" recieves 405 method not allowed
   Examples: 
     | endpointType |
     | PatientID    |
     | FileID       |
     
@getpatientWithInvalidMethod
  Scenario Outline: Check Dietician able to retrieve all patients with Invalid Method
  	Given "Dietician" create "PUT" request 
    When "Dietician" send "PUT" http request with "<endpointType>" 
    Then "Dietician" recieves 405 method not allowed
   Examples: 
     | endpointType |
     | AllPatients  |
     
@getpatientWithInvalidEndpoint
Scenario Outline: Check Dietician able to retrieve patients with Invalid Endpoint
  	Given "Dietician" create "GET" request 
    When "Dietician" send "GET" http request with invalid "<endpointType>" 
    Then "Dietician" recieves 404 not found
   Examples: 
     | endpointType |
     | AllPatients  |
     | PatientID    |
     | FileID       |
 
@getpatientWithInvalidId
Scenario Outline: Check Dietician able to retrieve patients with Invalid PatientID/FileID
		Given "Dietician" create "GET" request 
    When "Dietician" send "GET" http request with invalid ID "<endpointType>" 
    Then "Dietician" recieves 404 not found
   Examples: 
     | endpointType |
     | PatientID    |
     | FileID       |
     
@deletePatientwithNoAuth
  Scenario Outline: Check dietician able to delete patient by ID with No Auth
    Given "Dietician" create "DELETE" request with No Auth 
    When "Dietician" send "DELETE" http request with "<endpointType>"
    Then "Dietician" recieves 401 unauthorized
    Examples: 
     | endpointType  |
     | DeleteEndpoint |
     
@deletePatientwithAdminToken
  Scenario Outline: Check Admin able to delete patient by ID with Admin Token
    Given "Admin" create "DELETE" request  
    When "Admin" send "DELETE" http request with "<endpointType>"
    Then "Admin" recieves 403 Forbidden
    Examples: 
     | endpointType  |
     | DeleteEndpoint |
     
@deletepatientWithPatientToken
Scenario Outline: Check Patient able to delete patient by ID with Patient Token
  	Given "Patient" create "DELETE" request 
    When "Patient" send "DELETE" http request with "<endpointType>" 
    Then "Patient" recieves 403 Forbidden
    Examples: 
     | endpointType  |
     | DeleteEndpoint |
     
@deletepatientWithInvalidMethod
  Scenario Outline: Check dietician able to delete patient by id with invalid method
  	Given "Dietician" create "POST" request 
    When "Dietician" send "POST" http request with "<endpointType>" 
    Then "Dietician" recieves 405 method not allowed
   Examples: 
     | endpointType |
     | DeleteEndpoint|
     
@deletepatientWithInvalidEndpoint
Scenario Outline: Check dietician able to delete patient by id with invalid endpoint
  	Given "Dietician" create "DELETE" request 
    When "Dietician" send "DELETE" http request with invalid "<endpointType>" 
    Then "Dietician" recieves 404 not found
   Examples: 
     | endpointType |
     | DeleteEndpoint|
     
@deletepatientWithInvalidId
Scenario Outline: Check dietician able to delete patient with invalid Id
  	Given "Dietician" create "DELETE" request 
    When "Dietician" send "DELETE" http request with invalid ID "<endpointType>" 
    Then "Dietician" recieves 404 not found
   Examples: 
     | endpointType |
     | DeleteEndpoint|
     