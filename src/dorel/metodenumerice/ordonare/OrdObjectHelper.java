package dorel.metodenumerice.ordonare;

public abstract class OrdObjectHelper implements OrdObject {

    @Override
    public abstract double getValoareOrdonare();

    // return -1, 0, +1 pt oo: mai mic, egal, mai mare
    @Override
    public int compareTo(OrdObject oo) {
        if (oo.getValoareOrdonare() < getValoareOrdonare()) {
            return -1;
        } else {
            if (oo.getValoareOrdonare() == getValoareOrdonare()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
