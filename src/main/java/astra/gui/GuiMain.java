package astra.gui;

import astra.system.SaveSystem;
import astra.system.Ui;
import astra.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class GuiMain extends Application{
    private TaskList taskList = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Scene scene;

    public static ArrayList<MessageBox> messageList = new ArrayList<>();
    //private Responder responder = new Responder();
    @Override
    public void start(Stage stage){
//        SaveSystem.loadSaveFile("data/astraData.txt", taskList);
//        MainWindow.messageList.clear();
//        Ui.greet();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            //fxmlLoader.<MainWindow>getController().setResponder(responder);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void handleUserInput() {
//        String userText = userInput.getText();
//        taskList.command(userText);
//        dialogContainer.getChildren().addAll(
//                MessageBox.getUserDialog(userText)
//        );
//        for (int i = 0; i < messageList.size(); i++) {
//            dialogContainer.getChildren().add(messageList.get(i));
//        }
//
//
//        userInput.clear();
//    }



}
