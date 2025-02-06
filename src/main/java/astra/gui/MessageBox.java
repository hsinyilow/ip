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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.png"));
    private Image astraImage = new Image(this.getClass().getResourceAsStream("/images/astra_icon.png"));
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initializes the message box.
     * @param text the message to display.
     * @param image feedback who sent the message.
     */
    private MessageBox(String text, int image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (image == 1) {
            displayPicture.setImage(userImage);
        } else {
            displayPicture.setImage(astraImage);
        }

    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static MessageBox getUserDialog(String s) {
        return new MessageBox(s, 1);
    }

    public static MessageBox getAstraDialog(String s) {
        MessageBox mb = new MessageBox(s, 2);
        mb.flip();
        mb.setAlignment(Pos.CENTER_LEFT);
        return mb;
    }

}
