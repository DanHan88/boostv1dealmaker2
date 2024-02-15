package solanadownloadLink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class solanadownloadLink {

    public static void main(String[] args) {
    	 String inputCsvFile = "C://JavaProject/import_deal/data/solana/deal6.csv";
        String outputTextFile = "C://JavaProject/import_deal/data/solana/outcome6.txt"; // Replace with the output text file path
        String providerNameToFilter = "f01227505"; 

        List<String> urlList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputCsvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(providerNameToFilter)) {
                    String url = parts[3].trim(); // Assuming the URL is in the second column
                    urlList.add(url);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputTextFile))) {
            for (String url : urlList) {
                writer.write(url);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}