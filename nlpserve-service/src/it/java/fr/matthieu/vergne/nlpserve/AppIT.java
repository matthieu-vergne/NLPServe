package fr.matthieu.vergne.nlpserve;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AppIT {

	@Test
	public void promptEndpoint() throws Exception {
		ServerWaiter.instance().waitUntilReady();

		given()
				.contentType("text/plain")
				.body("foo")
			.when()
				.post("http://127.0.0.1:5555/agent/prompt")
			.then()
				.statusCode(200)
				.body(is("bar"));
	}
}
