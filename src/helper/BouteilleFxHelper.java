package helper;

import bean.Bouteille;
import java.util.List;

import javafx.scene.control.TableView;

public class BouteilleFxHelper extends AbstractFxHelper<Bouteille> {

    private static AbstractFxHelperItem[] titres;

    static {

        titres = new AbstractFxHelperItem[]{  
            new AbstractFxHelperItem("Nom", "produit.nom"),
            new AbstractFxHelperItem("Formule chimique", "produit.id"),
            new AbstractFxHelperItem("Emplacement", "emplacement.nomEmplacement"),
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
