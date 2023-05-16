package mib.p;

import javax.swing.JOptionPane;
import oru.inf.InfDB;
import oru.inf.InfException;

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
