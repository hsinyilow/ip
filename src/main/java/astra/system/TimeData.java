package astra.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeData {

    LocalDateTime dateTime;

    boolean keepTime;

    public TimeData(String input){
        String[] splitData = input.split(" ");
        this.dateTime = LocalDateTime.parse(splitData[0]);
        this.keepTime = splitData[1].equals("true");
    }

    public TimeData(LocalDateTime dateTime, boolean keepTime){
        this.dateTime = dateTime;
        this.keepTime = keepTime;
    }

    public String displayTimeData(){
        String time = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return keepTime? String.format("%s %s", date, time) : String.format("%s", date);
    }

    public String saveData(){
        return String.format("%s %b", dateTime.toString(), keepTime);
    }
}
