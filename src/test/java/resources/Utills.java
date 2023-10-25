package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utills {
	public static RequestSpecification rs;
	ResponseSpecification Resspec;

	public RequestSpecification reqSpec() throws IOException {
		if (rs == null) {
			PrintStream p = new PrintStream(new FileOutputStream("logging.txt"));
			rs = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(p))
					.setBaseUri(globalFile("baseURI")).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).addFilter(ResponseLoggingFilter.logResponseTo(p)).build();
			return rs;
		}
		return rs;
	}

	public ResponseSpecification respspec() {
		Resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return Resspec;
	}

	public String globalFile(String file) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Admin\\CucumberFramework\\src\\test\\java\\resources\\Global.properties");
		p.load(fis);
		return p.getProperty(file);
	}

	public String getJson(Response resp, String key) {
		String response = resp.asString();
		JsonPath jp = new JsonPath(response);
		return jp.getString(key);
	}
}
