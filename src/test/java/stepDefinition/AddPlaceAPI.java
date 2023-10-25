package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.JsonPayload;
import resources.Resources;
import resources.Utills;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.Assert;

public class AddPlaceAPI extends Utills {
	RequestSpecification addplace;
	Response response;
	ResponseSpecification resspec;
	JsonPayload jp = new JsonPayload();
	public static String place_id;

	@Given("Add place API Json Payload and Base URI {string} {string} {string}")
	public void add_place_api_json_payload_and_base_uri(String name, String language, String Phone_Number)
			throws IOException {

		addplace = given().spec(reqSpec()).body(jp.JsonBuildPayload(name, language, Phone_Number));

	}

	@When("User hits {string} using {string} request")
	public void user_hits_using_request(String ApiName, String type) {
		Resources res = Resources.valueOf(ApiName);
		System.out.println(res.getResources());
		if (type.equalsIgnoreCase("post")) {
			response = addplace.when().post(res.getResources()).then().spec(respspec()).extract().response();
		} else if (type.equalsIgnoreCase("get")) {
			response = addplace.when().get(res.getResources()).then().spec(respspec()).extract().response();
		}
	}

	@Then("A json response with Status Code is {int}")
	public void a_json_response_with_is(Integer Expectedstatuscode) {
		int ActualstatusCode = response.getStatusCode();
		Assert.assertEquals(ActualstatusCode, 200);
	}

	@And("Verifying if {string} is {string}")
	public void verifying_if_is(String Status, String value) {
		String resp = response.asString();
		JsonPath jp = new JsonPath(resp);
		String statusvalue = jp.getString(Status);
		Assert.assertEquals(statusvalue, value);
	}

	@Then("Verifying if {string} maps with place_id of {string}")
	public void verifying_if_maps_with_place_id_of(String name, String type) throws IOException {
		place_id = getJson(response, "place_id");
		addplace = given().spec(reqSpec()).queryParam("place_id", place_id);
		user_hits_using_request(type, "get");
		String actname = getJson(response, "name");
		Assert.assertEquals(actname, name);
	}

	@Given("User has Delete place api payload")
	public void user_has_delete_place_api_payload() throws IOException {
		addplace = given().spec(reqSpec()).body(jp.deletePayload(place_id));
	}

}
