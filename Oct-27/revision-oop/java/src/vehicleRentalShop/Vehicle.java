package vehicleRentalShop;

abstract class Vehicle implements Rentable {
    //id, model, daily rate and availability.
    //vehicleRentalShop.Car costs rate × days.
    private String id;
    private String model;
    private double dailyRate;
    private boolean available;

    Vehicle(String id, String model, double dailyRate) {
        this.id = id;
        this.model = model;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return getClass().getSimpleName() + " "
                + model + " (" + id + ")";
    }
}