public class Truck extends Vehicle {

    private static final double  INSURANCE_CHARGE = 500.0;

    Truck(String  id, String model, double dailyRent){
        super(id, model,dailyRent);
    }

    @Override
    public double calculateRent(int days){
            return getDailyRate() * days + INSURANCE_CHARGE;
    }

}