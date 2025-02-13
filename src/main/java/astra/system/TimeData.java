package astra.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles all the time data for the chatbot currently.
 */
public class TimeData {

    private LocalDateTime dateTime;

    private boolean shouldSaveTime;

    /**
     * Initializes the TimeData object from the save file.
     * @param input Data from the save file.
     */
    public TimeData(String input) {
        String[] splitData = input.split(" ");
        this.dateTime = LocalDateTime.parse(splitData[0]);
        this.shouldSaveTime = splitData[1].equals("true");
    }

    /**
     * Initializes the TimeData object from the chatbot instance.
     * @param dateTime The date and time.
     * @param shouldSaveTime Whether time should be ignored in the dateTime variable.
     */
    public TimeData(LocalDateTime dateTime, boolean shouldSaveTime) {
        this.dateTime = dateTime;
        this.shouldSaveTime = shouldSaveTime;
    }

    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    public String displayTimeData() {
        String time = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return shouldSaveTime ? String.format("%s %s", date, time) : String.format("%s", date);
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    public String saveData() {
        return String.format("%s %b", dateTime.toString(), shouldSaveTime);
    }
}
