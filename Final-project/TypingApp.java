import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TypingApp extends Application {

    private static final String[] WORDS = {"apple", "banana", "orange", "grape", "pear", "melon", "strawberry", "kiwi"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Typing Application");

        // Creating a StackPane as the root container for start screen
        StackPane startRoot = new StackPane();
        startRoot.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white;");

        // Creating a VBox to center the start button
        VBox startVBox = new VBox();
        startVBox.setAlignment(Pos.CENTER); // Aligns items to the center of the VBox

        // Creating the start button
        Button startButton = new Button("Press to Start");
        startButton.setStyle("-fx-background-color: #ffd700; -fx-text-fill: black; -fx-font-size: 18px; -fx-pref-width: 150px;");

        startButton.setOnAction(e -> {
            // Switching to difficulty selection screen
            primaryStage.setScene(createDifficultyScene(primaryStage));
        });

        startVBox.getChildren().add(startButton);
        startRoot.getChildren().add(startVBox);

        Scene startScene = new Scene(startRoot, 600, 400);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    private Scene createDifficultyScene(Stage primaryStage) {
        
        VBox difficultyVBox = new VBox(20);
        difficultyVBox.setAlignment(Pos.CENTER);
        difficultyVBox.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white;");

        Label difficultyLabel = new Label("Select your Difficulty");
        difficultyLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 20px;");
        VBox.setMargin(difficultyLabel, new javafx.geometry.Insets(20, 0, 0, 0));

        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button hardButton = new Button("Hard");
        easyButton.setFocusTraversable(false);
        mediumButton.setFocusTraversable(false);
        hardButton.setFocusTraversable(false);

        easyButton.setOnAction(e -> {
            Scene easyGameScene = EasyGameScreen.createEasyGameScene();
            primaryStage.setScene(easyGameScene);
            
        });

        mediumButton.setOnAction(e -> {
            Scene mediumGameScene = MediumGameScreen.createMediumGameScene();
            primaryStage.setScene(mediumGameScene);
        });

        hardButton.setOnAction(e -> {
            Scene hardGameScene = HardGameScreen.createHardGameScene();
            primaryStage.setScene(hardGameScene);
        });

        difficultyVBox.getChildren().addAll(difficultyLabel, easyButton, mediumButton, hardButton);

        Scene difficultyScene = new Scene(difficultyVBox, 600, 400);
        primaryStage.setScene(difficultyScene);
        return difficultyScene;



    }

    public static void main(String[] args) {
        launch(args);
    }
}
