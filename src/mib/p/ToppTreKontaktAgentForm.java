/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mib.p;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import oru.inf.InfDB;
import oru.inf.InfException;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * ToppTreKonTaktAgentForm �r en JPanel-klass d�r man kan s�ka fram info om
 * vilka tre agenter som har flest kontakt-aliens i databasen, Omr�de v�ljs
 * genom ComboBox och de agenter med flest kontaktaliens i valt omr�de visas i
 * textf�lt.
 *
 * @author samsung
 */
public class ToppTreKontaktAgentForm extends javax.swing.JPanel {

    private static InfDB idb;
    private String epost;
    private String isAdmin;

    /**
     * Creates new form Top3
     */
    public ToppTreKontaktAgentForm(String epost, String isAdmin) {
        initComponents();
        this.epost = epost;
        this.isAdmin = isAdmin;

        try {
            idb = new InfDB("mibdb", "3306", "mibdba", "mibkey");
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            
        }
        fyllComboBox();

    }

    /**
     * Metod f�r att fylla upp ComboBoxen med omr�desnamnet, genom att f�rst,
     * via InfDB-metoden fetchColumn skaa en arraylist �ver omr�des- namn och
     * sedan loopa genom denna och l�gga till varje objekt i comboboxen.
     */
    private void fyllComboBox() {
        ArrayList<String> omraden;
        try {
            omraden = idb.fetchColumn("SELECT benamning FROM omrade");
            cbValjOmrade.removeAllItems();
            for (String ettOmrade : omraden) {
                cbValjOmrade.addItem(ettOmrade);
            }
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel med h�mtning av data!");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRubrik = new javax.swing.JLabel();
        cbValjOmrade = new javax.swing.JComboBox<>();
        btnVisaTopp3 = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPlatsEtt = new javax.swing.JTextField();
        txtPlatsTva = new javax.swing.JTextField();
        txtPlatsTre = new javax.swing.JTextField();

        lblRubrik.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        lblRubrik.setText("V�lj omr�de:");

        btnVisaTopp3.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        btnVisaTopp3.setText("Visa ");
        btnVisaTopp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaTopp3ActionPerformed(evt);
            }
        });

        btnTillbaka.setBackground(new java.awt.Color(242, 242, 242));
        btnTillbaka.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        btnTillbaka.setText("Min sida");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), null, null));

        jLabel5.setFont(new java.awt.Font("MS Gothic", 1, 24)); // NOI18N
        jLabel5.setText("Topplista kontaktagenter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        jLabel1.setText("1:");

        jLabel2.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        jLabel2.setText("2:");

        jLabel3.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        jLabel3.setText("3:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTillbaka)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblRubrik)
                                .addGap(18, 18, 18)
                                .addComponent(cbValjOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVisaTopp3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtPlatsEtt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtPlatsTva, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPlatsTre, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbValjOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisaTopp3)
                    .addComponent(lblRubrik))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPlatsEtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPlatsTva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPlatsTre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(btnTillbaka)
                .addGap(54, 54, 54))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ToppTreKontaktAgentForm.this);
        frame.setContentPane(new MinSidaAgentForm(epost, isAdmin));
        frame.revalidate();
        frame.setTitle("Startsida: Agent");
        frame.repaint();
    }//GEN-LAST:event_btnTillbakaActionPerformed
    /**
     * Metod kopplad till btnVisaTopp3,som utifr�n valt omr�de k�r
     * skapaToppLista()-metoden med omr�dersnamnet som ing�ende parameter, efter
     * att f�rst ha rensat alla textf�lt genom metoden rensaTextFalt().
     *
     * @param evt
     */
    private void btnVisaTopp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaTopp3ActionPerformed
        rensaTextFalt();
        if (cbValjOmrade.getSelectedItem().toString().equals("Svealand")) {
            String omrade = "Svealand";
            skapaToppLista(omrade);
        }
        if (cbValjOmrade.getSelectedItem().toString().equals("G�taland")) {
            String omrade = "G�taland";
            skapaToppLista(omrade);
        }
        if (cbValjOmrade.getSelectedItem().toString().equals("Norrland")) {
            String omrade = "Norrland";
            skapaToppLista(omrade);
        }


    }//GEN-LAST:event_btnVisaTopp3ActionPerformed
    /**
     * Metod som genom InfDB-metoden fetchRows h�mtar hem de agenter med flest
     * kontaktaliens inom valt omr�de i comboboxen, dessa sparas som HashMaps
     * med nycklarna "Namn" och "Antal kontaktaliens", dessa HashMaps ligger i
     * en ArrayList som loopas genom f�r att f�r varje nyckel-v�rde par skapa en
     * String med agentens namn och antalet aliens, som lagras i en ArrayList
     * som skickas med in i metoden fyllToppTreLista().
     *
     * @param omrade
     */

    public void skapaToppLista(String omrade) {
        ArrayList<String> toppLista = new ArrayList<>();
        try {
            String query = "SELECT ag.Namn AS Agent, COUNT(*) AS 'Antal kontaktaliens' "
                    + "FROM alien AS a "
                    + "JOIN plats AS p ON a.Plats = p.Plats_ID "
                    + "JOIN omrade AS o ON p.Finns_I = o.Omrades_ID "
                    + "JOIN agent AS ag ON a.Ansvarig_Agent = ag.Agent_ID "
                    + "WHERE o.Benamning = '" + omrade + "' "
                    + "GROUP BY a.Ansvarig_Agent "
                    + "ORDER BY CAST('Antal kontaktaliens' AS SIGNED) DESC "
                    + "LIMIT 3;";
            ArrayList<HashMap<String, String>> agentRader = idb.fetchRows(query);

            for (HashMap<String, String> raden : agentRader) {
                String agent = raden.get("Namn") + ": " + raden.get("Antal kontaktaliens") + " kontaktalien.";

                toppLista.add(agent);
            }

            if (toppLista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Det finns ingen agent i omr�det.");
            } else {
                if (toppLista.size() == 3) {
                    txtPlatsEtt.setText(toppLista.get(2));
                    txtPlatsTva.setText(toppLista.get(1));
                    txtPlatsTre.setText(toppLista.get(0));
                } else if (toppLista.size() == 2) {
                    txtPlatsEtt.setText(toppLista.get(1));
                    txtPlatsTva.setText(toppLista.get(0));
                } else if (toppLista.size() == 1) {
                    txtPlatsEtt.setText(toppLista.get(0));
                }
            }

        } catch (InfException e) {
            JOptionPane.showMessageDialog(null, "N�got gick fel vid h�mtning av data.");
        }
    }

    /**
     * Metod som �terst�ller topplistans textf�lt inf�r varje s�kning.
     */
    public void rensaTextFalt() {
        txtPlatsEtt.setText((""));
        txtPlatsTva.setText("");
        txtPlatsTre.setText("");

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton btnVisaTopp3;
    private javax.swing.JComboBox<String> cbValjOmrade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JTextField txtPlatsEtt;
    private javax.swing.JTextField txtPlatsTre;
    private javax.swing.JTextField txtPlatsTva;
    // End of variables declaration//GEN-END:variables
}
