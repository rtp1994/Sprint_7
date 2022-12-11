package order;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sprint7.order.CourierOrder;
import sprint7.order.DataOrder;
import sprint7.order.OrderAssertions;
import sprint7.order.OrderClient;


import java.util.List;

@RunWith(Parameterized.class)
public class OrderParamTest {


    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    private int trackNumber;

    private List<String> colour;

    public OrderParamTest(List<String> colour) {
        this.colour = colour;
    }
    public DataOrder generic() { return new DataOrder("alex", "Like", "Lenina, 7", 5, "89009009090", 7, "2020-07-09", "Attention", colour);}

    @Parameterized.Parameters
    public static Object[][] dataGen() {
        return new Object[][] {
                {List.of("GRAY", "YELLOW")},
                {List.of("YELLOW")},
                {List.of(" ")},
        };
    }


    @Test
    @DisplayName("Check creating order /orders")
    public void order() {
        var data = generic();
        ValidatableResponse makeOrderResponse = client.makeOrder(data);
        trackNumber = check.createdOrderSuccessfully(makeOrderResponse);

        assert trackNumber > 100;
    }

    @Test
    @DisplayName("Check showing list of orders /orders")
    public void showList() {
        var courierList = new CourierOrder(4);
        ValidatableResponse checkListResponse = client.checkList(courierList);
        check.listOfOrders(checkListResponse);

    }
}
