package brain_builder.brain_builder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class NumberQuiz2Controlller {
    @FXML
    public TextField TexfieldNumber1;

    @FXML
    public Label MyLabelNumber;

    // Initialize method to set up event handling
    public void initialize() {
        // Add listener to the TextField for checking the answer dynamically
        TexfieldNumber1.setOnKeyReleased(this::checkAnswer);
    }

    // Method to check the user's input in the TextField
    private void checkAnswer(KeyEvent event) {
        String input = TexfieldNumber1.getText();

        // Check if the entered text is the correct answer (e.g., 5)
        if (input.equals("5")) {
            MyLabelNumber.setText("Your answer is correct!");
            MyLabelNumber.setStyle("-fx-text-fill: green; -fx-font-size: 18px;");
        } else if (!input.isEmpty()) {
            MyLabelNumber.setText("Try again!");
            MyLabelNumber.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
        } else {
            MyLabelNumber.setText(""); // Clear the label if no input
        }
    }

    // Method for Back button (add navigation logic here)
    public void Math_Back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NumberQuiz1.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Method for Next button (add navigation logic here)
    public void Math_next(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Numberquiz3.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public class NumberQuiz2Controller {
        public TextField TexfieldNumber1;
        public Label MyLabelNumber;


        private int userId; // Store the user's ID
        private String quizName = "Number Quiz";

        // Setter to receive the user ID
        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void checkAnswer() {
            String input = TexfieldNumber1.getText();
            if (input.equals("5")) {
                MyLabelNumber.setText("Your answer is correct!");

            } else {
                MyLabelNumber.setText("Your answer is incorrect!");

            }
        }
    }


}
