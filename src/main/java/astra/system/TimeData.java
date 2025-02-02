package astra.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeData {

    LocalDateTime dateTime;

    boolean keepTime;

<<<<<<< HEAD
    public TimeData(String input) {
=======
    /**
     * Initializes the TimeData object from the save file.
     * @param input Data from the save file.
     */
    public TimeData(String input){
>>>>>>> branch-A-JavaDocNew
        String[] splitData = input.split(" ");
        this.dateTime = LocalDateTime.parse(splitData[0]);
        this.keepTime = splitData[1].equals("true");
    }

<<<<<<< HEAD
    public TimeData(LocalDateTime dateTime, boolean keepTime) {
=======
    /**
     * Initializes the TimeData object from the chatbot instance.
     * @param dateTime The date and time.
     * @param keepTime Whether time should be ignored in the dateTime variable.
     */
    public TimeData(LocalDateTime dateTime, boolean keepTime){
>>>>>>> branch-A-JavaDocNew
        this.dateTime = dateTime;
        this.keepTime = keepTime;
    }

<<<<<<< HEAD
    public String displayTimeData() {
=======
    /**
     * Formats the data in display format.
     * @return Formatted data string.
     */
    public String displayTimeData(){
>>>>>>> branch-A-JavaDocNew
        String time = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return keepTime? String.format("%s %s", date, time) : String.format("%s", date);
    }

    /**
     * Formats the data in save format.
     * @return Formatted data string.
     */
    public String saveData(){
        return String.format("%s %b", dateTime.toString(), keepTime);
    }
}
