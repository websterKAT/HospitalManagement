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
@Table(name = "Employee", catalog = "hospital_info", schema = "")
@NamedQueries({
    @NamedQuery(name = "Employee_1.findAll", query = "SELECT e FROM Employee_1 e")
    , @NamedQuery(name = "Employee_1.findByEmpId", query = "SELECT e FROM Employee_1 e WHERE e.empId = :empId")
    , @NamedQuery(name = "Employee_1.findByFirstName", query = "SELECT e FROM Employee_1 e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Employee_1.findByLastName", query = "SELECT e FROM Employee_1 e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Employee_1.findByDob", query = "SELECT e FROM Employee_1 e WHERE e.dob = :dob")
    , @NamedQuery(name = "Employee_1.findByNic", query = "SELECT e FROM Employee_1 e WHERE e.nic = :nic")
    , @NamedQuery(name = "Employee_1.findByAdressLine1", query = "SELECT e FROM Employee_1 e WHERE e.adressLine1 = :adressLine1")
    , @NamedQuery(name = "Employee_1.findByAddressLine2", query = "SELECT e FROM Employee_1 e WHERE e.addressLine2 = :addressLine2")
    , @NamedQuery(name = "Employee_1.findByAddressLine3", query = "SELECT e FROM Employee_1 e WHERE e.addressLine3 = :addressLine3")
    , @NamedQuery(name = "Employee_1.findByTeleNo", query = "SELECT e FROM Employee_1 e WHERE e.teleNo = :teleNo")
    , @NamedQuery(name = "Employee_1.findByStartedDate", query = "SELECT e FROM Employee_1 e WHERE e.startedDate = :startedDate")
    , @NamedQuery(name = "Employee_1.findBySex", query = "SELECT e FROM Employee_1 e WHERE e.sex = :sex")})
public class Employee_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "empId")
    private String empId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "NIC")
    private String nic;
    @Column(name = "adressLine1")
    private String adressLine1;
    @Column(name = "addressLine2")
    private String addressLine2;
    @Column(name = "addressLine3")
    private String addressLine3;
    @Column(name = "teleNo")
    private String teleNo;
    @Column(name = "startedDate")
    @Temporal(TemporalType.DATE)
    private Date startedDate;
    @Column(name = "sex")
    private String sex;

    public Employee_1() {
    }

    public Employee_1(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        String oldEmpId = this.empId;
        this.empId = empId;
        changeSupport.firePropertyChange("empId", oldEmpId, empId);
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        String oldNic = this.nic;
        this.nic = nic;
        changeSupport.firePropertyChange("nic", oldNic, nic);
    }

    public String getAdressLine1() {
        return adressLine1;
    }

    public void setAdressLine1(String adressLine1) {
        String oldAdressLine1 = this.adressLine1;
        this.adressLine1 = adressLine1;
        changeSupport.firePropertyChange("adressLine1", oldAdressLine1, adressLine1);
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        String oldAddressLine2 = this.addressLine2;
        this.addressLine2 = addressLine2;
        changeSupport.firePropertyChange("addressLine2", oldAddressLine2, addressLine2);
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        String oldAddressLine3 = this.addressLine3;
        this.addressLine3 = addressLine3;
        changeSupport.firePropertyChange("addressLine3", oldAddressLine3, addressLine3);
    }

    public String getTeleNo() {
        return teleNo;
    }

    public void setTeleNo(String teleNo) {
        String oldTeleNo = this.teleNo;
        this.teleNo = teleNo;
        changeSupport.firePropertyChange("teleNo", oldTeleNo, teleNo);
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        Date oldStartedDate = this.startedDate;
        this.startedDate = startedDate;
        changeSupport.firePropertyChange("startedDate", oldStartedDate, startedDate);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        String oldSex = this.sex;
        this.sex = sex;
        changeSupport.firePropertyChange("sex", oldSex, sex);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee_1)) {
            return false;
        }
        Employee_1 other = (Employee_1) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hospital_v2.Employee_1[ empId=" + empId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
