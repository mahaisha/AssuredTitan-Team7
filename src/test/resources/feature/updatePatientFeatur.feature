@updatePatient
Feature: Patient Module

Background:

    Given Admin logs in with valid credentials and receives a Admin token
   # And Admin creates a Dietician
    And Dietician logs in and receives a Dietician token
    #And Dietician creates a Patient
    And Patient logs in receives a Patient token
#    Scenario: Creat Patient with Dietician token, Valid endpoits,and valid manadatory fields nd data
#Given base URI is set to the patient API endpoint
#When Dietician send the Post request to valid endpoints
#Then Dietician recievs 201 status code with Patient Details in response body
#And validate JSON Schema for the patient created 
@updatePatient
Scenario: Dietician able to update patient with valid data, patient id and token
   Given Dietician creates PUT request with valid data. 
   When Dietician send PUT http request with the endpoint
   Then Dietician recieves 200 ok  with updated response body. 
   
   Scenario: Dietician able to update patient only with valid mandatory details
  Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields
  When Dietician send PUT http request with the endpoint
 # Then Dietician recieves 200 ok  with updated response body.
 @updatePatient 
  Scenario: Dietician able to update patient with mandatory fields empty and only with valid additional details
  Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.
  When Dietician send PUT http request with the endpoint
  #Then Dietician recieves 400 Bad request
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid patient id in path parameter
  Given Dietician creates PUT request by entering only valid mandatory details in the form-data key and value fields
  When Dietician send PUT http request with the endpoint with invalid Id
  #Then Dietician recieves 404 not found
  @updatePatient
  Scenario:  Dietician able to update patient with existing file by not attaching new file
  Given Dietician creates PUT request by not attaching any file into the form-data key and value fields
  When Dietician send PUT http request with the endpoint
 # Then Dietician recieves 200 ok  with updated response body.
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid method
  Given Dietician creates PUT request by entering valid data into  form-data key and value fields
  When Dietician send POST http request with endpoint
  Then Dietician recieves 405 method not allowed 
  @updatePatient
  Scenario:  Dietician able to update patient with valid data and invalid endpoin
  Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
  When Dietician sent PUT http request with invalid endpoint
  Then Dietician recieves 404 not found
  @updatePatient
  Scenario:  Dietician able to update patient with valid data, patient id and invalid content type
  Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type
  When Dietician send PUT http request with the endpoint
  Then Dietician recieves 415 unsupported media type
