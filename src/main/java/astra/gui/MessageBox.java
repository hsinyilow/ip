package astra.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays the message.
 */
public class MessageBox extends HBox {
    //Images used are created by myself using this website: https://www.pixilart.com/draw
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.png"));
    private Image astraImage = new Image(this.getClass().getResourceAsStream("/images/astra_icon.png"));
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Holds all the current types of avatar available.
     */
    public enum ImageType { ASTRA, USER }

    /**
     * Initializes the message box.
     *
     * @param text The message to display.
     * @param imageType The feedback who sent the message.
     */
    private MessageBox(String text, ImageType imageType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        switch (imageType) {
        case ASTRA:
            displayPicture.setImage(astraImage);
            break;
        case USER:
            displayPicture.setImage(userImage);
            break;
        default:
            displayPicture.setImage(astraImage);
            break;
        }
    }

    /**
     * Handles the positioning of the message box.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        this.getChildren().setAll(nodes);
    }

    /**
     * Returns a MessageBox containing a user's message.
     *
     * @param message The message to be displayed in the dialogue.
     * @return MessageBox containing a user's message.
     */
    public static MessageBox getUserDialog(String message) {
        return new MessageBox(message, ImageType.USER);
    }

    /**
     * Returns a MessageBox containing Astra's message.
     *
     * @param message The message to be displayed in the dialogue.
     * @return MessageBox containing Astra's message.
     */
    public static MessageBox getAstraDialog(String message) {
        MessageBox astraMessage = new MessageBox(message, ImageType.ASTRA);
        astraMessage.flip();
        return astraMessage;
    }

}
