package sprint7.order;


public class CourierOrder {
    private int courierId;

    public CourierOrder(int courierId) {
        this.courierId = courierId;
    }
    public CourierOrder() {
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }
}
