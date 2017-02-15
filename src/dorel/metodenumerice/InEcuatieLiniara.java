package dorel.metodenumerice;

import dorel.metodenumerice.utile.Mesaj;
import javax.swing.JOptionPane;

public class InEcuatieLiniara {

    public enum Relatie {

        MAI_MARE,
        MAI_MARE_EGAL,
        EGAL,
        MAI_MIC_EGAL,
        MAI_MIC
    }

    private final FunctieLiniara f;  // functia liniara  a[0] * x[0] + a[1] * x[1] + ...
    private double b;  // valoarea din dreapta
    private final Relatie inegalitate;
    Mesaj mesaj;

    public int getNrDimensiuni() {
        if (f == null) {
            JOptionPane.showMessageDialog(null, "Inecuatie.getNrDimensiuni - functia a NU au fost definita.");
            return 0;
        }
        return f.getNrDimensiuni();
    }

    public double getCoefInecuatie(int index){
        return f.getCoef(index);
    }
    
    public double getValoare(){
        return b;
    }

    public InEcuatieLiniara(FunctieLiniara f, double b, Relatie inegalitate) {
        this.f = f;
        this.b = b;
        this.inegalitate = inegalitate;
        mesaj=new Mesaj(true);
    }

    public InEcuatieLiniara cloneInEc(){
        FunctieLiniara newf=f.cloneFunc();
        InEcuatieLiniara inecuatie = new InEcuatieLiniara(newf, b, inegalitate);
        return inecuatie;
    }
    
    public void reduDimensiuni(int index, double valX){
        b-=f.getValueIndexX(index,valX);
        f.reduDimensiuni(index, valX);
        //mesaj.write("InEcuatieLiniara.reduDimensiuni");
        //mesaj.write("new b="+b);
        //mesaj.write("f redusa: "+f.getString());
    }

}
