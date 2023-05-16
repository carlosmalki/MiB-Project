package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;

public class ValideringsKlass {

    private static InfDB idb;

    public ValideringsKlass() {
        try {
            idb = new InfDB("mibdb", "3306", "mibdba", "mibkey");
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ex.getMessage());
        }

    }

    public static boolean checkIfAlienEpostExist(String epost) {
        try {
            return idb.fetchSingle("SELECT 1 FROM mibdb.alien WHERE Epost = \"" + epost + "\"") != null;
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "Något gick fel vid sökning!");
            System.out.println("Internt felmeddelande: " + ex.getMessage());
            return false;
        }
    }

    public static boolean valideraInt(String input) {
        try {
            int value = Integer.parseInt(input);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkEpost(String epost) {
        ArrayList<String> alienEpostList = new ArrayList<>();
        ArrayList<String> agentEpostList = new ArrayList<>();

        try {
            alienEpostList = idb.fetchColumn("SELECT Epost FROM mibdb.alien");
        } catch (InfException e) {
            System.out.println("Ett fel uppstod vid hämtning av Epost från alien-tabellen: " + e.getMessage());
        }

        try {
            agentEpostList = idb.fetchColumn("SELECT Epost FROM mibdb.agent");
        } catch (InfException e) {
            System.out.println("Ett fel uppstod vid hämtning av Epost från agent-tabellen: " + e.getMessage());
        }

        return alienEpostList.contains(epost) || agentEpostList.contains(epost);
    }

    public static boolean validateTextFieldNotEmpty(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean valideraDatum(String input) {
        boolean korrektDatumInput = false;
        if (input.length() == 10) { // Kontrollera att inmatningssträngen har rätt längd (yyyy-MM-dd)
            String manad = input.substring(5, 7);
            String dag = input.substring(8, 10);
            String ar = input.substring(0, 4);
            boolean arSiffror = valideraInt(manad) && valideraInt(dag) && valideraInt(ar);
            if (arSiffror && input.substring(4, 5).equals("-") && input.substring(7, 8).equals("-")) {
                korrektDatumInput = true;
            }
        }
        return korrektDatumInput;
    }

}
