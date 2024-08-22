
Feature: Dietician

  @tag1
  Scenario: Create Dietician with full Dietician details
    Given Create Dietician has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician succeeds with http status CREATED
@tag2
  Scenario: Check user able to login as dietician with valid credential
		Given User creates Post request with request body
		When User send POST HTTP request with dietician credentials
		Then User recieves 200 created with response body
		
  @tag3
  Scenario: Create Dietician with Dietician Auth token
    Given Create Dietician has Dietician Auth token
    When Excel file has full Dietician details
    And Create Dietician with Dietician Auth token
    Then Create Dietician fails with http status FORBIDDEN

  @tag4
  Scenario: Create Dietician with Patient Auth token
    Given Create Dietician has Patient Auth token
    When Excel file has full Dietician details
    And Create Dietician with Patient Auth token
    Then Create Dietician fails with http status FORBIDDEN
  
@tag5
  Scenario: Create Dietician without Auth token
    Given Create Dietician has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician without Auth token
    Then Create Dietician fails with http status UNAUTHORIZED
    
  @tag6
  Scenario: Create Dietician with mandatory Dietician details
    Given Create Dietician has Admin Auth token
    When Excel file has mandatory Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician succeeds with http status CREATED

  @tag7
  Scenario: Create Dietician with additional Dietician details
    Given Create Dietician has Admin Auth token
    When Excel file has additional Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician fails with http status BAD_REQUEST

  @tag8
  Scenario: Create Dietician with invalid Dietician details
    Given Create Dietician has Admin Auth token
    When Excel file has invalid Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician fails with http status BAD_REQUEST

  @tag9
  Scenario: Create Dietician with full Dietician details and invalid HTTP method
    Given Create Dietician has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid HTTP method
    Then Create Dietician fails with http status METHOD_NOT_ALLOWED

  @tag10
  Scenario: Create Dietician with full Dietician details and invalid endpoint
    Given Create Dietician has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid endpoint
    Then Create Dietician fails with http status NOT_FOUND

  @tag11
  Scenario: Create Dietician with full Dietician details and invalid content type
    Given Create Dietician has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid content type
    Then Create Dietician fails with http status UNSUPPORTED_MEDIA_TYPE
   
