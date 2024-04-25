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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class CalculateBMIController implements Initializable {

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_BMI;

    @FXML
    private Button btn_Calories;

    @FXML
    private Button btn_user;
    
    @FXML
    private Label lbl_user;
    
    @FXML
    private TextField txt_height;

    @FXML
    private TextField txt_weight;

    @FXML
    private Button btn_calculate;
    
    @FXML
    private Label lbl_bmi;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    private String username;
    @FXML
    void BMI(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("CalculateBMI.fxml"));
        Scene NextPage = new Scene(loader);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(NextPage);
        appStage.show();
    }

    @FXML
    void Calories(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("CalculateCalories.fxml"));
        Scene NextPage = new Scene(loader);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(NextPage);
        appStage.show();
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
        Scene NextPage = new Scene(loader);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(NextPage);
        appStage.show();
    }

    @FXML
    void user(ActionEvent event) throws IOException {
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
    void calculate(ActionEvent event) {
        double height = Double.parseDouble(txt_height.getText());
        double weight = Double.parseDouble(txt_weight.getText());
        double bmi = CalculateBMI(height,weight);
        String clarify = clarifying(bmi);
        
        lbl_bmi.setText(clarify);
    }
    
    private double CalculateBMI (double height, double weight){
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    
    private String clarifying (double bmi){
        if (bmi < 18.4) {
                return "Underweight";
            } else if (bmi < 25.0) {
                return "Normal";
            } else if (bmi < 30.0) {
                return "Overweight";
            } else {
                return "Obesity";
            }
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
