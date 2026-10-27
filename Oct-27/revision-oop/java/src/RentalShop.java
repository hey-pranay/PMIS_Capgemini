import java.util.ArrayList;
import java.util.List;

public class RentalShop {
    private final List<Vehicle> fleet = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    public Rental rent(Vehicle vehicle, Customer customer, int days)
            throws VehicleNotAvailableException {
        if (days <= 0) {
            throw new IllegalArgumentException(
                    "Rental days must be greater than zero."
            );
        }

        // check that the vehicle exists in this shop
        Vehicle found = null;

        for (Vehicle item : fleet) {
            if (item.getId().equals(vehicle.getId())) {
                found = item;
                break;
            }
        }

        if (found == null) {
            throw new VehicleNotAvailableException(
                    vehicle.getDescription() + " is not in the fleet."
            );
        }

        if (!found.isAvailable()) {
            throw new VehicleNotAvailableException(
                    found.getDescription() + " is already rented."
            );
        }

        Rental rental = new Rental(found, customer, days);

        found.setAvailable(false);
        customer.addRental(rental);

        return rental;
    }

    public void giveBack(String vehicleId)
            throws VehicleNotAvailableException {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getId().equals(vehicleId)) {
                if (vehicle.isAvailable()) {
                    throw new VehicleNotAvailableException(
                            vehicle.getDescription() + " is nit currently rented."
                    );
                }

                vehicle.setAvailable(true);
                System.out.println(vehicle.getDescription() + " returned successfully.");
                return;
            }
        }

        throw new VehicleNotAvailableException(
                "Vehicle " + vehicleId + " not found."
        );

    }

    public void showAvailable() {
        System.out.println("Available vehicles");

        for (Vehicle vehicle : fleet) {
            if (vehicle.isAvailable()) {
                System.out.println(" " + vehicle.getDescription());
            }
        }
    }
}