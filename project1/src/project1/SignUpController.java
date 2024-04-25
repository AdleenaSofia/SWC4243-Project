/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Label lbl_message;

    @FXML
    private Button btn_signup;

    @FXML
    private Button btn_login;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void login(ActionEvent event) throws IOException {
        Parent loginPage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene login = new Scene(loginPage);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(login);
        appStage.show();
    }

    @FXML
    void signup(ActionEvent event) {
        conn = mysqlconnect.ConnectDb();
        String sql = "INSERT into user (email,username,password) "
                + "values (?,?,?)";
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_email.getText());
            pst.setString(2,txt_username.getText());
            pst.setString(3,txt_password.getText());
            
            pst.execute();
            
            lbl_message.setText("Done Signing Up!!!");
        }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
