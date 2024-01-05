import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlarmClock {
    public static void main(String[] args) {
        Map<DayOfWeek, Map<String, LocalTime>> alarmTimes = new HashMap<>();

        // Example alarms for Monday demonstration
        Map<String, LocalTime> MondayAlarms = new HashMap<>();
        MondayAlarms.put("Wake-up", LocalTime.of(7, 0));
        MondayAlarms.put("Breakfast", LocalTime.of(8, 30));
        MondayAlarms.put("Meeting", LocalTime.of(10, 0));
        alarmTimes.put(DayOfWeek.MONDAY, MondayAlarms);

        // Example alarms for Monday demonstration
        Map<String, LocalTime> ThursdayAlarms = new HashMap<>();
        ThursdayAlarms.put("Exercise", LocalTime.of(7, 0));
        ThursdayAlarms.put("Breakfast", LocalTime.of(7, 30));
        ThursdayAlarms.put("Meeting", LocalTime.of(11, 03));
        alarmTimes.put(DayOfWeek.THURSDAY, ThursdayAlarms);

        // Other days with example alarms for demonstration
        alarmTimes.put(DayOfWeek.TUESDAY, new HashMap<>());
        alarmTimes.put(DayOfWeek.WEDNESDAY, new HashMap<>());
        alarmTimes.put(DayOfWeek.FRIDAY, new HashMap<>());
        alarmTimes.put(DayOfWeek.SATURDAY, new HashMap<>());
        alarmTimes.put(DayOfWeek.SUNDAY, new HashMap<>());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            ZonedDateTime now = ZonedDateTime.now();
            DayOfWeek today = now.getDayOfWeek();
            LocalTime currentTime = now.toLocalTime();

            if (alarmTimes.containsKey(today)) {
                Map<String, LocalTime> dailyAlarms = alarmTimes.get(today);

                for (Map.Entry<String, LocalTime> entry : dailyAlarms.entrySet()) {
                    String alarmLabel = entry.getKey();
                    LocalTime alarmTime = entry.getValue();

                    if (!currentTime.isBefore(alarmTime) && currentTime.isBefore(alarmTime.plusMinutes(1))) {
                        System.out.println("Alarm for " + alarmLabel + " ringing at " + alarmTime.format(DateTimeFormatter.ofPattern("h:mm a")) + "!");
                        System.out.println("Snooze for 10 minutes? (Enter '1' for Yes, '2' for No)");
                        String snoozeChoice = scanner.nextLine();

                        if (snoozeChoice.equals("1")) {
                            System.out.println("Snooze activated. New alarm in 10 minutes for " + alarmLabel + "!");
                            alarmTimes.get(today).put(alarmLabel, alarmTime.plusMinutes(10)); // Update alarm time
                            break; // Exit the loop to wait for the next alarm
                        } else {
                            break; // Exit the loop when the alarm time is reached
                        }
                    }
                }
            }
            // Sleep for a short duration to prevent excessive CPU usage
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }      
        }
     }
}