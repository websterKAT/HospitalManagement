/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_v2;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author lakshan
 */
public class PatientPayment extends javax.swing.JFrame {
    Connection conn = new DBConnection().connect();  
    public String myID;
    /**
     * Creates new form PatientPayment
     */
    public PatientPayment() {
        initComponents();
    }
    
    private boolean PayPreCheck(){
        return(txtPatientId.getText().equals("") || txtAmount.getText().equals("") || paidDate.getDate() == null || txtDescription.getText().equals(""));
    }

    private boolean BalPreCheck(){
        return(txtPayment.getText().equals("") || txtBalance.getText().equals(""));
    }
    private void PayMyClear(){
        txtPatientId.setText("");
        txtAmount.setText("");
        paidDate.setCalendar(null);
        txtDescription.setText("");
    }
    private void BalMyClear(){
        txtPayment.setText("");
        txtBalance.setText("");
    }
    
    private void EnableEditable(){
        txtPatientId.setEditable(true);
        txtAmount.setEditable(true);
        paidDate.setEnabled(true);
        txtDescription.setEditable(true);
        txtPayment.setEditable(true);
        txtBalance.setEditable(true);
    }
    
    private void DisableEditable(){
        txtPatientId.setEditable(false);
        txtAmount.setEditable(false);
        paidDate.setEnabled(false);
        txtDescription.setEditable(false);
        txtPayment.setEditable(false);
        txtBalance.setEditable(false);
    }
    
    private void PayInsert(){
         String sqlQuary = "INSERT INTO Payment (patientId,amount,paidDate,description) VALUES (?,?,?,?)";
        if(PayPreCheck()) {
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1, txtPatientId.getText());
                ps.setString(2,txtAmount.getText());
                java.sql.Date payDay = new java.sql.Date(paidDate.getDate().getTime());
                ps.setDate(3,payDay);
                ps.setString(4,txtDescription.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                PayMyClear();
            }  
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem "+e);
                PayMyClear();
               
            }

        }
    }
    private void PayUpdate(String patientID){
        String sqlQuary;
        sqlQuary = "UPDATE Payement SET patientId=?,amount=?,paidDate=?,description=? Where empId='"+patientID+"'";
        if(PayPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtPatientId.getText());
                ps.setString(2,txtAmount.getText());
                java.sql.Date sqlDate1 = new java.sql.Date(paidDate.getDate().getTime());
                ps.setDate(3,sqlDate1);
                ps.setString(4,txtDescription.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
                PayMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                PayMyClear();
            }
        }
    }
    private void PayDelete(String Id){
        String sqlQuary;
        sqlQuary = "DELETE FROM Payment Where empId ='"+Id+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            PayMyClear();
            BalMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            PayMyClear();
            BalMyClear();
        }
        
    }
    private void getPaymentId(){
        try{
            String sqlQuary = "Select MAX(paymentId) from Payment";
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                txtPayment.setText(""+count);
           }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
        }
    }
    
    private void BalInsert(){
         String sqlQuary = "INSERT INTO Balance (id,Balance) VALUES (?,?)";
        if(PayPreCheck()) {
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                
                ps.setString(1, txtPayment.getText());
                ps.setString(2,txtAmount.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                BalMyClear();
            }  
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem "+e);
                BalMyClear();
               
            }

        }
    }
    private void BalUpdate(String id){
        String sqlQuary;
        sqlQuary = "UPDATE Balance SET Balance=? Where id='"+id+"'";
        if(PayPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtBalance.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
                BalMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                BalMyClear();
            }
        }
    }
    private void Search(String Id){
         try{
           String sqlQuary ="Select Payment.patientId,Payment.amount,Payment.paidDate,Payment.description,Balance.id,Balance.Balance FROM Payment LEFT JOIN Balance ON Payment.paymentId = Balance.id and Payment.paymentId='"+Id+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String Ide = rs.getString("patientId");
               txtPatientId.setText(Ide);
               String amount = rs.getString("amount");
               txtAmount.setText(amount);
               java.sql.Date PDate = rs.getDate("paidDate");
               paidDate.setDate(PDate);
               String des = rs.getString("description");
               txtDescription.setText(des);
               txtPayment.setText(Id);
               String bal = rs.getString("Balance");
               txtBalance.setText(bal);
           }
               
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    private void SearchPatient(String key){
        try{
            String sqlQuary1 = "Select paymentId,amount,paidDate,description from Payment where patientId='"+key+"'";
            String sqlQuary2 = "SELECT sum(Balance.Balance) From Payment LEFT JOIN Balance On Payment.paymentId = Balance.id and Payment.patientId='"+key+"'";
            PreparedStatement ps1 = conn.prepareStatement(sqlQuary1);
            PreparedStatement ps2 = conn.prepareStatement(sqlQuary2);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            tablePay.setModel(DbUtils.resultSetToTableModel(rs1));
            if(rs2.next()){
                int totBalance = rs2.getInt(1);
                txtTotBalance.setText(""+totBalance);
            }
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Problem:"+e);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPatientId = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        paidDate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        Choice = new javax.swing.JComboBox<>();
        cmdPayOK = new javax.swing.JButton();
        cmdPayCancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSearchPatient = new javax.swing.JTextField();
        cmdGO = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPayment = new javax.swing.JTextField();
        txtBalance = new javax.swing.JTextField();
        cmdGet = new javax.swing.JButton();
        cmdBalanceOK = new javax.swing.JButton();
        cmdBalanceCancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePay = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTotBalance = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel1.setText("Patient ID:");

        txtPatientId.setEditable(false);

        txtAmount.setEditable(false);

        jLabel2.setText("Amount:");

        paidDate.setEnabled(false);

        jLabel4.setText("Date:");

        jLabel5.setText("Description:");

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        Choice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NEW..", "UPDATE", "DELETE" }));
        Choice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoiceActionPerformed(evt);
            }
        });

        cmdPayOK.setText("OK");
        cmdPayOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPayOKActionPerformed(evt);
            }
        });

        cmdPayCancel.setText("CANCEL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Choice, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(173, 173, 173)
                            .addComponent(cmdPayOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmdPayCancel))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(paidDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAmount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPatientId, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(paidDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdPayOK)
                    .addComponent(cmdPayCancel))
                .addGap(30, 30, 30))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enter Patient ID:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel6.setText("Patient ID:");

        cmdGO.setText("GO");
        cmdGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdGO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGO))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Balance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel3.setText("Payment ID:");

        jLabel7.setText("Balance :");

        txtPayment.setEditable(false);

        txtBalance.setEditable(false);

        cmdGet.setText("GET");
        cmdGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGetActionPerformed(evt);
            }
        });

        cmdBalanceOK.setText("OK");
        cmdBalanceOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBalanceOKActionPerformed(evt);
            }
        });

        cmdBalanceCancel.setText("CANCEL");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(txtPayment))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cmdBalanceOK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdGet)
                    .addComponent(cmdBalanceCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGet))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdBalanceOK)
                    .addComponent(cmdBalanceCancel))
                .addGap(54, 54, 54))
        );

        tablePay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablePay);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Balance to Pay", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel8.setText("Balance");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTotBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Payement", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPayOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPayOKActionPerformed
        String Selected = (String) Choice.getSelectedItem(); 
        if(Selected.equals("NEW..")){
          PayInsert();
          PayMyClear();
        }
        else if(Selected.equals("UPDATE")){
            PayUpdate(myID);
            PayMyClear();
        }
        else if(Selected.equals("DELETE")){
                PayDelete(myID);
                PayMyClear();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Select a Option");
        }
    }//GEN-LAST:event_cmdPayOKActionPerformed

    private void cmdBalanceOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBalanceOKActionPerformed
       String Select = (String) Choice.getSelectedItem(); 
        if(Select.equals("NEW..")){
          BalInsert();
          BalMyClear();
        }
        else if(Select.equals("UPDATE")){
            BalUpdate(myID);
            BalMyClear();
        }
        
    }//GEN-LAST:event_cmdBalanceOKActionPerformed

    private void cmdGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGetActionPerformed
        getPaymentId();
    }//GEN-LAST:event_cmdGetActionPerformed

    private void ChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoiceActionPerformed
         String isSelected = (String) Choice.getSelectedItem();
         switch(isSelected){
             case("NEW.."):{
                PayMyClear();
                BalMyClear();
                EnableEditable(); 
                break;
            }
             case("UPDATE"):{
                EnableEditable();
                myID = JOptionPane.showInputDialog("Please Enter the Bill ID:");
                Search(myID);
                break;
                
             }
             case("DELETE"):{
                  myID = JOptionPane.showInputDialog("Please Enter the Bill ID:");
                  DisableEditable();
                  Search(myID);
                  break;
             }
         }
    }//GEN-LAST:event_ChoiceActionPerformed

    private void cmdGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGOActionPerformed
        SearchPatient(txtSearchPatient.getText());
    }//GEN-LAST:event_cmdGOActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Choice;
    private javax.swing.JButton cmdBalanceCancel;
    private javax.swing.JButton cmdBalanceOK;
    private javax.swing.JButton cmdGO;
    private javax.swing.JButton cmdGet;
    private javax.swing.JButton cmdPayCancel;
    private javax.swing.JButton cmdPayOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser paidDate;
    private javax.swing.JTable tablePay;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtPatientId;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JTextField txtSearchPatient;
    private javax.swing.JTextField txtTotBalance;
    // End of variables declaration//GEN-END:variables
}
