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
  	Given "<user>" create "GET" request 
    When "<user>" send "GET" http request with "<endpointType>" 
    Then "<user>" recieves 200 ok with response body for "<endpointType>"
   Examples: 
     | user      | endpointType     |
     | Dietician | AllPatients |
    
  @getPatientByIdPositive
  Scenario Outline: Check dietician/patient able to retrieve patients morbidity details by patient ID
    Given "<user>" create "GET" request
    When "<user>" send "GET" http request with "<endpointType>"
    Then "<user>" recieves 200 ok with response body for "<endpointType>"
    
     Examples: 
     | user      |  endpointType  |
     | Dietician |  PatientID |
     | Patient   |  PatientID |
     
     @getPatientByFileIdPositive
  Scenario Outline: Check dietician/patient able to retrieve patients by patient FileId
    Given "<user>" create "GET" request
    When "<user>" send "GET" http request with "<endpointType>"
    Then "<user>" recieves 200 ok with response body for "<endpointType>"
    Examples: 
     | user      |  endpointType  |
     | Dietician |  FileID |
     | Patient   |  FileID |
    
       
   #@deletePatientByPatientIdPositive
  #Scenario Outline: Check dietician able to delete patient by ID
   #Given "<user>" create "DELETE" request  
    #When "<user>" send "DELETE" http request with "<endpointType>"
    #Then "<user>" recieves 200 ok with response body for "<endpointType>"
    #Examples: 
     #| user      |  endpointType  |
     #| Dietician |  DeleteEndpoint |
     

  
