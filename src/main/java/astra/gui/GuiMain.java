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

/**
 * Handles the GUI for the application.
 */
public class GuiMain extends Application {
    private static Stage mainStage;

    /** Time delay in seconds before closing the application. */
    private static double timeDelay = 1;

    private Image astraImage = new Image(this.getClass().getResourceAsStream("/images/astra_icon.png"));


    /**
     * Sets up the application.
     *
     * @param stage The main stage of the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Astra");
            stage.getIcons().add(astraImage);
            mainStage = stage;

            FXMLLoader fxmlLoader = new FXMLLoader(GuiMain.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the application.
     */
    public static void closeApp() {
        //@@author FThompson-reused
        //Reused from https://stackoverflow.com/questions/52037435/return-a-value-after-javafx-timeline-has-finished
        //with minor modifications
        PauseTransition delayCloseApp = new PauseTransition(Duration.seconds(timeDelay));
        delayCloseApp.setOnFinished(event -> mainStage.close());
        delayCloseApp.play();
        //@@author
    }


}
