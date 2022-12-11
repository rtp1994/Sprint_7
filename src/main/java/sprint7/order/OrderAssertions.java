package sprint7.order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class OrderAssertions {
    public int createdOrderSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(201)
                .body("track", greaterThan(0))
                .extract()
                .path("track")
                ;

    }

    public void listOfOrders(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("orders", notNullValue())
                .extract()
                .path("orders")
        ;
    }
}


