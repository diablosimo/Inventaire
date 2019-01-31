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
import javax.persistence.OneToOne;

/**
 *
 * @author cneree
 */
@Entity
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double QteExistantProduit;
    private Double seuilAlerte;
    @OneToOne
    private ProduitChimique produitChimique;
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQteExistantProduit() {
        return QteExistantProduit;
    }

    public void setQteExistantProduit(Double QteExistantProduit) {
        this.QteExistantProduit = QteExistantProduit;
    }

    public Double getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(Double seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public ProduitChimique getProduitChimique() {
        return produitChimique;
    }

    public void setProduitChimique(ProduitChimique produitChimique) {
        this.produitChimique = produitChimique;
    }

    public Stock() {
    }

    public Stock(Long id) {
        this.id = id;
    }

    public Stock(Long id, Double QteExistantProduit, Double seuilAlerte) {
        this.id = id;
        this.QteExistantProduit = QteExistantProduit;
        this.seuilAlerte = seuilAlerte;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", QteExistantProduit=" + QteExistantProduit + ", seuilAlerte=" + seuilAlerte + '}';
    }

   
}
