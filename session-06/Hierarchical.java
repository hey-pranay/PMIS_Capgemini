class Vechicle {
    void run() {
        System.out.println("This is vehicle");
    }
}

class TwoWheeler extends Vechicle {
    void two() {
        System.out.println("This is two wheeler");
    }
}

class FourWheeler extends Vechicle {
    void four() {
        System.out.println("This is four wheeler");
    }
}

public class Hierarchical {
    public static void main(String[] args) {

        TwoWheeler tw = new TwoWheeler();
        tw.two();
        tw.run();

        FourWheeler fw = new FourWheeler();
        fw.four();
        fw.run();

    }
}
