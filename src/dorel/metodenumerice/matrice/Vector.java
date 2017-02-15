package dorel.metodenumerice.matrice;

import javax.swing.JOptionPane;

public class Vector {

    public double[] a;

    public int getDimensions() {
        return a.length;
    }

    public Vector(double[] a) {
        this.a = a;
    }

    //<editor-fold defaultstate="collapsed" desc="Operatii">
    public boolean isEqual(Vector vect) {
        if (getDimensions() == vect.getDimensions()) {
            for (int i = 0; i < getDimensions(); i++) {
                if (a[i] != vect.a[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isOrtogonal(Vector vect) {
        return produsScalarCuVectorul(vect) == 0;
    }

    public void adunCuVectorul(Vector vectorB) {
        int dimensions = getDimensions();
        if (vectorB.getDimensions() == dimensions) {
            for (int i = 0; i < dimensions; i++) {
                a[i] = a[i] + vectorB.a[i];
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vector.adunCuVectorul - Vectorul de adumat nu are dimensiune egala cu vectorul curent.");
        }
    }

    public void inmultescCuScalar(double lambda) {
        for (int i = 0; i < getDimensions(); i++) {
            a[i] = a[i] * lambda;
        }
    }

    public double produsScalarCuVectorul(Vector vectorB) {
        int dimensions = getDimensions();
        double valoare = 0;
        if (vectorB.getDimensions() == dimensions) {
            for (int i = 0; i < dimensions; i++) {
                valoare += a[i] * vectorB.a[i];
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vector.produsScalarCuVectorul - Vectorul de adumat nu are dimensiune egala cu vectorul curent.");
        }
        return valoare;
    }
    //</editor-fold>

    public void printText() {
        System.out.println("Vector.printText");
        boolean ePrima = true;
        for (int i = 0; i < getDimensions(); i++) {
            if (ePrima) {
                ePrima = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(a[i]);
        }
        System.out.println("");
    }
}
