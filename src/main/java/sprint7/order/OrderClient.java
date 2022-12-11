package sprint7.order;


import io.restassured.response.ValidatableResponse;
import sprint7.Client;


public class OrderClient extends Client {

    public ValidatableResponse makeOrder(DataOrder data) {
        return spec()
                .body(data)
                .when()
                .post("/orders")
                .then().log().all();
    }
    public ValidatableResponse checkList(CourierOrder courierId) {
        return spec()
                .body(courierId)
                .when()
                .get("/orders")
                .then().log().all();
    }

}

