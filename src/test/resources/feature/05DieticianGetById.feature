@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Get Dietician By Id without Auth token
    Given Get Dietician By Id has Admin Auth token
    When Get Dietician By Id without Auth token
    Then Get Dietician By Id fails with http status UNAUTHORIZED


  @tag2
  Scenario: Get Dietician By Id with Admin Auth token and valid id
    Given Get Dietician By Id has Admin Auth token
    When Get Dietician By Id with Admin Auth token and valid id
    Then Get Dietician By Id succeeds with http status OK

  @tag3
  Scenario: Get Dietician By Id with Admin Auth token and invalid id
    Given Get Dietician By Id has Admin Auth token
    When Get Dietician By Id with Admin Auth token and invalid id
    Then Get Dietician By Id fails with http status NOT_FOUND

  @tag4
  Scenario: Get Dietician By Id with Admin Auth token and invalid HTTP method
    Given Get Dietician By Id has Admin Auth token
    When Get Dietician By Id with Admin Auth token and invalid HTTP method
    Then Get Dietician By Id fails with http status METHOD_NOT_ALLOWED

  @tag5
  Scenario: Get Dietician By Id with Admin Auth token details and invalid endpoint
    Given Get Dietician By Id has Admin Auth token
    When Get Dietician By Id with Admin Auth token and invalid endpoint
    Then Get Dietician By Id fails with http status NOT_FOUND

  #@tag6
  #Scenario: Get Dietician By Id with Admin Auth token details and invalid content type
    #Given Get Dietician By Id has Admin Auth token
    #When Get Dietician By Id with Admin Auth token and invalid content type
    #Then Get Dietician By Id fails with http status NOT_ACCEPTABLE