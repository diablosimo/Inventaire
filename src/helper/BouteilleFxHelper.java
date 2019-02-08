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
            new AbstractFxHelperItem("Nom", "produitChimique.nom"),
            new AbstractFxHelperItem("Formule chimique", "produitChimique.id"),
            new AbstractFxHelperItem("Emplacement", "emplacement.emplacement"),
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
    
    @Override
public Object getValueAt(int rowIndex,int columnIndex){
    if(list!=null && rowIndex < list.size()) {
         if (columnIndex == 0) {
                return list.get(rowIndex).getProduitChimique().getNom();
            }
         if (columnIndex == 1) {
                return list.get(rowIndex).getProduitChimique().getId();
            }
         if (columnIndex == 2) {
                return list.get(rowIndex).getEmplacement().getEmplacement();
            }
         if (columnIndex == 4) {
            String dateS= dateU.formateDate("dd-MM-yyyy",list.get(rowIndex).getDateEntree());
                return dateS;
            }
          else {
                return super.getValueAt(rowIndex, columnIndex).toString();
            }
        }
        return "";
    }
}

