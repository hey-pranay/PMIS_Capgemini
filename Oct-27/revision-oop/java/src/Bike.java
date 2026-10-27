public class Bike extends Vehicle {

    Bike(String id, String model, double dailyRent) {
        super(id, model, dailyRent);
    }

    @Override
    public double calculateRent(int days) {
        double rent = getDailyRate() * days;

        // discount- 10%
        if (days >= 7) {
            rent = rent - (rent * 0.10);
        }
        return rent;
    }

}