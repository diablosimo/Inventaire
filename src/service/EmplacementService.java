/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Emplacement;

/**
 *
 * @author cneree
 */
public class EmplacementService extends AbstractFacade<Emplacement> {
    
      public EmplacementService() {
        super(Emplacement.class);
    }
        public void initDb(){
        for (int i = 0; i < 5; i++) {
           Emplacement emp = new Emplacement() ;
           emp.setId(i+1L);
           emp.setNomEmplacement("plaquart");
            create(emp);
        }
    }
}
