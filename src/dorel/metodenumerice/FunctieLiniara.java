package dorel.metodenumerice;

import dorel.metodenumerice.utile.Mesaj;
import javax.swing.JOptionPane;

public class FunctieLiniara {
    
    private double[] a;  // coeficientii functiei a[0] * x[0] + a[1] * x[1] + ...
    private double constanta;
    Mesaj mesaj;

    //<editor-fold defaultstate="collapsed" desc="Get Set">
    public int getNrDimensiuni() {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "FunctieLiniara.getNrDimensiuni - coeficientii functiei a NU au fost definiti.");
            return 0;
        }
        return a.length;
    }
    
    public double getValue(double[] x) {
        double suma = constanta;
        if (x.length == getNrDimensiuni()) {
            for (int i = 0; i < getNrDimensiuni(); i++) {
                suma += a[i] * x[i];
            }
        } else {
            JOptionPane.showMessageDialog(null, "FunctieLiniara.getValue - Dimensiunile a(" + getNrDimensiuni() + ") si x(" + x.length + ") NU sunt egale.");
        }
        return suma;
    }
    
    public double getValue(int[] x) {
        double suma = constanta;
        if (x.length == getNrDimensiuni()) {
            for (int i = 0; i < getNrDimensiuni(); i++) {
                suma += a[i] * x[i];
            }
        } else {
            JOptionPane.showMessageDialog(null, "FunctieLiniara.getValue - Dimensiunile a(" + getNrDimensiuni() + ") si x(" + x.length + ") NU sunt egale.");
        }
        return suma;
    }
    
    public double getValueIndexX(int index, double x) {
        double suma = constanta;
        for (int i = 0; i < getNrDimensiuni(); i++) {
            if (i == index) {
                suma += a[i] * x;
            }
        }
        return suma;
    }
    
    public double getCoef(int index) {
        return a[index];
    }
    
    public void setCoef(int index, double valoare) {
        if (index < getNrDimensiuni()) {
            a[index] = valoare;
        } else {
            JOptionPane.showMessageDialog(null, "FunctieLiniara.setCoef -indexul e mai mare dacat  dimensiunea a(" + getNrDimensiuni() + ") .");
        }
    }
    //</editor-fold>

    public FunctieLiniara(double[] a) {
        this.a = a;
        constanta = 0;
        mesaj = new Mesaj(true);
    }
    
    public FunctieLiniara cloneFunc() {
        double[] aTempo = new double[getNrDimensiuni()];
        System.arraycopy(a, 0, aTempo, 0, getNrDimensiuni());
        FunctieLiniara functie = new FunctieLiniara(aTempo);
        return functie;
    }
    
    public void reduDimensiuni(int index, double valX) {
        constanta += getValueIndexX(index, valX);
        a[index]=0;
        //mesaj.write("FunctieLiniara.reduDimensiuni constanta=" + constanta);
        //mesaj.writeArray(a);
    }
    
    public String getString(){
        String raspuns="FunctieLiniara: \n";
        raspuns+="constanta="+constanta+"\n";
            boolean ePrima=true;
            raspuns+="a[]: ";
            for (int i=0; i<a.length; i++){
                if (ePrima){
                    ePrima=false;
                } else{
                    raspuns+=(", ");
                }
                raspuns+=a[i];
            }
        raspuns+="\n";
        return raspuns;
    }
}
