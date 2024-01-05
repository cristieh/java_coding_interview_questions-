import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CSVReader {

    public static ArrayList<String[]> readCSV(String filePath) {
        ArrayList<String[]> contents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                contents.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public static void modifyCSV(String inputFilePath, String outputFilePath, String nameField, String phoneField, String emailField) {
        ArrayList<String[]> data = readCSV(inputFilePath);
    
        try (FileWriter fw = new FileWriter(outputFilePath)) {
        

            for (String[] row : data) {
                String[] modifiedRow = new String[row.length + 4]; // Extend the row length to accommodate new fields
                modifiedRow[0] = nameField;                       // Name field at position 0
                modifiedRow[1] = row[0];
                modifiedRow[2] = phoneField;                      // Phone field at position 2
                modifiedRow[4] = emailField;                      // Email field at position 3

                for (int i = 1; i < row.length; i++) {
                    modifiedRow[i + 2] = row[i];
                }
                fw.write(String.join(",", modifiedRow));
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortNames(String outputFilePath) {
        ArrayList<String[]> data = readCSV(outputFilePath);
        // Sort the data based on the nameField, assuming it is the first column
        Collections.sort(data, (a, b) -> a[0].toLowerCase().compareTo(b[1].toLowerCase()));
    
        try (FileWriter fw = new FileWriter(outputFilePath)) {
            for (String[] row : data) {
                fw.write(String.join(",", row));
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "contacts.csv";
        String outputFilePath = "modifiedContacts.csv";
        String nameField = "Name";
        String phoneField = "Phone";
        String emailField = "Email";

        modifyCSV(inputFilePath, outputFilePath, nameField, phoneField, emailField);
        sortNames(outputFilePath);

        System.out.println("Modified and sorted data has been written to: " + outputFilePath);
    }
}