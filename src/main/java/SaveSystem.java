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

    public static void Load(){
        filePath = Paths.get("data", "astraData.txt");
        if (Files.exists(filePath)){
            //load
            try {
                allLines = Files.readAllLines(filePath);

                for (int i = 0; i < allLines.size(); i++) {
                    //run the thing
                    TodoList.AddTask(allLines.get(i));
                }
            } catch (IOException e) {
                System.out.println("Error in reading a save file. Please try again later");
            }

        } else {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Error in creating a save file. Please try again later");
            }
        }

        write = true;

    }

    //append new items
    public static void Add(String data){
        if (!write) return;

        try(FileWriter fileWriter = new FileWriter(filePath.toString(), true)) {
            fileWriter.append(data);
            fileWriter.append(System.lineSeparator());
            allLines.add(data);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //update status (mark)
    public static void Update(int index, String data){
        if (!write) return;
        allLines.set(index, data);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    //delete task from save file
    public static void Delete(int index) {
        if(!write) return;
        allLines.remove(index);
        try {
            Files.write(filePath, allLines);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
