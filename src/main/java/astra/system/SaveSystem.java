package astra.system;

import astra.task.TaskList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveSystem {
    private static Path filePath;

    private static boolean write = false;
    private static List<String> allLines = new ArrayList<>();

    /**
     * Load the save data.
     * Load in save data if file is found.
     * Create new save data if file is not found.
     *
     * @param path file path
     * @param taskList task list to load the data into
     */
    public static void Load(String path, TaskList taskList) {
        filePath = Paths.get(path);

        if (Files.exists(filePath)) {
            //load save file
            try {
                allLines = Files.readAllLines(filePath);

                for (int i = 0; i < allLines.size(); i++) {
                    taskList.AddTask(allLines.get(i));
                }
            } catch (IOException e) {
                Ui.FeedbackError("Error in loading a save file.");
            }

        } else {
            //create new file
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                Ui.FeedbackError("Error in creating a save file.");
            }
        }

        write = true;
    }

    /**
     * Add to save data.
     *
     * @param data new data to add to save file.
     */
    //append new items
    public static void Add(String data){
        if (!write) return;

        try (FileWriter fileWriter = new FileWriter(filePath.toString(), true)) {
            fileWriter.append(data);
            fileWriter.append(System.lineSeparator());
            allLines.add(data);
        } catch (IOException e) {
            Ui.FeedbackError("Error in saving a task.");
        }
    }

    /**
     * Update save data.
     *
     * @param index index of line to change in save file.
     * @param data new data to change in save file.
     */
    //update changes
    public static void Update(int index, String data){
        if (!write) return;
        allLines.set(index, data);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.FeedbackError("Error in saving the changes.");
        }

    }

    /**
     * Delete specific save data.
     *
     * @param index index of line to delete in save file.
     */
    public static void Delete(int index) {
        if (!write) return;
        allLines.remove(index);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            Ui.FeedbackError("Error in saving the changes.");
        }
    }
}
