
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {
    private static double MONTHLY_BUDGET = 100.00;  // Example monthly budget of $100.00
    private static double totalExpenses = 0;

    public static void main(String[] args) {
        Map<String, Expense> expenses = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Set Monthly Budget");
            System.out.println("4. View Remaining Monthly Budget");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Invalid amount. Please enter a numeric value.");
                        scanner.next(); // discard non-numeric input
                        System.out.print("Enter amount: ");
                    }
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    expenses.put(category, new Expense(category, amount, dateStr));
                    totalExpenses += amount;
                    System.out.println("Expense added!");
                    break;
                case 2:
                    System.out.println("Expenses:");
                    for (Expense exp : expenses.values()) {
                        System.out.println(exp);
                    }
                    System.out.println("Total Expenses: $" + totalExpenses);
                    System.out.println("Remaining Monthly Budget: $" + (MONTHLY_BUDGET - totalExpenses));
                    if (totalExpenses > MONTHLY_BUDGET) {
                        System.out.println("Warning: You have exceeded your monthly budget!");
                    }
                    break;
                case 3:
                    System.out.print("Enter monthly budget (decimal): ");
                    MONTHLY_BUDGET = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline
                    System.out.println("Monthly budget set to $" + MONTHLY_BUDGET);
                    break;
                case 4:
                    System.out.println("Remaining Monthly Budget: $" + (MONTHLY_BUDGET - totalExpenses));
                    break;
                case 5:
                    System.out.println("Exiting the budget tracker.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}