@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Create Dietician without Auth token
    Given App has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician without Auth token
    Then Dietician creation fails with http status UNAUTHORIZED

  @tag2
  Scenario: Create Dietician with Dietician Auth token
    Given App has Dietician Auth token
    When Excel file has full Dietician details
    And Create Dietician with Dietician Auth token
    Then Dietician creation fails with http status FORBIDDEN

  @tag3
  Scenario: Create Dietician with Patient Auth token
    Given App has Patient Auth token
    When Excel file has full Dietician details
    And Create Dietician with Patient Auth token
    Then Dietician creation fails with http status FORBIDDEN

  @tag4
  Scenario: Create Dietician with full Dietician details
    Given App has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token
    Then Dietician creation succeeds with http status CREATED

  @tag5
  Scenario: Create Dietician with mandatory Dietician details
    Given App has Admin Auth token
    When Excel file has mandatory Dietician details
    And Create Dietician with Admin Auth token
    Then Dietician creation succeeds with http status CREATED

  @tag6
  Scenario: Create Dietician with additional Dietician details
    Given App has Admin Auth token
    When Excel file has additional Dietician details
    And Create Dietician with Admin Auth token
    Then Dietician creation fails with http status BAD_REQUEST

  @tag7
  Scenario: Create Dietician with invalid Dietician details
    Given App has Admin Auth token
    When Excel file has invalid Dietician details
    And Create Dietician with Admin Auth token
    Then Dietician creation fails with http status BAD_REQUEST

  @tag8
  Scenario: Create Dietician with full Dietician details and invalid HTTP method
    Given App has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid HTTP method
    Then Dietician creation fails with http status METHOD_NOT_ALLOWED

  @tag9
  Scenario: Create Dietician with full Dietician details and invalid endpoint
    Given App has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid endpoint
    Then Dietician creation fails with http status NOT_FOUND

  @tag10
  Scenario: Create Dietician with full Dietician details and invalid content type
    Given App has Admin Auth token
    When Excel file has full Dietician details
    And Create Dietician with Admin Auth token and invalid content type
    Then Dietician creation fails with http status UNSUPPORTED_MEDIA_TYPE
   