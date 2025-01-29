import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveSystem {
    private static Path filePath;
    private static String fileName = "astraData.txt";

    private static boolean write = false;

    public static void Load(){
        filePath = Paths.get("data", fileName);
        if (Files.exists(filePath)){
            //load
            try {
                FileReader fileReader = new FileReader(filePath.toString());
                BufferedReader reader = new BufferedReader(fileReader);
                while (true) {
                    String temp = reader.readLine();
                    if (temp == null) return;
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
    }

    //update status (delete / mark)
    public static void Update(String data){
        if (!write) return;
    }
}
