package helper;

import bean.Stock;
import java.util.List;

import javafx.scene.control.TableView;

public class StockFxHelper extends AbstractFxHelper<Stock> {

    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("Produit", "produitChimique"),
            new AbstractFxHelperItem("Quantity", "qteExistantProduit")
        };
    }
    
    
   
    public StockFxHelper(TableView<Stock> table, List<Stock> list) {
        super(titres, table, list);
    }

    public StockFxHelper(TableView<Stock> table) {
        super(titres, table);
    }

}
