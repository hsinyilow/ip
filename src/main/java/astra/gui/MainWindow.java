package astra.gui;

import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private TaskList taskList = new TaskList();
    //private Responder responder;
    public static ArrayList<String> messageList = new ArrayList<>();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.png"));
    private Image astraImage = new Image(this.getClass().getResourceAsStream("/images/astra_icon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        SaveSystem.loadSaveFile("data/astraData.txt", taskList);
        MainWindow.messageList.clear();
        Ui.greet();
        handleAstraOutput();
    }

    /** Injects the Duke instance */
//    public void setResponder(Responder r) {
//        responder = r;
//    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        taskList.command(userText);
        dialogContainer.getChildren().addAll(
                MessageBox.getUserDialog(userText)
        );
        for (int i = 0; i < messageList.size(); i++) {
            dialogContainer.getChildren().add(MessageBox.getAstraDialog(messageList.get(i)));
        }

        messageList.clear();

        userInput.clear();
    }

    private void handleAstraOutput() {
        for (int i = 0; i < messageList.size(); i++) {
            dialogContainer.getChildren().add(MessageBox.getAstraDialog(messageList.get(i)));
        }

        messageList.clear();
    }
}