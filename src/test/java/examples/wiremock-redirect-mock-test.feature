Feature: When A. valid successful HTML SUCCESS response B. logPrettyResponse == true and C. gradle Then should return success and log response
# Only fails when all of the following are true:
# karate=1.*.* ,
# build system==gradle
# logPrettyResponse == true

  Background:
    * def WireMock = Java.type('examples.WireMock')


  Scenario: post redirect to get

    * def wiremock = new WireMock()
    * wiremock.start()
    * def port = wiremock.getPort()
    * def indexUrl = 'https://localhost:' + port + '/index.html'

    * configure followRedirects = true
    * configure ssl = true
    Given url indexUrl
    And form field username = 'john'
    And form field password = 'secret'
    When method POST
    Then status 200
    * wiremock.stop()

