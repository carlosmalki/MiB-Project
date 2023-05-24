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
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ex.getMessage());
        }

    }

    public static boolean checkIfAlienEpostExist(String epost) {
        try {
            return idb.fetchSingle("SELECT 1 FROM mibdb.alien WHERE Epost = \"" + epost + "\"") != null;
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel vid s�kning!");
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
            System.out.println("Ett fel uppstod vid h�mtning av Epost fr�n alien-tabellen: " + e.getMessage());
        }

        try {
            agentEpostList = idb.fetchColumn("SELECT Epost FROM mibdb.agent");
        } catch (InfException e) {
            System.out.println("Ett fel uppstod vid h�mtning av Epost fr�n agent-tabellen: " + e.getMessage());
        }

        return alienEpostList.contains(epost) || agentEpostList.contains(epost);
    }

    public static boolean validateTextFieldNotEmpty(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean valideraDatum(String input) {
        boolean korrektDatumInput = false;
        if (input.length() == 10) { // Kontrollera att inmatningsstr�ngen har r�tt l�ngd (yyyy-MM-dd)
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

    //Kontrollera att ett v�rde finns i databasen - anv�nds t.ex. i registrera utrustning
    public static boolean vardeFinns(String varde, ArrayList<String> column) {
        boolean finns = false;
        for (String ettVarde : column) {
            if (varde.equals(ettVarde)) {
                finns = true;
            }
        }
        return finns;
    }

    //Kontrollerar att angivet namn i registrerautrustning �r okej
    public static boolean giltigtNamn(String ettNamn) {
        boolean giltigt = false;
        if (!isLongBenamning(ettNamn) && validateTextFieldNotEmpty(ettNamn)) {
            giltigt = true;
        }

        return giltigt;
    }

    //Anv�nds i metoden ovan
    private static boolean isLongBenamning(String enBenamning) {
        boolean isLong = false;
        if (enBenamning.length() > 20) {
            isLong = true;
            JOptionPane.showMessageDialog(null, "Namnet �r f�r l�ngt! (Max 20 tecken)");

        }
        return isLong;
        /**
         * Valideringsmetod som kontrollerar om en cobobox �r tom eller inte, �r
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
     * Metod f�r att kontrollera comboboxar med ett standardalternativ inlagt
     * fr�n b�rjan (ex, "V�lj agent") f�r att se om ett aktivt val har gjorts i
     * boxen, �r index av valt alternativ noll returneras false, inget aktivt
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