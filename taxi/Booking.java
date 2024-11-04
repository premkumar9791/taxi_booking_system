package taxi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;





public class Booking {

	public static void main(String[] args) {
		
		// Features of the Taxi Booking System:
		
		// 1. Taxi Booking: 
//		    - Users can book available taxis by entering their pickup and drop locations, along with the desired pickup time.
	
		// 2. Taxi Details: 
//		    - Displays detailed information for each taxi, including its current location, earnings, and availability status.
	
		// 3. Smart Taxi Allocation: 
//		    - Assigns the most suitable taxi based on proximity to the pickup location and current earnings, optimizing service efficiency.
	
		// 4. Menu-Driven Interface: 
//		    - Provides a simple and user-friendly menu for users to select options such as booking a taxi, viewing taxi details, or exiting the application.
	
		// 5. Input Validation: 
//		    - Ensures valid pickup and drop locations are entered by the user to prevent errors during booking.

		
		List<taxi> taxis=creatTaxi(4);
		
		Scanner s=new Scanner(System.in);
		int id=1;
		boolean loop=true;
		while(loop) {
			
			System.out.println("choose any one\n1.booktaxi\n2.taxi details\n3.exit");

			int n=s.nextInt();
			switch(n) {
			case 1:{
				int customerId=id;
				System.out.println("enter pickupLocation:");
				char pickupLocation=s.nextLine().toUpperCase().charAt(0);
				System.out.println("enter DropLocation:");
				char dropLocation=s.nextLine().toUpperCase().charAt(0);
				System.out.println("enter pickupTime:");
				char pickupTime=s.nextLine().charAt(0);
				
				if(pickupLocation < 'A' ||  pickupLocation > 'F' || dropLocation < 'A' || dropLocation > 'F' ) {
					System.out.println("enter valid location A , B, C , D , E ,  F.");
					return;
				}
				
				List<taxi> freetaxi=getFreeTaxi( pickupLocation, pickupTime,taxis);
				
				if(freetaxi.size()== 0) {
					System.out.println("No taxi available");
				}
				
				Collections.sort(freetaxi,(a,b)-> a.totalEarnings-b.totalEarnings);
				
				bookingTaxi(id,freetaxi,pickupTime,dropLocation,pickupLocation,customerId);
				id++;
				break;
				
			}
			case 2:{
				 for(taxi t : taxis)
		                t.printTaxiDetails();
		             for(taxi t : taxis)
		                t.printDetails();
				break;
			}
			case 3:{
				loop=false;
				s.close();
				break;
			}
			}
		}

	}

	private static void bookingTaxi(int id, List<taxi> freetaxi, char pickupTime, char dropLocation,char pickupLocation,int customerID) {
		
		int min=Integer.MAX_VALUE;
		int DistanceBetweenPickupAndDrop=0;
		int earnings=0;
		int nextFreeTime=0;
		char nextStop='Z';
		taxi BookedTaxi=null;
		String tripDetails="";
		
		for(taxi t:freetaxi) {
			
			 int distanceBetweenCustomerAndTaxi = Math.abs((t.currentSpot - '0') - (pickupLocation - '0')) * 15;
	            if(distanceBetweenCustomerAndTaxi < min)
	            {
	                BookedTaxi = t;
	                DistanceBetweenPickupAndDrop = Math.abs((dropLocation - '0') - (pickupLocation - '0')) * 15;

	                earnings = (DistanceBetweenPickupAndDrop-5) *  10 + 100;
	                
	                int dropTime  = pickupTime + DistanceBetweenPickupAndDrop/15;

	                nextFreeTime = dropTime;

	                nextStop = dropLocation;
	                tripDetails = customerID + "               " + customerID + "          " + pickupLocation +  "      " + dropLocation + "       " + pickupTime + "          " +dropTime + "           " + earnings;
	                min = distanceBetweenCustomerAndTaxi;
			
		}
		
		}
		
		
        BookedTaxi.SetDetails(true,nextStop,nextFreeTime,BookedTaxi.totalEarnings + earnings,tripDetails);
      
        System.out.println("Taxi " + BookedTaxi.taxiId + " booked");
			
		
	}

	private static List<taxi> getFreeTaxi(char pickupLocation, char pickupTime, List<taxi> taxis) {
	
		for(taxi t:taxis) {
			if(t.freeTime<=pickupTime && Math.abs((pickupLocation-'0')-(t.currentSpot-'0'))<=pickupTime-t.freeTime) {
				taxis.add(t);
			}
		}
		
		return taxis;
	}

	private static List<taxi> creatTaxi(int n) {
		List<taxi> taxis=new ArrayList<taxi>();
		for(int i=0;i<n;i++) {
			taxi t=new taxi();
			taxis.add(t);}
		return taxis;}

}
