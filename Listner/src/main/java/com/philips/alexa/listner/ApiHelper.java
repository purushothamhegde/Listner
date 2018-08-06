package com.philips.alexa.listner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.alexa.response.model.Columns;
import com.philips.alexa.response.model.MDMJobResponse;
import com.philips.alexa.response.model.Records;
import com.philips.alexa.response.model.SFDCResponse;

public class ApiHelper {

	public String sUrl="http://localhost:8080";
	
	public String ProcessCommand(String sContent) throws Exception {
		
		String speechresponse="";
		sContent=sContent.toUpperCase();	
		String [] arrOfStr = sContent.split(",");
		
		String sRes="";
		
		if (arrOfStr[0].equals("MDM")) {
			//Hard Coding the Batch Group for the time being
			sUrl= sUrl +"/informatica/mdm/Bulk_SRA";
			sRes= request(sUrl);
			
			speechresponse= GetSpeechResponse("MDM", "",sRes);
		}
		else if (arrOfStr[0].equals("DIH")) {
			
			sUrl= sUrl +"/informatica/dih/";
			speechresponse= GetSpeechResponse("DIH", "", sRes);			
		}
		else if (arrOfStr[0].equals("PC")) {
			sUrl= sUrl +"/informatica/pc/";
			speechresponse= GetSpeechResponse("PC","", sRes);
		}
		
		else if (arrOfStr[0].equals("SFDC")) {
			
			System.out.println(arrOfStr[1]);
			if (arrOfStr[1].equals("CONTACT")) {
				sUrl= sUrl +"/sfdc/contact";
				sRes= request(sUrl);
				speechresponse= GetSpeechResponse("SFDC", "CONTACT",sRes);
			}
			else if(arrOfStr[1].equals("OPPORTUNITY")) {
				sUrl= sUrl +"/sfdc/opportunity";
				sRes= request(sUrl);
				speechresponse= GetSpeechResponse("SFDC", "OPPORTUNITY",sRes);
			}


			
		}
		
		return speechresponse;
	}
	
	
	private String GetSpeechResponse(String JobType , String JobMethod,  String sResponse) {
		
		String sSpeechResponse ="";
		
		if (JobType.equals("MDM")) {
			
			String sLandtoStage="";
			String sL2SRecordsInserted="";
			String sL2SRecordsTotal ="";

			
			String sStagetoBase="";
			String sS2BRecordsInserted="";
			String sS2BRecordsTotal ="";
			
			ObjectMapper mapper1 = new ObjectMapper();
			MDMJobResponse[] mymdmresp;
			try {
				mymdmresp = mapper1.readValue(sResponse, MDMJobResponse[].class);
				
				for (int i =0 ; i <= mymdmresp.length-1 ; i++) {
					
					MDMJobResponse mdmres= mymdmresp[i];
					
					if (mdmres.getJobStage().contains("Stage")){
						
						// This is the Land to Stage job
						sLandtoStage= mdmres.getJobGroupName() + " Land to Stage Process completed with ";
						
						if (mdmres.getJobStageMetricName().contains("Inserted")){
							
							sL2SRecordsInserted=  mdmres.getJobStageMetricValue() +" Records inserted out of " ; 
							
						}	
							

						if (mdmres.getJobStageMetricName().contains("Total records")){
							
							sL2SRecordsTotal= " " + mdmres.getJobStageMetricValue() + " records";
							
						}	
					}
					
					if (mdmres.getJobStage().contains("Load")){
						
						// This is the Land to Stage job
						sStagetoBase= mdmres.getJobGroupName() + " Stage to Base Process completed with ";
						
						if (mdmres.getJobStageMetricName().contains("Updated XREF")){
							
							sS2BRecordsInserted=  mdmres.getJobStageMetricValue() +" Records updated out of " ; 
							
						}	
							

						if (mdmres.getJobStageMetricName().contains("Total records")){
							
							sS2BRecordsTotal = " " + mdmres.getJobStageMetricValue() +" records";
							
						}	
					}
										
				}
				
				
				sSpeechResponse = "<speak><s>" + sLandtoStage + sL2SRecordsInserted + sL2SRecordsTotal +"</s>";
				sSpeechResponse = sSpeechResponse + "<s>" + sStagetoBase + sS2BRecordsInserted + sS2BRecordsTotal +"</s></speak>";
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		else if (JobType.equals("SFDC")) {
			
			ObjectMapper mapper1 = new ObjectMapper();
			SFDCResponse mysfdcresp;			
			try {
				mysfdcresp = mapper1.readValue(sResponse, SFDCResponse.class);
				
				Records[] mysfdcrec=mysfdcresp.getRecords();
				
				
				
				String sContactName ="";
				String sEmail ="";
				String sPhone ="";
				String sAmount ="";				
				String sAccountName ="";
				
				String sStageName="";
				
				Columns[] mycols =  mysfdcrec[0].getColumns();
				
				for (int i=0 ; i<= mycols.length-1 ; i++){
					
					if (mycols[i].getFieldNameOrPath().equals("Email")) {
						
						sEmail = mycols[i].getValue1();
						
					}
					
					if (mycols[i].getFieldNameOrPath().equals("Name")) {
						
						sContactName = mycols[i].getValue1();
						
					}
					
					if (mycols[i].getFieldNameOrPath().equals("Phone")) {
						
						sPhone = mycols[i].getValue1();
						
					}
					
					if (mycols[i].getFieldNameOrPath().equals("Amount")) {
						
						sAmount = mycols[i].getValue1();
						
					}
					
					if (mycols[i].getFieldNameOrPath().equals("Account.Name")) {
						
						sAccountName = mycols[i].getValue1();
						
					}
					
					if (mycols[i].getFieldNameOrPath().equals("StageName")) {
						
						sStageName = mycols[i].getValue1();
						
					}
					
				}
				
				
				if (JobMethod.equals("CONTACT")) {

					sSpeechResponse= "<speak> <s> The contact details of " + sContactName + " are as follows </s>";
					if (!sEmail.equals("")) {
						sSpeechResponse= sSpeechResponse + "<s> Email address is " + sEmail +"</s>";
					}
					
					if (!sPhone.equals("")) {
						sSpeechResponse= sSpeechResponse + "<s> Phone Number is <say-as interpret-as=\"telephone\">" + sPhone +"</say-as></s>";
					}
					
					sSpeechResponse=sSpeechResponse +"</speak>";

				}
				else if(JobMethod.equals("OPPORTUNITY")) {
					sSpeechResponse= "<speak> <s> The Opportunity details of " + sAccountName + " are as follows </s>";
					
					if (!sAmount.equals("")) {
						
						sSpeechResponse= sSpeechResponse + "<s> The deal is worth <say-as interpret-as=\"unit\">$" + sAmount +"</say-as></s>";
					}		
					
					if (!sStageName.equals("")) {
						sSpeechResponse= sSpeechResponse + "<s> its in the " + sStageName +"stage </s>";
					}
					
					sSpeechResponse=sSpeechResponse +"</speak>";
				}
				
				System.out.println(sSpeechResponse);
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		return sSpeechResponse;
		
		
		
		
	}
	
    private String request(String url) throws IOException {
        BufferedReader reader = null;
        
        StringBuilder sb = new StringBuilder();
        URL APIHubRESTURL= new URL(url);
    	String inputLine;
    	StringBuffer response = new StringBuffer();
    	
		HttpURLConnection con = (HttpURLConnection) APIHubRESTURL.openConnection();
        con.setRequestMethod("GET");
        
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
        	BufferedReader in = new BufferedReader(new InputStreamReader(
        	con.getInputStream()));
        	

        	
        	while ((inputLine = in.readLine()) != null) {
        				response.append(inputLine);
        			}
        			in.close();
        			System.out.println(response.toString());
        		} else {
        			System.out.println("GET request not worked");
        		}
        	return response.toString();
        
        
}
}