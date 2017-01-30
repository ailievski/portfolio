package com.kiril.lia.parsers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.kiril.lia.domain.CompanyResultModel;

public class XMLParser {

	public static List<CompanyResultModel> parseXML(String content) {
		
		try {
			
		    boolean inDataItemTag = false;
		    String currentTagName = "";
		   CompanyResultModel companyModel = null;
		    List<CompanyResultModel> results = new ArrayList<CompanyResultModel>();

		    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		    XmlPullParser parser = factory.newPullParser();
		    parser.setInput(new StringReader(content));

		    int eventType = parser.getEventType();

		    while (eventType != XmlPullParser.END_DOCUMENT) {

		        switch (eventType) {
		            case XmlPullParser.START_TAG:
		                currentTagName = parser.getName();
		                if (currentTagName.equals("ResultatModel")) {
		                    inDataItemTag = true;
		                    companyModel  = new CompanyResultModel();
		                    results.add(companyModel);
		                }
		                break;

		            case XmlPullParser.END_TAG:
		                if (parser.getName().equals("ResulatModel")) {
		                    inDataItemTag = false;
		                }
		                currentTagName = "";
		                break;

		            case XmlPullParser.TEXT:
		                if (inDataItemTag && companyModel != null) {
		                    switch (currentTagName) {
		                        case "OsigurenaID":
		                            companyModel.setOsigurenaID(Integer.parseInt(parser.getText()));
		                            break;
		                        case "OsigurenaSuma":
		                        	 companyModel.setOsigurenaSuma(Integer.parseInt(parser.getText()));
		                        	break;
//		                        case "instructions":
//		                            flower.setInstructions(parser.getText());
//		                            break;
//		                        case "category":
//		                            flower.setCategory(parser.getText());
//		                            break;
//		                        case "price" :
//		                        	flower.setPrice(Double.parseDouble(parser.getText()));
//		                            break;
//		                        case "photo" :
//		                        	flower.setPhoto(parser.getText());
		                        default:
		                            break;
		                    }
		                }
		                break;
		        }

		       eventType = parser.next();

		    }

		    return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

		
	}

}