class SmartPhone {
    private String model;
    private int batteryLevel = 100;
    private int volume = 15;

    SmartPhone(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int newVolume) {
        if (newVolume >= 0 && newVolume <= 30) {
            this.volume = newVolume;
            System.out.println("Volume set to  : " + newVolume);
        } else {
            System.out.println("keep volume bet 0 to 30");
        }
    }

    public void useApp(String appName) {
        if (batteryLevel <= 0) {
            System.out.println("Phone is dead. Please charge the phone.");
            return;
        }

        System.out.println("Running..." + appName + "...");
        batteryLevel = batteryLevel - 10;

        if (batteryLevel < 0)
            batteryLevel = 0;

    }

    public void chargePhone() {
        System.out.println("Phone charging...");

        batteryLevel = batteryLevel + 20;

        if (batteryLevel >= 100) {
            batteryLevel = 100;
        }
        System.out.println("phone chareged with " + batteryLevel + "%" + " battery");
    }
}

public class Encapsulation {

    public static void main(String[] args) {
        SmartPhone sm = new SmartPhone("Samsung s24");
        sm.useApp("Whatsapp");
        sm.setVolume(60);
        sm.setVolume(20);

        System.out.println("my phone current battery : " + sm.getBatteryLevel() + "%");
    }
}
