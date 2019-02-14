/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import bean.Bouteille;
import bean.Emplacement;
import bean.ProduitChimique;
import helper.BouteilleFxHelper;
import helper.ProduitFxHelper;
import java.util.Date;
import service.BouteilleService;
import service.EmplacementService;
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
public class TestController implements Initializable {

    //ajouter produit
    @FXML
    private TextField id;
    @FXML
    private TextField libelle;
    @FXML
    private ComboBox<Emplacement> emplacementsBox;
   @FXML 
   private TextField typeP;
   
   
   //chercher produit
   @FXML
   private TextField formule;
    @FXML
    private TextField nomProduit;
   
   
   @FXML
   private TextField emplacement;

    @FXML
    TableView tableView = new TableView();

    private ProduitFxHelper produitFxHelper;
    
    DateUtil dateUtil = new DateUtil();
    ProduitChimiqueService produitService = new ProduitChimiqueService();
    BouteilleService bouteilleService = new BouteilleService();
    EmplacementService emplacementService = new EmplacementService();

    public void initHelper() {
        produitFxHelper = new ProduitFxHelper(tableView, produitService.findAll());
    }
    public void clear() {
        id.clear();
        libelle.clear();
        emplacementsBox.setValue(null);
        
        typeP.clear();
        
        formule.clear();
        nomProduit.clear();
       
        emplacement.clear();
    }

    public void alert(String contenttext, String headertext) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headertext);
        alert.setContentText(contenttext);
        alert.setTitle("Information dialog");
        alert.show();
    }

    public void Showselected() {
        ProduitChimique p = (ProduitChimique) tableView.getSelectionModel().getSelectedItem();
        id.setText(p.getId());
        libelle.setText(p.getNom());
        emplacementsBox.setValue(p.getEmplacement());
        typeP.setText(p.getTypeProduit());
    }

    public ProduitChimique getParam() {
      ProduitChimique p = new ProduitChimique(id.getText(), libelle.getText(), typeP.getText());
       p.setEmplacement(emplacementsBox.getValue());
        return p;

   }

    public void addProduct(ActionEvent event) {
       if (emplacementsBox.getValue() == null || id.getText().equals("") || libelle.getText().equals("") || typeP.getText().equals("")) {
           JOptionPane.showMessageDialog(null, "Please fill up all the fields !", "ERROR", JOptionPane.ERROR_MESSAGE);
      } else {
           ProduitChimique p = getParam();
           produitService.ajouterProduit(p.getId(),p.getNom(),p.getTypeProduit(), p.getEmplacement());
            produitFxHelper.create(p);
            clear();
            alert("Product has been successfully added", "DONE!");
        }
   }

   public void deleteProduct(ActionEvent event) {
        ProduitChimique p = (ProduitChimique) tableView.getSelectionModel().getSelectedItem();
        Alert aa = new Alert(Alert.AlertType.CONFIRMATION);
        aa.setHeaderText("WARNING!!");
        aa.setContentText("BY DELETING THIS PRODUCT, YOU WILL DELETE ALL THE ELEMENTS THAT ARE RELATED TO IT!");
        aa.setTitle("WARNING");
        Optional<ButtonType> result = aa.showAndWait();
        if (result.get() == ButtonType.OK) {
            produitService.deleteProduit(p);
            produitFxHelper.remove(p);
            clear();
            alert("Product has been successfully deleted", "Done!");
        } else {
            clear();
      }
    }

    public void updateProduct(ActionEvent event) {
    ProduitChimique p = (ProduitChimique) tableView.getSelectionModel().getSelectedItem();

        Emplacement e = emplacementsBox.getValue();
        p.setEmplacement(e);
        p.setId(id.getText());
        p.setNom(libelle.getText());
        p.setTypeProduit(typeP.getText());
        produitService.edit(p);
        tableView.refresh();
        clear();
        alert("Product has been successfully updated", "DONE!");
    }

    public void findByCriteria() {
        String idP = null;
        if (formule.getText() != null && !formule.getText().equals("")) {
            idP = formule.getText();
        }
        String emp = null;
        if (emplacement.getText() != null && !emplacement.getText().equals("")) {
            emp = emplacement.getText();
        }
        String nomP = null;
        if (nomProduit.getText() != null && !nomProduit.getText().equals("")) {
            nomP = nomProduit.getText();
        }
      
        produitFxHelper.setList(produitService.findByCriteria(idP, nomP, emp));
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
       emplacementsBox.setItems(FXCollections.observableArrayList(emplacementService.findAll()));
      
        initHelper();
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public TextField getLibelle() {
        return libelle;
    }

    public void setLibelle(TextField libelle) {
        this.libelle = libelle;
    }

   

    public TextField getTypeP() {
        return typeP;
    }

    public void setTypeP(TextField typeP) {
        this.typeP = typeP;
    }

   

    public TextField getFormule() {
        return formule;
    }

    public void setFormule(TextField formule) {
        this.formule = formule;
    }

    public TextField getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(TextField emplacement) {
        this.emplacement = emplacement;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public ProduitFxHelper getProduitFxHelper() {
        return produitFxHelper;
    }

    public void setProduitFxHelper(ProduitFxHelper produitFxHelper) {
        this.produitFxHelper = produitFxHelper;
    }

   


    public TextField getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(TextField nomProduit) {
        this.nomProduit = nomProduit;
    }

    public ComboBox<Emplacement> getEmplacementBox() {
        return emplacementsBox;
    }

    public void setEmplacementBox(ComboBox<Emplacement> emplacementBox) {
        this.emplacementsBox = emplacementBox;
    }

    

    
}
