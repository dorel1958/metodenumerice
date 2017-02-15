package dorel.metodenumerice.utile;

public class Mesaj {

    boolean verbose;

    public Mesaj(boolean verbose) {
        this.verbose = verbose;
    }

    public void write(String mesaj) {
        if (verbose) {
            System.out.println(mesaj);
        }
    }

    public void writeArray(double[] array) {
        if (verbose) {
            boolean ePrima = true;
            System.out.print("Array double: ");
            for (int i = 0; i < array.length; i++) {
                if (ePrima) {
                    ePrima = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(array[i]);
            }
            System.out.println();
        }
    }

    public void writeArray(int[] array) {
        if (verbose) {
            boolean ePrima = true;
            System.out.print("Array int: ");
            for (int i = 0; i < array.length; i++) {
                if (ePrima) {
                    ePrima = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(array[i]);
            }
            System.out.println();
        }
    }
}
