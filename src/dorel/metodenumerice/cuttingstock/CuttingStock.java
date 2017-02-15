package dorel.metodenumerice.cuttingstock;

import dorel.metodenumerice.FunctieLiniara;
import dorel.metodenumerice.InEcuatieLiniara;
import dorel.metodenumerice.SistemEcuatiiLiniare;
import dorel.metodenumerice.knapsack.KnapSack;
import dorel.metodenumerice.utile.Mesaj;
import dorel.metodenumerice.matrice.Matrice;
import dorel.metodenumerice.matrice.Vector;
import javax.swing.JOptionPane;

public class CuttingStock {

    boolean verbose;

    public CuttingStock(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean calcul(double[] lungItems, int[] nrItems, double lungBare) {
        Mesaj mesaj = new Mesaj(verbose);
        SistemEcuatiiLiniare sistem = new SistemEcuatiiLiniare();
        if (lungItems.length != nrItems.length) {
            JOptionPane.showMessageDialog(null, "CuttingStock.calcul - nr lungItems != nr nrItems.");
            return false;
        }
        //
        // Start
        // alcatuiesc matricea initiala (diagonala) - tai NUMAI iteme identice din fiecare bara:
        // pas1 - initializez matricea - tai NUMAI bucati identice
        // pe verticala apar patternurile initiale - diagonala 2, 2, 2, 3
        Matrice mat = new Matrice(lungItems.length, lungItems.length);
        for (int i = 0; i < lungItems.length; i++) {
            mat.setElement(i, i, Math.floor(lungBare / lungItems[i]));
        }
        
        // initializex vectorul cB = [ 1 1 1 1]
        double[] cB = new double[lungItems.length];
        for (int i = 0; i < lungItems.length; i++) {
            cB[i] = 1;
        }
        int pas=0;
        while (true) {
            pas+=1;
            mesaj.write("start iteratie pas="+pas);
            mat.printText();
            //
            // rezolv sistemul de ecuatii: y*B=CB                  [2 0 0 0]
            //                                                     |0 2 0 0|
            //                                    [y0, y1, y2, y3] |0 0 2 0| = [ 1 1 1 1]
            //                                                     [0 0 0 3]
            //
            // determin y []
            // transpusa pt ca am vectori linie x si b!!!
            double[] functionCoef = sistem.gaussMethod(mat.getMatriceaTranspusa().a, cB, 4);
            mesaj.write("functionCoef");
            mesaj.writeArray(functionCoef);
            //
            // aplic knapsack pentru a gasi un pattern mai bun -> enteringPattern
            FunctieLiniara functieLiniara = new FunctieLiniara(functionCoef);
            //
            // inecuatie == suma lungimilor barelor mai mica dacat 20
            FunctieLiniara functieLiniaraI = new FunctieLiniara(lungItems);
            InEcuatieLiniara inecuatie = new InEcuatieLiniara(functieLiniaraI, lungBare, InEcuatieLiniara.Relatie.MAI_MIC_EGAL);
            //
            KnapSack knapSack = new KnapSack(true);
            knapSack.calculOneConstrain(functieLiniara, inecuatie);
            Vector enteringPattern = new Vector(knapSack.getSolutiaDouble());
            enteringPattern.printText();
            //
            // determin leavingPattern
            double[] pj_ = sistem.gaussMethod(mat.a, enteringPattern.a, 2);
            //mesaj.write("Solutia sistemului");
            //mesaj.writeArray(pj_);
            // calcul teta - the gain
            double[] teta = new double[lungItems.length];
            double tetaMin = Double.MAX_VALUE;
            int indexLeavingPattern = -1;
            for (int i = 0; i < lungItems.length; i++) {
                if (pj_[i] > 0) {
                    teta[i] = nrItems[i] / mat.getElement(i, i) / pj_[i];
                } else {
                    teta[i] = Double.MAX_VALUE;
                }
                if (teta[i] < tetaMin) {
                    tetaMin = teta[i];
                    indexLeavingPattern = i;
                }
            }
            mesaj.write("teta");
            mesaj.writeArray(teta);
            mesaj.write("tetaMin="+tetaMin);
            mesaj.write("indexLeavingPattern="+indexLeavingPattern);
            //
            if (indexLeavingPattern==-1 || pas==3){
                return true;
            }
            mat.setVectorColumn(indexLeavingPattern, enteringPattern);
            mat.printText();
        }
    }
}
