package astra.system;

import static java.nio.file.Files.readAllLines;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import astra.task.TaskList;


/**
 * Handles saving of local data.
 */
public class SaveSystem {
    private static Path filePath;
    private static boolean shouldWrite = false;

    /** Holds all the task data lines in save format while the application is running. */
    private static List<String> allLines = new ArrayList<>();

    /**
     * Loads all the data from the save file.
     * Data is loaded into the specified task list.
     *
     * @param path File path of the save file.
     * @param taskList The task list that the data is going to be saved.
     */
    public static void loadSaveFile(String path, TaskList taskList) {
        filePath = Paths.get(path);

        //Solution inspired by: https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
        File saveFile = new File(path);
        File saveDirectory = new File("data");

        if (!saveDirectory.getAbsoluteFile().exists()) {
            saveDirectory.mkdirs();
        }

        if (saveFile.getAbsoluteFile().exists()) {
            //load save file into the task list when a file exists.
            try {
                allLines = readAllLines(filePath);

                for (int i = 0; i < allLines.size(); i++) {
                    taskList.command(allLines.get(i));
                }
            } catch (IOException e) {
                Ui.displayAstraError("Error in loading a save file.");
            }
        } else {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                Ui.displayAstraError("Error in creating a save file.");
            }
        }

        shouldWrite = true;
    }

    /**
     * Adds new task data to the save file.
     *
     * @param data The data that is being saved.
     */
    public static void addData(String data) {
        if (!shouldWrite) {
            return;
        }
        assert !data.isEmpty() : "data should be in proper save format";

        try (FileWriter fileWriter = new FileWriter(filePath.toString(), true)) {
            fileWriter.append(data);
            fileWriter.append(System.lineSeparator());
            allLines.add(data);
        } catch (IOException e) {
            Ui.displayAstraError("Error in saving a task.");
        }
    }

    /**
     * Updates the specified task data in the save file.
     *
     * @param index The index of the task.
     * @param data The new data that is being saved.
     */
    public static void updateData(int index, String data) {
        if (!shouldWrite) {
            return;
        }
        assert !data.isEmpty() : "data should be in proper save format";

        allLines.set(index, data);

        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.displayAstraError("Error in saving the changes.");
        }

    }

    /**
     * Deletes the specified task data from the save file.
     *
     * @param index The index of the task.
     */
    public static void deleteData(int index) {
        if (!shouldWrite) {
            return;
        }

        assert index >= 0 : "Index should be valid";

        allLines.remove(index);

        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.displayAstraError("Error in saving the changes.");
        }
    }
}
