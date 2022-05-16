Feature: Set of Test scenarios to verify Student Enrollment Application REST Api services.

  Background: Student API should be UP and running
    Given Student API endpoint is UP and running

  @SmokeTest @E2E
  Scenario: Add a new Student and verify the Student is added successfully.
    Given I perform POST operation to add a new Student with body
      | firstName | id | lastName | nationality | studentClass | expectedResponse |
      | Pawan     | 10 | Kalra    | Singapore   | 9A           | 200              |
    Then  I perform a GET operation with student ID to verify the details are correct
      | id | firstName | lastName | nationality | studentClass | expectedResponse | getBy |
      | 10 | Pawan     | Kalra    | Singapore   | 9A           | 200              | id    |
    Then  I perform a GET operation with student class to verify the details are correct
      | id | firstName | lastName | nationality | studentClass | expectedResponse | getBy        |
      | 10 | Pawan     | Kalra    | Singapore   | 9A           | 200              | studentClass |
    When I perform PUT operation to update an existing Student with body
      | firstName | id | lastName | nationality | studentClass | expectedResponse |
      | PAWAN     | 10 | K        | Singapore   | 10A          | 200              |
    Then  I perform a GET operation with student ID to verify the details are correct
      | id | firstName | lastName | nationality | studentClass | expectedResponse | getBy |
      | 10 | PAWAN     | K        | Singapore   | 10A          | 200              | id    |
    Then  I perform a GET operation with student class to verify the details are correct
      | id | firstName | lastName | nationality | studentClass | expectedResponse | getBy        |
      | 10 | PAWAN     | K        | Singapore   | 10A          | 200              | studentClass |

    And  I perform a DELETE operation for student ID 10 and verify 200 response.

    When I perform PUT operation to for same id which does not exist
      | firstName | id | lastName | nationality | studentClass | expectedResponse |
      | PAWAN     | 10 | K        | Singapore   | 10A          | 500              |


    And  I perform a DELETE operation for student ID 10 and verify 500 response.

  @SmokeTest @NegativeScenario
  Scenario: Adding a student with invalid body for the request results in a 400 response
    When I get 400 response if I add a student with invalid json body consisting of only "{"

  @SmokeTest @NegativeScenario
  Scenario: Updating a student with invalid body for the request results in a 400 response
    When I get 400 response if I update a student with invalid json body consisting of only "{"

  @SmokeTest @NegativeScenario
  Scenario: Deleting a student with invalid body for the request results in a 400 response
    When I get 400 response if I delete a student with invalid json body consisting of only "{"

