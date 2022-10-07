Feature: feature to test login functionality

  Scenario: Check login is successful

    #Given user is on todomvc page
    #When user types 'item1' on the 'What's needs to be done' text box and clicks enter
    #Then a new todo item named 'item1' is created

   # Given user is on todomvc page
   # When user types 'item2' on the 'What's needs to be done' text box and clicks enter
   # Then user is able to clear the item called 'item2' that is created by clicking on the X icon

    Given user is on todomvc page
    When user types 'item2' on the 'What's needs to be done' text box and clicks enter
    Then user is able to click the checkbox to clear the item called 'item2'

    Given user is on todomvc page
    When user types 'item1' on the 'What's needs to be done' text box and clicks enter
    And user is able to click the checkbox to clear the item called 'item1'
    And user types 'item2' on the 'What's needs to be done' text box and clicks enter
    Then user is able to click the checkbox to clear the item called 'item2'

