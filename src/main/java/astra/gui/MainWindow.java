package astra.gui;

import java.util.ArrayList;

import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



/**
 * Controls for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private static ArrayList<String> messageList = new ArrayList<>();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private TaskList taskList = new TaskList();

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        SaveSystem.loadSaveFile("data/astraData.txt", taskList);
        MainWindow.messageList.clear();
        Ui.greet();
        handleAstraOutput();
    }

    /**
     * Handles all the user input.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();

        dialogContainer.getChildren().addAll(
                MessageBox.getUserDialog(userText)
        );

        userInput.clear();

        if (userText.equals("bye")) {
            Ui.end();
            handleAstraOutput();
            return;
        } else if (userText.equals("help")) {
            Ui.help();
            handleAstraOutput();
            return;
        }

        taskList.command(userText);
        handleAstraOutput();


    }

    /**
     * Handles the chatbot output.
     */
    private void handleAstraOutput() {

        StringBuilder combinedReply = new StringBuilder();
        int totalMessages = messageList.size();
        for (int i = 0; i < totalMessages; i++) {
            combinedReply.append(messageList.get(i));

            if (i == totalMessages - 1) {
                break;
            }

            combinedReply.append("\n");
        }

        dialogContainer.getChildren().add(MessageBox.getAstraDialog(combinedReply.toString()));


        messageList.clear();
    }

    /**
     * Adds message to the list that is being shown.
     * @param messages the message to show.
     */
    public static void addMessage(String... messages) {

        for (int i = 0; i < messages.length; i++) {
            messageList.add(messages[i]);
        }
    }
}
