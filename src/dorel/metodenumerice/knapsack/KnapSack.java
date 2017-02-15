package dorel.metodenumerice.knapsack;

import dorel.metodenumerice.FunctieLiniara;
import dorel.metodenumerice.InEcuatieLiniara;
import dorel.metodenumerice.ordonare.Ordonare;
import dorel.metodenumerice.utile.Mesaj;

public class KnapSack {

    boolean verbose;
    public int[] solutia;

    public KnapSack(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean calculOneConstrain(FunctieLiniara functieLiniara, InEcuatieLiniara inecuatie) {
        // functieLiniara=suma valorilor obiectelor
        // inecuatie suma maselor mai mica decat ...
        Mesaj mesaj = new Mesaj(verbose);
        int nrVariabile = functieLiniara.getNrDimensiuni();
        // test corectitudine date
        if (nrVariabile != inecuatie.getNrDimensiuni()) {
            mesaj.write("KnapSack.calculOneConstrain - dimensiunea functieLiniara (" + nrVariabile + ") si dimensiunea inecuatie (" + inecuatie.getNrDimensiuni() + ") NU sunt egale.");
            return false;
        }
        // ordoneaza dupa (coef in obj function)/(coef in constraint)
        VariabilaX[] variabileX = new VariabilaX[nrVariabile];
        for (int i = 0; i < nrVariabile; i++) {
            variabileX[i] = new VariabilaX(i, functieLiniara.getCoef(i), inecuatie.getCoefInecuatie(i));
        }
        Ordonare.ordoneazaArray(variabileX, true);
        //
        FunctieLiniara func = functieLiniara.cloneFunc();
        InEcuatieLiniara inec = inecuatie.cloneInEc();
        for (int j = 0; j < variabileX.length; j++) {
            //mesaj.write("j="+j);
            double valDouble = inec.getValoare() / variabileX[j].coefConstrain;
            //double LPOpt=functieLiniara.getValueIndexX(variabileX[0].indexInitial,valDouble1);
            //mesaj.write("variabileX[j].indexInitial="+variabileX[j].indexInitial);
            //mesaj.write("variabileX[j].coefConstrain="+variabileX[j].coefConstrain);
            //mesaj.write("variabileX[j].coefFunction="+variabileX[j].coefFunction);
            //mesaj.write("valDouble="+valDouble);
            int valIntMax = (int) Math.floor(valDouble);
            //mesaj.write("valIntMax="+valIntMax);
            if (valIntMax > 0) {
                int valMax = 0;
                double zMax = 0;
                for (int i = 0; i <= valIntMax; i++) {
                    double z = func.getValueIndexX(variabileX[j].indexInitial, i);
                    if (z > zMax) {
                        zMax = z;
                        valMax = i;
                    }
                }
                if (valMax > 0) {
                    variabileX[j].valoareFinala = valMax;
                    func.reduDimensiuni(variabileX[j].indexInitial, valMax);
                    inec.reduDimensiuni(variabileX[j].indexInitial, valMax);
                }
            }
        }
        // extrage solutia
//        for (int j = 0; j < variabileX.length; j++) {
//            mesaj.write("j="+j);
//            mesaj.write("variabileX[j].coefConstrain="+variabileX[j].coefConstrain);
//            mesaj.write("variabileX[j].valoareFinala="+variabileX[j].valoareFinala);
//            mesaj.write("");
//        }
        
        solutia = new int[nrVariabile];
        for (int j = 0; j < variabileX.length; j++) {
            for (int i=0; i<variabileX.length; i++){
                if (variabileX[i].indexInitial==j){
                    solutia[j]=variabileX[i].valoareFinala;
                }
            }
        }
        //
        // Nu observa solutiile exacte Ex: 2*6+8, ar trebui sa abanoneze solutia LP pentru cea IP
        //
        mesaj.writeArray(solutia);
        mesaj.write("functia="+functieLiniara.getValue(solutia));
        return true;
    }

    public double[] getSolutiaDouble(){
        double[] solutiaDouble = new double[solutia.length];
        for (int i=0; i<solutia.length; i++){
            solutiaDouble[i]=(double)solutia[i];
        }
        return solutiaDouble;
    }
}

// tai bucati lungi de 9,8,7,6 din stocul de 20 lungime
//
// Maximize 1/2a + 1/2b + 1/2c + 1/3d  (>1)  (Objective function)
// 9a + 8b + 7c + 6d <= 20
// a,b,c,d,>=0 AND integers
//
// pas 1: ordonez dupa (coef in obj function)/(coef in constraint)
