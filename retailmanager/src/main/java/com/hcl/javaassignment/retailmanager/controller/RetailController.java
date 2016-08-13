package com.hcl.javaassignment.retailmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.javaassignment.retailmanager.bean.Retailer;
import com.hcl.javaassignment.retailmanager.daolayer.RetailService;


@RestController
public class RetailController{
	
	
	//url to add new retails shop
	// == http://localhost:8080/retailmanager/addRetailer
	
	/**
	 *
{
    "name":"RAJ",
    "number":1,
    "addressName":"katraj",
    "postCode":"411046"
}



	 * @param retailer
	 * @return
	 */
	@RequestMapping(value = "/addRetailer", method = RequestMethod.POST,headers="Accept=application/json")
	public String addRetailer(@RequestBody Retailer retailer) {
		RetailService retailService	=	new RetailService();
		try {
			retailService.addRetailer(retailer);
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return "Retail Shop Added";
	}
	
	
	
	//URL -- http://localhost:8080/retailmanager/showRetailer
	@RequestMapping(value = "/showRetailer", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Retailer> showRetailers() {
		return RetailService.reatailerList;
	}
	
	
	
	//URL - http://localhost:8080/retailmanager/searchNearbyRetailers/18.4575324/73.8677464/K
	@RequestMapping(value = "/searchNearbyRetailers/{lattitude}/{longtitude}/{unit}", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Retailer> searchNearbyRetailers(@PathVariable("lattitude") double lattitude, @PathVariable("longtitude") double longtitude, @PathVariable("unit") String unit) {
		RetailService retailService	=	new RetailService();
		return retailService.getNearbyShop(lattitude, longtitude, unit);
	}
		
}
