package dealMaker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EpochCalculator {
	public static void main(String[] args) throws ParseException {
		  
		String dateTimeString = "2023-09-05 18:24:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(dateTimeString);
		long default_epoch = 3186646;
		
		 Date now = new Date();
		 long currentEpoch = default_epoch + (now.getTime()-date.getTime())/1000/30;
		
		 long twoWeeksLater = currentEpoch + 24*14*60*2;
		 
		 System.out.println("currentEpoch: " + currentEpoch);
		 System.out.println("intwoWeeks: " + twoWeeksLater);
    }
}
