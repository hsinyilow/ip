import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    protected LocalDateTime deadline;
    protected boolean keepTime = false;

    public DeadlineTask(String input) throws AstraException{

        if(input.startsWith("D ")) {
            String[] parseInput = input.split(" \\Q|\\E ");
            this.description = parseInput[2];
            this.done = parseInput[1].equals("true");
            this.deadline = LocalDateTime.parse(parseInput[3]);
            this.keepTime = parseInput[4].equals("true");
        } else {
            String[] parseInput = input.split("/by");
            if(parseInput.length != 2){
                throw new AstraException("Invalid Deadline Task command");
            }

            String descriptionResult = commandCheck(parseInput[0], 9);
            String deadlineResult = commandCheck(parseInput[1], 0);

            if(descriptionResult.isEmpty()){
                throw new AstraException("Invalid Task description");
            } else if (deadlineResult.isEmpty()) {
                throw new AstraException("Invalid Task deadline");
            }

            this.description = descriptionResult;
            parseInput = deadlineResult.split(" ");
            LocalDate date = LocalDate.parse(parseInput[0]);
            LocalTime time = LocalTime.MIN;
            if (parseInput.length != 1){
                time = LocalTime.of(Integer.parseInt(parseInput[1].substring(0,2)),
                        Integer.parseInt(parseInput[1].substring(2)));
                keepTime = true;
            }
            deadline = LocalDateTime.of(date, time);
        }


    }

    @Override
    public String displayTask() {

        return String.format("[D][%s] %s (by: %s)", (done? "X" : " "), description, getDeadline(keepTime));

    }

    public String getDeadline(boolean withTime){
        String time = deadline.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
        String date = deadline.toLocalDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        return withTime? String.format("%s %s", date, time) : String.format("%s", date);
    }

    @Override
    protected String saveString(){
        return "D | " + done + " | " + description + " | " + deadline + " | " + keepTime;
    }
}
