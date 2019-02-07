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
     public List<Bouteille> findByCriteria(String idProduit, String nomProduit,  String code, String emplacement,Date dateEntrée) {
        String query = " SELECT b FROM Bouteille b WHERE 1 = 1 ";
        query += SearchUtil.addConstraint("b", "produitChimique.nom", "=", nomProduit);
          query += SearchUtil.addConstraint("b", "produitChimique.id", "=", idProduit);
        query += SearchUtil.addConstraint("b", "emplacement.nomEmplacement", "=", emplacement);
    query += SearchUtil.addConstraint("b", "code", "=", code);
    query += SearchUtil.addConstraintDate("b", "dateEntree","=", dateEntrée);
        return getEntityManager().createQuery(query).getResultList();
    }
     public int ajouterBouteille(Long id, String code, double qte, Date dateE,String idP,String nomP,String typeP,Long emplacementId) {
        ProduitChimique p = new ProduitChimique();
        Bouteille b = new Bouteille();
        ProduitChimiqueService ps= new ProduitChimiqueService();
        EmplacementService emplacementService = new EmplacementService();
        Emplacement e = emplacementService.find(emplacementId);
        if (e != null) {
            p.setId(idP);
            p.setNom(nomP);
            p.setTypeProduit(typeP);
            ps.create(p);
            b.setId(id);
            b.setCode(code);
            b.setDateEntree(dateE);
            b.setEmplacement(e);
            b.setQteInitial(qte);
            b.setProduitChimique(p);
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
        }
}}
