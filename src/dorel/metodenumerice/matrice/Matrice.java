package dorel.metodenumerice.matrice;

import javax.swing.JOptionPane;

public class Matrice {

    public double[][] a;

    //<editor-fold defaultstate="collapsed" desc="Get Set">
    public int getNrRows() {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Matrice.getDimensionX - matricea a NU a fost definita.");
            return 0;
        }
        return a.length;
    }

    public int getNrColumns() {
        if (a == null) {
            JOptionPane.showMessageDialog(null, "Matrice.getDimensionY - matricea a NU a fost definita.");
            return 0;
        }
        if (a[0] == null) {
            JOptionPane.showMessageDialog(null, "Matrice.getDimensionY - vectorul a[0] NU a fost definit.");
            return 0;
        }
        return a[0].length;
    }

    public void setVectorRow(int index, Vector v) {
        System.arraycopy(v.a, 0, a[index], 0, getNrColumns());
    }

    public Vector getVectorRow(int index) {
        double[] v = new double[getNrColumns()];
//        for (int i = 0; i < getNrColumns(); i++) {
//            v[i] = a[index][i];
//        }
        System.arraycopy(a[index], 0, v, 0, getNrColumns());
        return new Vector(v);
    }

    public void setVectorColumn(int index, Vector v) {
        for (int i = 0; i < getNrRows(); i++) {
            a[i][index] = v.a[i];
        }
    }

    public Vector getVectorColumn(int index) {
        double[] v = new double[getNrRows()];
        for (int i = 0; i < getNrRows(); i++) {
            v[i] = a[i][index];
        }
        return new Vector(v);
    }

    public void setElement(int row, int column, double valoare) {
        a[row][column] = valoare;
    }

    public double getElement(int row, int column) {
        return a[row][column];
    }

    public Matrice getMatriceaTranspusa() {
        Matrice transpusa = new Matrice(getNrColumns(), getNrRows());
        for (int i = 0; i < getNrRows(); i++) {
            for (int j = 0; j < getNrColumns(); j++) {
                transpusa.setElement(j, i, a[i][j]);
            }
        }
        return transpusa;
    }
    //</editor-fold>

    public Matrice(double[][] a) {
        // a[rows][coluumns]
        this.a = a;
    }

    public Matrice(int rows, int columns) {
        // a[rows][coluumns]
        a = new double[rows][columns];
    }

    //<editor-fold defaultstate="collapsed" desc="Operatii Statice">
    public static Matrice adunareMatrici(Matrice matriceA, Matrice matriceB) {
        int nrRows = matriceA.getNrRows();
        int nrColumns = matriceA.getNrColumns();
        if (matriceB.getNrRows() == nrRows && matriceB.getNrColumns() == nrColumns) {
            double[][] c = new double[nrRows][nrColumns];
            for (int i = 0; i < nrRows; i++) {
                for (int j = 0; j < nrColumns; j++) {
                    c[i][j] = matriceA.a[i][j] + matriceB.a[i][j];
                }
            }
            return new Matrice(c);
        } else {
            JOptionPane.showMessageDialog(null, "Matrice.adunareMatrici - Cele doua matrici nu au dimensiuni egale.");
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Operatii">
    public boolean isEqual(Matrice matr) {
        if (getNrRows() == matr.getNrRows()) {
            if (getNrColumns() == matr.getNrColumns()) {
                for (int i = 0; i < getNrRows(); i++) {
                    for (int j = 0; j < getNrColumns(); j++) {
                        if (a[i][j] != matr.a[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void adunCuMatricea(Matrice matriceB) {
        int nrRows = getNrRows();
        int nrColumns = getNrColumns();
        if (matriceB.getNrRows() == nrRows && matriceB.getNrColumns() == nrColumns) {
            for (int i = 0; i < nrRows; i++) {
                for (int j = 0; j < nrColumns; j++) {
                    a[i][j] = a[i][j] + matriceB.a[i][j];
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matrice.adunCuMatricea - Matricea de adumat nu are dimensiuni egale cu matricea curenta.");
        }
    }

    public void inmultescCuScalar(double lambda) {
        for (int i = 0; i < getNrRows(); i++) {
            for (int j = 0; j < getNrColumns(); j++) {
                a[i][j] = a[i][j] * lambda;
            }
        }
    }

    public Matrice inmultescCuMatricea(Matrice matriceB) {
        int nrRows1 = getNrRows();
        int nrColumns1 = getNrColumns();
        int nrRows2 = matriceB.getNrRows();
        int nrColumns2 = matriceB.getNrColumns();
        if (nrRows2 == nrColumns1) {
            Matrice matriceC = new Matrice(nrRows1, nrColumns2);
            for (int i = 0; i < nrRows1; i++) {
                for (int j = 0; j < nrColumns2; j++) {
//                    Vector vectorA=getVectorRow(i);
//                    Vector vectorB=matriceB.getVectorColumn(j);
//                    double prodScalar=vectorA.produsScalarCuVectorul(vectorB);
                    matriceC.setElement(i, j, getVectorRow(i).produsScalarCuVectorul(matriceB.getVectorColumn(j)));
                }
            }
            return matriceC;
        } else {
            JOptionPane.showMessageDialog(null, "Matrice.inmultescCuMatricea - Matricea de inmultit nu are dimensiuni corespondente egale cu matricea curenta.");
        }
        return null;
    }
    //</editor-fold>

    public void printText() {
        boolean ePrima;
        System.out.println("Matrice.printText");
        for (int i = 0; i < getNrRows(); i++) {
            ePrima = true;
            for (int j = 0; j < getNrColumns(); j++) {
                if (ePrima) {
                    ePrima = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(a[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
