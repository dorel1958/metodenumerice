package dorel.metodenumerice;

public class Regresie {

    public double[] regresiePolinom(double[][] puncte, int gradFunctie, int nrDecimals) {
        // Metode numerice in inginerie.pdf pag 145
        // - minimalizeaza SUMA abaterilor patratice - erori la exemplul lor!!!
        // - intoarce coeficientii functiei de gradul gradFunctie: g(x) = a0 + a1x + a2x2 + ...
        //      ce interpoleaza cu abatere patratica minima(pe verticala) punctele transmise in array-ul Puncte p(x,y)
        //
        int nrCoefFunctie = gradFunctie + 1;
        int nPuncte; // numarul de puncte date
        nPuncte = puncte.length;
        double[][] a = new double[nrCoefFunctie][nrCoefFunctie]; // matricea sistenului
        double[] b = new double[nrCoefFunctie]; //matricea coloana a sistenului
        double suma;
        for (int i = 0; i < nrCoefFunctie; i++) {
            for (int j = 0; j < nrCoefFunctie; j++) {
                suma = 0;
                for (int k = 0; k < nPuncte; k++) {
                    suma += Math.pow(puncte[k][0], i + j);
                }
                a[i][j] = suma;
            }
            suma = 0;
            for (int k = 0; k < nPuncte; k++) {
                suma += puncte[k][1] * Math.pow(puncte[k][0], i);
            }
            b[i] = suma;
        }
        SistemEcuatiiLiniare sel = new SistemEcuatiiLiniare();
        return sel.gaussMethod(a, b, nrDecimals);
    }
}
