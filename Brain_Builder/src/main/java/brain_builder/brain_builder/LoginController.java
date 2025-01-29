package brain_builder.brain_builder;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Label SignUpLabelButton;
    public Pane PastelBGBox02Login;
    public Pane PastelBGBox01Login;
    public AnchorPane LoginBG;
    public AnchorPane SignupBoxPane;
    public TextField UsernameOrEmailTextfieldLogin;
    public Label UserEmailLoginLabel;
    public Label PasswordLoginLabel;
    public PasswordField PasswordLoginTextField;
    public Button LoginButton;
    @FXML
    private Label welcomeText;




    public void LoginAction(ActionEvent actionEvent) {

    }



    public void SignupLabelClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpScene.fxml"));
        Scene SignupScene = new Scene(fxmlLoader.load());
        Stage currentStage = (Stage)  SignUpLabelButton.getScene().getWindow();
        currentStage.setScene(SignupScene);
    }
    private void setRedBorder(TextField textField) {
        textField.setBorder(new Border(new BorderStroke(
                Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    private boolean isEmail(String input) {
        return input.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
    private boolean isPhoneNumber(String input){
        return input.matches("\\d{11}");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }

    public void SubmitClicked(MouseEvent mouseEvent) { String input=UsernameOrEmailTextfieldLogin.getText().trim();
        String Password=PasswordLoginTextField.getText().trim();

        if(input.isEmpty()|| Password.isEmpty()){
            setRedBorder(UsernameOrEmailTextfieldLogin);
            setRedBorder(PasswordLoginTextField);
            UsernameOrEmailTextfieldLogin.clear();
            PasswordLoginTextField.clear();
        }


        Connection connection= null;
        try{
            connection = DButil.getConnection();
            String query = "";
            if(isPhoneNumber(input)){
                query = "SELECT Password FROM login WHERE phone = ?";
            }else if(isEmail(input)){
                query ="SELECT Password FROM login WHERE email = ?";
            }
            else{
                setRedBorder(PasswordLoginTextField);
                setRedBorder(UsernameOrEmailTextfieldLogin);
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, input);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");

                // Compare the input password with the stored password
                if (Password.equals(storedPassword)) {
                    System.out.println("Successful");
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root=loader.load();
                    dashboardController ds=loader.getController();
                    Stage stage=(Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                    Scene scene=new Scene(root,600,400);
                    stage.setScene(scene);
                    stage.show();
                    PasswordLoginTextField.clear();
                    UsernameOrEmailTextfieldLogin.clear();
                } else {
                    setRedBorder(PasswordLoginTextField);
                    PasswordLoginTextField.clear();
                }
            } else {
                setRedBorder(UsernameOrEmailTextfieldLogin);
                UsernameOrEmailTextfieldLogin.clear();
            }

        }catch (SQLException | IOException e){
            e.printStackTrace();
        }finally {
            DButil.closeConnection(connection);
        }
    }
}