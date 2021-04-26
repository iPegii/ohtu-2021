Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "toimii" and password "kahdeksan8" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "kahdeksan8" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "sa" and password "kahdeksan8" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "sasa" and password "eitoimi" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "sasa" and password "kahdeksaneitoimi" are entered
        Then  system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "great" with password "salainen12" is created
        And   command login is selected
        When  username "great" with password "salainen12" are entered
        Then  system will respond with "logged in"
