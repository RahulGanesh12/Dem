package resources;

import java.util.ArrayList;
import java.util.List;

import PojoClasses.InputJsonPayload;
import PojoClasses.Location;

public class JsonPayload {
	public InputJsonPayload JsonBuildPayload(String name, String language, String Phone_Number) {
		InputJsonPayload ijp = new InputJsonPayload();
		ijp.setAccuracy(50);
		ijp.setAddress("29, side layout, cohen 09");
		ijp.setLanguage(language);
		ijp.setName(name);
		ijp.setPhone_number(Phone_Number);
		ijp.setWebsite("http://rahulshettyacademy.com");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ijp.setLocation(l);
		List<String> lst = new ArrayList<>();
		lst.add("Shoe Park");
		lst.add("Park");
		ijp.setTypes(lst);
		return ijp;
	}

	public String deletePayload(String place_id) {
		return "{\r\n" + "    \"place_id\":\"" + place_id + "\"\r\n" + "}\r\n" + "";
	}
}
