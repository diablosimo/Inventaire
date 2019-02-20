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
import java.util.Objects;
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
     private String code;
    @ManyToOne
    private ProduitChimique produitChimique;
    private Double qteInitial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEntree;
    
    @OneToMany(mappedBy = "bouteille")
    private List<LigneCommande> ligneCommandes;

    public Bouteille() {
    }

    public Bouteille(String code) {
        this.code = code;
    }

   

    public Bouteille( String code, Double qteInitial, Date dateEntree) {
        this.code = code;
        this.qteInitial = qteInitial;
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
        return qteInitial;
    }

    public void setQteInitial(Double qteInitial) {
        this.qteInitial = qteInitial;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bouteille other = (Bouteille) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bouteille{" + "code=" + code + ", qteInitial=" + qteInitial + ", dateEntree=" + dateEntree + '}';
    }

    

   
    
    
}
