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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.UserService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author CHAACHAI Youssef <youssef.chaachai@gmail.com>
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    UserService userService = new UserService();

    public void login(ActionEvent actionEvent) throws IOException {
        User user = new User();
        user.setEmail(username.getText());
        user.setPassword(password.getText());
        int res = userService.seConnecter(user);
        if (res > 0) {
           // JOptionPane.showMessageDialog(null, "Welcome back Mr. " + username.getText(), "Login succeeded", JOptionPane.INFORMATION_MESSAGE);
            Session.updateAttribute(user, "connectedUser");
            System.out.println("haaaaaaaaaaaaaniiiiiiiiiiii");
                        ViewLauncher.forward(actionEvent, "HomeView.fxml", this.getClass());
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect!", "Login Faild", JOptionPane.ERROR_MESSAGE);
            System.out.println("mahaaaaaaaaaaaaaaaaniiiiiiiiiiiiiich "); }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

}
