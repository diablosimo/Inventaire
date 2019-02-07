/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Emplacement;
import java.util.List;

/**
 *
 * @author cneree
 */
public class EmplacementService extends AbstractFacade<Emplacement> {
    
      public EmplacementService() {
        super(Emplacement.class);
    }
         public void initDB() {
        creerEmplacement(2L, "Ar1/E1");
        creerEmplacement(3L, "Ar1/E2");
        creerEmplacement(4L, "Ar1/E3");
        creerEmplacement(5L, "Ar1/E4");
        creerEmplacement(6L, "Ar2/E1");
        creerEmplacement(7L, "Ar2/E2");
        creerEmplacement(8L, "Ar2/E4");
        creerEmplacement(9L, "Ar2/E5");
        creerEmplacement(10L, "Plaquart N6");
        creerEmplacement(11L, "Plaquart 3");
        creerEmplacement(12L, "Plaquart 4");
        creerEmplacement(13L, "Carton 1");
        creerEmplacement(14L, "Carton 2");
        creerEmplacement(15L, "Carton 3");
        creerEmplacement(16L, "Carton 4");
        creerEmplacement(17L, "Carton 5");
        creerEmplacement(18L, "Carton 6");
        creerEmplacement(19L, "Carton 7");
        creerEmplacement(20L, "Carton 8");
    }
    
        
        public List<String> findEmName(){
       return  getEntityManager().createQuery("SELECT e.nomEmplacement FROM Emplacement e ").getResultList();
       
    }
        public void creerEmplacement(Long id, String nomEmplacement) {
        Emplacement emplacement = new Emplacement();
        emplacement.setId(id);
        emplacement.setEmplacement(nomEmplacement);
        create(emplacement);
    }
}
