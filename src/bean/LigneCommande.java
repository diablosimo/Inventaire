/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author cneree
 */
@Entity
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Bouteille bouteille;
    private Double QteCommande;
   
    @ManyToOne
    private ProduitChimique produitChimique;
    
    @ManyToOne
    private Commande commande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LigneCommande() {
    }

    public LigneCommande(Long id) {
        this.id = id;
    }

    public LigneCommande(Long id, Double QteCommande) {
        this.id = id;
        this.QteCommande = QteCommande;
    }

    public Bouteille getBouteille() {
        return bouteille;
    }

    public void setBouteille(Bouteille bouteille) {
        this.bouteille = bouteille;
    }

    public Double getQteCommande() {
        return QteCommande;
    }

    public void setQteCommande(Double QteCommande) {
        this.QteCommande = QteCommande;
    }

    public ProduitChimique getProduitChimique() {
        return produitChimique;
    }

    public void setProduitChimique(ProduitChimique produitChimique) {
        this.produitChimique = produitChimique;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
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
        if (!(object instanceof LigneCommande)) {
            return false;
        }
        LigneCommande other = (LigneCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", QteCommande=" + QteCommande + '}';
    }

    
    
}
