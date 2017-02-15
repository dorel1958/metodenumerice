package dorel.metodenumerice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;

public class SistemEcuatiiLiniare {

    private final boolean isDesktop = true;
    private boolean verbose = false;

    public double[] gaussMethod(double[][] a, double[] b, int nrDecimals) {
        // rezolvarea sistemului de ecuatii liniare (de gradul I)
        // a - matricea coeficientilor necunoscutelor
        // b - matricea cu partea de dupa egal (termenii liberi)
        // cu o precizie de nrDecimals
        //
        mesaj("matricea A initiala");
        listMatrice2(a);
        mesaj("matricea B initiala");
        listMatrice1(b);
        // teste
        int dimensionY = a.length;
        int dimensionX = a[0].length;
        for (int i = 0; i < dimensionY; i++) {
            dimensionX = a[i].length;
            if (dimensionX != dimensionY) {
                mesajEroare("SystemOfLinearEquations.gaussMethod - matricea a nu esta patrata (i=" + i + ").");
                return null;
            }
        }
        int dimensionB = b.length;
        if (dimensionB != dimensionY) {
            mesajEroare("SystemOfLinearEquations.gaussMethod - matricea b nu are dimensiunea corecta.");
            return null;
        }
        if (nrDecimals < 0) {
            nrDecimals = 3;
        }
        // notez ordinea coloanelor:
        int[] ordineColoane = new int[dimensionX];
        for (int i = 0; i < dimensionX; i++) {
            ordineColoane[i] = i;
        }
        //
        //populez matricea de lucru
        double[][] matrLucru = new double[dimensionY][dimensionX + 1];
        for (int i = 0; i < dimensionY; i++) {
            System.arraycopy(a[i], 0, matrLucru[i], 0, dimensionX);
            matrLucru[i][dimensionX] = b[i];
        }
        mesaj("matriciea de lucru initiala:");
        listMatrice2(matrLucru);
        //
        // iteratii pentru anularea coeficientilor de sub diagonala
        double coefMax;
        double coefCurent;
        int indexMax;
        double divizor;
        mesaj("for pas=0 pas<" + dimensionY);
        for (int pas = 0; pas < dimensionY; pas++) { // pentru fiecare rand
            mesaj("caut coef max pe restul randului curent pas=" + pas);
            // caut coef max pe restul randului curent (fara ultimul termen provenit de la b)
            coefMax = 0;
            indexMax = pas;
            for (int i = pas; i < dimensionX; i++) {
                coefCurent = Math.abs(matrLucru[pas][i]);
                if (coefCurent > coefMax) {
                    coefMax = coefCurent;
                    indexMax = i;
                }
            }
            mesaj("Am gasit: coefMax=" + coefMax + " indexMax=" + indexMax);
            if (coefMax > 0) {
                // aduc coloana cu coef maxim pe pozitia 'pas'
                mesaj("aduc coloana cu coef maxim pe pozitia 'pas' coefMax=" + coefMax + " indexMax=" + indexMax);
                if (indexMax > pas) {
                    for (int i = 0; i < dimensionY; i++) {
                        coefCurent = matrLucru[i][pas];
                        matrLucru[i][pas] = matrLucru[i][indexMax];
                        matrLucru[i][indexMax] = coefCurent;
                    }
                    int index = ordineColoane[pas];
                    ordineColoane[pas] = ordineColoane[indexMax];
                    ordineColoane[indexMax] = index;
                } else {
                    mesaj("coloana e pe pozitie corecta");
                }
                listMatrice2(matrLucru);
            } else {
                mesajEroare("SystemOfLinearEquations.gaussMethod - coefMax=0 la pas=" + pas + ".");
                return null;
            }
            // aduc la 1 coeficientul de pe diagonala, impartind linia la divizor
            divizor = matrLucru[pas][pas];
            for (int i = pas; i < dimensionX + 1; i++) {
                matrLucru[pas][i] = matrLucru[pas][i] / divizor;
            }
            mesaj("am adus la 1 coeficientul de pe diagonala, impartind linia la divizor");
            listMatrice2(matrLucru);
            // aduc termenuii coloanei de sub diagonala la 0
            for (int i = pas + 1; i < dimensionY; i++) {
                divizor = matrLucru[i][pas];
                if (divizor != 0) {
                    //if (divizor != 1) {
                        for (int j = pas; j < dimensionX + 1; j++) {
                            matrLucru[i][j] = matrLucru[i][j] - matrLucru[pas][j] * divizor;
                        }
                    //} else {
                    //    mesaj("divizorul este 1 -> nu modific");
                    //}
                } else {
                    mesaj("Divizor null la pas=" + pas + " coloana=" + i);
                    //return null;
                }
            }
            mesaj("Am adus termenii coloanei de sub diagonala la 0");
            listMatrice2(matrLucru);
        }
        // solutia:
        double[] x = new double[dimensionY];
        //x[dimensionY - 1] = matrLucru[dimensionY - 1][dimensionX];
        double dif;
        mesaj("Calculex x:");
        for (int i = dimensionY - 1; i >= 0; i--) {
            x[i] = matrLucru[i][dimensionX];
            dif = 0;
            for (int j = i + 1; j < dimensionX; j++) {
                dif += x[j] * matrLucru[i][j];
            }
            x[i] -= dif;
            mesaj("x[" + i + "]=" + x[i]);
        }
        mesaj("Solutia x");
        listMatrice1(x);
        // rotunjire
        BigDecimal bd;
        for (int i = 0; i < dimensionY; i++) {
            bd = new BigDecimal(x[i]);
            bd = bd.setScale(nrDecimals, RoundingMode.HALF_UP);
            x[i] = bd.doubleValue();
        }
        mesaj("Solutia x rotunjita");
        listMatrice1(x);
        // reasezare coloane
        double[] solutia = new double[dimensionX];
        for (int i = 0; i < dimensionX; i++) {
            solutia[i] = x[ordineColoane[i]];
        }
        mesaj("Solutia rearanjata:");
        listMatrice1(solutia);
        return solutia;
    }

    private void mesaj(String mesaj) {
        if (verbose) {
            System.out.println(mesaj);
        }
    }

    private void mesajEroare(String mesaj) {
        System.out.println(mesaj);
        if (isDesktop) {
            JOptionPane.showMessageDialog(null, mesaj);
        }
    }

    private void listMatrice1(double[] matr) {
        if (!verbose) {
            return;
        }
        boolean ePrima = true;
        for (int i = 0; i < matr.length; i++) {
            if (ePrima) {
                ePrima = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(matr[i]);
        }
        System.out.println();
    }

    private void listMatrice2(double[][] matr) {
        if (!verbose) {
            return;
        }
        for (int i = 0; i < matr.length; i++) {
            boolean ePrima = true;
            for (int j = 0; j < matr[i].length; j++) {
                if (ePrima) {
                    ePrima = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(matr[i][j]);
            }
            System.out.println();
        }
    }

    public void printMatrice1(double[] matr) {
        boolean xVerbose = verbose;
        verbose = true;
        listMatrice1(matr);
        verbose = xVerbose;
    }
}
