import java.util.*;
import java.text.*; 

public class test {
  
   public static void main(String []args) {

   		String minDateString = "31.12.2017";
   		String maxDateString = "01.01.2019";

		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		Date startDate=null;
		Date endDate=null;
		Date minDate=null;
		Date maxDate=null;

		boolean good = false;
		do{
		System.out.println("Upišite pocetni datum: ");
		String start = scanner.next();
		
		try {
			minDate = dateFormat.parse(minDateString);
			maxDate = dateFormat.parse(maxDateString);
		    startDate = dateFormat.parse(start);
		} catch (ParseException e) {
		    System.out.println("Datum mora biti u obliku DD.MM.YYYY!");
		}

		if(startDate.after(minDate) && startDate.before(maxDate)){
			good = true;
		}else{
			System.out.println("Datum mora biti u 2018 godini!");
		}
		}while(!good);

		good = false;
		do{
		System.out.println("Upišite krajnji datum: ");
		String end = scanner.next();
		
		try {
		    endDate = dateFormat.parse(end);
		} catch (ParseException e) {
		    System.out.println("Datum mora biti u obliku DD.MM.YYYY!");
		}

		if(startDate.equals(endDate) || startDate.after(endDate)){
			System.out.println("Pocetni datum mora biti manji od zavrsnog,te oni nesmiju biti jednaki!");
		}else{
			if(endDate.after(minDate) && endDate.before(maxDate) )
			{
				good = true;
			}else{
				System.out.println("Datum mora biti u 2018 godini!");
			}
		}
		}while(!good);


		String[] HolidaysString ={
  			"01.01.2018",
  			"06.01.2018",
  			"01.04.2018",
  			"02.04.2018",
  			"01.05.2018",
 			"31.05.2018",
 			"22.06.2018",
 			"25.06.2018",
 			"05.08.2018",
 			"15.08.2018",
 			"08.10.2018",
 			"01.11.2018",
 			"25.12.2018",
 			"26.12.2018",
 		    };
 
 		Date[] HolidaysDate = new Date[HolidaysString.length];

		for (int i = 0; i<HolidaysString.length; i++){
		    try {
				HolidaysDate[i] = dateFormat.parse(HolidaysString[i]);
			}catch (ParseException e) {
			}
		}

		long miliDiff = endDate.getTime() - startDate.getTime();
		long daysDiff = miliDiff/(1000*60*60*24)+1;

		int daysTotal = (int) daysDiff;
		System.out.println("Ukupno dana u intervalu: "+ daysTotal);

		Date[] intervalDates = new Date[daysTotal];

		int index = 0;
		while (startDate.before(new Date (endDate.getTime ()+1000*60*60*24))) 
		{
    		intervalDates[index] = startDate;
    		startDate = new Date (startDate.getTime ()+1000*60*60*24);
    		index++;
		}

		int daysOff=0;
		dateFormat = new SimpleDateFormat("E");

		for (Date date:intervalDates) {
			if (dateFormat.format(date).equals("sub") || dateFormat.format(date).equals("ned")) {
				daysOff++;
			}else{
			for (Date holiday:HolidaysDate ) {
				if(date.equals(holiday)){
					daysOff++;
				}
			}
			}
		}
		
		System.out.println("Broj radnih dana u intervalu: "+(daysTotal-daysOff));
		System.out.println("Broj neradnih dana u intervalu: "+daysOff);

   }
}