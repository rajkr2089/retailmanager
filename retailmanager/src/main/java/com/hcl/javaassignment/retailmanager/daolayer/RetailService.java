package com.hcl.javaassignment.retailmanager.daolayer;

import java.util.ArrayList;
import java.util.List;

import com.hcl.javaassignment.retailmanager.bean.Retailer;
import com.hcl.javaassignment.retailmanager.geocode.DistanceCalculator;
import com.hcl.javaassignment.retailmanager.geocode.LatitudeAndLongitudeWithPincodeOrAddress;

public class RetailService {
	
	public static List<Retailer> reatailerList	=	new ArrayList<Retailer>();;
	
	/**
	 * Add new retailer
	 * @param retailer
	 * @throws Exception 
	 */
	public void addRetailer(Retailer retailer) throws Exception {
		//Adding new retailer
		
		//find the location of the retail shop
		String latLongs[] = LatitudeAndLongitudeWithPincodeOrAddress.getLatLongPositions(retailer.getPostCode());
		if(latLongs == null){
			throw new Exception("Unable to find your location");
		}
		
		retailer.setLattitude(latLongs[0]);
		retailer.setLongtitude(latLongs[1]);
		
		reatailerList.add(retailer);
	}
	
	
	public List<Retailer> getNearbyShop(double lat2, double lon2, String unit){
		
		//initialize the list as out should be 0 or more.
		List<Retailer> nearbyShop	=	new ArrayList<Retailer>();
		
		// reference code
		// = http://sforsuresh.in/finding-nearest-location-using-latitude-longitude/
		
		for (Retailer retailer : reatailerList) {
			//double lat1, double lon1, double lat2, double lon2, String unit 
			double distance	=	DistanceCalculator.distance(Double.parseDouble(retailer.getLattitude()), Double.parseDouble(retailer.getLongtitude()), lat2, lon2, unit);
			
			if(distance <2){
				nearbyShop.add(retailer);	
			}
		}
		
		return nearbyShop;
	}

}
