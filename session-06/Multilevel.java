class Calculator {
    void cal() {
        System.out.println("calculate...");
    }
}

class Add extends Calculator {
    void add() {
        System.out.println("add...");
    }
}

class TwoAdd extends Add {
    void twoAdd() {
        System.out.println("Two no addition...");
    }
}

public class Multilevel {
    public static void main(String[] args) {
        TwoAdd to = new TwoAdd();
        to.cal();
        to.add();
        to.twoAdd();
    }
}
