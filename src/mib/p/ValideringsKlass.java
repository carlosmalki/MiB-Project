package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import javax.swing.JComboBox;

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

    //Kontrollera att ett värde finns i databasen - används t.ex. i registrera utrustning
    public static boolean vardeFinns(String varde, ArrayList<String> column) {
        boolean finns = false;
        for (String ettVarde : column) {
            if (varde.equals(ettVarde)) {
                finns = true;
            }
        }
        return finns;
    }

    //Kontrollerar att angivet namn i registrerautrustning är okej
    public static boolean giltigtNamn(String ettNamn) {
        boolean giltigt = false;
        if (!isLongBenamning(ettNamn) && validateTextFieldNotEmpty(ettNamn)) {
            giltigt = true;
        }

        return giltigt;
    }

    //Används i metoden ovan
    private static boolean isLongBenamning(String enBenamning) {
        boolean isLong = false;
        if (enBenamning.length() > 20) {
            isLong = true;
            JOptionPane.showMessageDialog(null, "Namnet är för långt! (Max 20 tecken)");

        }
        return isLong;
        /**
         * Valideringsmetod som kontrollerar om en cobobox är tom eller inte, är
         * den tom returneras true, annars false.
         */
    }

    public static boolean valideraComboBox(JComboBox box) {
        boolean boxEmpty = false;
        if (box.getItemCount() == 0) {
            boxEmpty = true;
        }
        return boxEmpty;
    }

    /**
     * Metod för att kontrollera comboboxar med ett standardalternativ inlagt
     * från början (ex, "Välj agent") för att se om ett aktivt val har gjorts i
     * boxen, är index av valt alternativ noll returneras false, inget aktivt
     * val har gjorts, annars true.
     *
     * @param box
     * @return
     */
    public static boolean valideraComboBoxAktivtVal(JComboBox box) {
        boolean aktivtVal = true;
        if (box.getSelectedIndex() == 0) {
            aktivtVal = false;
        }
        return aktivtVal;
    }


   public static boolean arArrayListTom(ArrayList lista)
   {
   boolean isEmpty = false;
   if(lista.isEmpty())
   {
   isEmpty = true;
   }
   return isEmpty;
   }

}