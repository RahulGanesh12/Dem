package resources;

public enum Resources {
	AddPlaceApi("/maps/api/place/add/json"), GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json");

	private String Api;

	Resources(String Api) {
		this.Api = Api;
	}

	public String getResources() {
		return Api;
	}

}
