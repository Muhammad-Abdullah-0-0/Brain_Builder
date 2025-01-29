package brain_builder.brain_builder;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NumberQuiz3Controller {
    public TextField TexfieldNumber1;
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
        if (input.equals("8")) {
            MyLabelNumber.setText("Your answer is correct!");
            MyLabelNumber.setStyle("-fx-text-fill: green; -fx-font-size: 18px;");
        } else if (!input.isEmpty()) {
            MyLabelNumber.setText("Try again!");
            MyLabelNumber.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
        } else {
            MyLabelNumber.setText(""); // Clear the label if no input
        }
    }


    public void Math_Back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NumberQuiz2.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void Math_next(MouseEvent mouseEvent) throws IOException {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            // Get the current stage from the event source
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading the FXML file: " + e.getMessage());
            e.printStackTrace();
        }

    }


    public TextField answerField1;
    public TextField answerField2;
    public Label feedbackLabel;

    private String userId; // To store the logged-in user's ID
    private int totalQuestions = 2;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void handleSubmit(MouseEvent mouseEvent) {
        int correctAnswers = 0;

        // Example quiz evaluation logic
        if (answerField1.getText().trim().equals("5")) {
            correctAnswers++;
        }
        if (answerField2.getText().trim().equals("10")) {
            correctAnswers++;
        }

        // Calculate score
        int score = (correctAnswers * 100) / totalQuestions;

        // Display feedback
        feedbackLabel.setText("Your score: " + score + "%");

        // Store the score in the database
        storeQuizMarks(userId, score);
    }

    private void storeQuizMarks(String userId, int marks) {
        Connection connection = null;
        try {
            connection = DButil.getConnection();
            String query = "INSERT INTO quiz_scores (userid, marks) VALUES (?, ?)";
            PreparedStatement preparedStatement = ((Connection) connection).prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, marks);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.closeConnection(connection);
        }
    }

}
