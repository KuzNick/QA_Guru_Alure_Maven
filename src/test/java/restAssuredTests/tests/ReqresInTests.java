package restAssuredTests.tests;

import org.junit.jupiter.api.Test;
import restAssuredTests.model.pojo.LoginBodyPojoModel;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {
    //String logopas = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";


    @Test
    void loginTest() {
        LoginBodyPojoModel bodyModel = new LoginBodyPojoModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");


        given()
                .log().uri()
                .contentType(JSON)
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }



    @Test
    void negativeLoginTest() {
        given()
                .log().uri()
                //.contentType(JSON)
                .body("1234")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
}
