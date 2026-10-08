abstract class CoffeeMachine {
    // with no body
    public abstract void brewCoffee();

    private void grindBeans() {
        System.out.println("Grinding fresh coffee beans...");
    }

    private void boilWater() {
        System.out.println("Boiling water at 98°C...");
    }

    private void mixIngredients() {
        System.out.println("Mixing espresso shot with hot water...");
    }

    public void pressStartBtn() {
        System.out.println("Starting coffee preparation....");
        grindBeans();
        boilWater();
        brewCoffee();
        mixIngredients();
        System.out.println("Your coffe is ready...");
    }

}

class EspressoMachine extends CoffeeMachine {
    @Override
    public void brewCoffee() {
        System.out.println("Extracting rish espresso under high pressure...");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        CoffeeMachine cf = new EspressoMachine();
        cf.pressStartBtn();
    }
}
