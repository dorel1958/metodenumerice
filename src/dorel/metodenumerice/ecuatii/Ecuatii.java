package dorel.metodenumerice.ecuatii;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;

public class Ecuatii {

    private final boolean isDesktop = true;

    public double metodaInjumInterval(Functie functie, double start, double end, int nrDecimals) {
        //determinarea solutiei ecuatiei f(x)=0 - unde functia este o clasa copil a clasei cFunctie din acest fisier
        //in interiorul segmentului start, end
        //cu o precizie de nrDecimals

        boolean aConvers = false;
        double eps = 1 / Math.pow(10, nrDecimals);
        double x = 0;
        double fx;
        double x1 = start;
        double x2 = end;
        double f1 = functie.valoare(x1);
        double f2 = functie.valoare(x2);
        // limitare nr pasi la 1000
        for (int i = 0; i < 1000; i++) {
            if (f1 * f2 < 0) {
                x = (x1 + x2) / 2;
                fx = functie.valoare(x);
                // daca abaterea e mica iese
                if (Math.abs(fx) < eps) {
                    aConvers = true;
                    break;
                }
                // seteaza noul interval
                if (f1 * fx < 0) {
                    x2 = x;
                    f2 = fx;
                } else {
                    x1 = x;
                    f1 = fx;
                }
            } else {
                mesajEroare("Ecuatii.metodaInjumInterval - Functia nu are radacini in intervalul start=" + x1 + " - end=" + x2);
                return 0;
            }
        }
        if (aConvers) {
            BigDecimal bd = new BigDecimal(x);
            bd = bd.setScale(nrDecimals, RoundingMode.HALF_UP);
            x = bd.doubleValue();
            return x;
        } else {
            mesajEroare("Ecuatii.metodaInjumInterval - Metoda nu a convers in 1000 pasi.");
            return 0;
        }
    }

    public double metodaCoardei(Functie functie, double start, double end, int nrDecimals) {
        //determinarea solutiei ecuatiei f(x)=0 - unde functia este o clasa copil a clasei cFunctie din acest fisier
        //in interiorul segmentului start, end
        //cu o precizie de nrDecimals

        boolean aConvers = false;
        double eps = 1 / Math.pow(10, nrDecimals);
        double x = 0;
        double fx;
        double x1 = start;
        double x2 = end;
        double f1 = functie.valoare(x1);
        double f2 = functie.valoare(x2);
        // limitare nr pasi la 1000
        for (int i = 0; i < 1000; i++) {
            if (f1 * f2 < 0) {
                x = x1 - f1 * (x2 - x1) / (f2 - f1);
                fx = functie.valoare(x);
                // daca abaterea e mica iese
                if (Math.abs(fx) < eps) {
                    aConvers = true;
                    break;
                }
                // seteaza noul interval
                if (f1 * fx < 0) {
                    x2 = x;
                    f2 = fx;
                } else {
                    x1 = x;
                    f1 = fx;
                }
            } else {
                mesajEroare("Ecuatii.metodaInjumInterval - Functia nu are radacini in intervalul start=" + x1 + " - end=" + x2);
                return 0;
            }
        }
        if (aConvers) {
            BigDecimal bd = new BigDecimal(x);
            bd = bd.setScale(nrDecimals, RoundingMode.HALF_UP);
            x = bd.doubleValue();
            return x;
        } else {
            mesajEroare("Ecuatii.metodaInjumInterval - Metoda nu a convers in 1000 pasi.");
            return 0;
        }

    }

    private void mesajEroare(String mesaj) {
        System.out.println(mesaj);
        if (isDesktop) {
            JOptionPane.showMessageDialog(null, mesaj);
        }
    }

}
