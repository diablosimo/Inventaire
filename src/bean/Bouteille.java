/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author cneree
 */
@Entity
public class Bouteille implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ProduitChimique produitChimique;
    private String code;
    private Double QteInitial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntree;
    @OneToOne
    private Emplacement emplacement;
    @OneToMany(mappedBy = "bouteille")
    private List<LigneCommande> ligneCommandes;

    public Bouteille() {
    }

    public Bouteille(Long id) {
        this.id = id;
    }

    public Bouteille(Long id, String code, Double QteInitial, Date dateEntree) {
        this.id = id;
        this.code = code;
        this.QteInitial = QteInitial;
        this.dateEntree = dateEntree;
    }

    public List<LigneCommande> getLigneCommandes() {
         if (ligneCommandes == null) {
            ligneCommandes = new ArrayList();}
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProduitChimique getProduitChimique() {
        return produitChimique;
    }

    public void setProduitChimique(ProduitChimique produitChimique) {
        this.produitChimique = produitChimique;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getQteInitial() {
        return QteInitial;
    }

    public void setQteInitial(Double QteInitial) {
        this.QteInitial = QteInitial;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouteille)) {
            return false;
        }
        Bouteille other = (Bouteille) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bouteille{" + "id=" + id + ", code=" + code + ", QteInitial=" + QteInitial + ", dateEntree=" + dateEntree + '}';
    }

    
    
}
