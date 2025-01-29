package brain_builder.brain_builder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupSceneController {
    public Pane PastelBGBox01;
    public Label LoginLabelbutton;
    public PasswordField PasswordConfirmTextField;
    public PasswordField PasswordTextField;
    public Button SubmitButton;
    public Label UserNamelabel1;
    public Label PasswordLabel;
    public TextField NameTextField;
    public Label NameLabel;
    public Label PhoneNumLabel;
    public TextField PhoneNumTextField;
    public AnchorPane SignupBoxPane;
    public Pane PastelBGBox02;
    public AnchorPane SignInBG;
    public Label showOperation;

    public void SubmitAction(ActionEvent actionEvent) {
    }

    public void SubmitClicked(MouseEvent mouseEvent) {
     resetBorders();
     if(NameTextField.getText().trim().isEmpty()){
         setRedBorder(NameTextField);
         NameTextField.clear();
     }
     if(PhoneNumTextField.getText().trim().isEmpty()) {
         setRedBorder(PhoneNumTextField);
         PhoneNumTextField.clear();
     }
     if(PasswordConfirmTextField.getText().trim().isEmpty()){
            setRedBorder(PasswordConfirmTextField);
         PasswordConfirmTextField.clear();
     }
     if(PasswordTextField.getText().trim().isEmpty()){
            setRedBorder(PasswordTextField);
         PasswordTextField.clear();
     }
     boolean isvalid = false;
     String input = PhoneNumTextField.getText().trim();
     String name = NameTextField.getText().trim();
     String Password = PasswordTextField.getText().trim();
     String reEnteredPass = PasswordConfirmTextField.getText().trim();
     String Phone= null;
     String Email = null;

        Connection connection =null;
        try{
            connection = DButil.getConnection();
            if(isPhoneNumber(input)){
                isvalid= true;
                Phone = input;




            }else if(isEmail(input)){ isvalid =true;

                Email = input;
            }

            else{
                isvalid =false;
                setRedBorder(PhoneNumTextField);
                PhoneNumTextField.clear();
            }
            if(Password.equals(reEnteredPass)){
                isvalid = true;



            }else{
                isvalid =false;
                setRedBorder(PasswordTextField);
                PasswordTextField.clear();
                setRedBorder(PasswordConfirmTextField);
                PasswordConfirmTextField.clear();
            }




        if(isvalid==true){
            InsertData(connection,Email,name,Password,Phone);
            System.out.println("Data is inserted");
            showOperation.setText("Registration SuccessFul!");
            showOperation.setStyle("textFill:#07ee2d");
        }
        else{
            showOperation.setText("Registration Failed! Try Again");
            showOperation.setStyle("textFill:#FF0000");

        }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(connection);
        }

    }

    public void resetBorders(){
        NameTextField.setBorder(null);
        PhoneNumTextField.setBorder(null);
        PasswordConfirmTextField.setBorder(null);
        PasswordTextField.setBorder(null);


    }
    private boolean isPhoneNumber(String input){
        return input.matches("\\d{11}");
    }
    private void InsertData(Connection connection, String Email,String name, String Password, String phone) throws SQLException {

        String query = "INSERT INTO Login (email, name, Password, Phone) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,Email );
            preparedStatement.setString(2,name );
            preparedStatement.setString(3,Password );
            preparedStatement.setString(4,phone);
            preparedStatement.executeUpdate();
        }
    }




    private boolean isEmail(String input) {
        return input.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private void setRedBorder(TextField textField) {
        textField.setBorder(new Border(new BorderStroke(
                Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public void LoginLabelClick(MouseEvent mouseEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
            Scene SignupScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage)  LoginLabelbutton.getScene().getWindow();
            currentStage.setScene(SignupScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
