package restAssuredTests.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import restAssuredTests.model.lombok.LoginBodyLombokModel;
import restAssuredTests.model.lombok.LoginResponseLombokBodyModel;
import restAssuredTests.model.pojo.LoginBodyPojoModel;
import restAssuredTests.model.pojo.LoginResponsePojoBodyModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInExtendedTests {
    //String logopas = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";



    @Test
    void loginWithoutModelsTest() {
        LoginBodyPojoModel loginBody = new LoginBodyPojoModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");


        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }


    @Test
    void loginWithPojoModelTest() {
        LoginBodyPojoModel loginBody = new LoginBodyPojoModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponsePojoBodyModel loginResponse= given()
                .log().uri()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoBodyModel.class);

        //assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithLombokModelTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokBodyModel loginResponse= given()
                .log().uri()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokBodyModel.class);

        //assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokBodyModel loginResponse= given()
                .filter(new AllureRestAssured())
                .log().uri()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokBodyModel.class);

        //assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

}
