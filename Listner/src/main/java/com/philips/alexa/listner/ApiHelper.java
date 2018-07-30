package com.philips.alexa.listner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.alexa.response.model.MDMJobResponse;
import com.philips.alexa.response.model.SFDCRecords;
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
			
			speechresponse= GetSpeechResponse("MDM", sRes);
		}
		else if (arrOfStr[0].equals("DIH")) {
			
			sUrl= sUrl +"/informatica/dih/";
			speechresponse= GetSpeechResponse("MDM", sRes);			
		}
		else if (arrOfStr[0].equals("PC")) {
			sUrl= sUrl +"/informatica/pc/";
			speechresponse= GetSpeechResponse("MDM", sRes);
		}
		
		else if (arrOfStr[0].equals("SFDC")) {
			sUrl= sUrl +"/sfdc/contact";
			sRes= request(sUrl);
			speechresponse= GetSpeechResponse("MDM", sRes);
			
		}
		
		return speechresponse;
	}
	
	
	private String GetSpeechResponse(String JobType , String sResponse) {
		
		String sSpeechResponse ="";
		
		if (JobType.equals("MDM")) {
			
			
			ObjectMapper mapper1 = new ObjectMapper();
			MDMJobResponse mymdmresp;
			try {
				mymdmresp = mapper1.readValue(sResponse, MDMJobResponse.class);
				sSpeechResponse = mymdmresp.getJobStageStatus();
				
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
				
				SFDCRecords[] mysfdcrec=mysfdcresp.getRecords();
				
				
				sSpeechResponse= mysfdcrec[0].getColumns().toString();
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
	
    private String request(String url) throws Exception {
        BufferedReader reader = null;
        
        StringBuilder sb = new StringBuilder();
        URL APIHubRESTURL= new URL(url);
        
            try {

                HttpsURLConnection myHttpsUrlConnection =(HttpsURLConnection) APIHubRESTURL.openConnection();
                myHttpsUrlConnection.setRequestMethod("GET");
                myHttpsUrlConnection.setDoInput(true);
                myHttpsUrlConnection.setDoOutput(true);

                reader = new BufferedReader(new InputStreamReader(myHttpsUrlConnection.getInputStream()), 1);

                char[] buf = new char[1024];
                
                int count = 0;
                while( -1 < (count = reader.read(buf)) ) {
                    sb.append(buf, 0, count);
                }
                System.out.println(sb.toString());
                reader.close();
                return sb.toString();

            } catch (IOException ex) {
                System.out.println(ex);

                if( null != reader ) {
                    reader.close();
                }
            }
			return sb.toString();
        }
    
}
