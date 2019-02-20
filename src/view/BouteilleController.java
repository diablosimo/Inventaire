/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import bean.Bouteille;
import bean.ProduitChimique;
import helper.BouteilleFxHelper;
import java.util.Date;
import service.BouteilleService;
import service.ProduitChimiqueService;
import java.io.IOException;
//import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import util.DateUtil;

/**
 * FXML Controller class
 *
 * @author Cneree
 */
public class BouteilleController implements Initializable {

    //ajouter Bouteille
  
   @FXML
    private TextField codeP;
   @FXML
   private TextField qteInit;
    @FXML
    private ComboBox<ProduitChimique> produitsBox;
   
   
   //chercher produit
   
    @FXML
    private TextField nomProduit;
   @FXML
   private TextField code;
   @FXML
   private TextField dateEntree;
   

    @FXML
    TableView tableView = new TableView();

    private BouteilleFxHelper bouteilleFxHelper;
    
    DateUtil dateUtil = new DateUtil();
    ProduitChimiqueService produitService = new ProduitChimiqueService();
    BouteilleService bouteilleService = new BouteilleService();

    public void initHelper() {
        bouteilleFxHelper = new BouteilleFxHelper(tableView, bouteilleService.findAll());
    }
    public void clear() {
    
        produitsBox.setValue(null);
        codeP.clear();
      
        qteInit.clear();
      
        nomProduit.clear();
        code.clear();
        dateEntree.clear();
        
    }

    public void alert(String contenttext, String headertext) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headertext);
        alert.setContentText(contenttext);
        alert.setTitle("Information dialog");
        alert.show();
    }

    public void Showselected() {
        Bouteille b = (Bouteille) tableView.getSelectionModel().getSelectedItem();
        produitsBox.setValue(b.getProduitChimique());
        codeP.setText(b.getCode());
        qteInit.setText(b.getQteInitial()+"");
        
    }

    public Bouteille getParam() {
      Bouteille b=new Bouteille( codeP.getText(), new Double(qteInit.getText()),new Date());
       b.setProduitChimique(produitsBox.getValue());
        return b;

   }

    public void addBouteille(ActionEvent event) {
       if (produitsBox.getValue() == null || codeP.getText().equals("")|| qteInit.getText().equals("")) {
           JOptionPane.showMessageDialog(null, "Please fill up all the fields !", "ERROR", JOptionPane.ERROR_MESSAGE);
      } else {
           Bouteille b = getParam();
            bouteilleService.ajouterBouteille( b.getCode(),b.getQteInitial(), b.getDateEntree(), b.getProduitChimique().getId());
            bouteilleFxHelper.create(b);
            clear();
            alert("Product has been successfully added", "DONE!");
        }
   }

   public void deleteBouteille(ActionEvent event) {
        Bouteille b = (Bouteille) tableView.getSelectionModel().getSelectedItem();
        Alert aa = new Alert(Alert.AlertType.CONFIRMATION);
        aa.setHeaderText("WARNING!!");
        aa.setContentText("BY DELETING THIS PRODUCT, YOU WILL DELETE ALL THE ELEMENTS THAT ARE RELATED TO IT!");
        aa.setTitle("WARNING");
        Optional<ButtonType> result = aa.showAndWait();
        if (result.get() == ButtonType.OK) {
            bouteilleService.deleteBouteille(b);
            bouteilleFxHelper.remove(b);
            clear();
            alert("Product has been successfully deleted", "Done!");
        } else {
            clear();
      }
    }

    public void updateBouteille(ActionEvent event) {
    Bouteille b = (Bouteille) tableView.getSelectionModel().getSelectedItem();

        b.setCode(codeP.getText());
        b.setQteInitial(new Double(qteInit.getText()));
        ProduitChimique p = produitsBox.getValue();
        b.setProduitChimique(p);
        bouteilleService.edit(b);
        tableView.refresh();
        clear();
        alert("Product has been successfully updated", "DONE!");
    }

    public void findByCriteria() {
      
        String nomP = null;
        if (nomProduit.getText() != null && !nomProduit.getText().equals("")) {
            nomP = nomProduit.getText();
        }
        String codeProduit=null;
        if(code.getText() !=null && !code.getText().equals("")){
            codeProduit=code.getText();
        }
        Date datePr = null;
        if(dateEntree.getText()!= null && !dateEntree.getText().equals("")){
            datePr = dateUtil.parse(dateEntree.getText());
        }
       
        bouteilleFxHelper.setList(bouteilleService.findByCriteria( nomP, codeProduit, datePr));
        clear();
    }

    public void returnbutton(ActionEvent event) throws IOException {
      ViewLauncher.forward(event, "HomeView.fxml", getClass());
    }

    public void printbutton(ActionEvent event) throws JRException, IOException {
//        Map params = new HashMap();
//        UserService us = new UserService();
//      String iduser = ((User) Session.getAttribut("connectedUser")).getId();
//        String username = us.findUsername(iduser);
//        params.put("user", username);
//        JasperUtil.generatePdf(produitService.findAll(), "C:\\Users\\CHAÂCHAÏ\\Documents\\NetBeansProjects\\Stock2\\src\\report\\products_list.jasper", "D:\\ProductReport.pdf", params, true);
   }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       produitsBox.setItems(FXCollections.observableArrayList(produitService.findAll()));
      
        initHelper();
    }

   

    public TextField getCodeP() {
        return codeP;
    }

    public void setCodeP(TextField codeP) {
        this.codeP = codeP;
    }

   

    public TextField getQteInit() {
        return qteInit;
    }

    public void setQteInit(TextField qteInit) {
        this.qteInit = qteInit;
    }

   

    public TextField getCode() {
        return code;
    }

    public void setCode(TextField code) {
        this.code = code;
    }

    public TextField getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(TextField dateEntree) {
        this.dateEntree = dateEntree;
    }


    public TextField getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(TextField nomProduit) {
        this.nomProduit = nomProduit;
    }

    public ComboBox<ProduitChimique> getProduitsBox() {
        return produitsBox;
    }

    public void setProduitsBox(ComboBox<ProduitChimique> produitsBox) {
        this.produitsBox = produitsBox;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public BouteilleFxHelper getBouteilleFxHelper() {
        return bouteilleFxHelper;
    }

    public void setBouteilleFxHelper(BouteilleFxHelper bouteilleFxHelper) {
        this.bouteilleFxHelper = bouteilleFxHelper;
    }

   

    

    
}
