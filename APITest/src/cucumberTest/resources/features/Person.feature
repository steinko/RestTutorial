Feature: Person API Test
  I want to test the Person API

 
  Scenario Outline: Get the detail information for selecte id of a  person
    Given there are pesons with <id>
    When  I select person with spesific <Id>
    Then  I recive  <firstName> and <familyName> for that id    
 Examples:
    | id |firstName|familyName|
    |  1 | Oddmund |Korsveien |
    |  2 | Stein   |Korsveien |
   
    
  Scenario: Create a Person
    Given Person do not exist
    When  I want to create a person with first name "Anne" and family name "Korsveien"
    Then  The person is created with id 3
    
  Scenario: Delete a Person
    Given Person with id 1 do  exist
    When  I want to delete a person with id 1
    Then  The person is deleted 
    
  Scenario: Update person
    Given Person with id 2 exist with first name "Stein" and familiy name "Korsveien"
    When  I want to update the person with 2 with first name "Sigvardt" familiy name "Cool"
    Then  The person with id 2 is updated with first name "Sigvardt" and fammiliy name "Cool"
