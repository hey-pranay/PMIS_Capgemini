package vehicleRentalShop;

public class Rental {
    private final Vehicle vehicle;
    private final Customer customer;
    private final int days;
    private final double totalRent;

    Rental(Vehicle vehicle, Customer customer, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException(
                    "vehicleRentalShop.Rental days must be greater than 0."
            );
        }

        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.totalRent = vehicle.calculateRent(days);
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getTotalRent() {
        return totalRent;
    }
}