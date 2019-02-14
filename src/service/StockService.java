/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ProduitChimique;
import bean.Stock;
import java.util.List;
import util.SearchUtil;

/**
 *
 * @author cneree
 */
public class StockService extends AbstractFacade<Stock>{
     public StockService() {
        super(Stock.class);
    }
           ProduitChimiqueService produitChimiqueService= new ProduitChimiqueService();

     public int ajouterStock(Long idStock,  String idProduit, double quantite) {
        if (findStockByProduit( idProduit) == null) {
            creerStock(idStock,  idProduit, quantite);
            return 1;
        } else {
            updateStock( idProduit, quantite);
            return 2;
        }
    }
     public Stock findStockByProduit(String idProduit) {
        ProduitChimique produit;
         produit = produitChimiqueService.find(idProduit);
        List<Stock> stocks = findAll();
        if ( produit == null) {
            return null;
        } else {
            for (int i = 0; i < stocks.size(); i++) {
                Stock stock = stocks.get(i);
                if (stock.getProduitChimique().equals(produit) ) {
                    return stocks.get(i);
                }
            }
            return null;
        }
    }
     public int creerStock(Long idStock, String idProduit, double quantite) {
        Stock stock = new Stock();
        ProduitChimique produit = produitChimiqueService.find(idProduit);
        if (produit != null ) {
            stock.setId(idStock);
            stock.setQteExistantProduit(quantite);
            stock.setProduitChimique(produit);
            create(stock);
            return 1;
        }
        return -1;
    }
     public void updateStock(String idProduit, double quantite) {
        Stock stock = findStockByProduit( idProduit);
        stock.setQteExistantProduit(stock.getQteExistantProduit()+ quantite);
        edit(stock);
    }
     public List<Stock> findByCriteria(Long idStock, String libelleProduit,  Double quantiteMax, Double quantiteMin) {
        String query = "SELECT s FROM Stock s WHERE 1 = 1 ";
        query += SearchUtil.addConstraint("s", "id", "=", idStock);
        query += SearchUtil.addConstraint("s", "produitChimique.id", "=", libelleProduit);
        query += SearchUtil.addConstraintMinMax("s", "quantite", quantiteMin, quantiteMax);
        return getEntityManager().createQuery(query).getResultList();
    }
}
