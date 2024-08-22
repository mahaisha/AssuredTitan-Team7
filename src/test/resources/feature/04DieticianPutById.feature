@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Put Dietician By Id without Auth token
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has full Dietician details
    And Put Dietician By Id without Auth token
    Then Put Dietician By Id fails with http status UNAUTHORIZED



  @tag2
  Scenario: Put Dietician By Id with Admin Auth token and invalid id
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has full Dietician details
    And Put Dietician By Id with Admin Auth token and invalid id
    Then Put Dietician By Id fails with http status NOT_FOUND

  @tag3
  Scenario: Put Dietician By Id with full Dietician details
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has full Dietician details
    And Put Dietician By Id with Admin Auth token
    Then Put Dietician By Id succeeds with http status OK

  @tag4
  Scenario: Put Dietician By Id with mandatory Dietician details
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has mandatory Dietician details
    And Put Dietician By Id with Admin Auth token
    Then Put Dietician By Id succeeds with http status OK

  @tag5
  Scenario: Put Dietician By Id with additional Dietician details
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has additional Dietician details
    And Put Dietician By Id with Admin Auth token
    Then Put Dietician By Id fails with http status BAD_REQUEST

  @tag6
  Scenario: Put Dietician By Id with invalid Dietician details
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has invalid Dietician details
    And Put Dietician By Id with Admin Auth token
    Then Put Dietician By Id fails with http status BAD_REQUEST

  @tag7
  Scenario: Put Dietician By Id with full Dietician details and invalid HTTP method
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has full Dietician details
    And Put Dietician By Id with Admin Auth token and invalid HTTP method
    Then Put Dietician By Id fails with http status METHOD_NOT_ALLOWED

  @tag8
  Scenario: Put Dietician By Id with full Dietician details and invalid endpoint
    Given Put Dietician By Id has Admin Auth token
    When Put Dietician By Id Excel file has full Dietician details
    And Put Dietician By Id with Admin Auth token and invalid endpoint
    Then Put Dietician By Id fails with http status NOT_FOUND

  #@tag9
  #Scenario: Put Dietician By Id with full Dietician details and invalid content type
    #Given Put Dietician By Id has Admin Auth token
    #When Put Dietician By Id Excel file has full Dietician details
    #And Put Dietician By Id with Admin Auth token and invalid content type
    #Then Put Dietician By Id fails with http status UNSUPPORTED_MEDIA_TYPE
   