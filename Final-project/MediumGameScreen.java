import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MediumGameScreen {

    private static final String[] WORDS = {"apple.", "banana!", "orange?", "grape,", "pear;", "melon:", "strawberry'", "kiwi-", "peach(", "plum)"};

    public static Scene createMediumGameScene() {
        VBox mediumGameVBox = new VBox(20);
        mediumGameVBox.setAlignment(Pos.CENTER);
        mediumGameVBox.setStyle("-fx-background-color: #2b2b2b;");

        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        Button backButton = new Button("Back to Start");
        backButton.setFocusTraversable(false);
        backButton.setStyle("-fx-background-color: #ffd700; -fx-text-fill: black;");
        backButton.setOnAction(e -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            TypingApp typingApp = new TypingApp();
            typingApp.start(primaryStage);
        });
        topRightBox.getChildren().add(backButton);

        FlowPane wordsFlowPane = new FlowPane();
        wordsFlowPane.setAlignment(Pos.CENTER);
        wordsFlowPane.setPrefWrapLength(600);

        List<Label> wordLabels = generateRandomWords(wordsFlowPane);

        long[] startTime = {0}; // Array to hold start time for completion tracking

        Scene scene = new Scene(mediumGameVBox, 600, 400);

        scene.setOnKeyTyped(event -> {
            String typedCharacter = event.getCharacter();
            if (typedCharacter.matches("[a-zA-Z.,!?';:\\-()]")) {
                updateWordLabels(wordLabels, typedCharacter);
                if (isAllWordsTyped(wordLabels)) {
                    CompletionMessageHandler.showCompletionMessage(mediumGameVBox, startTime[0]);
                }
                if (startTime[0] == 0) {
                    startTime[0] = System.currentTimeMillis();
                }
            }
        });

        mediumGameVBox.getChildren().addAll(topRightBox, wordsFlowPane);

        return scene;
    }



    private static List<Label> generateRandomWords(FlowPane wordsFlowPane) {
        List<Label> wordLabels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String randomWord = generateRandomWord(WORDS);
            Label wordLabel = new Label(randomWord);
            wordLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-padding: 0 10px 0 0;");
            wordLabels.add(wordLabel);
            wordsFlowPane.getChildren().add(wordLabel);
        }
        return wordLabels;
    }

    private static String generateRandomWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }

    private static void updateWordLabels(List<Label> wordLabels, String typedCharacter) {
        boolean characterMatched = false;

        for (Label wordLabel : wordLabels) {
            String word = wordLabel.getText();
            if (!word.isEmpty()) {
                char firstChar = word.charAt(0);
                if (Character.toString(firstChar).equalsIgnoreCase(typedCharacter)) {
                    if (!characterMatched) {
                        wordLabel.setText(word.substring(1)); // Remove the first character
                        wordLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: yellow; -fx-padding: 0 10px 0 0;");
                        characterMatched = true;
                    }
                } else {
                    wordLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-padding: 0 10px 0 0;");
                }
            }
        }
    }

    private static boolean isAllWordsTyped(List<Label> wordLabels) {
        for (Label wordLabel : wordLabels) {
            if (!wordLabel.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
