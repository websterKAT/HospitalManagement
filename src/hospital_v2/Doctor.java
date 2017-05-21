/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital_v2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author lakshan
 */
@Entity
@Table(name = "Doctor", catalog = "hospital_info", schema = "")
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")
    , @NamedQuery(name = "Doctor.findByDocId", query = "SELECT d FROM Doctor d WHERE d.docId = :docId")
    , @NamedQuery(name = "Doctor.findByPosition1", query = "SELECT d FROM Doctor d WHERE d.position1 = :position1")
    , @NamedQuery(name = "Doctor.findBySpecifiedField", query = "SELECT d FROM Doctor d WHERE d.specifiedField = :specifiedField")})
public class Doctor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "docId")
    private String docId;
    @Column(name = "position1")
    private String position1;
    @Column(name = "specified_field")
    private String specifiedField;

    public Doctor() {
    }

    public Doctor(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        String oldDocId = this.docId;
        this.docId = docId;
        changeSupport.firePropertyChange("docId", oldDocId, docId);
    }

    public String getPosition1() {
        return position1;
    }

    public void setPosition1(String position1) {
        String oldPosition1 = this.position1;
        this.position1 = position1;
        changeSupport.firePropertyChange("position1", oldPosition1, position1);
    }

    public String getSpecifiedField() {
        return specifiedField;
    }

    public void setSpecifiedField(String specifiedField) {
        String oldSpecifiedField = this.specifiedField;
        this.specifiedField = specifiedField;
        changeSupport.firePropertyChange("specifiedField", oldSpecifiedField, specifiedField);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docId != null ? docId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.docId == null && other.docId != null) || (this.docId != null && !this.docId.equals(other.docId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hospital_v2.Doctor[ docId=" + docId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
