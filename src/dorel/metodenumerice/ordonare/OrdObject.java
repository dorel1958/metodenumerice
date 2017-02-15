package dorel.metodenumerice.ordonare;

public interface OrdObject {

    public double getValoareOrdonare();  // return valoarea double dupa care se ordoneaza

    public int compareTo(OrdObject oo);  // return -1, 0, +1 pt oo: mai mic, egal, mai mare
}
