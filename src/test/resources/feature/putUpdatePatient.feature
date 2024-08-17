

Feature: Update patient
Background:  
Given Set dietician bearer token


  
  Scenario: Dietician able to update patient with valid data, patient id and token
   Given Dietician creates PUT request by entering valid data. 
   When Dietician send PUT http request with endpoint
   Then Dietician recieves 200 ok and with updated response body. 

  Scenario: Dietician able to update patient only with valid mandatory details
  Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 200 ok and with updated response body.
  
  Scenario: Dietician able to update patient with mandatory fields empty and only with valid additional details
  Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 400 Bad request
  
  Scenario:
  Given
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 400 Bad request
  
  Scenario:
  Given
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 404 not found
  
  Scenario:
  Given
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 200 ok and with updated response body. 
  
  Scenario:
  Given
  When Dietician send POST http request with endpoint
  Then Dietician recieves 405 method not allowed
  
  Scenario:
  Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
  When Dietician sent PUT http request with invalid endpoint
  Then Dietician recieves 404 not found
  
  Scenario:
  Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type
  When Dietician send PUT http request with endpoint
  Then Dietician recieves 415 unsupported media type
  
  
