package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * Denna klass är till för att validera all sorts användarinmatning. 
 * I projektets olika sidor finns det metoder som använder sig utav och
 * refererar till dessa valideringsmetoder. Detta är för att undvika
 * upprepning av kod och för att organisera funktionaliteter.
 * 
 * I denna klass finns även kommentarer för de specifika valideringsmetoderna.
 */
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

    //Denna metod kontrollerar om angiven alien epost stämmer överens med 
    //registrerad epost.
    public static boolean checkIfAlienEpostExist(String epost) {
        try {
            return idb.fetchSingle("SELECT 1 FROM mibdb.alien WHERE Epost = \"" + epost + "\"") != null;
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "Något gick fel vid sökning!");
            System.out.println("Internt felmeddelande: " + ex.getMessage());
            return false;
        }
    }

    //Denna metod kontrollerar så att input består av heltal där det efterfrågas. 
    public static boolean valideraInt(String input) {
        try {
            int value = Integer.parseInt(input);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Denna metod kontrollerar så att både aliens och agenters epostadresser
    //stämmer överens med de registrerade epostadresserna. 
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

    //Kontrollerar så att användaren har matat in värden där det krävs.
    public static boolean validateTextFieldNotEmpty(String input) {
        return input != null && !input.isEmpty();
    }

    //Denna metod ser till så att användaren har använt rätt format 
    //vid input av datum. Det finns många olika sätt att skriva datum
    //och det kan vara svårt att hantera utan en date picker. 
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

    //Kontrollerar om ett värde finns i databasen.
    //Detta för att bl.a. undvika att försöka lägga in data i databasen
    //som redan existerar. 
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
    //Använder sig utav 2 inbakade metoder. Dessa 2 metoder hittar man också i 
    //denna valideringsklass. (Den ena precis under)
    public static boolean giltigtNamn(String ettNamn) {
        boolean giltigt = false;
        if (!isLongBenamning(ettNamn) && validateTextFieldNotEmpty(ettNamn)) {
            giltigt = true;
        }

        return giltigt;
    }

    //Används i metoden ovan. Ser till så att benämningar inte är för långa.
    private static boolean isLongBenamning(String enBenamning) {
        boolean isLong = false;
        if (enBenamning.length() > 20) {
            isLong = true;
            JOptionPane.showMessageDialog(null, "Namnet är för långt! (Max 20 tecken)");

        }
        return isLong;
    }

    //Kontrollera att comboboxar inte är tomma utan har fyllts upp med alternativ
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

    //Kontrollerar om arraylistan har värden eller inte
    public static boolean arArrayListTom(ArrayList lista) {
        boolean isEmpty = false;
        if (lista.isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Metod för att validera om ett telefonnummer är giltigt, här har
     * kriterierna sats till: numret får enbart innehålla siffor och max ett
     * bindestreck, som ej får vara på första eller sista plats i
     * strängen,uppfylls alla de kraven returneras true, annars false.
     *
     * @param telefon
     * @return
     */
    public static boolean valideraTelefonNummer(String telefon) {
        boolean giltigtNr = true;
        int antalBindestreck = 0;
        int bindestreckIndex = -1;

        for (int i = 0; i < telefon.length(); i++) {
            if (telefon.charAt(i) == '-') {
                antalBindestreck++;
                bindestreckIndex = i;
            }
        }

        if (antalBindestreck > 1) {
            giltigtNr = false;
        } else if (bindestreckIndex == 0 || bindestreckIndex == telefon.length() - 1) {
            giltigtNr = false;
        } else if (antalBindestreck == 1) {
            String forstaDel = telefon.substring(0, bindestreckIndex);
            String andraDel = telefon.substring(bindestreckIndex + 1);

            try {
                int forstaDelNummer = Integer.parseInt(forstaDel);
                int andraDelNummer = Integer.parseInt(andraDel);

            } catch (NumberFormatException e) {
                giltigtNr = false;
            }
        } else {

            try {
                int telefonNummer = Integer.parseInt(telefon);

            } catch (NumberFormatException e) {
                giltigtNr = false;
            }
        }

        return giltigtNr;
    }
  /**
   * Metod avsedd för att kontrollera om ett Alien ID existerar i databasen,
   * genom att skapa en ArrayList av allaIDs geno InfDB-metodn fetchColumn,
   * och sedan loopa genom den för att se om listan innehåller valt ID,
   * hittas ID returneras true, annars false.
   * @param alienID
   * @return 
   */
    public static boolean existerarAlienID(String alienID) {
        boolean idExisterar = false;
        String query = "SELECT Alien_ID FROM mibdb.alien;";
        ArrayList<String> allaIDs;

        try {
            allaIDs = idb.fetchColumn(query);

            if (allaIDs.contains(alienID)) {

                idExisterar = true;
            }
        } catch (InfException e) {

        }

        return idExisterar;
    }
    
   /**
   * Metod avsedd för att kontrollera om ett Agent ID existerar i databasen,
   * genom att skapa en ArrayList av allaIDs genom InfDB-metodn fetchColumn,
   * och sedan loopa genom den för att se om listan innehåller valt ID,
   * hittas ID returneras true, annars false.
   * @param agentID
   * @return 
   */
    public static boolean existerarAgentID(String agentID) {
        boolean idExisterar = false;
        String query = "SELECT Agent_ID FROM mibdb.agent;";
        ArrayList<String> allaAgentIDs;

        try {
            allaAgentIDs = idb.fetchColumn(query);

            if (allaAgentIDs.contains(agentID)) {

                idExisterar = true;
            }
        } catch (InfException e) {

        }

        return idExisterar;
    }

/**
 * Metod för att validera om en inmatad epost-adress anses giltig i systemet,
 * de krav vi valt på en epost är: måste innehålla "@" och ".", @ får inte vara
 * först eller sist eller komma före punkten, och punkten måste ha två eller tre
 * tecken efter sig.
 * @param epost
 * @return 
 */
public static boolean giltigEpost(String epost) {
    boolean isGiltig = true;

    if (!epost.contains("@") || !epost.contains(".")) {
        isGiltig = false;
    }

    if (epost.indexOf("@") > epost.indexOf(".")) {
        isGiltig = false;
    }

    if (epost.indexOf("@") == epost.length() - 1 || epost.indexOf("@") == 0) {
        isGiltig = false;
    }

    if (!(epost.indexOf(".") == epost.length() - 3 || epost.indexOf(".") == epost.length() - 4)) {
        isGiltig = false;
    }

    return isGiltig;

}
}