/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Emplacement;
import bean.LigneCommande;
import bean.ProduitChimique;
import bean.Stock;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author cneree
 */
public class ProduitChimiqueService extends AbstractFacade<ProduitChimique>{
     public ProduitChimiqueService() {
        super(ProduitChimique.class);
    }
      public int ajouterProduit(String id, String nom, String type,Emplacement e) {
        ProduitChimique p = new ProduitChimique();
        if (e != null) {
            p.setEmplacement(e);
            p.setId(id);
            p.setNom(nom);
            p.setTypeProduit(type);
            create(p);
            return 1;
        }
        return -1;
    }
       public List<ProduitChimique> findByCriteria(String idProduit, String nom,  String e) {
        String query = " SELECT p FROM ProduitChimique p WHERE 1 = 1 ";
        query += SearchUtil.addConstraint("p", "id", "=", idProduit);
        query += SearchUtil.addConstraint("p", "nom", "=", nom);
        query += SearchUtil.addConstraint("p", "emplacement.emplacement", "=", e);
        return getEntityManager().createQuery(query).getResultList();
    }
         public void deleteProduit(ProduitChimique produit) {
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        StockService stockService = new StockService();
        List<LigneCommande> ligneCommandes = ligneCommandeService.findAll();
        List<Stock> stocks = stockService.findAll();
        if (!ligneCommandes.isEmpty()) {
            for (LigneCommande ligneCommande : ligneCommandes) {
                if (ligneCommande.getProduitChimique().equals(produit)) {
                    ligneCommandeService.remove(ligneCommande);
                }
            }
        }
        if (!stocks.isEmpty()) {
            for (Stock stock : stocks) {
                if (stock.getProduitChimique().equals(produit)) {
                    stockService.remove(stock);
                }
            }
        }
        remove(produit);
    }
}
