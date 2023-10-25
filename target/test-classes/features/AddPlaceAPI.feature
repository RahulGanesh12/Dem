Feature: Testing Add Place API

@AddPlaceApi @Regression
Scenario Outline: User should be able to use Add Place API and add details
Given Add place API Json Payload and Base URI "<name>" "<language>" "<Phone_Number>"
When User hits "AddPlaceApi" using "post" request
Then A json response with Status Code is 200
And Verifying if "status" is "OK"
And Verifying if "scope" is "APP"
And Verifying if "<name>" maps with place_id of "GetPlaceApi"

Examples:
|name|language|Phone_Number|
|Rahul|Tamil|9686721172|

@DeletePlaceApi1 @Regression
Scenario: Check Delete place API functionality
Given User has Delete place api payload
When User hits "DeletePlaceApi" using "post" request
And Verifying if "status" is "OK"

