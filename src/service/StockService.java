/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Bouteille;
import bean.Stock;

/**
 *
 * @author cneree
 */
public class StockService extends AbstractFacade<Stock>{
     public StockService() {
        super(Stock.class);
    }
    
}
