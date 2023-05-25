package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Denna klass är till för att starta själva programmet. 
 * Genom denna klass så öppnas den första sidan: HuvudSidan.
 * 
 * Det här är det enda stället i projektet där det finns en main metod.
 * För att vidare undvika upprepning av mainmetoder så har vi arbetat med
 * JPanels istället för JFrames.
 * @author samsung
 */
public class Start {
    
    private static InfDB idb;

    public static void main(String[] args) {
        
        try {
            idb = new InfDB("mibdb", "3306", "mibdba", "mibkey");
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            System.out.println("Internt felmeddelande" + ex.getMessage());
        }

        {
            new HuvudSidan(idb).setVisible(true);
        }
    }
    
    }
