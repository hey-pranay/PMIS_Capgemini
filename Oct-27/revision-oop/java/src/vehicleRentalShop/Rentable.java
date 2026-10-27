package vehicleRentalShop;

public interface Rentable {
    double calculateRent(int days);
    boolean isAvailable();
}