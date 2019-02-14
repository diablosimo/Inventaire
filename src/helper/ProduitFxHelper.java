package helper;

import bean.ProduitChimique;
import java.util.List;

import javafx.scene.control.TableView;

public class ProduitFxHelper extends AbstractFxHelper<ProduitChimique> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Formule", "id"),
            new AbstractFxHelperItem("Name", "nom"),
            new AbstractFxHelperItem("Type de produit", "typeProduit"),
            new AbstractFxHelperItem("emplacement", "emplacement")

        };
    }

    public ProduitFxHelper(TableView<ProduitChimique> table, List<ProduitChimique> list) {
        super(titres, table, list);
    }

    public ProduitFxHelper(TableView<ProduitChimique> table) {
        super(titres, table);
    }

}
