package dorel.metodenumerice.knapsack;

import dorel.metodenumerice.ordonare.OrdObject;

public class VariabilaX implements OrdObject {

    public int indexInitial;
    public double coefFunction;
    public double coefConstrain;
    public int valoareFinala;

    public VariabilaX(int indexInitial, double coefFunction, double coefConstrain) {
        this.indexInitial = indexInitial;
        this.coefFunction = coefFunction;
        this.coefConstrain = coefConstrain;
        valoareFinala=0;
    }

    @Override
    public double getValoareOrdonare() {
        if (coefConstrain !=0){
            return coefFunction/coefConstrain;
        } else {
            return Double.MAX_VALUE;
        }
    }

    @Override
    public int compareTo(OrdObject oo) {
        // return -1, 0, +1 pt oo: mai mic, egal, mai mare
        if (getValoareOrdonare() < oo.getValoareOrdonare()) {
            return -1;
        } else {
            if (getValoareOrdonare() == oo.getValoareOrdonare()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
