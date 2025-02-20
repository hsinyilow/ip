package astra.gui;


import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;

/**
 * Handles the GUI for the application.
 */
public class GuiMain extends Application {
    static Stage stage;
    static double timeDelay = 2;

    private Image astraImage = new Image(this.getClass().getResourceAsStream("/images/astra_icon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Astra");
            stage.getIcons().add(astraImage);
            stage.setScene(scene);
            this.stage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeApp() {

        //@@author FThompson-reused
        //Reused from https://stackoverflow.com/questions/52037435/return-a-value-after-javafx-timeline-has-finished
        // with minor modifications
        PauseTransition delayCloseApp = new PauseTransition(Duration.seconds(timeDelay));
        delayCloseApp.setOnFinished(event -> stage.close());
        delayCloseApp.play();
        //@@author
    }


}
