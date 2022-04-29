Feature: Fanatic Account Customization
  Scenario: Photo configuration
    Given go to the Configure section
    When fan click the + button
    And choose an image
    Then fan can see your image on your profile

  Scenario:  Name configuration
    Given go to the Configure section
    When fill in the form fan data correctly
    And click on the Change fan Profile button
    Then will be able to see the change in your profile name

  Scenario: Artist places their Tags
    Given go to the Configure section
    When click on the Add button
    Then your art will be defined by a tag