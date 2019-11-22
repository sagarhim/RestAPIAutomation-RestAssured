
Feature: This feature tests the POST operation of the service.
  
  Scenario: This scenario will insert FirstName, LastName, Username, Password & email id
   of an employee in the company's database.
    Given the admin wants to add a new employee in the system
    When he call the api to add the employee information
    Then the employee is added in the system
    When the admin tries to add the same employee again
    Then he should get an error