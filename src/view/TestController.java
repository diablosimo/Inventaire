/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author cneree
 */
public class TestController implements Initializable {
  @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML private Label label;
    /**
     * Initializes the controller class.
     * @param event     */
    
    public void radioAction(ActionEvent event)  {
             if(radio1.isSelected()){
            label.setText("you are a male!!");}
         if(radio2.isSelected()){
            label.setText("you are a Female!!");
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        // TODO
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    
}
