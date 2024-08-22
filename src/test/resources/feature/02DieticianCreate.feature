
Feature: Dietician
Background:

  @tag1
  Scenario: Create Dietician with full Dietician details
	  Given Create Dietician has Admin Auth token
		Given Excel file has login Dietician details
		Given Create Dietician with Admin Auth token
		Given Create Dietician succeeds with http status CREATED
		Given Create Dietician has Dietician Auth token
	  #Given Create Dietician has Patient Auth token
	  
  	Given Excel file has full Dietician details
		Given Create Dietician with Admin Auth token
    Then Create Dietician succeeds with http status CREATED

  @tag2
  Scenario: Create Dietician with Dietician Auth token
    When Excel file has full Dietician details
    And Create Dietician with Dietician Auth token
    Then Create Dietician fails with http status FORBIDDEN

  @tag3
  Scenario: Create Dietician with Patient Auth token
    When Excel file has full Dietician details
    And Create Dietician with Patient Auth token
    Then Create Dietician fails with http status FORBIDDEN
  
  @tag4
  Scenario: Create Dietician without Auth token
    When Excel file has full Dietician details
    And Create Dietician without Auth token
    Then Create Dietician fails with http status UNAUTHORIZED
    
  @tag5
  Scenario: Create Dietician with mandatory Dietician details
    When Excel file has mandatory Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician succeeds with http status CREATED

  @tag6
  Scenario: Create Dietician with additional Dietician details
    When Excel file has additional Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician succeeds with http status CREATED

  @tag7
  Scenario: Create Dietician with invalid Dietician details
    When Excel file has invalid Dietician details
    And Create Dietician with Admin Auth token
    Then Create Dietician fails with http status BAD_REQUEST

  @tag8
  Scenario: Create Dietician with full Dietician details and invalid HTTP method
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid HTTP method
    Then Create Dietician fails with http status METHOD_NOT_ALLOWED

  @tag9
  Scenario: Create Dietician with full Dietician details and invalid endpoint
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid endpoint
    Then Create Dietician fails with http status NOT_FOUND

  #@tag10
  #Scenario: Create Dietician with full Dietician details and invalid content type
    #Given Create Dietician has Admin Auth token
    #When Excel file has full Dietician details
    #And Create Dietician with Admin Auth token and invalid content type
    #Then Create Dietician fails with http status UNSUPPORTED_MEDIA_TYPE
   
