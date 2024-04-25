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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class WelcomePageController implements Initializable {

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_BMI;

    @FXML
    private Button btn_calories;

    @FXML
    private Button btn_user;

    @FXML
    private Label lbl_user;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    private String username;
    
    @FXML
    void BMI(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculateBMI.fxml"));
        Parent root = loader.load();
        CalculateBMIController controller = loader.getController();
                
        controller.setUsername(username);
                
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Calories(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculateCalories.fxml"));
        Parent root = loader.load();
        CalculateCaloriesController controller = loader.getController();
                
        controller.setUsername(username);
                
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void User(ActionEvent event) throws IOException {
        username = null;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThirdPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePage.fxml"));
        Parent root = loader.load();
        WelcomePageController controller = loader.getController();
                
        controller.setUsername(username);
                
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    void setUsername(String username){
        try{
            conn = mysqlconnect.ConnectDb();
            String sql = "SELECT username FROM user WHERE username=?";
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            rs=pst.executeQuery();
            if (rs.next()) {
            String dbUsername = rs.getString("username");
            lbl_user.setText(dbUsername);
            }
        }catch(Exception e){e.printStackTrace();}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
