@tag
Feature: Dietician
#	Background:
#	Given User has Admin token

  @tag1
  Scenario: Create Dietician
    Given App has Admin token
    When Excel file has Dietician details
    Then Dietician is created
   