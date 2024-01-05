import java.util.Scanner;

public class CompoundingInterestCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double principal = 0, annualInterestRate = 0;
        int years = 0, compoundingPeriodsPerYear = 0;

        // Input for principal amount
        while (principal <= 0) {
            System.out.print("Enter the principal amount: ");
            principal = scanner.nextDouble();
            if (principal <= 0) {
                System.out.println("Invalid input! Amount must be positive.");
            }
        }

        // Input for annual interest rate with range check
        while (annualInterestRate <= 0 || annualInterestRate > 50) {
            System.out.print("Enter the annual interest rate as a decimal (0.05 to 50%): ");
            annualInterestRate = scanner.nextDouble();
            if (annualInterestRate <= 0) {
                System.out.println("Invalid input! Interest rate must be positive.");
            } else if (annualInterestRate > 50) {
                System.out.println("Invalid input! Interest rate is too high.");
            }
        }

        // Input for number of years
        while (years <= 0) {
            System.out.print("Enter the number of years: ");
            years = scanner.nextInt();
            if (years <= 0) {
                System.out.println("Invalid input! Years must be positive.");
            }
        }

        // Calculate and display simple interest
        double simpleInterest = principal * annualInterestRate * years;
        System.out.println("Simple Interest: " + simpleInterest);

        // Input for compounding periods per year
        while (compoundingPeriodsPerYear <= 0) {
            System.out.print("Enter the number of compounding periods per year: ");
            compoundingPeriodsPerYear = scanner.nextInt();
            if (compoundingPeriodsPerYear <= 0) {
                System.out.println("Invalid input! Compounding periods must be positive.");
            }
        }

        // Calculate the final amount using the compound interest formula
        double finalAmount = principal * Math.pow(1 + (annualInterestRate / compoundingPeriodsPerYear), compoundingPeriodsPerYear * years);

        // Calculate the total interest earned
        double interestEarned = finalAmount - principal;

        // Display results
        System.out.println("Original Principal: " + String.format("%.2f", principal));
        System.out.println("Total Interest Earned (Compound): " + String.format("%.2f", interestEarned));
        System.out.println("Total Amount After Interest (Compound): " + String.format("%.2f", finalAmount));

        scanner.close();
    }
}