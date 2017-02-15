package dorel.metodenumerice.simplex;

import dorel.metodenumerice.utile.Mesaj;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Iteratie {

    double[] coefZ; // linia de sus
    public LinieIteratie[] linii;
    // linia Cj-Zj
    public double[] coeficienti;
    public double rhs;
    private final int nrVariabile;
    private final int nrInecuatii;
    Mesaj mesaj;

    public Iteratie(double[] coefZin, double[][] coefInegal, double[] valInegal, Mesaj mesaj) {
        this.mesaj = mesaj;
        nrVariabile = coefZin.length;
        nrInecuatii = valInegal.length;
        coefZ = new double[nrVariabile + nrInecuatii];
        System.arraycopy(coefZin, 0, coefZ, 0, nrVariabile);
        for (int i = nrVariabile; i < nrVariabile + nrInecuatii; i++) {
            coefZ[i] = 0;
        }
        //
        linii = new LinieIteratie[nrInecuatii];
        for (int i = 0; i < nrInecuatii; i++) {
            linii[i] = new LinieIteratie(new Variabila(0, nrVariabile + i), nrVariabile, nrInecuatii, coefInegal[i], valInegal[i], mesaj);
        }
        mesaj.write("Iteratie - Gata");
    }

    private Iteratie(double[] coefZ, int nrVariabile, int nrInecuatii, Mesaj mesaj) {
        this.mesaj = mesaj;
        this.nrVariabile = nrVariabile;
        this.nrInecuatii = nrInecuatii;
        this.coefZ = coefZ;
        linii = new LinieIteratie[nrInecuatii];
        mesaj.write("Iteratie - scurta  Gata");
    }

    public Iteratie calcul() {
        // calcul coeficienti pe orizontala si rhs
        mesaj.write("Iteratie.calcul - calcul coeficienti pe orizontala");
        rhs = 0;
        coeficienti = new double[nrVariabile + nrInecuatii];
        for (int i = 0; i < nrVariabile + nrInecuatii; i++) {
            double zj = 0;
            for (int j = 0; j < nrInecuatii; j++) {
                LinieIteratie li = linii[j];
                zj += li.variabila.coeficient * li.coeficienti[i];
                //rhs += li.variabila.coeficient * li.rhs;
            }
            coeficienti[i] = coefZ[i] - zj;
            mesaj.write("Iteratie.calcul - coeficienti[" + i + "]=" + coeficienti[i]);
        }
        mesaj.write("Iteratie.calcul - calcul rhs");
        for (int j = 0; j < nrInecuatii; j++) {
            LinieIteratie li = linii[j];
            rhs += li.variabila.coeficient * li.rhs;
        }
        mesaj.write("Iteratie.calcul - rhs=" + rhs);
        // caut coeficientul maxim
        mesaj.write("Iteratie.calcul - caut coeficientul maxim");
        double coefMax = -Double.MAX_VALUE;
        int indexEnteringColumn = -1;
        for (int i = 0; i < nrVariabile + nrInecuatii; i++) {
            if (coeficienti[i] > coefMax) {
                coefMax = coeficienti[i];
                indexEnteringColumn = i;
            }
        }
        mesaj.write("Iteratie.calcul - indexEnteringColumn=" + indexEnteringColumn);
        // calcul teta linii
        mesaj.write("Iteratie.calcul - calcul teta linii");
        for (int j = 0; j < nrInecuatii; j++) {
            LinieIteratie li = linii[j];
            li.calculTeta(indexEnteringColumn);
        }
        //gasesc minim teta -> index exit variable
        mesaj.write("Iteratie.calcul - gasesc minim teta -> index exit variable");
        double tetaMin = Double.MAX_VALUE;
        int indexLeavingRow = -1;
        for (int j = 0; j < nrInecuatii; j++) {
            LinieIteratie li = linii[j];
            if (li.teta < tetaMin) {
                tetaMin = li.teta;
                indexLeavingRow = j;
            }
        }
        mesaj.write("Iteratie.calcul - indexLeavingRow=" + indexLeavingRow);

        // test iesire
        mesaj.write("Iteratie.calcul - test iesire");
        boolean eFinal = true;
        for (int i = 0; i < nrVariabile + nrInecuatii; i++) {
            if (coeficienti[i] > 0) {
                //mesaj.write("Iteratie.calcul - coeficienti[" + i + "]==0 (" + coeficienti[i] + ") -> NU iese");
                eFinal = false;
            }
        }
        if (eFinal) {
            mesaj.write("Iteratie.calcul - iesire -> return null");
            return null;
        }

        // creez noua iteratie
        mesaj.write("Iteratie.calcul - Nu a iesit - creez noua iteratie");
        Iteratie newIteratie = new Iteratie(coefZ, nrVariabile, nrInecuatii, mesaj);

        // pun coeficientii pe linia modificata
        mesaj.write("Iteratie.calcul - pun coeficientii pe linia modificata");
        newIteratie.linii[indexLeavingRow] = new LinieIteratie(new Variabila(coefZ[indexEnteringColumn], indexEnteringColumn), nrVariabile, nrInecuatii, mesaj);
        //
        double linieVal = linii[indexLeavingRow].coeficienti[indexEnteringColumn];
        mesaj.write("Iteratie.calcul - linieVal=" + linieVal);
        //
        for (int i = 0; i < nrVariabile + nrInecuatii; i++) {
            // calculez coeficienti pe randul schimbat
            newIteratie.linii[indexLeavingRow].coeficienti[i] = linii[indexLeavingRow].coeficienti[i] / linieVal;
            mesaj.write("Iteratie.calcul - randul schimbat: newIteratie.linii[" + indexLeavingRow + "].coeficienti[" + i + "]=" + newIteratie.linii[indexLeavingRow].coeficienti[i]);
        }
        newIteratie.linii[indexLeavingRow].rhs = linii[indexLeavingRow].rhs / linieVal;
        mesaj.write("Iteratie.calcul - randul schimbat: newIteratie.linii[" + indexLeavingRow + "].rhs=" + newIteratie.linii[indexLeavingRow].rhs);
        // restul liniilor
        mesaj.write("Iteratie.calcul - pun coeficientii pe restul liniilor");
        for (int j = 0; j < nrInecuatii; j++) {
            if (j != indexLeavingRow) {
                double linieVal2 = linii[j].coeficienti[indexEnteringColumn];
                mesaj.write("Iteratie.calcul - linieVal2=" + linieVal2);
                newIteratie.linii[j] = new LinieIteratie(linii[j].variabila, nrVariabile, nrInecuatii, mesaj);
                for (int i = 0; i < nrVariabile + nrInecuatii; i++) {
                    newIteratie.linii[j].coeficienti[i] = linii[j].coeficienti[i] - newIteratie.linii[indexLeavingRow].coeficienti[i] * linieVal2;
                    mesaj.write("Iteratie.calcul - rand ramas, modificat: newIteratie.linii[" + j + "].coeficienti[" + i + "]=" + newIteratie.linii[j].coeficienti[i]);
                }
                newIteratie.linii[j].rhs = linii[j].rhs - newIteratie.linii[indexLeavingRow].rhs * linieVal2;
                mesaj.write("Iteratie.calcul - rand ramas, modificat: newIteratie.linii[" + j + "].rhs=" + newIteratie.linii[j].rhs);
            }
        }
        return newIteratie;
    }

    public String getStringSolutia(int nrZecimale) {
        String solutia = "";
        for (int i = 0; i < nrInecuatii; i++) {
            BigDecimal bd = new BigDecimal(linii[i].rhs);
            bd = bd.setScale(nrZecimale, RoundingMode.HALF_UP);
            solutia += "X" + linii[i].variabila.index + "=" + bd.toString() + "\n";
        }
        BigDecimal bd = new BigDecimal(rhs);
        bd = bd.setScale(nrZecimale, RoundingMode.HALF_UP);
        solutia += "Z=" + bd.toString() + "\n";
        return solutia;
    }
}
