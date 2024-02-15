package import_boost_deal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class import_deal {
    public static void main(String[] args) {
        String csvFilePath = "C://JavaProject/import_deal/data/f09848_64ec2935d6e7bbb7e866e610.csv";
        String textFilePath = "C://JavaProject/import_deal/data/fourth300.txt";
        String outputFilePath = "C://JavaProject/import_deal/data/output.csv";

        try {
            // Read filenames from the text file
            List<String> filenames = readFilenames(textFilePath);

            // Read and filter CSV file
            List<String> filteredRows = filterCSVByFilenames(csvFilePath, filenames);

            // Write filtered rows to output CSV file
            writeFilteredRowsToCSV(outputFilePath, filteredRows);
            
            System.out.println("Filtered CSV file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readFilenames(String filePath) throws IOException {
        List<String> filenames = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            filenames.add(line.trim());
        }

        reader.close();
        return filenames;
    }

    public static List<String> filterCSVByFilenames(String csvFilePath, List<String> filenames) throws IOException {
        List<String> filteredRows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));

        String header = reader.readLine(); // Assuming the first line is the header
        filteredRows.add(header);

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String filename = fields[2].trim(); // Assuming filename is in the third column

            if (filenames.contains(filename)) {
                filteredRows.add(line);
            }
        }

        reader.close();
        return filteredRows;
    }

    public static void writeFilteredRowsToCSV(String outputPath, List<String> rows) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

        for (String row : rows) {
            writer.write(row);
            writer.newLine();
        }

        writer.close();
    }
}
