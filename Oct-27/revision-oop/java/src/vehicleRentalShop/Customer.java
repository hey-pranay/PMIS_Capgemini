package vehicleRentalShop;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Rental> rentalHistory = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentalHistory.add(rental);
    }


    public void showTransactionHistory() {
        System.out.println("\nTransaction History for " + name + ":");

        if (rentalHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Rental rental : rentalHistory) {
            System.out.printf(
                    java.util.Locale.US,
                    "%s | %d days | Total Rent: %.2f%n",
                    rental.getVehicle().getDescription(),
                    rental.getDays(),
                    rental.getTotalRent()
            );
        }
    }

}