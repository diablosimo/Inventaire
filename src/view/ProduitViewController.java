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
public class ProduitViewController implements Initializable {

    //ajouter produit
    @FXML
    private TextField id;
    @FXML
    private TextField libelle;
    @FXML
    private ComboBox<Emplacement> emplacementsBox;
   @FXML
    private TextField codeP;
   @FXML 
   private TextField typeP;
   @FXML
   private TextField qteInit;
   
   //chercher produit
   @FXML
   private TextField formule;
    @FXML
    private TextField nomProduit;
   @FXML
   private TextField code;
   @FXML
   private TextField dateEntree;
   @FXML
   private TextField emplacement;

    @FXML
    TableView tableView = new TableView();

    private BouteilleFxHelper bouteilleFxHelper;
    
    DateUtil dateUtil = new DateUtil();
    ProduitChimiqueService produitService = new ProduitChimiqueService();
    BouteilleService bouteilleService = new BouteilleService();
    EmplacementService emplacementService = new EmplacementService();

    public void initHelper() {
        bouteilleFxHelper = new BouteilleFxHelper(tableView, bouteilleService.findAll());
    }
    public void clear() {
        id.clear();
        libelle.clear();
        emplacementsBox.setValue(null);
        codeP.clear();
        typeP.clear();
        qteInit.clear();
        formule.clear();
        nomProduit.clear();
        code.clear();
        dateEntree.clear();
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
        Bouteille b = (Bouteille) tableView.getSelectionModel().getSelectedItem();
        id.setText(b.getProduitChimique().getId());
        libelle.setText(b.getProduitChimique().getNom());
        emplacementsBox.setValue(b.getEmplacement());
        codeP.setText(b.getCode());
        typeP.setText(b.getProduitChimique().getTypeProduit());
        qteInit.setText(b.getQteInitial()+"");
        
    }

    public Bouteille getParam() {
      ProduitChimique p = new ProduitChimique(id.getText(), libelle.getText(), typeP.getText());
      Bouteille b=new Bouteille(Long.MIN_VALUE, codeP.getText(), new Double(qteInit.getText()),new Date());
      b.setProduitChimique(p);
       b.setEmplacement(emplacementsBox.getValue());
        return b;

   }

    public void addProduct(ActionEvent event) {
       if (emplacementsBox.getValue() == null || id.getText().equals("") || libelle.getText().equals("") || codeP.getText().equals("")|| typeP.getText().equals("")||qteInit.getText().equals("")) {
           JOptionPane.showMessageDialog(null, "Please fill up all the fields !", "ERROR", JOptionPane.ERROR_MESSAGE);
      } else {
           Bouteille b = getParam();
            bouteilleService.ajouterBouteille(b.getId(), b.getCode(),b.getQteInitial(), b.getDateEntree(), b.getProduitChimique().getId(), b.getProduitChimique().getNom(),b.getProduitChimique().getTypeProduit(), b.getEmplacement().getId());
            bouteilleFxHelper.create(b);
            clear();
            alert("Product has been successfully added", "DONE!");
        }
   }

   public void deleteProduct(ActionEvent event) {
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

    public void updateProduct(ActionEvent event) {
    Bouteille b = (Bouteille) tableView.getSelectionModel().getSelectedItem();
        b.setId(Long.MIN_VALUE);
        b.setCode(codeP.getText());
        b.setQteInitial(new Double(qteInit.getText()));
        Emplacement e = emplacementsBox.getValue();
        b.setEmplacement(e);
        ProduitChimique p = new ProduitChimique();
        p.setId(id.getText());
        p.setNom(libelle.getText());
        p.setTypeProduit(typeP.getText());
        b.setProduitChimique(p);
        bouteilleService.edit(b);
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
        String codeProduit=null;
        if(code.getText() !=null && !code.getText().equals("")){
            codeProduit=code.getText();
        }
        Date datePr = null;
        if(dateEntree.getText()!= null && !dateEntree.getText().equals("")){
            datePr = dateUtil.parse(dateEntree.getText());
        }
       
        bouteilleFxHelper.setList(bouteilleService.findByCriteria(idP, nomP, codeProduit, emp, datePr));
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

    public TextField getCodeP() {
        return codeP;
    }

    public void setCodeP(TextField codeP) {
        this.codeP = codeP;
    }

    public TextField getTypeP() {
        return typeP;
    }

    public void setTypeP(TextField typeP) {
        this.typeP = typeP;
    }

    public TextField getQteInit() {
        return qteInit;
    }

    public void setQteInit(TextField qteInit) {
        this.qteInit = qteInit;
    }

    public TextField getFormule() {
        return formule;
    }

    public void setFormule(TextField formule) {
        this.formule = formule;
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

    public ComboBox<Emplacement> getEmplacementBox() {
        return emplacementsBox;
    }

    public void setEmplacementBox(ComboBox<Emplacement> emplacementBox) {
        this.emplacementsBox = emplacementBox;
    }

    

    
}
