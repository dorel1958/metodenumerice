package dorel.metodenumerice.ordonare;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Ordonare {

    public static void ordoneazaArray(OrdObject[] objectsArray, boolean crescator) {
        boolean inverseaza;
        for (int i = 0; i < objectsArray.length; i++) {
            for (int j = 0; j < objectsArray.length - 1 - i; j++) {
                inverseaza = false;
                if (crescator) {
                    if (objectsArray[j + 1].compareTo(objectsArray[j]) > -1) {
                        inverseaza = true;
                    }
                } else {
                    if (objectsArray[j + 1].compareTo(objectsArray[j]) < 1) {
                        inverseaza = true;
                    }
                }
                if (inverseaza) {
                    OrdObject ox = objectsArray[j];
                    objectsArray[j] = objectsArray[j + 1];
                    objectsArray[j + 1] = ox;
                }
            }
        }
    }

    public static void ordoneazaList(List<OrdObject> lista, boolean crescator) {
        OrdObject[] aOrdObject;
        aOrdObject = lista.toArray(new OrdObject[0]);

        ordoneazaArray(aOrdObject, crescator);

        lista.clear();
        for (int i = 0; i < aOrdObject.length; i++) {
            lista.add(aOrdObject[i]);
        }
    }

    public static List getListObOrd(List lista, boolean crescator) {
        // Obiectele din lista TREBUIE sa implementeze interfata OrdObject
        OrdObject[] aOrdObject = new OrdObject[lista.size()];
        List ordList = new ArrayList();
        try {
            for (int i = 0; i < lista.size(); i++) {
                aOrdObject[i] = (OrdObject) lista.get(i);
            }

            ordoneazaArray(aOrdObject, crescator);

            for (int i = 0; i < aOrdObject.length; i++) {
                ordList.add(aOrdObject[i]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ordonare.getListObOrd - pentru a putea face ordonarea obiectele din lista TREBUIE sa implementeze interfata OrdObject! - "+e.getLocalizedMessage());
        }
        return ordList;
    }
}

// Exemplu de utilizare
//    public List<Computer> getComputere() {
//        OrdObject[] objectsArray = new OrdObject[computere.size()];
//        for(int i=0; i<computere.size(); i++){
//            objectsArray[i]=(OrdObject)computere.get(i);
//        }
//        Ordonare.ordoneazaArray(objectsArray, false);
//        List<Computer> ordComputere = new ArrayList<>();
//        for(int i=0; i<computere.size(); i++){
//            ordComputere.add((Computer)objectsArray[i]);
//        }
//        return ordComputere;
//    }
