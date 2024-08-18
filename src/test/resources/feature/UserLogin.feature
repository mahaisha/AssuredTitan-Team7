Feature: Login POST

  Scenario: Check user able to login as admin with valid data
    Given User creates Post request with request body
    When User send POST HTTP request with endpoint
    Then User recieves 200 created with response body

  Scenario: Check admin able to logout
    Given User creates GET request 
    When User send GET HTTP request with endpoint 
    Then User gets 200 created with Logout successful message

	Scenario: Check user able to login as admin with invalid credential
    Given User creates Post request with invalid credential
    When User send invalid POST HTTP request with endpoint
    Then User recieves 401 unauthorized
    
  Scenario: Check user able to login as admin with valid credential and invalid method
		Given  User creates GET request with request body
		When User send GET HTTP request with valid credential
		Then User recieves 405 method not allowed

		Scenario: Check user able to login as admin with valid credential and invalid content type
		Given User creates Post request with request body and invalid content type
		When User send POST HTTP request endpoint with invalid content type
		Then User recieves 415 unsupported media type
		
		Scenario: Check user able to login as dietician with valid credential
		Given User creates Post request with request body
		When User send POST HTTP request with dietician credentials
		Then User recieves 200 created with response body
		
		Scenario: Check user able to login as dietician with invalid credential
		Given User creates Post request with invalid credential
		When User send POST HTTP request with invalid dietician
		Then User recieves 401 unauthorized
		
		Scenario: Check user able to login as patient with valid credential
		Given User creates Post request with request body
		When User send POST HTTP request with patient credentials
		Then User recieves 200 created with response body
		
		Scenario: Check user able to login as patient with invalid credential
		Given User creates Post request with invalid credential
		When User send POST HTTP request with invalid patient
		Then User recieves 401 unauthorized
		
		
		
		