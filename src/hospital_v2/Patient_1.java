/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_v2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author lakshan
 */
@Entity
@Table(name = "Patient", catalog = "hospital_info", schema = "")
@NamedQueries({
    @NamedQuery(name = "Patient_1.findAll", query = "SELECT p FROM Patient_1 p")
    , @NamedQuery(name = "Patient_1.findById", query = "SELECT p FROM Patient_1 p WHERE p.id = :id")
    , @NamedQuery(name = "Patient_1.findByFirstName", query = "SELECT p FROM Patient_1 p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Patient_1.findByLastName", query = "SELECT p FROM Patient_1 p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Patient_1.findByDob", query = "SELECT p FROM Patient_1 p WHERE p.dob = :dob")
    , @NamedQuery(name = "Patient_1.findByAddressL1", query = "SELECT p FROM Patient_1 p WHERE p.addressL1 = :addressL1")
    , @NamedQuery(name = "Patient_1.findByAddressL2", query = "SELECT p FROM Patient_1 p WHERE p.addressL2 = :addressL2")
    , @NamedQuery(name = "Patient_1.findByAddressL3", query = "SELECT p FROM Patient_1 p WHERE p.addressL3 = :addressL3")
    , @NamedQuery(name = "Patient_1.findByNic", query = "SELECT p FROM Patient_1 p WHERE p.nic = :nic")
    , @NamedQuery(name = "Patient_1.findBySex", query = "SELECT p FROM Patient_1 p WHERE p.sex = :sex")
    , @NamedQuery(name = "Patient_1.findByGuardianName", query = "SELECT p FROM Patient_1 p WHERE p.guardianName = :guardianName")
    , @NamedQuery(name = "Patient_1.findByGuardianTeleNo", query = "SELECT p FROM Patient_1 p WHERE p.guardianTeleNo = :guardianTeleNo")
    , @NamedQuery(name = "Patient_1.findByGuardianNIC", query = "SELECT p FROM Patient_1 p WHERE p.guardianNIC = :guardianNIC")
    , @NamedQuery(name = "Patient_1.findByWordId", query = "SELECT p FROM Patient_1 p WHERE p.wordId = :wordId")
    , @NamedQuery(name = "Patient_1.findByConfirmedBy", query = "SELECT p FROM Patient_1 p WHERE p.confirmedBy = :confirmedBy")
    , @NamedQuery(name = "Patient_1.findByAssignedTO", query = "SELECT p FROM Patient_1 p WHERE p.assignedTO = :assignedTO")
    , @NamedQuery(name = "Patient_1.findByAdmitDate", query = "SELECT p FROM Patient_1 p WHERE p.admitDate = :admitDate")
    , @NamedQuery(name = "Patient_1.findByAdmitTime", query = "SELECT p FROM Patient_1 p WHERE p.admitTime = :admitTime")})
public class Patient_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "addressL1")
    private String addressL1;
    @Column(name = "addressL2")
    private String addressL2;
    @Column(name = "addressL3")
    private String addressL3;
    @Column(name = "NIC")
    private String nic;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "guardianName")
    private String guardianName;
    @Column(name = "guardianTeleNo")
    private String guardianTeleNo;
    @Column(name = "guardianNIC")
    private String guardianNIC;
    @Column(name = "wordId")
    private String wordId;
    @Column(name = "confirmedBy")
    private String confirmedBy;
    @Column(name = "assignedTO")
    private String assignedTO;
    @Column(name = "admitDate")
    @Temporal(TemporalType.DATE)
    private Date admitDate;
    @Column(name = "admitTime")
    private String admitTime;

    public Patient_1() {
    }

    public Patient_1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        Date oldDob = this.dob;
        this.dob = dob;
        changeSupport.firePropertyChange("dob", oldDob, dob);
    }

    public String getAddressL1() {
        return addressL1;
    }

    public void setAddressL1(String addressL1) {
        String oldAddressL1 = this.addressL1;
        this.addressL1 = addressL1;
        changeSupport.firePropertyChange("addressL1", oldAddressL1, addressL1);
    }

    public String getAddressL2() {
        return addressL2;
    }

    public void setAddressL2(String addressL2) {
        String oldAddressL2 = this.addressL2;
        this.addressL2 = addressL2;
        changeSupport.firePropertyChange("addressL2", oldAddressL2, addressL2);
    }

    public String getAddressL3() {
        return addressL3;
    }

    public void setAddressL3(String addressL3) {
        String oldAddressL3 = this.addressL3;
        this.addressL3 = addressL3;
        changeSupport.firePropertyChange("addressL3", oldAddressL3, addressL3);
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        String oldNic = this.nic;
        this.nic = nic;
        changeSupport.firePropertyChange("nic", oldNic, nic);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        String oldSex = this.sex;
        this.sex = sex;
        changeSupport.firePropertyChange("sex", oldSex, sex);
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        String oldGuardianName = this.guardianName;
        this.guardianName = guardianName;
        changeSupport.firePropertyChange("guardianName", oldGuardianName, guardianName);
    }

    public String getGuardianTeleNo() {
        return guardianTeleNo;
    }

    public void setGuardianTeleNo(String guardianTeleNo) {
        String oldGuardianTeleNo = this.guardianTeleNo;
        this.guardianTeleNo = guardianTeleNo;
        changeSupport.firePropertyChange("guardianTeleNo", oldGuardianTeleNo, guardianTeleNo);
    }

    public String getGuardianNIC() {
        return guardianNIC;
    }

    public void setGuardianNIC(String guardianNIC) {
        String oldGuardianNIC = this.guardianNIC;
        this.guardianNIC = guardianNIC;
        changeSupport.firePropertyChange("guardianNIC", oldGuardianNIC, guardianNIC);
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        String oldWordId = this.wordId;
        this.wordId = wordId;
        changeSupport.firePropertyChange("wordId", oldWordId, wordId);
    }

    public String getConfirmedBy() {
        return confirmedBy;
    }

    public void setConfirmedBy(String confirmedBy) {
        String oldConfirmedBy = this.confirmedBy;
        this.confirmedBy = confirmedBy;
        changeSupport.firePropertyChange("confirmedBy", oldConfirmedBy, confirmedBy);
    }

    public String getAssignedTO() {
        return assignedTO;
    }

    public void setAssignedTO(String assignedTO) {
        String oldAssignedTO = this.assignedTO;
        this.assignedTO = assignedTO;
        changeSupport.firePropertyChange("assignedTO", oldAssignedTO, assignedTO);
    }

    public Date getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(Date admitDate) {
        Date oldAdmitDate = this.admitDate;
        this.admitDate = admitDate;
        changeSupport.firePropertyChange("admitDate", oldAdmitDate, admitDate);
    }

    public String getAdmitTime() {
        return admitTime;
    }

    public void setAdmitTime(String admitTime) {
        String oldAdmitTime = this.admitTime;
        this.admitTime = admitTime;
        changeSupport.firePropertyChange("admitTime", oldAdmitTime, admitTime);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient_1)) {
            return false;
        }
        Patient_1 other = (Patient_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hospital_v2.Patient_1[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
