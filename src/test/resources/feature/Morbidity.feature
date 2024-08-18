
@ValidateMorbidityModule
Feature: Title of your feature
  I want to use this template for my feature file

  @TC01
  Scenario: Validate Dietician can retrieve all morbidity details
    Given Dietician create GET request
    When Dietician sends GET request with endpoint
    Then Dietician receives status 401 unauthorized
