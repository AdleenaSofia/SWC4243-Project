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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OWN
 */
public class CalculateCaloriesController implements Initializable {

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
    private TextField txt_age;

    @FXML
    private RadioButton radio_male;

    @FXML
    private RadioButton radio_female;

    @FXML
    private Label lbl_c1;

    @FXML
    private Label lbl_c2;

    @FXML
    private Label lbl_c3;

    @FXML
    private Label lbl_c4;

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
        int age = Integer.parseInt(txt_age.getText());
        double height = Double.parseDouble(txt_height.getText());
        double weight = Double.parseDouble(txt_weight.getText());
        String gender = radio_male.isSelected() ? "Male" : "Female";
        double calory = calorieCheck(age,height,weight,gender);
        double[] clarify = Clarifying(calory);
        
        lbl_c1.setText(String.format("%.0f", clarify[0]));
        lbl_c2.setText(String.format("%.0f", clarify[1]));
        lbl_c3.setText(String.format("%.0f", clarify[2]));
        lbl_c4.setText(String.format("%.0f", clarify[3]));
    }
    
    private double calorieCheck(int age, double height,double weight, String gender){
        if(gender.equals("male")){
            return 66 + (13.7*weight)+(5*height)+(6.8*age);
        }else{
            return 655 + (9.6*weight)+(1.8*height)+(4.7*age);
        }
    }
    
    private double[] Clarifying(double calory){
        double c1 = calory;
        double c2 = 0.84*calory;
        double c3 = 0.69*calory;
        double c4 = 0.38*calory;
        
        return new double[]{c1, c2, c3, c4};
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
