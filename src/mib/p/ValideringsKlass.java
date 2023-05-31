package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * Denna klass �r till f�r att validera all sorts anv�ndarinmatning. 
 * I projektets olika sidor finns det metoder som anv�nder sig utav och
 * refererar till dessa valideringsmetoder. Detta �r f�r att undvika
 * upprepning av kod och f�r att organisera funktionaliteter.
 * 
 * I denna klass finns �ven kommentarer f�r de specifika valideringsmetoderna.
 */
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

    //Denna metod kontrollerar om angiven alien epost st�mmer �verens med 
    //registrerad epost.
    public static boolean checkIfAlienEpostExist(String epost) {
        try {
            return idb.fetchSingle("SELECT 1 FROM mibdb.alien WHERE Epost = \"" + epost + "\"") != null;
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel vid s�kning!");
            System.out.println("Internt felmeddelande: " + ex.getMessage());
            return false;
        }
    }

    //Denna metod kontrollerar s� att input best�r av heltal d�r det efterfr�gas. 
    public static boolean valideraInt(String input) {
        try {
            int value = Integer.parseInt(input);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Denna metod kontrollerar s� att b�de aliens och agenters epostadresser
    //st�mmer �verens med de registrerade epostadresserna. 
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

    //Kontrollerar s� att anv�ndaren har matat in v�rden d�r det kr�vs.
    public static boolean validateTextFieldNotEmpty(String input) {
        return input != null && !input.isEmpty();
    }

    //Denna metod ser till s� att anv�ndaren har anv�nt r�tt format 
    //vid input av datum. Det finns m�nga olika s�tt att skriva datum
    //och det kan vara sv�rt att hantera utan en date picker. 
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

    //Kontrollerar om ett v�rde finns i databasen.
    //Detta f�r att bl.a. undvika att f�rs�ka l�gga in data i databasen
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

    //Kontrollerar att angivet namn i registrerautrustning �r okej
    //Anv�nder sig utav 2 inbakade metoder. Dessa 2 metoder hittar man ocks� i 
    //denna valideringsklass. (Den ena precis under)
    public static boolean giltigtNamn(String ettNamn) {
        boolean giltigt = false;
        if (!isLongBenamning(ettNamn) && validateTextFieldNotEmpty(ettNamn)) {
            giltigt = true;
        }

        return giltigt;
    }

    //Anv�nds i metoden ovan. Ser till s� att ben�mningar inte �r f�r l�nga.
    private static boolean isLongBenamning(String enBenamning) {
        boolean isLong = false;
        if (enBenamning.length() > 20) {
            isLong = true;
            JOptionPane.showMessageDialog(null, "Namnet �r f�r l�ngt! (Max 20 tecken)");

        }
        return isLong;
    }

    //Kontrollera att comboboxar inte �r tomma utan har fyllts upp med alternativ
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

    //Kontrollerar om arraylistan har v�rden eller inte
    public static boolean arArrayListTom(ArrayList lista) {
        boolean isEmpty = false;
        if (lista.isEmpty()) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Metod f�r att validera om ett telefonnummer �r giltigt, h�r har
     * kriterierna sats till: numret f�r enbart inneh�lla siffor och max ett
     * bindestreck, som ej f�r vara p� f�rsta eller sista plats i
     * str�ngen,uppfylls alla de kraven returneras true, annars false.
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
   * Metod avsedd f�r att kontrollera om ett Alien ID existerar i databasen,
   * genom att skapa en ArrayList av allaIDs geno InfDB-metodn fetchColumn,
   * och sedan loopa genom den f�r att se om listan inneh�ller valt ID,
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
   * Metod avsedd f�r att kontrollera om ett Agent ID existerar i databasen,
   * genom att skapa en ArrayList av allaIDs genom InfDB-metodn fetchColumn,
   * och sedan loopa genom den f�r att se om listan inneh�ller valt ID,
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
 * Metod f�r att validera om en inmatad epost-adress anses giltig i systemet,
 * de krav vi valt p� en epost �r: m�ste inneh�lla "@" och ".", @ f�r inte vara
 * f�rst eller sist eller komma f�re punkten, och punkten m�ste ha tv� eller tre
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