
#Divya
@getPatientPositive
Feature: Patient Module

Background:
    Given Admin logs in with valid credentials and receives a Admin token
   # And Admin creates a Dietician
    And Dietician logs in and receives a Dietician token
    #And Dietician creates a Patient
   # And Patient logs in receives a Patient token
    
 Scenario: Set no auth - Check dietician able to create patient with valid data
Given base URI is set to the patient API endpoint
When Dietician sets NoAuth in Post request to valid endpoints
Then "Dietician" recieves 401 unauthorized 
#
 Scenario: Set adminToken - Check dietician able to create patient with valid data
Given base URI is set to the patient API endpoint
When Dietician sends Post request with adminToken to valid endpoints
Then "Admin" recieves 403 Forbidden 

 #Scenario: Set patientToken - Check dietician able to create patient with valid data
#Given base URI is set to the patient API endpoint
#When Dietician sends Post request with patientToken to valid endpoints
#Then "Patient" recieves 403 Forbidden

Scenario: Set dieticianToken - Check dietician able to create patient only with valid additional details
   Given base URI is set to the patient API endpoint
    When Dietician sends POST request with only valid additional details 
    Then Dietician recieves 400 Bad Request
    
  Scenario: Dietician tries to create a patient with a PUT request(invalid method)
    Given base URI is set to the patient API endpoint
    When Dietician sends PUT request for create patient
    Then Dietician receives 405 Method Not Allowed

   Scenario: Dietician tries to create a patient with a POST request to an invalid endpoint
    Given base URI is set to the patient API endpoint
    When Dietician sends POST request with valid data to invalid endpoint
    Then Dietician receives 404 Not Found

  #Scenario: Dietician tries to create a patient with a POST request with an invalid content type
    #Given base URI is set to the patient API endpoint
    #When Dietician sends POST request with valid data to invalid content type
    #Then Dietician receives 415 Unsupported Media Type 
    
  Scenario: Creat Patient with Dietician token, Valid endpoits,and valid manadatory fields nd data
Given base URI is set to the patient API endpoint
When Dietician send the Post request to valid endpoints
Then Dietician recievs 201 status code with Patient Details in response body
And validate JSON Schema for the patient created 
    
 
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
     
#@getPatientByIdPositiveByPatient
  #Scenario Outline: Check patient able to retrieve patients morbidity details by patient ID
  #	Given 
    #Given "Patient" create "GET" request
    #When "Patient" send "GET" http request with "<endpointType>"
    #Then "Patient" recieves 200 ok with response body for "<endpointType>"
    #
     #Examples: 
  #			 |  endpointType|
     #		 |  PatientID |
     
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
  	Given Patient logs in receives a Patient token
    And "Patient" create "GET" request
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
     
     
     
     @tag3
  Scenario: Create Dietician with Patient Auth token
    Given Create Dietician has Patient Auth token
    When Excel file has full Dietician details
    And Create Dietician with Patient Auth token
    Then Create Dietician fails with http status FORBIDDEN
    
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
		Given Patient logs in receives a Patient token
  	And "Patient" create "GET" request 
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
		Given Patient logs in receives a Patient token
  	And "Patient" create "DELETE" request 
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
     
   Scenario: Set patientToken - Check dietician able to create patient with valid data
   Given base URI is set to the patient API endpoint
   And Patient logs in receives a Patient token
   When Dietician sends Post request with patientToken to valid endpoints
   Then "Patient" recieves 403 Forbidden

@getPatientByIdPositiveByPatient
  Scenario Outline: Check patient able to retrieve patients morbidity details by patient ID
  	Given Patient logs in receives a Patient token
    And "Patient" create "GET" request
    When "Patient" send "GET" http request with "<endpointType>"
    Then "Patient" recieves 200 ok with response body for "<endpointType>"
    
     Examples: 
  			 |  endpointType|
     		 |  PatientID |
     		 
 
     
#################################################################################

@updatePatient
Scenario: Dietician able to update patient with valid data, patient id and token
   Given Dietician creates PUT request with valid data.
   When Dietician send PUT http request with the endpoint
   Then Dietician recieves 200 ok  with updated response body.
   
   Scenario: Dietician able to update patient only with valid mandatory details
  Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields
  When Dietician send PUT http request with the endpoint
  Then Dietician recieves 200 ok  with updated response body.
 @updatePatient
  Scenario: Dietician able to update patient with mandatory fields empty and only with valid additional details
  Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.
  When Dietician send PUT http request with the endpoint
  Then Dietician recieves 400 Bad request
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid patient id in path parameter
  Given Dietician creates PUT request by entering only valid mandatory details in the form-data key and value fields
  When Dietician send PUT http request with the endpoint with invalid Id
  Then Dietician recieves 404 Not Found
  @updatePatient
  Scenario:  Dietician able to update patient with existing file by not attaching new file
  Given Dietician creates PUT request by not attaching any file into the form-data key and value fields
  When Dietician send PUT http request with the endpoint
  Then Dietician recieves 200 ok  with updated response body.
  
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid method
  Given Dietician creates PUT request by entering valid data into  form-data key and value fields
  When Dietician send POST http request with endpoint
  Then Dietician recieves 405 method not allowed
  
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid endpoint
  Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
  When Dietician sent PUT http request with invalid endpoint
  Then Dietician recieves 404 Not Found


 @deletePatientByPatientIdPositive
  Scenario Outline: Check dietician able to delete patient by ID
   Given "Dietician" create "DELETE" request  
    When "Dietician" send "DELETE" http request with "<endpointType>"
    Then "Dietician" recieves 200 ok with response body for "<endpointType>"
    Examples: 
     |  endpointType  |
     |  DeleteEndpoint |



#Scenario Outline: Check dietician able to add new reports for existing patient with valid data
  #	Given "<user>" creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    #When "<user>" send PUT http request with "<endpointType>"
    #Then "<user>" recieves <status> unauthorized
   #Examples: 
     #| user    | endpointType         |  status
     #|Dietician| PatientIdEndpoint    |
     #|Admin    | PatientIdEndpoint    |
     #|Patient  | PatientIdEndpoint    |
     #|Dietician| AddNewReportsEndpoint|
     #|Admin    | AddNewReportsEndpoint|
     #|Patient  | AddNewReportsEndpoint|
     #
#Scenario Outline: Check dietician able to add new reports for existing patient with valid data
  #	Given "Admin" creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    #When "Admin" send PUT http request with "<endpointType>"
    #Then "Admin" recieves 403 forbidden
   #Examples: 
     #| endpointType |
     #| AddNewReportsEndpoint|


