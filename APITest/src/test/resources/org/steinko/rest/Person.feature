Feature: Person API Test
  I want to test the Person API

 
  Scenario: Get the Persion
    Given Person Exist
    When  I activet the get person
    Then  I recive a Person
   
    
  Scenario: Create a Person
    Given Person do not exist
    When  I want to create a person with first name "Oddmund" and family name "Korsveien"
    Then  The person is created with id
    
  Scenario: Delete a Person
    Given Person with id 1 do  exist
    When  I want to delete a person with id 1
    Then  The person is deleted 
    
  Scenario: Update person
    Given Person with id 1 exist with first name "Stein" and familiy name "Korsveien"
    When  I want to update the person with 1 with first name "Sigvardt" familiy name "Cool"
    Then  The persons first name and fammiliy name is updated
