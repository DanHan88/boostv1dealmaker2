package dealMaker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DealMaker {
	public static void main(String[] args) {
        // Specify the path to your JSON file  Gwangju
        String filePath = "C://JavaProject/import_deal/data/gwangju/data.json";
        String fullDealPath = "C://JavaProject/import_deal/data/gwangju/BroadCastdeal1.txt";
        try {
            // Read the JSON data from the file
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            DealsResponse dealsResponse = objectMapper.readValue(jsonData, DealsResponse.class);

            // Access the list of deals
            List<Deal> deals = dealsResponse.getData().getDeals().getDeals();

            Map<String, Deal> hashSuccessDeals = new HashMap<>();
            Map<String, Deal> fullDeals = new HashMap<>();
            Map<String, Deal> remainingDeals = new HashMap<>();
            
            String storageProvider = "f01695888"; //광주 //"f01695888";//광주
            String startEpochHeadOffset = "40320";
            String clientWallet ="f13y7egp3cu7o6eog5fliizqt24f5o6qwnedbdq5a";
            String duration = "1500000";
            String storagePrice ="0";
            
            
            // Now you can work with the list of Deal objects
            for (Deal deal : deals) {    
            	hashSuccessDeals.put(deal.getPieceCid(),deal);
            }
            
            BufferedReader br = new BufferedReader(new FileReader(fullDealPath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("│"); // Split the line into columns
                if (columns.length >= 7) {
                    Deal deal = new Deal();
                    deal.setIndex(Integer.parseInt(columns[3].trim()));
                    deal.setPublishCid(columns[5].trim().replace("'",""));
                    deal.setPieceCid(columns[6].trim().replace("'",""));
                    deal.setPieceSize(columns[7].trim());
                    deal.setCarSize(columns[8].trim());      
                    // Add more setters for other fields as needed
                    fullDeals.put(deal.getPieceCid(),deal);
                    
                    if(!hashSuccessDeals.containsKey(deal.getPieceCid())) {
                    	remainingDeals.put(deal.getPieceCid(),deal);
                    	System.out.println("boost -vv offline-deal "
                    	+" --provider=" + storageProvider 
                    	+" --start-epoch-head-offset="+startEpochHeadOffset
                    	+" --commp="+ deal.getPieceCid() 
                    	+" --car-size="+ deal.getCarSize() 
                    	+" --piece-size="+ deal.getPieceSize() 
                    	+" --payload-cid="+ deal.getPublishCid()
                    	+" --wallet="+ clientWallet
                    	+" --duration=" + duration
                    	+" --storage-price=" +storagePrice
							);
                    }       
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static class DealsResponse {
	    @JsonProperty("data")
	    private Data data;

	    // Add a constructor, getters, and setters for the 'data' field
	    public Data getData() {
	        return data;
	    }

	    public void setData(Data data) {
	        this.data = data;
	    }
	}
	public static class Data {
	    @JsonProperty("deals")
	    private Deals deals;

	    // Add a constructor, getters, and setters for the 'deals' field
	    public Deals getDeals() {
	        return deals;
	    }

	    public void setDeals(Deals deals) {
	        this.deals = deals;
	    }
	}

	public static class Deals {
	    @JsonProperty("deals")
	    private List<Deal> deals;

	    // Add a constructor, getters, and setters for the 'deals' field
	    public List<Deal> getDeals() {
	        return deals;
	    }

	    public void setDeals(List<Deal> deals) {
	        this.deals = deals;
	    }
	}
}








