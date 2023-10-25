package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlaceApi1")
	public void independantDeletePlaceApi() throws IOException {
		AddPlaceAPI a = new AddPlaceAPI();
		if (AddPlaceAPI.place_id == null) {
			a.add_place_api_json_payload_and_base_uri("GaneshLu", "Aramic", "999999");
			a.user_hits_using_request("AddPlaceApi", "post");
			a.verifying_if_maps_with_place_id_of("GaneshLu", "GetPlaceApi");
		}
	}
}
