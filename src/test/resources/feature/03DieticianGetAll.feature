@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Get All Dieticians without Auth token
    Given Get All Dieticians has Admin Auth token
    When Get All Dieticians without Auth token
    Then Get All Dieticians fails with http status UNAUTHORIZED

  @tag2
  Scenario: Get All Dieticians with Dietician Auth token
    Given Get All Dieticians has Patient Auth token
    When Get All Dieticians with Dietician Auth token
    Then Get All Dieticians succeeds with http status OK

  @tag3
  Scenario: Get All Dieticians with Patient Auth token
    Given Get All Dieticians has Patient Auth token
    When Get All Dieticians with Patient Auth token
    Then Get All Dieticians succeeds with http status OK

  @tag4
  Scenario: Get All Dieticians with Admin Auth token
    Given Get All Dieticians has Admin Auth token
    When Get All Dieticians with Admin Auth token
    Then Get All Dieticians succeeds with http status OK

  @tag5
  Scenario: Get All Dieticians with Admin Auth token and invalid HTTP method
    Given Get All Dieticians has Admin Auth token
    When Get All Dieticians with Admin Auth token and invalid HTTP method
    Then Get All Dieticians fails with http status METHOD_NOT_ALLOWED

  @tag6
  Scenario: Get All Dieticians with Admin Auth token details and invalid endpoint
    Given Get All Dieticians has Admin Auth token
    When Get All Dieticians with Admin Auth token and invalid endpoint
    Then Get All Dieticians fails with http status NOT_FOUND

  @tag7
  Scenario: Get All Dieticians with Admin Auth token details and invalid content type
    Given Get All Dieticians has Admin Auth token
    When Get All Dieticians with Admin Auth token and invalid content type
    Then Get All Dieticians fails with http status NOT_ACCEPTABLE