package brain_builder.brain_builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MathZeroController {



    private MediaPlayer mediaPlayer;

    public void playingAudio(String audio) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // Ensure no overlap
            }

            String audioPath = Objects.requireNonNull(getClass().getResource("/Audio/" + audio)).toExternalForm();
            Media sound = new Media(audioPath);
            mediaPlayer = new MediaPlayer(sound);

            // Event logging for debugging
            mediaPlayer.setOnReady(() -> {
                System.out.println("Playing audio: " + audio);
                System.out.println("Duration: " + mediaPlayer.getMedia().getDuration().toSeconds() + " seconds");
                mediaPlayer.play();
            });
            mediaPlayer.setOnEndOfMedia(() -> System.out.println("Playback finished."));
            mediaPlayer.setOnStopped(() -> System.out.println("Playback stopped unexpectedly."));
            mediaPlayer.setOnError(() -> {
                System.out.println("MediaPlayer Error: " + mediaPlayer.getError());
                mediaPlayer.getError().printStackTrace();
            });
        } catch (Exception e) {
            System.out.println("Error playing audio: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void ZeroVoice(MouseEvent mouseEvent) {
        playingAudio("Zero.mp3");
    }

    public void Math_Back(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Math.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void Math_next(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Math_NumbersOne.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}


