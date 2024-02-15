package dealMakerBigBear;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DealMaker2 {
	public static void main(String[] args) {
		
		DealMaker2 dealMaker2 = new DealMaker2();
		
		//전체 딜에서, 만들고 싶은 만큼의 아이디가 담긴 text file (500 개 정도씩 진행함)
		String filePath = "C://JavaProject/import_deal/data/bigbear/dataset1_firstdeal.txt";
		//전체 딜 정보 가 담긴 text file --- 아마 싱귤러리티V1에서 조회했었던거 같음...
        String fullDealPath = "C://JavaProject/import_deal/data/bigbear/dataset1deal.txt";
        //마이너 아이디 (딜을 보내는 마이너 아이디)
        String storageProvider = "f09848"; //광주 //"f01695888";//광주
        //딜 시작 점인 ephoch 한 2주정도 뒤로 잡으면 좋음 (데이터 보내는 시간이 필욯서)
        String startEpochHeadOffset = "40320";
        //데이터캡 있는 지갑 이름  **** 주의 **** 이거 꼭 제대로 써야함. 다른 데이터캡지갑에서 보내면 안됨.
        String clientWallet ="f1pkpa3h4suh77mi3guur25r5u4twkere5r35fjii"; //방송데이터 지갑 ->>> "f13y7egp3cu7o6eog5fliizqt24f5o6qwnedbdq5a";
        //총 딜 시간 EPOCH 
        String duration = "1500000";
        //스토리지 가격 
        String storagePrice ="0";
        
        List<String> newDeal = dealMaker2.createBoostV1Deal(filePath,fullDealPath,storageProvider,startEpochHeadOffset,clientWallet,duration,storagePrice);
        
        for(int i = 0;i<newDeal.size();i++) {
        	System.out.println(newDeal.get(i));
        }
        // 다 진행되면 콘솔에있는 값 카피해서 진행하면 됨
        
        
    }
	
	public List<String> createBoostV1Deal(String filePath, String fullDealPath,String storageProvider, String startEpochHeadOffset , String clientWallet, String duration, String storagePrice){
		 List<String> newDeals = new ArrayList<>();
		 	try {           
	            ObjectMapper objectMapper = new ObjectMapper();
	            String line;
	            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
	            HashMap<String, String> deals = new HashMap<>();
	            while ((line = br2.readLine()) != null) {
	            	String cid = line.split(".car")[0];
	            	deals.put(cid,cid);
	            }
	            Map<String, Deal2> fullDeals = new HashMap<>();
	            Map<String, Deal2> remainingDeals = new HashMap<>();                  
	            BufferedReader br = new BufferedReader(new FileReader(fullDealPath));
	            while ((line = br.readLine()) != null) {
	                String[] columns = line.split("│"); // Split the line into columns
	                if (columns.length >= 7) {
	                    Deal2 deal = new Deal2();
	                    deal.setIndex(Integer.parseInt(columns[3].trim()));
	                    deal.setPublishCid(columns[5].trim().replace("'",""));
	                    deal.setPieceCid(columns[6].trim().replace("'",""));
	                    deal.setPieceSize(columns[7].trim());
	                    deal.setCarSize(columns[8].trim());      
	                    fullDeals.put(deal.getPieceCid(),deal);
	                    
	                    if(deals.containsKey(deal.getPieceCid())) {
	                    	remainingDeals.put(deal.getPieceCid(),deal);
	                    	newDeals.add("boost -vv offline-deal "
	                    	+" --provider=" + storageProvider 
	                    	+" --start-epoch-head-offset="+startEpochHeadOffset
	                    	+" --commp="+ deal.getPieceCid() 
	                    	+" --car-size="+ deal.getCarSize() 
	                    	+" --piece-size="+ deal.getPieceSize() 
	                    	+" --payload-cid="+ deal.getPublishCid()
	                    	+" --duration=" + duration
	                    	+" --storage-price=" +storagePrice
	                    	+" --wallet="+ clientWallet);
	                    }       
	                }
	            }   
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return newDeals;
		
	}
	
	
	public static class DealsResponse {
	    @JsonProperty("data")
	    private Data data;

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

	    public Deals getDeals() {
	        return deals;
	    }

	    public void setDeals(Deals deals) {
	        this.deals = deals;
	    }
	}

	public static class Deals {
	    @JsonProperty("deals")
	    private List<Deal2> deals;

	    public List<Deal2> getDeals() {
	        return deals;
	    }

	    public void setDeals(List<Deal2> deals) {
	        this.deals = deals;
	    }
	}
}








