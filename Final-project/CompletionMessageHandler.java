import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CompletionMessageHandler {

    public static void showCompletionMessage(VBox root, long startTime) {
        long endTime = System.currentTimeMillis();
        long timeTakenSeconds = (endTime - startTime) / 1000;

        String message;
        Color textColor;
        Color backgroundColor;
        Color subBackgroundColor;

        if (timeTakenSeconds <= 30) {
            message = "Excellent!";
            textColor = Color.WHITE;
            backgroundColor = Color.GREEN;
            subBackgroundColor = Color.LIGHTGREEN;
        } else if (timeTakenSeconds <= 60) {
            message = "Good Job!";
            textColor = Color.WHITE;
            backgroundColor = Color.ORANGE;
            subBackgroundColor = Color.LIGHTYELLOW;
        } else {
            message = "Try More!";
            textColor = Color.WHITE;
            backgroundColor = Color.RED;
            subBackgroundColor = Color.LIGHTPINK;
        }

        Label resultLabel = new Label(message);
        resultLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: " + textColor.toString() + "; -fx-font-weight: bold;");
        resultLabel.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
        resultLabel.setPadding(new javafx.geometry.Insets(20));

        // Create a sub-background for better contrast with the text label
        VBox subBackground = new VBox();
        subBackground.setBackground(new Background(new BackgroundFill(subBackgroundColor, CornerRadii.EMPTY, null)));
        subBackground.getChildren().add(resultLabel);

        // Clear previous content and add the result label with animation
        root.getChildren().clear();
        root.getChildren().add(subBackground);

        // Fade in animation for the result label
        FadeTransition ft = new FadeTransition(Duration.millis(2000), resultLabel);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
}
