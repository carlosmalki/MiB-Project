package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * Denna klass �r till f�r att starta sj�lva programmet. 
 * Genom denna klass s� �ppnas den f�rsta sidan: HuvudSidan.
 * 
 * Det h�r �r det enda st�llet i projektet d�r det finns en main metod.
 * F�r att vidare undvika upprepning av mainmetoder s� har vi arbetat med
 * JPanels ist�llet f�r JFrames.
 * @author samsung
 */
public class Start {
    
    private static InfDB idb;

    public static void main(String[] args) {
        
        try {
            idb = new InfDB("mibdb", "3306", "mibdba", "mibkey");
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            System.out.println("Internt felmeddelande" + ex.getMessage());
        }

        {
            new HuvudSidan(idb).setVisible(true);
        }
    }
    
    }
