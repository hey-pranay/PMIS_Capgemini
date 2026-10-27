/*
Problem : Vehicle rental shop

Build a rental shop for different kinds of vehicles.

Create an interface Rentable with calculateRent(int days) and isAvailable().
Create an abstract class Vehicle that implements it. Each vehicle has an id, model, daily rate and availability.
Car costs rate × days.
Bike costs rate × days, with a 10% discount for 7 or more days.
Truck costs rate × days plus a fixed 500 insurance charge.
A Customer keeps a history of their Rental objects. A Rental links a vehicle, a customer and a number of days.
RentalShop stores the fleet. It supports rent(), which throws an exception if the vehicle is missing or already rented, as well as giveBack() and showAvailable().

Expected OutPut:

Asha rented Bike Activa (B1) for 8 days = 2880.00
Ravi rented Truck Tata Ace (T1) for 2 days = 5500.00
Error: Bike Activa (B1) is already rented
Available vehicles:
  Car Swift (C1)
  Bike Activa (B1)

*/


import java.util.Locale;

public class VehicleRentalShop {
    public static void main(String[] args) {

        RentalShop shop = new RentalShop();

        Car car = new Car("C1", "Swift", 2000);
        Bike bike = new Bike("B1", "Activa", 400);
        Truck truck = new Truck("T1", "Tata Ace", 2500);

        shop.addVehicle(car);
        shop.addVehicle(bike);
        shop.addVehicle(truck);

        Customer pranay = new Customer("Pranay");
        Customer akash = new Customer("Akash");

        // Rent the bike successfully
        try {
            Rental r1 = shop.rent(bike, pranay, 8);

            System.out.printf(
                    Locale.US,
                    "%s rented %s for %d days = %.2f%n",
                    r1.getCustomer().getName(),
                    r1.getVehicle().getDescription(),
                    r1.getDays(),
                    r1.getTotalRent()
            );
        } catch (VehicleNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Rent the truck successfully
        try {
            Rental r2 = shop.rent(truck, akash, 2);

            System.out.printf(
                    Locale.US,
                    "%s rented %s for %d days = %.2f%n",
                    r2.getCustomer().getName(),
                    r2.getVehicle().getDescription(),
                    r2.getDays(),
                    r2.getTotalRent()
            );
        } catch (VehicleNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Attempt to rent the already-rented bike
        try {
            shop.rent(bike, akash, 5);
        } catch (VehicleNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Show currently available vehicles
        shop.showAvailable();

        pranay.showTransactionHistory();
        akash.showTransactionHistory();

    }
}