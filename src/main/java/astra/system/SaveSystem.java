package astra.system;

import static java.nio.file.Files.readAllLines;

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
    private static boolean write = false;
    private static List<String> allLines = new ArrayList<>();

    /**
     * Loads all the data from the save file.
     * Data is loaded into the specified task list.
     * @param path File path of the save file.
     * @param taskList The task list that the data is going to be saved.
     */
    public static void loadSaveFile(String path, TaskList taskList) {
        filePath = Paths.get(path);

        if (Files.exists(filePath)) {
            //load save file
            try {
                allLines = readAllLines(filePath);

                for (int i = 0; i < allLines.size(); i++) {
                    taskList.addTask(allLines.get(i));
                }
            } catch (IOException e) {
                Ui.feedbackError("Error in loading a save file.");
            }

        } else {
            //create new file
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                Ui.feedbackError("Error in creating a save file.");
            }
        }

        write = true;
    }

    /**
     * Adds new task data to the save file.
     * @param data The data that is being saved.
     */
    public static void add(String data) {
        if (!write) {
            return;
        }

        try (FileWriter fileWriter = new FileWriter(filePath.toString(), true)) {
            fileWriter.append(data);
            fileWriter.append(System.lineSeparator());
            allLines.add(data);
        } catch (IOException e) {
            Ui.feedbackError("Error in saving a task.");
        }
    }

    /**
     * Updates the specified task data in the save file.
     * @param index The index of the task.
     * @param data The new data that is being saved.
     */
    public static void update(int index, String data) {
        if (!write) {
            return;
        }
        allLines.set(index, data);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.feedbackError("Error in saving the changes.");
        }

    }

    /**
     * Deletes the specified task data from the save file.
     * @param index The index of the task.
     */
    public static void delete(int index) {
        if (!write) {
            return;
        }
        allLines.remove(index);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.feedbackError("Error in saving the changes.");
        }
    }
}
