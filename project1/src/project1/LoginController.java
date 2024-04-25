/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project1;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class LoginController implements Initializable {

     @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btn_login;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void loginButton(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }
        
        
        try{
            conn = mysqlconnect.ConnectDb();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePage.fxml"));
                Parent root = loader.load();
                WelcomePageController controller = loader.getController();
                
                controller.setUsername(username);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }else
                JOptionPane.showMessageDialog(null,"Invalid Username or Password");
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
