package taxi;

import java.util.ArrayList;
import java.util.List;

public class taxi {

	static int taxiCount=0;
	int id;
	boolean booked;
	char currentSpot;
	int freeTime;
	public int totalEarnings;
	public String taxiId;
	List<String> trips;
	
	
	public taxi() {
         taxiCount=taxiCount+1;
		 id=taxiCount;
		 booked=false;
		 currentSpot='A';
		 freeTime=6;
	     totalEarnings=0;
		 trips=new ArrayList<>();
	}
	
	public void SetDetails(boolean booked,char currentSpot,int freeTime,int totalEarnings,String StripDetails) {
		this.booked=booked;
		this.currentSpot=currentSpot;
		this.freeTime=freeTime;
		this.totalEarnings=totalEarnings;
		this.trips.add(StripDetails);
	}

	public void printTaxiDetails() {
		
		System.out.println("TaxiId="+this.taxiId +"   TotalEarnings="+this.totalEarnings);
		 System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
	        for(String trip : trips)
	        {
	            System.out.println(id + "          " + trip);
	        }
	        System.out.println("--------------------------------------------------------------------------------------");
		
	}

	public void printDetails() {
		  System.out.println("Taxi - "+ this.id + " Total Earnings - " + this.totalEarnings + " Current spot - " + this.currentSpot +" Free Time - " + this.freeTime);
	
	}
	
	
	
}
