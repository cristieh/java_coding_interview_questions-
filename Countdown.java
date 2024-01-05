import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;

public class Countdown {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dateInput = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Welcome to the Christmas Countdown!");
            System.out.println("Please enter today's date in the format day/month/year, or type 'exit' to quit: ");
            dateInput = scanner.next();

            // Check if user wants to exit
            if (dateInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            String[] dateParts = dateInput.split("/");

            if (dateParts.length != 3) {
                System.out.println("Invalid date format! Please enter day/month/year.");
                continue;
            }

            try {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                LocalDate enteredDate = LocalDate.of(year, month, day);

                // Check for past dates
                if (!enteredDate.isAfter(LocalDate.now())) {
                    System.out.println("Invalid date! Please enter a date in the future.");
                    continue;
                }

                LocalDate christmas = LocalDate.of(year, 12, 25);
                long daysUntilChristmas = ChronoUnit.DAYS.between(enteredDate, christmas);

                if (daysUntilChristmas >= 0) {
                    System.out.println("Santa is coming! There are " + daysUntilChristmas + " days left until Christmas!");
                } else {
                    System.out.println("Christmas has already passed this year!");
                }

                validInput = true;
            } catch (NumberFormatException | DateTimeException e) {
                System.out.println("Invalid input! Please enter valid integers for day, month, and year.");
            }
        }

        scanner.close();
        if (validInput) {
            System.out.println("Countdown complete. Happy Holidays!");
        }
    }
}