public class Car extends Vehicle{

    Car(String  id, String model, double dailyRent){
        super(id, model, dailyRent);
    }

    @Override
    public double calculateRent(int days){
        return getDailyRate() * days;
    }
}