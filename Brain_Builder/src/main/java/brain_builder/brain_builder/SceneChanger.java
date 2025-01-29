package brain_builder.brain_builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SceneChanger {

    /**
     * Universal scene change function.
     *
     * @param mouseEvent The MouseEvent triggering the scene change.
     * @param fxmlFileName The name of the FXML file to load (including path relative to resources, e.g., "brain_builder/brain_builder/Math.fxml").
     * @param cssFileName (Optional) The name of the CSS file to apply (including path relative to resources, e.g., "Style.css").
     * @throws IOException If the FXML file is not found or fails to load.
     */
    public static void changeScene(MouseEvent mouseEvent, String fxmlFileName, String cssFileName) throws IOException {
        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource("/" + fxmlFileName));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        // Create a new scene with the loaded FXML
        Scene scene = new Scene(root);

        // Apply the CSS stylesheet if provided
        if (cssFileName != null && !cssFileName.isEmpty()) {
            scene.getStylesheets().add(SceneChanger.class.getResource("/" + cssFileName).toExternalForm());
        }

        // Set the new scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }
}
