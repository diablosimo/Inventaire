/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.ProduitChimique;
import bean.Stock;
import helper.StockFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import net.sf.jasperreports.engine.JRException;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import service.ProduitChimiqueService;
import service.StockService;

/**
 * FXML Controller class
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
public class StockViewController implements Initializable {
//Ajouter 

    @FXML
    private TextField id;
    @FXML
    private ComboBox<ProduitChimique> product = new ComboBox<>();
    @FXML
    private TextField quantity;
//Chercher
    @FXML
    private TextField stockId;
    @FXML
    private TextField productName;
    @FXML
    private TextField maxQty;
    @FXML
    private TextField minQty;
    @FXML
    private TableView stockTableView;

    ProduitChimiqueService produitChimiqueService = new ProduitChimiqueService();
    StockService stockService = new StockService();
    private StockFxHelper stockFxHelper;

    public void clear() {
        id.clear();

        product.setValue(null);
        quantity.clear();
        stockId.clear();
        productName.clear();
        maxQty.clear();
        minQty.clear();
    }

    public void alert(String contenttext, String headertext) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headertext);
        alert.setContentText(contenttext);
        alert.setTitle("Information dialog");
        alert.show();
    }

    private void initHelper() {
        stockFxHelper = new StockFxHelper(stockTableView, stockService.findAll());
    }

    public void initComboBox1() {
        product.setItems(FXCollections.observableArrayList(produitChimiqueService.findAll()));
    }

    public Stock getParam() {
        return new Stock(new Long(id.getText()), new Double(quantity.getText()));
    }

    @FXML
    public void retour(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "HomeView.fxml", this.getClass());
    }

    public void addStock() {
        if (product.getValue() == null || id.getText().equals("") || quantity.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill up all the fields !", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Stock stock = getParam();
            String idProduit = product.getSelectionModel().getSelectedItem().getId();
            Long idStock = stock.getId();
            double quantite = stock.getQteExistantProduit();
            ProduitChimique produit = produitChimiqueService.find(idProduit);
            stock.setProduitChimique(produit);
            stockService.ajouterStock(idStock, idProduit, quantite);
            stockFxHelper.create(stock);
            clear();
            alert("Stock has been successfully added", "DONE!");
        }
    }

    public void tableMouseClicked() {
        Stock stock = stockFxHelper.getSelected();
        id.setText(stock.getId() + " ");
        quantity.setText(stock.getQteExistantProduit() + "");
        product.getSelectionModel().select(stock.getProduitChimique());
    }

    public void deleteStock() {
        Stock stock = (Stock) stockTableView.getSelectionModel().getSelectedItem();
        Alert aa = new Alert(Alert.AlertType.CONFIRMATION);
        aa.setHeaderText("WARNING!!");
        aa.setContentText("Are you sure you want to delete this stock ?");
        aa.setTitle("WARNING");
        Optional<ButtonType> result = aa.showAndWait();
        if (result.get() == ButtonType.OK) {
            stockService.remove(stock);
            stockFxHelper.remove(stock);
            clear();
            alert("Stock has been successfully deleted", "DONE!");
        } else {
            clear();
        }
    }

    public void updateStock() {
        Stock stock = getParam();
        String idProduit = product.getSelectionModel().getSelectedItem().getId();
        ProduitChimique produit = produitChimiqueService.find(idProduit);
        stock.setProduitChimique(produit);
        stockService.edit(stock);
        stockTableView.refresh();
        clear();
        alert("Stock has been successfully updated", "DONE!");
    }

    public void findByCriteria() {
        Long idStock = null;
        if (stockId.getText() != null && !stockId.getText().equals("")) {
            idStock = new Long(stockId.getText());
        }
        String idProduit = null;
        if (productName.getText() != null && !productName.getText().equals("")) {
            idProduit = productName.getText();
        }

        Double quantiteMin = null;
        if (minQty.getText() != null && !minQty.getText().equals("")) {
            quantiteMin = new Double(minQty.getText());
        }
        Double quantiteMax = null;
        if (maxQty.getText() != null && !maxQty.getText().equals("")) {
            quantiteMax = new Double(maxQty.getText());
        }
        stockFxHelper.setList(stockService.findByCriteria(idStock, idProduit, quantiteMin, quantiteMax));
        clear();
    }

    public void printbutton(ActionEvent event) throws JRException, IOException {
//        Map params = new HashMap();
//        UserService us = new UserService();
//        String iduser = ((User) Session.getAttribut("connectedUser")).getId();
//        String username = us.findUsername(iduser);
//        params.put("user", username);
//        JasperUtil.generatePdf(stockService.findAll(), "C:\\Users\\CHAÂCHAÏ\\Documents\\NetBeansProjects\\Stock2\\src\\report\\stocks_list.jasper", "D:\\StockReport.pdf", params, true);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        initComboBox1();
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public ComboBox<ProduitChimique> getProduct() {
        return product;
    }

    public TextField getQuantity() {
        return quantity;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public TextField getStockId() {
        return stockId;
    }

    public void setStockId(TextField stockId) {
        this.stockId = stockId;
    }

    public TextField getProductId() {
        return productName;
    }

    public void setProductId(TextField productId) {
        this.productName = productId;
    }

    public TextField getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(TextField maxQty) {
        this.maxQty = maxQty;
    }

    public TextField getMinQty() {
        return minQty;
    }

    public void setMinQty(TextField minQty) {
        this.minQty = minQty;
    }

    public TableView getStockTableView() {
        return stockTableView;
    }

    public void setStockTableView(TableView stockTableView) {
        this.stockTableView = stockTableView;
    }

}
