import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    protected LocalDateTime[] timings = new LocalDateTime[2];
    protected boolean[] keepTime = {false, false};
    public EventTask(String input) throws AstraException{
        if(input.startsWith("E ")) {
            String[] parseInput = input.split(" \\Q|\\E ");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            timings[0]= LocalDateTime.parse(parseInput[3]);
            timings[1] = LocalDateTime.parse(parseInput[4]);
            keepTime[0] = parseInput[5].equals("true");
            keepTime[1] = parseInput[6].equals("true");
        } else {
            String[] parseInput = input.split("/");
            if(parseInput.length != 3 ||
                    !parseInput[1].startsWith("from") || !parseInput[2].startsWith("to")){
                throw new AstraException("Invalid Event Task command");
            }

            String descriptionResult = commandCheck(parseInput[0], 5);
            String[] timingResult = {commandCheck(parseInput[1], 4), commandCheck(parseInput[2], 2)};

            if(descriptionResult.isEmpty()){
                throw new AstraException("Invalid task description");
            } else if (timingResult[0].isEmpty()) {
                throw new AstraException("Invalid event start");
            } else if (timingResult[1].isEmpty()){
                throw new AstraException("Invalid event end");
            }

            this.description = descriptionResult;

            for (int i = 0; i < 2; i++) {
                parseInput = timingResult[i].split(" ");
                LocalDate date = LocalDate.parse(parseInput[0]);
                LocalTime time = LocalTime.MIN;
                if (parseInput.length != 1){
                    time = LocalTime.of(Integer.parseInt(parseInput[1].substring(0,2)),
                            Integer.parseInt(parseInput[1].substring(2)));
                    keepTime[i] = true;
                }
                timings[i] = LocalDateTime.of(date, time);
            }
        }

    }

    public String getDeadline(LocalDateTime input, boolean withTime){
        String time = input.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = input.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return withTime? String.format("%s %s", date, time) : String.format("%s", date);
    }

    @Override
    public String displayTask() {
        return String.format("[E][%s] %s (from: %s to: %s)", (done? "X" : " "), description,
                getDeadline(timings[0], keepTime[0]), getDeadline(timings[1], keepTime[1]));
    }

    @Override
    protected String saveString(){
        return "E | " + done + " | " + description + " | " + timings[0] + " | " + timings[1]
                + " | " + keepTime[0] + " | " + keepTime[1];
    }
}
