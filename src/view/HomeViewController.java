/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.UserService;
import util.Session;

/**
 * FXML Controller class
 *
 *  @author cneree
 */
public class HomeViewController implements Initializable {

    @FXML
    private Label welcome;

    UserService us = new UserService();

    

    @FXML
    public void stock(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "StockView.fxml", this.getClass());
    }

    @FXML
    public void logout(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Login.fxml", this.getClass());
    }

    @FXML
    public void addUser(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "AddUserView.fxml", this.getClass());
    }

    @FXML
    public void commande(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "CommandeView.fxml", this.getClass());
    }

    

    @FXML
    public void produit(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Produit.fxml", this.getClass());
    }

    @FXML
    public void bouteille(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Bouteille.fxml", this.getClass());
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      String email = ((User) Session.getAttribut("connectedUser")).getEmail();
        String name = us.findUsername(email);
       
        String genre =us.findGenre(email);
        if(genre.equals("Femme")){
            welcome.setText("Bienvenue Mme. " + name); 
        }else{
        welcome.setText("Bienvenue Mr. " + name);}
    }

    public Label getWelcome() {
        return welcome;
    }

    public void setWelcome(Label welcome) {
        this.welcome = welcome;
    }

}
