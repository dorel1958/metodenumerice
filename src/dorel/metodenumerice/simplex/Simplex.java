package dorel.metodenumerice.simplex;

import dorel.metodenumerice.utile.Mesaj;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Simplex {

    boolean verbose;
    public List<Iteratie> iteratii;

    public Iteratie getIteratiaSolutie(){
        return iteratii.get(iteratii.size()-1);
    }

    public Simplex(boolean verbose) {
        this.verbose=verbose;
    }
    
    public boolean calcul(double[] coefZin, double[][] coefInegal, double[] valInegal, int nrZecimale) {
        Mesaj mesaj = new Mesaj(verbose);
        Iteratie iteratieCurenta;
        //
        iteratii = new ArrayList<>();
        String solutia = "";
        mesaj.write("Iteratia:0");
        iteratieCurenta = new Iteratie(coefZin, coefInegal, valInegal, mesaj);
        int i = 0;
        while (iteratieCurenta != null) {
            iteratii.add(iteratieCurenta);
            Iteratie iteratie = iteratieCurenta.calcul();
            if (iteratie == null) {
                solutia = iteratieCurenta.getStringSolutia(nrZecimale);
            }
            iteratieCurenta = iteratie;
            i += 1;
            mesaj.write("Iteratia:" + i);
            if (i == 100) {
                JOptionPane.showMessageDialog(null, "Simplex - metoda nu a convers in 100 pasi.");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, solutia);
        return true;
    }
}

//    public void testSimplex1() {
//        //Exemplul 1
//        // Maximize Z = 6x1 + 5x2 (Objective function)
//        // Subject to (Constrains)
//        // x1 + x2 <= 5
//        // 3x1 + 2x2 <= 12
//        // x1, x2 >= 0
//
// pt rezolvare inegalitatile se transforma in egalitati punand necunoscute suplimentare:
//        // Maximize Z = 6x1 + 5x2+0x3+0x4
//        // Subject to
//        // x1 + x2 + x3 = 5
//        // 3x1 + 2x2 + x4 = 12
//        // x1, x2, x3, x4 >= 0
//
//        double[] coefZ = new double[2];
//        coefZ[0] = 6;
//        coefZ[1] = 5;
//
//        double[][] coefInegal = new double[2][2];
//        coefInegal[0][0] = 1;
//        coefInegal[0][1] = 1;
//        coefInegal[1][0] = 3;
//        coefInegal[1][1] = 2;
//
//        double[] valInegal = new double[2];
//        valInegal[0] = 5;
//        valInegal[1] = 12;
//
//        Simplex simplex = new Simplex(coefZ, coefInegal, valInegal, 2);
//        // solutia: x1=2, X2=3 Z=27
//    }
//
//    public void testSimplex() {
//        //Exemplul 2
//        // minimize Z = 6x1 + 8x2 (Objective function)
//        // Subject to   (Constrains)
//        // x1 + x2 <= 10
//        // 2x1 + 3x2 <= 25
//        // x1 + 5x2 <= 35
//        // x1, x2 >= 0
//        double[] coefZ = new double[2];
//        coefZ[0] = 6;
//        coefZ[1] = 8;
//
//        double[][] coefInegal = new double[3][2];
//        coefInegal[0][0] = 1;
//        coefInegal[0][1] = 1;
//        coefInegal[1][0] = 2;
//        coefInegal[1][1] = 3;
//        coefInegal[2][0] = 1;
//        coefInegal[2][1] = 5;
//
//        double[] valInegal = new double[3];
//        valInegal[0] = 10;
//        valInegal[1] = 25;
//        valInegal[2] = 35;
//
//        Simplex simplex = new Simplex(coefZ, coefInegal, valInegal, 2);
//        // solutia: x1=5, X2=5 Z=70
//    }
