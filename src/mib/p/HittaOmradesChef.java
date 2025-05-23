/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package mib.p;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 * HittaOmradesChef �r en JPanel-klass d�r anv�ndaren utifr�n valt omr�de (valt
 * i en combobox) kan s�ka upp Omr�derschefen och se hens namn, vidare kan man
 * trycka p� en Jbutton, som tar anv�ndaren vidare till en panel med lite mer
 * information om chefen, klassen tar in String epost, och String isAdmin som
 * h�ller reda p� vilken agent som �r inloggad, och om denne �r admin.
 *
 * @author samsung
 */
public class HittaOmradesChef extends javax.swing.JPanel {

    private static InfDB idb;
    private String epost;
    private String isAdmin;

    /**
     * Creates new form HittaOmradesChef
     */
    public HittaOmradesChef(String epost, String isAdmin) {
        initComponents();
        this.epost = epost;
        this.isAdmin = isAdmin;

        try {
            idb = new InfDB("mibdb", "3306", "mibdba", "mibkey");
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
            
        }
        fyllComboBoxOmrade();
        txtHittadChef.setEditable(false);
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
        cbOmrade = new javax.swing.JComboBox<>();
        btnVisaOmradesChef = new javax.swing.JButton();
        lblChefRubrik = new javax.swing.JLabel();
        lblListadChef = new javax.swing.JLabel();
        btnMinSida = new javax.swing.JButton();
        lblHittaChef = new javax.swing.JLabel();
        txtHittadChef = new javax.swing.JTextField();
        btnChefInfo = new javax.swing.JButton();

        lblRubrik.setFont(new java.awt.Font("MS Gothic", 1, 12)); // NOI18N
        lblRubrik.setText("V�lj omr�de:");

        btnVisaOmradesChef.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        btnVisaOmradesChef.setText("Visa omr�deschef");
        btnVisaOmradesChef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaOmradesChefActionPerformed(evt);
            }
        });

        lblChefRubrik.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        lblChefRubrik.setText("Chef:");

        btnMinSida.setBackground(new java.awt.Color(242, 242, 242));
        btnMinSida.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        btnMinSida.setText("Min sida");
        btnMinSida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinSidaActionPerformed(evt);
            }
        });

        lblHittaChef.setFont(new java.awt.Font("MS Gothic", 1, 36)); // NOI18N
        lblHittaChef.setText("Hitta omr�deschef");

        btnChefInfo.setFont(new java.awt.Font("MS Gothic", 1, 14)); // NOI18N
        btnChefInfo.setText("Info");
        btnChefInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChefInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinSida)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblListadChef))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHittaChef)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblRubrik)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cbOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnVisaOmradesChef)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(lblChefRubrik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHittadChef, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChefInfo)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblHittaChef)
                .addGap(37, 37, 37)
                .addComponent(lblListadChef)
                .addGap(11, 11, 11)
                .addComponent(lblRubrik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOmrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisaOmradesChef))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChefRubrik)
                    .addComponent(txtHittadChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChefInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnMinSida)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents
/**
 * Metod kopplad till btnVisaOmradesChef som genom InfDB-metoden fetchSingle() h�mtar namnet
 * p� den agent som f�r tillf�llet �r chef f�r valt omr�de, och s�tter ett textf�lt med namnet.
 * @param evt 
 */
    private void btnVisaOmradesChefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaOmradesChefActionPerformed
        String valtKontor = cbOmrade.getSelectedItem().toString();
        try {
            String chef = idb.fetchSingle("Select agent.namn from agent join omradeschef on agent.AGENT_ID = omradeschef.AGENT_ID join omrade on omradeschef.OMRADE = omrade.OMRADES_ID where omrade.BENAMNING = '" + valtKontor + "'");
            txtHittadChef.setText(chef);
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
        }
    }//GEN-LAST:event_btnVisaOmradesChefActionPerformed

    private void btnMinSidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinSidaActionPerformed
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HittaOmradesChef.this);
        frame.setContentPane(new MinSidaAgentForm(epost, isAdmin));
        frame.revalidate();
        frame.setTitle("Startsida: Agent");
        frame.repaint();
    }//GEN-LAST:event_btnMinSidaActionPerformed
/**
 * Metod kopplad till btnChefInfo,som om textf�ltet f�r chefsnamn inte �r tomt h�mtar all info om vald chef,
 * genom InfDB-metoden fetchRow och lagrar det i en HashMap, som sedan skickas med
 * som parameter n�r en ny instans av HittaOmradesChef initieras.
 * @param evt 
 */
    private void btnChefInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChefInfoActionPerformed
        if (ValideringsKlass.validateTextFieldNotEmpty(txtHittadChef.getText())) {
            try {
                String omradet = cbOmrade.getSelectedItem().toString();
                HashMap<String, String> chefInfo = idb.fetchRow("SELECT * FROM MIBDB.AGENT WHERE MIBDB.AGENT.AGENT_ID IN (SELECT MIBDB.OMRADESCHEF.AGENT_ID FROM MIBDB.OMRADESCHEF WHERE OMRADE IN (SELECT OMRADES_ID FROM MIBDB.OMRADE WHERE BENAMNING = '" + omradet + "'))");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HittaOmradesChef.this);
                frame.setContentPane(new OmradesChefInfoForm(chefInfo, omradet, epost, isAdmin));
                frame.revalidate();
                frame.setTitle("Omr�deschef: Information");
                frame.repaint();

            } catch (InfException e) {

                JOptionPane.showMessageDialog(null, "Ett fel uppstod: " + e.getMessage(), "Fel", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vad god s�k chef f�rst.");
        }
    }//GEN-LAST:event_btnChefInfoActionPerformed
/**
 * Metod som fyller upp cbOmrade med namn p� de omr�den som finns i databasen genom
 * InfDB-metode fetchColumn().
 */
    private void fyllComboBoxOmrade() {

        ArrayList<String> omrade;
        cbOmrade.removeAllItems();
        try {
            omrade = idb.fetchColumn("select benamning from omrade");

            for (String ettKontor : omrade) {
                cbOmrade.addItem(ettKontor);
            }
        } catch (InfException ex) {
            JOptionPane.showMessageDialog(null, "N�got gick fel!");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChefInfo;
    private javax.swing.JButton btnMinSida;
    private javax.swing.JButton btnVisaOmradesChef;
    private javax.swing.JComboBox<String> cbOmrade;
    private javax.swing.JLabel lblChefRubrik;
    private javax.swing.JLabel lblHittaChef;
    private javax.swing.JLabel lblListadChef;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JTextField txtHittadChef;
    // End of variables declaration//GEN-END:variables
}
