
Feature: When valid HTML mock which can't be parsed as XML then mock should succeed
#Note: fails only when ALL of the following conditions are met
# karate version == 1.1.0 || 1.1.0.RC5
# build system == gradle
# logPrettyResponse == true

  Scenario: post redirect to get

    * def port = karate.start('classpath:examples/login-redirect-mock.feature').port
    * def loginUrl = 'http://localhost:' + port + '/login'

    Given url loginUrl
    And request {}
    When method POST
    Then status 200


  Scenario: get

    * def port = karate.start('classpath:examples/login-redirect-mock.feature').port
    * def loginUrl = 'http://localhost:' + port + '/login'

    Given url loginUrl
    When method GET
    Then status 200

  Scenario: post no redirect

    * configure followRedirects = false
    * def port = karate.start('classpath:examples/login-redirect-mock.feature').port
    * def loginUrl = 'http://localhost:' + port + '/login'

    Given url loginUrl
    And request {}
    When method POST
    Then status 302