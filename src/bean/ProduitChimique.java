/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author cneree
 */
@Entity
public class ProduitChimique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String nom;
    private String typeProduit;
    @OneToOne(mappedBy = "produitChimique")
    private Stock stock;
    @OneToMany(mappedBy = "produitChimique")
    private List<Bouteille> bouteilles;
    @OneToMany(mappedBy = "produitChimique")
    private List<LigneCommande> ligneCommandes;
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public List<Bouteille> getBouteilles() {
        if(bouteilles==null){
            bouteilles= new ArrayList();
        }
        return bouteilles;
    }

    public void setBouteilles(List<Bouteille> bouteilles) {
        this.bouteilles = bouteilles;
    }

    public List<LigneCommande> getLigneCommandes() {
         if (ligneCommandes == null) {
            ligneCommandes = new ArrayList();}
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

   

    public ProduitChimique(String id, String nom) {
        this.id = id;
        this.nom = nom;
       
    }

    public ProduitChimique(String id, String nom, String emplacement, String typeProduit) {
        this.id = id;
        this.nom = nom;
        this.typeProduit = typeProduit;
    }

    public ProduitChimique(String id) {
        this.id = id;
    }

    public ProduitChimique() {
    }

    public ProduitChimique(String id, String nom, String typeProduit) {
        this.id = id;
        this.nom = nom;
        this.typeProduit = typeProduit;
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
        if (!(object instanceof ProduitChimique)) {
            return false;
        }
        ProduitChimique other = (ProduitChimique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProduitChimique{" + "id=" + id + ", nom=" + nom +  ", typeProduit=" + typeProduit + '}';
    }

    
}
