/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Bouteille;
import bean.Emplacement;
import bean.LigneCommande;
import bean.ProduitChimique;
import bean.Stock;
import java.util.Date;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author cneree
 */
public class BouteilleService extends AbstractFacade<Bouteille>{
     public BouteilleService() {
        super(Bouteille.class);
    }
     public List<Bouteille> findByCriteria( String nomProduit,  String code, Date dateEntrée) {
        String query = " SELECT b FROM Bouteille b WHERE 1 = 1 ";
        query += SearchUtil.addConstraint("b", "produitChimique.nom", "=", nomProduit);
    query += SearchUtil.addConstraint("b", "code", "=", code);
    query += SearchUtil.addConstraintDate("b", "dateEntree","=", dateEntrée);
        return getEntityManager().createQuery(query).getResultList();
    }
     public int ajouterBouteille( String code, double qte, Date dateE,String idP) {
      
        Bouteille b = new Bouteille();
        ProduitChimiqueService ps= new ProduitChimiqueService();
       
        ProduitChimique p = ps.find(idP);
        if (p != null) {
            b.setProduitChimique(p);
            b.setCode(code);
            b.setDateEntree(dateE);
            b.setQteInitial(qte);
            create(b);
            return 1;
        }
        return -1;
    }
     public void deleteBouteille(Bouteille bouteille) {
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        StockService stockService = new StockService();
        List<LigneCommande> ligneCommandes = ligneCommandeService.findAll();
        List<Stock> stocks = stockService.findAll();
        if (!ligneCommandes.isEmpty()) {
            for (LigneCommande ligneCommande : ligneCommandes) {
                if (ligneCommande.getBouteille().equals(bouteille)) {
                    ligneCommandeService.remove(ligneCommande);
                }
            }
        }if (!stocks.isEmpty()) {
            for (Stock stock : stocks) {
                if (stock.getProduitChimique().equals(bouteille.getProduitChimique())) {
                    stockService.remove(stock);
                }
            }
        }
        remove(bouteille);
}}
