package fr.matthieu.vergne.nlpserve;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

public class AppTest {

  @Test
  public void promptEndpoint() {
    JavalinTest.test(
        (app, http) -> {
          App.registerRoutes(app);

          String response = http.post("/agent/prompt", "foo").body().string();
          assertEquals(200, http.post("/agent/prompt", "foo").code());
          assertEquals("bar", response);
        });
  }
}
