package org.acme.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitRESTResourceTest {

    @Test
    public void testPost() {

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .body("{\"name\": \"Bananna\"}")
                .when()
                .post("/fruits")
                .then()
                .statusCode(201)
                .body(is("{\"id\":1,\"name\":\"bananna\"}"));
    }
}
