/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package trippingo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.LongStream;

import trippingo.datatype.Range;
import trippingo.utils.StringUtils;

public class AppTest {
    public static void main(String[] args) throws ParseException {
        System.out.println(parseTime(" 8:30 PM ").toString());
        System.out.println(parseDate("December 2018"));
        System.out.println("1 to 2 hour".replaceFirst("<", "0 to "));
        System.out.println("99000+".replaceFirst("\\+", ""));
        
        
        LongStream.rangeClosed(1,11).collect(HashSet::new, HashSet::add, (o1,o2) -> o1.addAll(o2)).forEach(System.out::println);
    }
    
    private static LocalTime parseTime(String time) {
		if(StringUtils.isNotNull(time) && time.trim()!= "-") {
			time = time.trim();
			boolean isPM = time.endsWith("PM");
			int additive = isPM? 12 : 0;
			int middleIndex = time.indexOf(":");
			String hour = time.substring(0,middleIndex);
			String minutes = time.substring(middleIndex+1,middleIndex+3);
			return LocalTime.of(Integer.parseInt(hour)+additive, Integer.parseInt(minutes));
		}
		return null;
    }
    
    private static LocalDate parseDate(String dateString) throws ParseException {
		String[] splitDateStr = dateString.split(" ");
    	Date monthDate = new SimpleDateFormat("MMMM").parse(splitDateStr[0]);
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(monthDate);
    	int month = cal.get(Calendar.MONTH);
		return LocalDate.of(Integer.parseInt(splitDateStr[1]), month+1, 1);
    }
    
    private static Range parseDuration(String duration) {
		duration = duration.trim().toLowerCase();
		if(StringUtils.isNull(duration) || duration== "-")
			return new Range();
		duration = duration.replaceFirst("hours", "").trim();
		if(duration.contains("to")) {
			String[] durationArr = duration.split("to");
			return new Range(Double.valueOf(durationArr[0].trim()), Double.valueOf(durationArr[1].trim()));
		}
		else if(duration.contains("-")) {
			String[] durationArr = duration.split("-");
			return new Range(Double.valueOf(durationArr[0].trim()), Double.valueOf(durationArr[1].trim()));
		}
		else {
			return new Range(Double.valueOf(duration.trim()), Double.valueOf(duration.trim())); 
		}
		
	}
}
