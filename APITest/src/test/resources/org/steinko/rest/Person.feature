@tag
Feature: Person API Test
  I want to test the Person API

  @tag1
  Scenario: Get the Persion
    Given Person Exist
    When I activet the get person
    Then I recive a Person
   
    
  Scenario: Create a Person
    Given Person do not exist
    When I want to create a person with first name "Oddmund" and family name "Korsveien"
    Then The person is created 
    
