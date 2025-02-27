package utils;

import com.zl.mobileAutomation.service.Credentials;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredManager {

        private static final String BASE_URL = "http://localhost:8080/api/auth";

        public RestAssuredManager() {
            RestAssured.baseURI = BASE_URL;
        }

        public Response getCredentialsResponse() {
            return given()
                    .when()
                    .get("/credentials")
                    .then()
                    .extract().response();
        }

        public Credentials getCredentials() {
            return given()
                    .when()
                    .get("/credentials")
                    .then()
                    .statusCode(200)
                    .extract().as(Credentials.class);
        }
    }
