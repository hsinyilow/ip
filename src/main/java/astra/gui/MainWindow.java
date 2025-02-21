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

        String filePath = "data/astraData.txt";
        SaveSystem.loadSaveFile(filePath, taskList);
        MainWindow.messageList.clear();

        Ui.greetUser();
        handleAstraOutput();
    }

    /**
     * Handles all the user input.
     */
    @FXML
    private void handleUserInput() {
        //Handles the user message output.
        String userText = handleUserOutput();

        //Handles the main commands unrelated to tasks.
        if (userText.equals("bye")) {
            Ui.sayGoodbye();
            handleAstraOutput();
            return;
        } else if (userText.equals("help")) {
            Ui.displayHelpMenu();
            handleAstraOutput();
            return;
        }

        //Handles the rest of the commands.
        taskList.command(userText);
        handleAstraOutput();
    }


    /**
     * Handles the user output.
     *
     * @return the command for Astra to process.
     */
    private String handleUserOutput() {
        String userText = userInput.getText();
        MessageBox userMessage = MessageBox.getUserDialog(userText);
        dialogContainer.getChildren().addAll(userMessage);
        userInput.clear();
        return userText;
    }

    /**
     * Handles the chatbot output.
     */
    private void handleAstraOutput() {
        //Combine all the strings into a single message.
        StringBuilder combinedReply = new StringBuilder();
        int totalMessages = messageList.size();
        for (int i = 0; i < totalMessages; i++) {
            combinedReply.append(messageList.get(i));
            if (i != totalMessages - 1) {
                combinedReply.append(System.lineSeparator());
            }
        }

        MessageBox astraMessage = MessageBox.getAstraDialog(combinedReply.toString());
        dialogContainer.getChildren().add(astraMessage);

        messageList.clear();
    }

    /**
     * Adds message to the list that is being shown.
     *
     * @param messages The collection of messages to show.
     */
    public static void addMessage(String... messages) {
        for (int i = 0; i < messages.length; i++) {
            messageList.add(messages[i]);
        }
    }
}
