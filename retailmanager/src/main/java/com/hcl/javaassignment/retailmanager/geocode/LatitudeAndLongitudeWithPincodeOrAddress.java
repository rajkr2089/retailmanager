package com.hcl.javaassignment.retailmanager.geocode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class LatitudeAndLongitudeWithPincodeOrAddress {
	
	
	//Code reference -
	// http://stackoverflow.com/questions/18455394/java-function-that-accepts-address-and-returns-longitude-and-latitude-coordinate
			
	
	/**
	 * This API returns the lat and long of a position based on addess or pin code.
	 * you can see the above URL for reference
	 * @param address
	 * @return
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws XPathExpressionException 
	 * @throws Exception
	 */
	public static String[] getLatLongPositions(String address) throws Exception
	  {
	    int responseCode = 0;
	    String status = null;
	    try{
	    	
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    System.out.println("URL : "+api);
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         return new String[] {latitude, longitude};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    } catch (UnsupportedEncodingException e) {
	    	throw new Exception("UnsupportedEncodingException"+status);
		} catch (IOException e) {
			throw new Exception("IOException"+status);
		} catch (ParserConfigurationException e) {
			throw new Exception("ParserConfigurationException"+status);
		} catch (XPathExpressionException e) {
			throw new Exception("XPathExpressionException"+status);
		} catch (SAXException e) {
			throw new Exception("SAXException"+status);
		}
	    return null;
	  }

}
