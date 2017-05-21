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
public class Employee extends javax.swing.JFrame {
    Connection conn = new DBConnection().connect();  
    public String key;
     
    /**
     * Creates new form Employee
     */
    public Employee() {
        initComponents();
    }
   
    
    private boolean EmpPreCheck(){
        return ((txtFname.getText().equals("")) || (txtLname.getText().equals("")) || (txtId.getText().equals("")) || (startDate.getDate() == null) || (bDay.getDate() == null));
    }
    private boolean DocPreCheck(){
        return(txtPosition.getText().equals("") || txtField.getText().equals(""));
    }
    private boolean LabPreCheck(){
        return(txtRoomNo.getText().equals(""));
    }
    private boolean NursePreCheck(){
        return(txtWardId.getText().equals(""));
    }
    
    private boolean AttPreCheck(){
        return (txtempID.getText().equals("") || txtDATE.getDate() == null || txtINTIME.getText().equals("") || txtOUTTIME.getText().equals(""));
    }
    
    private void AttMyClear(){
        txtempID.setText("");
        txtDATE.setCalendar(null);
        txtINTIME.setText("");
        txtOUTTIME.setText("");
    }
    
    private void EmpMyClear() {
        txtId.setText("");
        txtFname.setText("");
        txtLname.setText("");
        bDay.setCalendar(null);
        txtAddL1.setText("");
        txtAddL2.setText("");
        txtAddL3.setText("");
        txtNic.setText("");
        startDate.setCalendar(null);
        txtTeleNo.setText("");
    }
    
    private void DocMyClear(){
        txtPosition.setText("");
        txtField.setText("");
    }
    
    private void LabMyClear(){
        txtRoomNo.setText("");
    }
    
    private void NurseMyClear(){
        txtWardId.setText("");
    }

    private void EmpDisableEditable(){
        txtId.setEditable(false);
        txtFname.setEditable(false);
        txtLname.setEditable(false);
        bDay.setEnabled(false);
        txtAddL1.setEditable(false);
        txtAddL2.setEditable(false);
        txtAddL3.setEditable(false);
        txtNic.setEditable(false);
        rdMale.setEnabled(false);
        rdFemale.setEnabled(false);
        startDate.setEnabled(false);
        txtTeleNo.setEditable(false);
    }
    
    
    private void DocDisableEditable(){
        txtPosition.setEditable(false);
        txtField.setEditable(false);
    }
    
    private void LabDisableEditable(){
       txtRoomNo.setEditable(false);
    }
    private void NurseDisableEditable(){
       txtWardId.setEditable(false);
    }
    
   
    private void EmpEnableEditable(){
        txtId.setEditable(true);
        txtFname.setEditable(true);
        txtLname.setEditable(true);
        bDay.setEnabled(true);
        txtAddL1.setEditable(true);
        txtAddL2.setEditable(true);
        txtAddL3.setEditable(true);
        txtNic.setEditable(true);
        rdMale.setEnabled(true);
        rdFemale.setEnabled(true);
        startDate.setEnabled(true);
        txtTeleNo.setEditable(true);
    }
    
    private void DocEnableEditable() {
       txtPosition.setEditable(true);
       txtField.setEditable(true);
    }
    private void LabEnableEditable() {
        txtRoomNo.setEditable(true);
    }
    private void NurseEnableEditable(){
        txtWardId.setEditable(true);
    }
    private void AttEnableEditable(){
        txtempID.setEditable(true);
        txtDATE.setEnabled(true);
        txtINTIME.setEditable(true);
        txtOUTTIME.setEditable(true);
    }   
    private void AttDisableEditable(){
        txtempID.setEditable(false);
        txtDATE.setEnabled(false);
        txtINTIME.setEditable(false);
        txtOUTTIME.setEditable(false);
    }
    
    private void EmpInsertData(){
        String sqlQuary = "INSERT INTO Employee (empId,firstName,lastName,DOB,NIC,adressLine1,addressLine2,addressLine3,teleNo,startedDate,sex) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        if(EmpPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1, txtId.getText());
                ps.setString(2,txtFname.getText());
                ps.setString(3,txtLname.getText());
                java.sql.Date birthDay = new java.sql.Date(bDay.getDate().getTime());
                ps.setDate(4,birthDay);
                ps.setString(5,txtNic.getText());
                ps.setString(6,txtAddL1.getText());
                ps.setString(7,txtAddL2.getText());
                ps.setString(8,txtAddL3.getText());
                ps.setString(9,txtTeleNo.getText());
                java.sql.Date startDay = new java.sql.Date(startDate.getDate().getTime());
                ps.setDate(10,startDay);
                String gender="";
                if(rdMale.isSelected()){
                    gender = "Male";
                }
                if(rdFemale.isSelected()){
                    gender = "Female";
                }
                ps.setString(11,gender);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                EmpMyClear();
               
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                 EmpMyClear();
               
            }

        }
    }
        private void DocInsert(){
        String sqlQuary = "INSERT INTO Doctor (docId,position1,specified_field) VALUES (?,?,?)";
        if(DocPreCheck()) {
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1, key);
                ps.setString(2,txtPosition.getText());
                ps.setString(3,txtField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                DocMyClear();
            }  
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem "+e);
                DocMyClear();
               
            }

        }
    }
    
    private void LabInsert(){
        String sqlQuary = "INSERT INTO Labtechnician (tecId,roomNo) VALUES (?,?)";
        if(LabPreCheck()) {
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1, key);
                ps.setString(2,txtRoomNo.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                LabMyClear();
            }  
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem "+e);
                LabMyClear();
               
            }

        }
    }
    
    private void NurseInsert(){
        String sqlQuary = "INSERT INTO Nurse (nurseId,wardId) VALUES (?,?)";
        if(NursePreCheck()) {
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1, key);
                ps.setString(2,txtPosition.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                NurseMyClear();
            }  
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem "+e);
                NurseMyClear();
               
            }

        }
    }
   private void AttInsert(){
        String sqlQuary = "INSERT INTO Attendence (empId,attendedDate,inTime,outTime) VALUES (?,?,?,?)";
        if(AttPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtempID.getText());
                java.sql.Date tDate = new java.sql.Date(txtDATE.getDate().getTime());
                ps.setDate(2,tDate);
                ps.setString(3,txtINTIME.getText());
                ps.setString(4,txtOUTTIME.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Inserted Sucessfully");
                AttMyClear();
               
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                AttMyClear();
               
            }

        }
    }   

   private void AttUpdate(String empp){
        String sqlQuary;
        sqlQuary = "UPDATE Attendence SET empId=?,attendedDate=?,inTime=?,outTime=? Where empId='"+empp+"'";
        if(AttPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else {
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtempID.getText());
                java.sql.Date sqlDate1 = new java.sql.Date(txtDATE.getDate().getTime());
                ps.setDate(2,sqlDate1);
                ps.setString(3,txtINTIME.getText());
                ps.setString(4,txtOUTTIME.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
                AttMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                AttMyClear();
            }
        } 
    }   

    private void EmpUpdate(String employeeId) {
        String sqlQuary;
        sqlQuary = "UPDATE Employee SET firstName=?,lastName=?,DOB=?,NIC=?,adressLine1=?,addressLine2=?,addressLine3=?,teleNo=?,startedDate=?,sex=? Where empId='"+employeeId+"'";
        if(EmpPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtFname.getText());
                ps.setString(2,txtLname.getText());
                java.sql.Date sqlDate1 = new java.sql.Date(bDay.getDate().getTime());
                ps.setDate(3,sqlDate1);
                ps.setString(4,txtNic.getText());
                ps.setString(5,txtAddL1.getText());
                ps.setString(6,txtAddL2.getText());
                ps.setString(7,txtAddL3.getText());
                ps.setString(8,txtTeleNo.getText());
                java.sql.Date sqlDate2 = new java.sql.Date(startDate.getDate().getTime());
                ps.setDate(9, sqlDate2);
                String gender="";
                if(rdMale.isSelected()){
                    gender = "Male";
                }
                if(rdFemale.isSelected()){
                    gender = "Female";
                }
                ps.setString(10,gender);
                 ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
                EmpMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                EmpMyClear();
            }
        }
    }
    
    private void DocUpdate(String doctorId){
        String sqlQuary;
        sqlQuary = "UPDATE Doctor SET position1=?,specified_field=? Where docId='"+doctorId+"'";
        if(DocPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtPosition.getText());
                ps.setString(2,txtField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
                DocMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                DocMyClear();
            }
        }
    }
    private void LabUpdate(String labTechId){
        String sqlQuary;
        sqlQuary = "UPDATE Labtechnician SET roomNo=? Where tecId='"+labTechId+"'";
        if(LabPreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtRoomNo.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
               LabMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                LabMyClear();
            }
        }
    }
    private void NurseUpdate(String NurseId){
        String sqlQuary;
        sqlQuary = "UPDATE Nurse SET wardId=? Where nurseId='"+NurseId+"'";
        if(NursePreCheck()){
            JOptionPane.showMessageDialog(null,"please enter all data field");
        }
        else{
            try{
                PreparedStatement ps = conn.prepareStatement(sqlQuary);
                ps.setString(1,txtWardId.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Edited Sucessfully");
               NurseMyClear();
            }
            catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"problem"+e);
                NurseMyClear();
            }
        }
    }
    
    private void EmpDelete(String employeeId){
        String sqlQuary;
        sqlQuary = "DELETE FROM Employee Where empId ='"+employeeId+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            EmpMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            EmpMyClear();
        }
        
    }
    
    private void AttDelete(String empp){
        String sqlQuary;
        sqlQuary = "DELETE FROM Attendence Where empId ='"+empp+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            AttMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            AttMyClear();
        }
        
    }  
    private void DocDelete(String doctorId){
        String sqlQuary;
        sqlQuary = "DELETE FROM Doctor Where docId ='"+doctorId+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            DocMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            DocMyClear();
        }
    }
    
    private void LabDelete(String labtechId){
        String sqlQuary;
        sqlQuary = "DELETE FROM LabTechnician Where tecId ='"+labtechId+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            LabMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            LabMyClear();
        }
    }
     
    private void NurseDelete(String nurseId){
        String sqlQuary;
        sqlQuary = "DELETE FROM Nurse Where tecId ='"+nurseId+"'";
        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
            NurseMyClear();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"problem"+e);
            NurseMyClear();     
        }
    }
    
    
    private void EmpSearch(String empId){
         try{
           String sqlQuary ="Select * from Employee where empId='"+empId+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String Id = rs.getString("empId");
               txtId.setText(Id);
               String fName = rs.getString("firstName");
               txtFname.setText(fName);
               String lName = rs.getString("lastName");
               txtLname.setText(lName);
               java.sql.Date bDate = rs.getDate("DOB");
               bDay.setDate(bDate);
               String nic = rs.getString("NIC");
               txtNic.setText(nic);
               String add1 = rs.getString("adressLine1");
               txtAddL1.setText(add1);
               String add2 = rs.getString("addressLine2");
               txtAddL2.setText(add2);
               String add3 = rs.getString("addressLine3");
               txtAddL3.setText(add3);
               String tele = rs.getString("teleNo");
               txtTeleNo.setText(tele);
               java.sql.Date aDate = rs.getDate("startedDate");
               startDate.setDate(aDate);
               String gender = rs.getString("sex");
               if(gender.equals("Male")){
                   rdMale.setSelected(true);
                   rdFemale.setSelected(false);
               }
               else{
                   rdFemale.setSelected(true);
                   rdMale.setSelected(false);
                }
              }
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    
    private void DocSearch(String docId){
        try{
           String sqlQuary ="Select * from Doctor where docId='"+docId+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String pos = rs.getString("position1");
               txtPosition.setText(pos);
               String field = rs.getString("specified_field");
               txtField.setText(field);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void LabSearch(String id){
        try{
           String sqlQuary ="Select * from Labtechnician where tecId='"+id+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String room = rs.getString("roomNo");
               txtRoomNo.setText(room);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void NurseSearch(String id){
         try{
           String sqlQuary ="Select * from Nurse where nurseId='"+id+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String ward = rs.getString("wardId");
               txtWardId.setText(ward);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void SearchByDate(java.sql.Date date){
        try{
            String sqlQuary = "Select empId,attendedDate,inTime,outTime from Attendence where attendedDate='"+date+"'";
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ResultSet rs = ps.executeQuery();
            tableDaily.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Problem:"+e);
        }
    }
    
     private void SearchById(String empp){
        try{
            String sqlQuary = "Select empId,attendedDate,inTime,outTime from Attendence where empId='"+empp+"'";
            PreparedStatement ps = conn.prepareStatement(sqlQuary);
            ResultSet rs = ps.executeQuery();
            tableDaily.setModel(DbUtils.resultSetToTableModel(rs));
            
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

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("hospital_info?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        employee_1Query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT e FROM Employee_1 e");
        employee_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : employee_1Query.getResultList();
        buttonGroup1 = new javax.swing.ButtonGroup();
        employee_1Query1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT e FROM Employee_1 e");
        employee_1List1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : employee_1Query1.getResultList();
        patient_1Query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Patient_1 p");
        patient_1List = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : patient_1Query.getResultList();
        doctorQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT d FROM Doctor d");
        doctorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : doctorQuery.getResultList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtFname = new javax.swing.JTextField();
        txtLname = new javax.swing.JTextField();
        bDay = new com.toedter.calendar.JDateChooser();
        txtNic = new javax.swing.JTextField();
        txtAddL1 = new javax.swing.JTextField();
        txtAddL2 = new javax.swing.JTextField();
        txtAddL3 = new javax.swing.JTextField();
        txtTeleNo = new javax.swing.JTextField();
        startDate = new com.toedter.calendar.JDateChooser();
        rdMale = new javax.swing.JRadioButton();
        rdFemale = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        choiceBox = new javax.swing.JComboBox<>();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        docPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPosition = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtField = new javax.swing.JTextArea();
        labPanel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtRoomNo = new javax.swing.JTextField();
        nursePanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtWardId = new javax.swing.JTextField();
        cmbPosition = new javax.swing.JComboBox<>();
        cmdOtherOk = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtempID = new javax.swing.JTextField();
        txtDATE = new com.toedter.calendar.JDateChooser();
        txtINTIME = new javax.swing.JTextField();
        txtOUTTIME = new javax.swing.JTextField();
        cmbAttendence = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaily = new javax.swing.JTable();
        cmdSearchGo = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtSearchPID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        searchDay = new com.toedter.calendar.JDateChooser();
        cmdSearchById = new javax.swing.JButton();
        SearchByDate = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        txtFname1 = new javax.swing.JTextField();
        txtLname1 = new javax.swing.JTextField();
        bDay1 = new com.toedter.calendar.JDateChooser();
        txtNic1 = new javax.swing.JTextField();
        txtAddL4 = new javax.swing.JTextField();
        txtAddL5 = new javax.swing.JTextField();
        txtAddL6 = new javax.swing.JTextField();
        txtTeleNo1 = new javax.swing.JTextField();
        startDate1 = new com.toedter.calendar.JDateChooser();
        rdMale1 = new javax.swing.JRadioButton();
        rdFemale1 = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        choiceBox1 = new javax.swing.JComboBox<>();
        btnOk1 = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        docPanel1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtPosition1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtField1 = new javax.swing.JTextArea();
        labPanel1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtRoomNo1 = new javax.swing.JTextField();
        nursePanel1 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtWardId1 = new javax.swing.JTextField();
        cmbPosition1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emplyee_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel1.setText("Employee ID:");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel2.setText("FirstName:");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel3.setText("Last Name:");

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel4.setText("DOB:");

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel5.setText("NIC:");

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel6.setText("Address:");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel7.setText("Telephone No:");

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel8.setText("Started Date:");

        txtId.setEditable(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtFname.setEditable(false);

        txtLname.setEditable(false);

        bDay.setEnabled(false);

        txtNic.setEditable(false);

        txtAddL1.setEditable(false);
        txtAddL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddL1ActionPerformed(evt);
            }
        });

        txtAddL2.setEditable(false);

        txtAddL3.setEditable(false);

        txtTeleNo.setEditable(false);

        startDate.setEnabled(false);

        buttonGroup1.add(rdMale);
        rdMale.setText("Male");
        rdMale.setEnabled(false);

        buttonGroup1.add(rdFemale);
        rdFemale.setText("Female");
        rdFemale.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel9.setText("Sex:");

        choiceBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEARCH", "INSERT", "UPDATE", "DELETE" }));
        choiceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceBoxActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        docPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel20.setText("Position:");

        jLabel21.setText("SpecialField:");

        txtPosition.setEditable(false);
        txtPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPositionActionPerformed(evt);
            }
        });

        txtField.setEditable(false);
        txtField.setColumns(20);
        txtField.setRows(5);
        jScrollPane2.setViewportView(txtField);

        javax.swing.GroupLayout docPanelLayout = new javax.swing.GroupLayout(docPanel);
        docPanel.setLayout(docPanelLayout);
        docPanelLayout.setHorizontalGroup(
            docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        docPanelLayout.setVerticalGroup(
            docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(docPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        labPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lab_Technician_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel23.setText("Room No:");

        txtRoomNo.setEditable(false);

        javax.swing.GroupLayout labPanelLayout = new javax.swing.GroupLayout(labPanel);
        labPanel.setLayout(labPanelLayout);
        labPanelLayout.setHorizontalGroup(
            labPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(31, 31, 31)
                .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        labPanelLayout.setVerticalGroup(
            labPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(labPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nursePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nurse_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel25.setText("Ward id");

        txtWardId.setEditable(false);

        javax.swing.GroupLayout nursePanelLayout = new javax.swing.GroupLayout(nursePanel);
        nursePanel.setLayout(nursePanelLayout);
        nursePanelLayout.setHorizontalGroup(
            nursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nursePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(txtWardId, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        nursePanelLayout.setVerticalGroup(
            nursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nursePanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(nursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtWardId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        cmbPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Other", "Doctor", "Nurse", "Lab Technician" }));
        cmbPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPositionActionPerformed(evt);
            }
        });

        cmdOtherOk.setText("OK");
        cmdOtherOk.setEnabled(false);
        cmdOtherOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOtherOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(choiceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(cmbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtId)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtAddL1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtAddL2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddL3))
                                            .addComponent(txtFname, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLname, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNic, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel7)
                                                .addGap(6, 6, 6))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel9))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTeleNo, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rdMale)
                                                .addGap(44, 44, 44)
                                                .addComponent(rdFemale)))))
                                .addGap(11, 11, 11)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nursePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 329, Short.MAX_VALUE)
                        .addComponent(docPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(cmdOtherOk, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(324, 324, 324))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choiceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(170, 170, 170))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(docPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(bDay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtAddL1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddL2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddL3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(25, 25, 25)
                                        .addComponent(txtTeleNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(nursePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdOtherOk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rdMale)
                    .addComponent(rdFemale)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        jTabbedPane1.addTab("Employee_Details", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Serif", 1, 12))); // NOI18N

        jLabel10.setText("EmpId:");

        jLabel11.setText("Intime:");

        jLabel12.setText("OutTime:");

        jLabel13.setText("Date:");

        cmbAttendence.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INSERT", "UPDATE", "DELETE" }));
        cmbAttendence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAttendenceActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CANCEL");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(48, 48, 48))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAttendence, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtINTIME)
                            .addComponent(txtDATE, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtempID)
                            .addComponent(txtOUTTIME))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cmbAttendence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtempID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtDATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtINTIME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtOUTTIME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Daily Attendence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        tableDaily.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDaily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDailyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDaily);

        cmdSearchGo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel17.setText("Employee ID:");

        jLabel18.setText("Date:");

        cmdSearchById.setText("Search By ID");
        cmdSearchById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchByIdActionPerformed(evt);
            }
        });

        SearchByDate.setText("Search By Date");
        SearchByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchByDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cmdSearchGoLayout = new javax.swing.GroupLayout(cmdSearchGo);
        cmdSearchGo.setLayout(cmdSearchGoLayout);
        cmdSearchGoLayout.setHorizontalGroup(
            cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmdSearchGoLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchDay, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(txtSearchPID))
                .addGap(44, 44, 44)
                .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdSearchById, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SearchByDate, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cmdSearchGoLayout.setVerticalGroup(
            cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdSearchGoLayout.createSequentialGroup()
                .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cmdSearchGoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SearchByDate))
                    .addGroup(cmdSearchGoLayout.createSequentialGroup()
                        .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtSearchPID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdSearchById))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(cmdSearchGoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                    .addComponent(cmdSearchGo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdSearchGo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Attendence", jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Serif", 1, 12))); // NOI18N

        jLabel14.setText("EmpId:");

        jLabel15.setText("Intime:");

        jLabel16.setText("OutTime");

        jLabel19.setText("Date");

        jTextField4.setText("jTextField1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel15)
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(730, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Attendence", jPanel4);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emplyee_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N
        jPanel8.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(1000, 600));

        jLabel22.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel22.setText("Employee ID:");

        jLabel24.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel24.setText("FirstName:");

        jLabel26.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel26.setText("Last Name:");

        jLabel27.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel27.setText("DOB:");

        jLabel28.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel28.setText("NIC:");

        jLabel29.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel29.setText("Address:");

        jLabel30.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel30.setText("Telephone No:");

        jLabel31.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel31.setText("Started Date:");

        txtId1.setEditable(false);
        txtId1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId1ActionPerformed(evt);
            }
        });

        txtFname1.setEditable(false);

        txtLname1.setEditable(false);

        bDay1.setEnabled(false);

        txtNic1.setEditable(false);

        txtAddL4.setEditable(false);
        txtAddL4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddL4ActionPerformed(evt);
            }
        });

        txtAddL5.setEditable(false);

        txtAddL6.setEditable(false);

        txtTeleNo1.setEditable(false);

        startDate1.setEnabled(false);

        buttonGroup1.add(rdMale1);
        rdMale1.setText("Male");
        rdMale1.setEnabled(false);

        buttonGroup1.add(rdFemale1);
        rdFemale1.setText("Female");
        rdFemale1.setEnabled(false);

        jLabel32.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        jLabel32.setText("Sex:");

        choiceBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEARCH", "INSERT", "UPDATE", "DELETE" }));
        choiceBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceBox1ActionPerformed(evt);
            }
        });

        btnOk1.setText("OK");
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        btnCancel1.setText("CANCEL");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        docPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel33.setText("Position:");

        jLabel34.setText("SpecialField:");

        txtPosition1.setEditable(false);
        txtPosition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPosition1ActionPerformed(evt);
            }
        });

        txtField1.setEditable(false);
        txtField1.setColumns(20);
        txtField1.setRows(5);
        jScrollPane3.setViewportView(txtField1);

        javax.swing.GroupLayout docPanel1Layout = new javax.swing.GroupLayout(docPanel1);
        docPanel1.setLayout(docPanel1Layout);
        docPanel1Layout.setHorizontalGroup(
            docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(txtPosition1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        docPanel1Layout.setVerticalGroup(
            docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtPosition1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(docPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        labPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lab_Technician_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel35.setText("Room No:");

        txtRoomNo1.setEditable(false);

        javax.swing.GroupLayout labPanel1Layout = new javax.swing.GroupLayout(labPanel1);
        labPanel1.setLayout(labPanel1Layout);
        labPanel1Layout.setHorizontalGroup(
            labPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGap(31, 31, 31)
                .addComponent(txtRoomNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        labPanel1Layout.setVerticalGroup(
            labPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(labPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtRoomNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nursePanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nurse_Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 12))); // NOI18N

        jLabel36.setText("Ward id");

        txtWardId1.setEditable(false);

        javax.swing.GroupLayout nursePanel1Layout = new javax.swing.GroupLayout(nursePanel1);
        nursePanel1.setLayout(nursePanel1Layout);
        nursePanel1Layout.setHorizontalGroup(
            nursePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nursePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(txtWardId1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        nursePanel1Layout.setVerticalGroup(
            nursePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nursePanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(nursePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtWardId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        cmbPosition1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Other", "Doctor", "Nurse", "Lab Technician" }));
        cmbPosition1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPosition1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(choiceBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(cmbPosition1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtId1)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                                .addComponent(txtAddL4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtAddL5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtAddL6))
                                            .addComponent(txtFname1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLname1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNic1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bDay1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel30)
                                                .addGap(6, 6, 6))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel31)
                                                    .addComponent(jLabel32))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTeleNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(startDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(rdMale1)
                                                .addGap(44, 44, 44)
                                                .addComponent(rdFemale1)))))
                                .addGap(11, 11, 11)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nursePanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 329, Short.MAX_VALUE)
                        .addComponent(docPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(324, 324, 324))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choiceBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPosition1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFname1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(txtLname1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(170, 170, 170))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(docPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(labPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(nursePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel27))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(bDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNic1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtAddL4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddL5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAddL6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel29))
                                        .addGap(25, 25, 25)
                                        .addComponent(txtTeleNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(startDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOk1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(110, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(rdMale1)
                            .addComponent(rdFemale1))
                        .addGap(93, 93, 93))))
        );

        jTabbedPane2.addTab("Employee_Details", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAddL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddL1ActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void choiceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceBoxActionPerformed
        String isSelected = (String) choiceBox.getSelectedItem();
        String selectedPosition = (String) cmbPosition.getSelectedItem();
        String id;
        switch(isSelected){
            case("SEARCH"):{
                EmpDisableEditable();
                DocDisableEditable();
                LabDisableEditable();
                NurseDisableEditable();
                id = JOptionPane.showInputDialog("Please Enter the Emp ID:");
                if(id != null){
                    EmpSearch(id);
                    switch (selectedPosition) {
                        case "Doctor":
                            DocSearch(id);
                            break;
                        case "Lab Technician":
                            LabSearch(id);
                            break;
                        default:
                            NurseSearch(id);
                            break;
                    }
                }
                break;
            }
            case("INSERT"):{
                EmpMyClear();
                DocMyClear();
                LabMyClear();
                NurseMyClear();
                EmpEnableEditable(); 
                break;
            }
            case("UPDATE"):{
                EmpEnableEditable();
                id = JOptionPane.showInputDialog("Please Enter the Emp ID:");
            switch (selectedPosition) {
                case "Doctor":
                    EmpSearch(id);
                    DocSearch(id);
                    DocEnableEditable();
                    break;
                case "Lab Technician":
                    EmpSearch(id);
                    LabSearch(id);
                    LabEnableEditable();
                    break;
                case "Nurse":
                    EmpSearch(id);
                    NurseSearch(id);
                    NurseEnableEditable();
                    break;
                default:
                    EmpSearch(id);
                    break;
            }
                break;
            }
            case("DELETE"):{
                id = JOptionPane.showInputDialog("Please Enter the Emp ID:");
                EmpDisableEditable();
                DocDisableEditable();
                LabDisableEditable();
                NurseDisableEditable();
                switch (selectedPosition) {
                case "Doctor":
                    EmpSearch(id);
                    DocSearch(id);
                    DocDisableEditable();
                    break;
                case "Lab Technician":
                    EmpSearch(id);
                    LabSearch(id);
                    LabDisableEditable();
                    break;
                case "Nurse":
                    EmpSearch(id);
                    NurseSearch(id);
                    NurseDisableEditable();
                    break;
                default:
                    EmpSearch(id);
                    break;
            }
                break;
            }
        }
    }//GEN-LAST:event_choiceBoxActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        String isSelected = (String) choiceBox.getSelectedItem(); 
        key = txtId.getText();
        if(isSelected.equals("INSERT")){
           EmpInsertData();
           EmpMyClear();
        }
        else if(isSelected.equals("UPDATE")){
            EmpUpdate(key);
            EmpMyClear();
        }
        else if(isSelected.equals("DELETE")){
                EmpDelete(key);
                EmpMyClear();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Select a Option");
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        EmpMyClear();
        EmpDisableEditable();
        choiceBox.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPositionActionPerformed

    private void cmbPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPositionActionPerformed
        String isSelected = (String) cmbPosition.getSelectedItem();
        switch(isSelected){
            case("Other"):{
                docPanel.setEnabled(false);
                labPanel.setEnabled(false);
                nursePanel.setEnabled(false);
                DocDisableEditable();
                NurseDisableEditable();
                LabDisableEditable();
                cmdOtherOk.setEnabled(false);
                break;
            }
            case("Doctor"):{
                docPanel.setEnabled(true);
                labPanel.setEnabled(false);
                nursePanel.setEnabled(false);
                DocEnableEditable();
                NurseDisableEditable();
                LabDisableEditable();
                cmdOtherOk.setEnabled(true);
                break;
            }
            case("Nurse"):{
               docPanel.setEnabled(false);
               labPanel.setEnabled(false);
               nursePanel.setEnabled(true);
               DocDisableEditable();
               NurseEnableEditable();
               LabDisableEditable();
               cmdOtherOk.setEnabled(true);
               break;
            }
            case("Lab Technician"):{
                docPanel.setEnabled(false);
                labPanel.setEnabled(true);
                nursePanel.setEnabled(false);
                DocDisableEditable();
                NurseDisableEditable();
                LabEnableEditable();
                cmdOtherOk.setEnabled(true);
                break;
            }
        }
    }//GEN-LAST:event_cmbPositionActionPerformed

    private void txtId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1ActionPerformed

    private void txtAddL4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddL4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddL4ActionPerformed

    private void choiceBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choiceBox1ActionPerformed

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOk1ActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void txtPosition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPosition1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPosition1ActionPerformed

    private void cmbPosition1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPosition1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPosition1ActionPerformed

    private void cmdOtherOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOtherOkActionPerformed
        String isSelected = (String) choiceBox.getSelectedItem(); 
        String positionB = (String) cmbPosition.getSelectedItem();
        if(isSelected.equals("INSERT")){
            if(positionB.equals("Doctor")){
                DocInsert();
                DocMyClear();
            }    
            else if(positionB.equals("Nurse")){
                NurseInsert();
                NurseMyClear();
            }
            else{
                LabInsert();
                LabMyClear();
            }
        }
        else if(isSelected.equals("UPDATE")){
            if(positionB.equals("Doctor")){
               DocUpdate(key);
               DocMyClear();
            }
            else if(positionB.equals("Nurse")){
               NurseUpdate(key);
               NurseMyClear();
            }
            else{
                LabUpdate(key);
                LabMyClear();
            }
            
        }
        else if(isSelected.equals("DELETE")){
            if(positionB.equals("Doctor")){
                DocDelete(key);
                DocMyClear();
                
            }
            else if(positionB.equals("Nurse")){
               NurseDelete(key);
               NurseMyClear();
            }
            else{
                LabDelete(key);
                LabMyClear();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please Select a Option");
        }
        
        
    }//GEN-LAST:event_cmdOtherOkActionPerformed

    private void cmdSearchByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchByIdActionPerformed
       SearchById(txtSearchPID.getText());
    }//GEN-LAST:event_cmdSearchByIdActionPerformed

    private void SearchByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchByDateActionPerformed
        SearchByDate(new java.sql.Date(searchDay.getDate().getTime()));
    }//GEN-LAST:event_SearchByDateActionPerformed

    private void tableDailyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDailyMouseClicked
        try{
           int row = tableDaily.getSelectedRow();
           String tableClick=(tableDaily.getModel().getValueAt(row,0).toString());
           String sqlQuary ="Select * from Attendence where empId='"+tableClick+"'";
           PreparedStatement ps = conn.prepareStatement(sqlQuary);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               String Id = rs.getString("empId");
               txtempID.setText(Id);
               java.sql.Date bDate = rs.getDate("attendedDate");
               txtDATE.setDate(bDate);
               String intime = rs.getString("inTime");
               txtINTIME.setText(intime);
               String outtime = rs.getString("outTime");
               txtOUTTIME.setText(outtime);
           }
        }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_tableDailyMouseClicked

    private void cmbAttendenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAttendenceActionPerformed
        String isSelected = (String) cmbAttendence.getSelectedItem();
        switch(isSelected){
            case("INSERT"):{
                AttMyClear();
                AttEnableEditable();
                break;
            }
            case("UPDATE"):{
                AttEnableEditable();
                break;
            }
            case("DELETE"):{
                AttDisableEditable();
                break;
            }
            
        }
    }//GEN-LAST:event_cmbAttendenceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String choice = (String) cmbAttendence.getSelectedItem();
       if(choice.equals("INSERT")){
           AttInsert();
       }
       else if(choice.equals("UPDATE")){
           AttUpdate(txtempID.getText());
       }
       else if(choice.equals("DELETE")){
           AttDelete(txtempID.getText());
       }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    
     
    
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
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Employee().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SearchByDate;
    private com.toedter.calendar.JDateChooser bDay;
    private com.toedter.calendar.JDateChooser bDay1;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    public javax.swing.JButton btnOk;
    private javax.swing.JButton btnOk1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> choiceBox;
    private javax.swing.JComboBox<String> choiceBox1;
    private javax.swing.JComboBox<String> cmbAttendence;
    private javax.swing.JComboBox<String> cmbPosition;
    private javax.swing.JComboBox<String> cmbPosition1;
    private javax.swing.JButton cmdOtherOk;
    private javax.swing.JButton cmdSearchById;
    private javax.swing.JPanel cmdSearchGo;
    private javax.swing.JPanel docPanel;
    private javax.swing.JPanel docPanel1;
    private java.util.List<hospital_v2.Doctor> doctorList;
    private javax.persistence.Query doctorQuery;
    private java.util.List<hospital_v2.Employee_1> employee_1List;
    private java.util.List<hospital_v2.Employee_1> employee_1List1;
    private javax.persistence.Query employee_1Query;
    private javax.persistence.Query employee_1Query1;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel labPanel;
    private javax.swing.JPanel labPanel1;
    private javax.swing.JPanel nursePanel;
    private javax.swing.JPanel nursePanel1;
    private java.util.List<hospital_v2.Patient_1> patient_1List;
    private javax.persistence.Query patient_1Query;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdFemale1;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JRadioButton rdMale1;
    private com.toedter.calendar.JDateChooser searchDay;
    private com.toedter.calendar.JDateChooser startDate;
    private com.toedter.calendar.JDateChooser startDate1;
    private javax.swing.JTable tableDaily;
    private javax.swing.JTextField txtAddL1;
    private javax.swing.JTextField txtAddL2;
    private javax.swing.JTextField txtAddL3;
    private javax.swing.JTextField txtAddL4;
    private javax.swing.JTextField txtAddL5;
    private javax.swing.JTextField txtAddL6;
    private com.toedter.calendar.JDateChooser txtDATE;
    private javax.swing.JTextArea txtField;
    private javax.swing.JTextArea txtField1;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtFname1;
    private javax.swing.JTextField txtINTIME;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtLname1;
    private javax.swing.JTextField txtNic;
    private javax.swing.JTextField txtNic1;
    private javax.swing.JTextField txtOUTTIME;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtPosition1;
    private javax.swing.JTextField txtRoomNo;
    private javax.swing.JTextField txtRoomNo1;
    private javax.swing.JTextField txtSearchPID;
    private javax.swing.JTextField txtTeleNo;
    private javax.swing.JTextField txtTeleNo1;
    private javax.swing.JTextField txtWardId;
    private javax.swing.JTextField txtWardId1;
    private javax.swing.JTextField txtempID;
    // End of variables declaration//GEN-END:variables
}
