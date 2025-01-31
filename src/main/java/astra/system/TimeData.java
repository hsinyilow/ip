package astra.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeData {

    LocalDateTime dateTime;

    boolean keepTime;

    /**
     * Constructor for TimeData using save data.
     *
     * @param input line of save data.
     */
    public TimeData(String input){
        String[] splitData = input.split(" ");
        this.dateTime = LocalDateTime.parse(splitData[0]);
        this.keepTime = splitData[1].equals("true");
    }

    /**
     * Constructor for TimeData using date and boolean.
     *
     * @param dateTime LocalDateTime to save.
     * @param keepTime boolean to keep time in the display and in save file.
     */
    public TimeData(LocalDateTime dateTime, boolean keepTime){
        this.dateTime = dateTime;
        this.keepTime = keepTime;
    }

    /**
     * Display data.
     *
     * @return String of information for Ui.
     */
    public String displayTimeData(){
        String time = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return keepTime? String.format("%s %s", date, time) : String.format("%s", date);
    }

    /**
     * Save format of TimeData.
     * This string is saved locally in a txt file.
     *
     * @return String of data that is being saved.
     */
    public String saveData(){
        return String.format("%s %b", dateTime.toString(), keepTime);
    }
}
