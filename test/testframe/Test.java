package testframe;
// D:\Documentatie\MetodeNumerice\Metode numerice in inginerie.pdf

import dorel.metodenumerice.FunctieLiniara;
import dorel.metodenumerice.InEcuatieLiniara;
import dorel.metodenumerice.ecuatii.Ecuatii;
import dorel.metodenumerice.ecuatii.Functie;
import dorel.metodenumerice.ordonare.OrdObject;
import dorel.metodenumerice.ordonare.OrdObjectHelper;
import dorel.metodenumerice.ordonare.Ordonare;
import dorel.metodenumerice.Regresie;
import dorel.metodenumerice.SistemEcuatiiLiniare;
import dorel.metodenumerice.cuttingstock.CuttingStock;
import dorel.metodenumerice.knapsack.KnapSack;
import dorel.metodenumerice.simplex.Iteratie;
import dorel.metodenumerice.simplex.Simplex;
import javax.swing.JOptionPane;

public class Test {

    // <editor-fold defaultstate="collapsed" desc="testeSistemEcLiniare">      
    public boolean testeSistemEcLiniare() {
        boolean raspuns = true;
        if (!test1SistemEcLiniare2_2()) {
            raspuns = false;
        }
        if (!test1SistemEcLiniare3_3()) {
            raspuns = false;
        }
        return raspuns;
    }

    private boolean test1SistemEcLiniare2_2() {
        SistemEcuatiiLiniare sle = new SistemEcuatiiLiniare();
        double[][] a = new double[2][2];
        a[0][0] = 2;
        a[0][1] = -3;
        a[1][0] = 4;
        a[1][1] = 5;
        double[] b = new double[2];
        b[0] = 5;
        b[1] = -1;
        int nrDecimals = 2;
        double[] x = sle.gaussMethod(a, b, nrDecimals);
        if (x != null) {
            //System.out.println("Solutia:");
            //sle.printMatrice1(x);
            // solutia: 1, -1
        }
        return x[0] == 1 && x[1] == -1;
    }

    private boolean test1SistemEcLiniare3_3() {
        SistemEcuatiiLiniare sle = new SistemEcuatiiLiniare();
        double[][] a = new double[3][3];
        a[0][0] = 1;
        a[0][1] = 1;
        a[0][2] = 2;
        a[1][0] = 2;
        a[1][1] = -1;
        a[1][2] = 2;
        a[2][0] = 4;
        a[2][1] = 1;
        a[2][2] = 4;
        double[] b = new double[3];
        b[0] = -1;
        b[1] = -4;
        b[2] = -2;
        int nrDecimals = 2;
        double[] x = sle.gaussMethod(a, b, nrDecimals);
        if (x != null) {
            //System.out.println("Solutia:");
            //sle.printMatrice1(x);
            // solutia: 1, 2, -2
        }
        return x[0] == 1 && x[1] == 2 && x[2] == -2;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="testeRegresie">
    public boolean testeRegresie() {
        boolean raspuns = true;
        if (!testRegresieExact()) {
            raspuns = false;
        }
        if (!testRegresieDreapta()) {
            raspuns = false;
        }
        if (!testRegresieParabola()) {
            raspuns = false;
        }
        return raspuns;
    }

    private boolean testRegresieExact() {
        Regresie regresie = new Regresie();
        double[][] puncte = new double[4][2];
        puncte[0][0] = 0;
        puncte[0][1] = 0;

        puncte[1][0] = 1;
        puncte[1][1] = 1;

        puncte[2][0] = 2;
        puncte[2][1] = 2;

        puncte[3][0] = 3;
        puncte[3][1] = 3;

        double[] coef = regresie.regresiePolinom(puncte, 1, 2);
        return (coef[0] == 0 && coef[1] == 1);
    }

    private boolean testRegresieDreapta() {
        Regresie regresie = new Regresie();
        double[][] puncte = new double[5][2];
        puncte[0][0] = 1;
        puncte[0][1] = -1;

        puncte[1][0] = 2;
        puncte[1][1] = 0;

        puncte[2][0] = 3;
        puncte[2][1] = 3;

        puncte[3][0] = 4;
        puncte[3][1] = 3;

        puncte[4][0] = 5;
        puncte[4][1] = 5;

        double[] coef = regresie.regresiePolinom(puncte, 1, 1);
        return (coef[0] == -2.5 && coef[1] == 1.5);
    }

    private boolean testRegresieParabola() {
        Regresie regresie = new Regresie();
        double[][] puncte = new double[5][2];
        puncte[0][0] = 1;
        puncte[0][1] = -1;

        puncte[1][0] = 2;
        puncte[1][1] = 0;

        puncte[2][0] = 3;
        puncte[2][1] = 3;

        puncte[3][0] = 4;
        puncte[3][1] = 3;

        puncte[4][0] = 5;
        puncte[4][1] = 5;

        double[] coef = regresie.regresiePolinom(puncte, 2, 2);
        // -685/218 = -3.14
        // 447/218 = 2.05
        // -20/218 = -0.09
        return (coef[0] == -3.0 && coef[1] == 1.93 && coef[2] == -0.07);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="testeEcuatii">
    public boolean testeEcuatii() {
        boolean raspuns = true;
        if (!testEcuatieExact()) {
            raspuns = false;
        }
        if (!testEcuatieExact2()) {
            raspuns = false;
        }
        if (!testEcuatie1()) {
            raspuns = false;
        }
        if (!testEcuatie2()) {
            raspuns = false;
        }
        if (!testEcuatieExactc()) {
            raspuns = false;
        }
        if (!testEcuatieExact2c()) {
            raspuns = false;
        }
        if (!testEcuatie1c()) {
            raspuns = false;
        }
        if (!testEcuatie2c()) {
            raspuns = false;
        }
        return raspuns;
    }

    private boolean testEcuatieExact() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaInjumInterval(new Functia(), -5, 5, 2);
        return (x == 0);
    }

    private boolean testEcuatieExact2() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaInjumInterval(new Functie() {
            @Override
            public double valoare(double x) {
                return x * x - 1;
            }
        }, 0, 5, 2);
        return (x == 1);
    }

    private boolean testEcuatie1() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaInjumInterval(new FunctiaLog(), 0.9, 2, 4);
        // precizia este la valoarea functiei nu la X!!!
        return (Math.abs(x - 1) < 0.01);
    }

    private boolean testEcuatie2() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaInjumInterval(new FunctiaLog(), 3, 8, 4);
        // precizia este la valoarea functiei nu la X!!!
        return (Math.abs(x - 5) < 0.01);
    }

    private boolean testEcuatieExactc() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaCoardei(new Functia(), -5, 5, 2);
        return (x == 0);
    }

    private boolean testEcuatieExact2c() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaCoardei(new Functie() {
            @Override
            public double valoare(double x) {
                return x * x - 1;
            }
        }, 0, 5, 2);
        return (x == 1);
    }

    private boolean testEcuatie1c() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaCoardei(new FunctiaLog(), 0.9, 2, 4);
        // precizia este la valoarea functiei nu la X!!!
        return (Math.abs(x - 1) < 0.01);
    }

    private boolean testEcuatie2c() {
        Ecuatii ec = new Ecuatii();
        double x = ec.metodaCoardei(new FunctiaLog(), 3, 8, 4);
        // precizia este la valoarea functiei nu la X!!!
        return (Math.abs(x - 5) < 0.01);
    }
    // </editor-fold>

    public void testOrdonare() {
        // cu clasa
        OrdObject[] aOrdObject = new COO[5];
        aOrdObject[0] = new COO(3);
        aOrdObject[1] = new COO(4);
        aOrdObject[2] = new COO(0);
        aOrdObject[3] = new COO(0);
        aOrdObject[4] = new COO(2);

        //Ordonare o = new Ordonare();
        Ordonare.ordoneazaArray(aOrdObject, true);
        String a = "";
        Ordonare.ordoneazaArray(aOrdObject, false);
        a = "";

        //cu interfata
        OrdObject[] aOrdObject2 = new COO2[5];
        aOrdObject[0] = new COO2(3);
        aOrdObject[1] = new COO2(4);
        aOrdObject[2] = new COO2(0);
        aOrdObject[3] = new COO2(0);
        aOrdObject[4] = new COO2(2);

        //Ordonare o = new Ordonare();
        Ordonare.ordoneazaArray(aOrdObject2, true);
        a = "";
        Ordonare.ordoneazaArray(aOrdObject2, false);
        a = "";
    }

//    public void testSimplex(){
//        double[][] a = new double[3][5];
//        
////        Matrice matrice = new Matrice(a);
////        int x=matrice.getRows();
////        int y=matrice.getColumns();
//        a[0][0]=1;
//        a[0][1]=2;
//        a[0][2]=3;
//        a[0][3]=4;
//        a[0][4]=5;
//        a[1][0]=6;
//        a[1][1]=7;
//        a[1][2]=8;
//        a[1][3]=9;
//        a[1][4]=10;
//        a[2][0]=11;
//        a[2][1]=12;
//        a[2][2]=13;
//        a[2][3]=14;
//        a[2][4]=15;
//        Matrice mat1=new Matrice(a);
//        double[][] b = new double[3][5];
//        
////        Matrice matrice = new Matrice(a);
////        int x=matrice.getRows();
////        int y=matrice.getColumns();
//        b[0][0]=1;
//        b[0][1]=2;
//        b[0][2]=3;
//        b[0][3]=4;
//        b[0][4]=5;
//        b[1][0]=6;
//        b[1][1]=7;
//        b[1][2]=8;
//        b[1][3]=9;
//        b[1][4]=10;
//        b[2][0]=11;
//        b[2][1]=12;
//        b[2][2]=13;
//        b[2][3]=14;
//        b[2][4]=16;
//        Matrice mat2=new Matrice(b);
//        boolean egale=mat1.isEqual(mat2);
//        mat1.printText();
//    }
//    public void testSimplex(){
//        double[][] a = new double[2][3];
//        
//        a[0][0]=1;
//        a[0][1]=0;
//        a[0][2]=2;
//        a[1][0]=-1;
//        a[1][1]=3;
//        a[1][2]=1;
//        Matrice mat1=new Matrice(a);
//        mat1.printText();
//        
//        double[][] b = new double[3][2];
//        b[0][0]=3;
//        b[0][1]=1;
//        b[1][0]=2;
//        b[1][1]=1;
//        b[2][0]=1;
//        b[2][1]=0;
//        Matrice mat2=new Matrice(b);
//        mat2.printText();
//        
//        Matrice transpusa=mat2.getMatriceaTranspusa();
//        transpusa.printText();
//
//        Matrice mat3 = mat1.inmultescCuMatricea(mat2);
//        mat3.printText();
//    }
    public void testSimplex1() {
        //Exemplul 1
        // Maximize Z = 6x1 + 5x2
        // Subject to
        // x1 + x2 <= 5
        // 3x1 + 2x2 <= 12
        // x1, x2 >= 0
        double[] coefZ = new double[2];
        coefZ[0] = 6;
        coefZ[1] = 5;

        double[][] coefInegal = new double[2][2];
        coefInegal[0][0] = 1;
        coefInegal[0][1] = 1;
        coefInegal[1][0] = 3;
        coefInegal[1][1] = 2;

        double[] valInegal = new double[2];
        valInegal[0] = 5;
        valInegal[1] = 12;

        Simplex simplex = new Simplex(true);
        if (simplex.calcul(coefZ, coefInegal, valInegal, 2)) {
            Iteratie solutia = simplex.getIteratiaSolutie();
        }
// solutia: x1=2, X2=3 Z=27
    }

    public void testSimplex() {
        //Exemplul 2
        // minimize Z = 6x1 + 8x2
        // Subject to
        // x1 + x2 <= 10
        // 2x1 + 3x2 <= 25
        // x1 + 5x2 <= 35
        // x1, x2 >= 0
        double[] coefZ = new double[2];
        coefZ[0] = 6;
        coefZ[1] = 8;

        double[][] coefInegal = new double[3][2];
        coefInegal[0][0] = 1;
        coefInegal[0][1] = 1;
        coefInegal[1][0] = 2;
        coefInegal[1][1] = 3;
        coefInegal[2][0] = 1;
        coefInegal[2][1] = 5;

        double[] valInegal = new double[3];
        valInegal[0] = 10;
        valInegal[1] = 25;
        valInegal[2] = 35;

        Simplex simplex = new Simplex(true);
        if (simplex.calcul(coefZ, coefInegal, valInegal, 2)) {
            Iteratie solutia = simplex.getIteratiaSolutie();
        }
        // solutia: x1=5, X2=5 Z=70
    }

    public void testKnapSack1() {
        //JOptionPane.showMessageDialog(null, "Merge");
        // am pornit cu o matrice diagonala  - tai NUMAI iteme identice - numarul de cate ori incape bara in lungimea de 20
        // patternurile sunt pe verticala
        //
        // |2 0 0 0|
        // |0 2 0 0|
        // |0 0 2 0|
        // |0 0 0 3|

        //  consumul de bare pentru a produce 1 item == 1 / numarul de cate ori incape itemul in lungimea barei
        double[] functionCoef = new double[4];
        functionCoef[0] = 0.5;
        functionCoef[1] = 0.5;
        functionCoef[2] = 0.5;
        functionCoef[3] = 1.0 / 3;
        FunctieLiniara functieLiniara = new FunctieLiniara(functionCoef);
        //
        // suma lungimilor barelor mai mica dacat 20
        double[] constrainCoef = new double[4];
        constrainCoef[0] = 9;
        constrainCoef[1] = 8;
        constrainCoef[2] = 7;
        constrainCoef[3] = 6;
        FunctieLiniara functieLiniaraI = new FunctieLiniara(constrainCoef);
        InEcuatieLiniara inecuatie = new InEcuatieLiniara(functieLiniaraI, 20, InEcuatieLiniara.Relatie.MAI_MIC_EGAL);
        //
        KnapSack knapSack = new KnapSack(true);
        if (knapSack.calculOneConstrain(functieLiniara, inecuatie)) {
            // solutia corecta: Array int: 0, 0, 2, 1
            JOptionPane.showMessageDialog(null, "Corect");
        }
    }
    
    public void testKnapSack2() {
        //JOptionPane.showMessageDialog(null, "Merge");
        double[] functionCoef = new double[5];
        functionCoef[0] = 3;
        functionCoef[1] = 2;
        functionCoef[2] = 1;
        functionCoef[3] = 2;
        functionCoef[4] = 3;
        FunctieLiniara functieLiniara = new FunctieLiniara(functionCoef);
        //
        double[] constrainCoef = new double[5];
        constrainCoef[0] = 2;
        constrainCoef[1] = 3;
        constrainCoef[2] = 2;
        constrainCoef[3] = 3;
        constrainCoef[4] = 2;
        FunctieLiniara functieLiniaraI = new FunctieLiniara(constrainCoef);
        InEcuatieLiniara inecuatie = new InEcuatieLiniara(functieLiniaraI, 8, InEcuatieLiniara.Relatie.MAI_MIC_EGAL);
        //
        KnapSack knapSack = new KnapSack(true);
        if (knapSack.calculOneConstrain(functieLiniara, inecuatie)) {
            // solutia corecta: Array int: 1, 0, 1, 0
            JOptionPane.showMessageDialog(null, "Corect");
        }
    }
    
    public void testKnapSack3() {
        //JOptionPane.showMessageDialog(null, "Merge");
        // functieLiniara=suma valorilor obiectelor
        double[] functionCoef = new double[3];
        functionCoef[0] = 60;
        functionCoef[1] = 100;
        functionCoef[2] = 120;
        FunctieLiniara functieLiniara = new FunctieLiniara(functionCoef);
        //
        // inecuatie suma maselor mai mica decat ...
        double[] constrainCoef = new double[3];
        constrainCoef[0] = 10;
        constrainCoef[1] = 20;
        constrainCoef[2] = 30;
        FunctieLiniara functieLiniaraI = new FunctieLiniara(constrainCoef);
        InEcuatieLiniara inecuatie = new InEcuatieLiniara(functieLiniaraI, 50, InEcuatieLiniara.Relatie.MAI_MIC_EGAL);
        //
        KnapSack knapSack = new KnapSack(true);
        if (knapSack.calculOneConstrain(functieLiniara, inecuatie)) {
            // solutia corecta: Array int: 1, 0, 1, 0
            JOptionPane.showMessageDialog(null, "Corect");
        }
    }
    public void testKnapSack() {
        //JOptionPane.showMessageDialog(null, "Merge");
        // am pornit cu o matrice diagonala  - tai NUMAI iteme identice - numarul de cate ori incape bara in lungimea de 20
        // patternurile sunt pe verticala
        //
        // |2 0 0 0|
        // |0 2 0 0|
        // |0 0 2 0|
        // |0 0 0 3|

        //  consumul de bare pentru a produce 1 item == 1 / numarul de cate ori incape itemul in lungimea barei
        double[] functionCoef = new double[4];
        functionCoef[0] = 0.5;
        functionCoef[1] = 0.5;
        functionCoef[2] = 1.0 / 3;
        functionCoef[3] = 1.0 / 3;
        FunctieLiniara functieLiniara = new FunctieLiniara(functionCoef);
        //
        // suma lungimilor barelor mai mica dacat 20
        double[] constrainCoef = new double[4];
        constrainCoef[0] = 9;
        constrainCoef[1] = 8;
        constrainCoef[2] = 7;
        constrainCoef[3] = 6;
        FunctieLiniara functieLiniaraI = new FunctieLiniara(constrainCoef);
        InEcuatieLiniara inecuatie = new InEcuatieLiniara(functieLiniaraI, 20, InEcuatieLiniara.Relatie.MAI_MIC_EGAL);
        //
        KnapSack knapSack = new KnapSack(true);
        if (knapSack.calculOneConstrain(functieLiniara, inecuatie)) {
            // solutia corecta: Array int: 0, 1, 0, 2
            JOptionPane.showMessageDialog(null, "Corect");
        }
    }

    public void testCutting() {
        //JOptionPane.showMessageDialog(null, "Merge");
        CuttingStock cuttingStock = new CuttingStock(true);
        double[] lungItems = new double[4];
        lungItems[0]=9;
        lungItems[1]=8;
        lungItems[2]=7;
        lungItems[3]=6;
        int[] nrItems = new int[4];
        nrItems[0]=511;
        nrItems[1]=301;
        nrItems[2]=263;
        nrItems[3]=383;
        double lungBare=20;
        
        cuttingStock.calcul(lungItems, nrItems, lungBare);
    }
}

//<editor-fold defaultstate="collapsed" desc="Functia">
class Functia implements Functie {

    @Override
    public double valoare(double x) {
        return x;
    }
}

class FunctiaLog implements Functie {

    @Override
    public double valoare(double x) {
        return Math.log10(2 * x * x - x + 1) - Math.log10(x * x + 5 * x - 4);
        //1, 5
    }
}

class COO extends OrdObjectHelper {

    public COO(double valoare) {
        this.valoare = valoare;
    }

    public double valoare;

    @Override
    public double getValoareOrdonare() {
        return valoare;
    }
}

class COO2 implements OrdObject {

    public COO2(double valoare) {
        this.valoare = valoare;
    }

    public double valoare;

    @Override
    public double getValoareOrdonare() {
        return valoare;
    }

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
//</editor-fold>
