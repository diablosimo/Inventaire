package helper;

import bean.Bouteille;
import java.util.List;

import javafx.scene.control.TableView;
import util.DateUtil;

public class BouteilleFxHelper extends AbstractFxHelper<Bouteille> {

    private static AbstractFxHelperItem[] titres;
    DateUtil dateU= new DateUtil();

    static {

        titres = new AbstractFxHelperItem[]{  
       
            new AbstractFxHelperItem("Formule chimique", "produitChimique"),
           
            new AbstractFxHelperItem("Code", "code"),
            new AbstractFxHelperItem("Date d'entreé", "dateEntree")

        };
    }

    public BouteilleFxHelper(TableView<Bouteille> table, List<Bouteille> list) {
        super(titres, table, list);
    }

    public BouteilleFxHelper(TableView<Bouteille> table) {
        super(titres, table);
    }
    
    
}

