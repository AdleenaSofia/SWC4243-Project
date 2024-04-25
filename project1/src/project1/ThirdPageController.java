/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project1;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class ThirdPageController implements Initializable {

     @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    void loginButton(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene login = new Scene(loginPage);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(login);
        appStage.show();
    }

    @FXML
    void signup(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene login = new Scene(loginPage);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(login);
        appStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
