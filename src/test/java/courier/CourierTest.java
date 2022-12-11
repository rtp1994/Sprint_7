package courier;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import sprint7.courier.CourierAssertions;
import sprint7.courier.CourierClient;
import sprint7.courier.CourierGenerator;
import sprint7.courier.Credentials;

public class CourierTest {

    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private int courierId;  // default value

    @After public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse response = client.delete(courierId);
            check.deletedSuccessfully(response);
        }
    }

    @Test
    @DisplayName("Check creating and login /courier")
    public void courier() {
        var courier = generator.random();
        ValidatableResponse creationResponse = client.create(courier);
        check.createdSuccessfully(creationResponse);

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        assert courierId > 100;
    }

    @Test
    @DisplayName("Check repeating courier /courier")
    public void courierRepeated() {
        var courier = generator.generic();
        courier.setLogin("ninja");
        ValidatableResponse creationResponse = client.create(courier);
        String message = check.creationFailedRepeatedLogin(creationResponse);

        assert !message.isBlank();
    }

    @Test
    @DisplayName("Check login courier without password /courier")
    public void loginFails() {
        var courier = generator.generic();
        courier.setPassword(null);

        ValidatableResponse loginResponse = client.create(courier);
        String message = check.creationFailed(loginResponse);
        assert !message.isBlank();
    }

    @Test
    @DisplayName("Check login courier with wrong password /courier")
    public void loginFailsWrongPassword() {
        var courier = generator.generic();
        courier.setLogin("ninja");
        courier.setPassword("7654321");

        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        String message = check.loginFailed(loginResponse);

        assert !message.isBlank();
    }
}

