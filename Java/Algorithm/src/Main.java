import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Data> hi = new ArrayList<>();

        hi.add(new Data());

        for(Data pos : hi) {
            pos.diff();
        }

        for(Data pos : hi) {
            System.out.println(pos.number);
        }
    }

    public static class Data {
        int number;

        public Data() {
            number = 1;
        }

        public void diff() {
            number = 2;
        }
    }
}
