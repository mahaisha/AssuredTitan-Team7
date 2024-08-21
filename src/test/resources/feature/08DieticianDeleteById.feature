@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Delete Dietician By Id without Auth token
    Given Delete Dietician By Id has Admin Auth token
    When Delete Dietician By Id without Auth token
    Then Delete Dietician By Id fails with http status UNAUTHORIZED

  @tag2
  Scenario: Delete Dietician By Id with Dietician Auth token and valid id
    Given Delete Dietician By Id has Patient Auth token
    When Delete Dietician By Id with Dietician Auth token and valid id
    Then Delete Dietician By Id fails with http status FORBIDDEN

  @tag3
  Scenario: Delete Dietician By Id with Patient Auth token and valid id
    Given Delete Dietician By Id has Patient Auth token
    When Delete Dietician By Id with Patient Auth token and valid id
    Then Delete Dietician By Id fails with http status FORBIDDEN

  @tag4
  Scenario: Delete Dietician By Id with Admin Auth token and valid id
    Given Delete Dietician By Id has Admin Auth token
    When Delete Dietician By Id with Admin Auth token and valid id
    Then Delete Dietician By Id succeeds with http status OK

  @tag5
  Scenario: Delete Dietician By Id with Admin Auth token and invalid id
    Given Delete Dietician By Id has Admin Auth token
    When Delete Dietician By Id with Admin Auth token and invalid id
    Then Delete Dietician By Id fails with http status NOT_FOUND

  @tag6
  Scenario: Delete Dietician By Id with Admin Auth token and invalid HTTP method
    Given Delete Dietician By Id has Admin Auth token
    When Delete Dietician By Id with Admin Auth token and invalid HTTP method
    Then Delete Dietician By Id fails with http status METHOD_NOT_ALLOWED

  @tag7
  Scenario: Delete Dietician By Id with Admin Auth token details and invalid endpoint
    Given Delete Dietician By Id has Admin Auth token
    When Delete Dietician By Id with Admin Auth token and invalid endpoint
    Then Delete Dietician By Id fails with http status NOT_FOUND