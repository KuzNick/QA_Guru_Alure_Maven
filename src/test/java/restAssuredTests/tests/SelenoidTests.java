package restAssuredTests.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(5));

    }

    @Test
    void checkTotalWithStatusCode() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(5));

    }

    @Test
    void checkTotalWithStatusCodeAndLogs() {
        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(5));

    }

    @Test
    void checkTotalWithStatusCodeAndSomeLogs() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(5));

    }

    @Test
    void checkChromeVersion() {
        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("browsers.chrome", hasKey("127.0"));

    }

    @Test
    void checkResponseBadPractics() {
        String ExpectedResponseString = "{\"total\": 5,\"used\": 0,\"queued\": 0,\"pending\": 0, \"browsers\": { \"chrome\": { \"127.0\": { }, \"128.0\": { } }, \"firefox\": { \"124.0\": { }, \"125.0\": { } }, \"opera\": { \"108.0\": { }, \"109.0\": { } } } }\n";

        Response actualResponse = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        assertEquals(ExpectedResponseString, actualResponse.asString());

    }

    // https://selenoid.autotests.cloud/wb/hub/status

    @Test
    void checkWbHubStatus() {
        given()
                .log().uri()
                .when()
                .get("https://user1:1234@selenoid.autotests.cloud/wb/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body("value.ready", is(true));

    }

    @Test
    void checkWbHubWithAuthStatus() {
        given()
                .log().uri()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wb/hub/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(404)
                .body("value.ready", is(true));

    }
}
