package sprint7.courier;


import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class CourierAssertions {
    public void createdSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true))
        ;

    }
    public int loggedInSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id")
                ;

    }

    public String creationFailed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;
    }

    public String creationFailedRepeatedLogin(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(409)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;
    }

    public String loginFailed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(404)
                .body("message", notNullValue())
                .extract()
                .path("message")
                ;
    }

    public void deletedSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true))
        ;
    }
}
