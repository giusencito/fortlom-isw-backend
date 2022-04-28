Feature: Fraud Report
  Scenario: Report on publication fraud
    Given that the artist is on Posts section fraud
    And press the button See Posts
    When there is a publication that seems incorrect to him
    And press the button Flag
    Then the report is created