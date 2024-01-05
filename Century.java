import java.util.Scanner;

public class Century {
    public static void main(String[] args) {
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter years until they enter a -1 value
        while (true) {
            System.out.println("Enter a year (or -1 to exit):");

            // Read the input as a string
            String input = scanner.nextLine();

            int year;
            try {
                // Try to parse the input to an integer
                year = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Handle non-integer inputs
                System.out.println("Invalid input! Please enter a valid integer or -1 to exit.");
                continue;
            }

            // Check for the exit condition
            if (year == -1) {
                break;
            }

            // Validate positive year
            if (year <= 0) {
                System.out.println("Invalid input! Please enter a positive integer.");
                continue;
            }

            // Calculate the century based on the year
            int century = calculateCentury(year);

            // Get the suffix for the century
            String centurySuffix = getCenturySuffix(century);

            // Print the century and suffix
            System.out.printf("The century representing the year %d is the %d%s century.%n", year, century, centurySuffix);
        }

        // Close the scanner
        scanner.close();
    }

    // Method to calculate the century
    public static int calculateCentury(int year) {
        if (year % 100 == 0) {
            return year / 100;
        } else {
            return (year / 100) + 1;
        }
    }

    // Method to get the suffix for a given century
    public static String getCenturySuffix(int century) {
        if ((century % 100) == 11 || (century % 100) == 12 || (century % 100) == 13) {
            return "th";
        }
        switch (century % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}