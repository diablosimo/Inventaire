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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author cneree
 */
public class AddUserViewController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;

    UserService userService = new UserService();
    

    public User getParam() {
        if (radio1.isSelected()) {
            return new User(Integer.SIZE, lastName.getText(), firstName.getText(), email.getText(), password.getText(), radio1.getText(), Boolean.FALSE);
        }else{
            if (radio2.isSelected()) {
                return new User(Integer.SIZE,lastName.getText(), firstName.getText(), email.getText(), password.getText(), radio2.getText(),Boolean.FALSE);
            }
            else return new User(lastName.getText(), firstName.getText(), email.getText(), password.getText()," " );
        }
    }

    public void addUser(javafx.event.ActionEvent actionEvent) throws IOException {
        User user = getParam();
        int res = userService.createUser(user);
        if (res == 1) {
            JOptionPane.showMessageDialog(null, "User was added successfully!", null, JOptionPane.INFORMATION_MESSAGE);
            ViewLauncher.forward(actionEvent, "Login.fxml", this.getClass());
        }
    }

    @FXML
    public void retour(javafx.event.ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "HomeView.fxml", this.getClass());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public TextField getFirstName() {
        return firstName;
    }

    public void setFirstName(TextField firstName) {
        this.firstName = firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public void setLastName(TextField lastName) {
        this.lastName = lastName;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public RadioButton getRadio1() {
        return radio1;
    }

    public void setRadio1(RadioButton radio1) {
        this.radio1 = radio1;
    }

    public RadioButton getRadio2() {
        return radio2;
    }

    public void setRadio2(RadioButton radio2) {
        this.radio2 = radio2;
    }
    

}
