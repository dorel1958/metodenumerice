package dorel.metodenumerice.simplex;

import dorel.metodenumerice.utile.Mesaj;

public class LinieIteratie {

    public Variabila variabila;
    public double[] coeficienti;
    public double rhs;
    public double teta;
    Mesaj mesaj;

    public LinieIteratie(Variabila variabila, int nrVariabile, int nrInecuatii, double[] coefInegal, double rhs, Mesaj mesaj) {
        this.mesaj = mesaj;
        coeficienti = new double[nrVariabile + nrInecuatii];
        // coef inegalit primii
        for (int i = 0; i < nrVariabile; i++) {
            coeficienti[i] = coefInegal[i];
            mesaj.write("LinieIteratie -constructor coef inegal coeficienti[" + i + "]=" + coeficienti[i]);
        }
        // coef inegalit (restul)
        for (int i = nrVariabile; i < nrVariabile + nrInecuatii; i++) {
            if (i == variabila.index) {
                coeficienti[i] = 1;
                mesaj.write("LinieIteratie -constructor coef adaugati coeficienti[" + i + "]=" + coeficienti[i]);
            } else {
                coeficienti[i] = 0;
                mesaj.write("LinieIteratie -constructor coef adaugati coeficienti[" + i + "]=" + coeficienti[i]);
            }
        }
        //nrCoef = 2 * nrCoef; // pun nrCoef TOTAL
        //mesaj.write("LinieIteratie -constructor nrCoef=" + nrCoef);
        this.variabila = variabila;
        mesaj.write("LinieIteratie -constructor variabila.coeficient=" + variabila.coeficient + " index=" + variabila.index);
        this.rhs = rhs;
        mesaj.write("LinieIteratie -constructor rhs=" + this.rhs);
        teta = 0;
        mesaj.write("LinieIteratie -constructor Gata");
    }

    public LinieIteratie(Variabila variabila, int nrVariabile, int nrInecuatii, Mesaj mesaj) {
        this.mesaj = mesaj;
        mesaj.write("LinieIteratie - constructor cu variabila coeficient=" + variabila.coeficient + " index=" + variabila.index);
        this.variabila = variabila;
        coeficienti = new double[nrVariabile + nrInecuatii];
        this.rhs = 0;
        teta = 0;
        mesaj.write("LinieIteratie - constructor cu variabila Gata");
    }

    public void calculTeta(int indexEnteringColumn) {
        if (coeficienti[indexEnteringColumn] == 0) {
            teta = Double.MAX_VALUE;
            mesaj.write("LinieIteratie.calculTeta - teta=Infinity");
        } else {
            teta = rhs / coeficienti[indexEnteringColumn];
            if (teta < 0) {
                // teta NU poate fi negativa
                teta = Double.MAX_VALUE;
            }
            mesaj.write("LinieIteratie.calculTeta - teta=" + teta);
        }
    }
}
