package com.philips.alexa.listner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class Listner {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String bucketName = "com.philips.purush";
		String in_folderName="alexa_inbox";
		String out_folderName="alexa_outbox";
		String SUFFIX="/";
		
		
	    BasicAWSCredentials basicCred = new BasicAWSCredentials("XXXX", "YYYYYY"); 
	    
	    ClientConfiguration clientCfg = new ClientConfiguration();
	    clientCfg.setProtocol(Protocol.HTTP);

	    //setup proxy connection:
//	    clientCfg.setProxyHost("185.46.212.97");
//	    clientCfg.setProxyPort(10015);
	    AmazonS3 s3 = new AmazonS3Client(basicCred, clientCfg);
	      
	    
	    
	    String filecontent="";
	    
	    
		ListObjectsRequest param = new ListObjectsRequest() ;
		param.setBucketName(bucketName);
		param.setPrefix(in_folderName);
		
		while (true) {

			String sContent="";
			System.out.println("Looking for the files in Inbox for processing!!");
			ObjectListing lst = s3.listObjects(param);
			
			for ( S3ObjectSummary summ : lst.getObjectSummaries()) {
			
				if (summ.getKey().contains("_req.txt")){
					// Got the file which needs to be processed
					String sFileName=summ.getKey();
					System.out.println(sFileName);
					sFileName=sFileName.replace(in_folderName, "").replace(SUFFIX, "");
					filecontent="";
					GetObjectRequest param2 = new GetObjectRequest(bucketName, in_folderName+ SUFFIX  + sFileName);
					  S3Object fullObject= s3.getObject(param2);
					  BufferedReader reader = new BufferedReader(new InputStreamReader(fullObject.getObjectContent()));
						  	String line = null;
						  	
							try {
								while ((line = reader.readLine()) != null) 
								{
									filecontent=filecontent+ line;
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						  	System.out.println(filecontent);
						  	
						  	// Now let us process the files
						  	
						  	ApiHelper myhelper = new ApiHelper();
						  	
						  	try {
						  		sContent= myhelper.ProcessCommand(filecontent);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						  	
						  	DeleteObjectRequest param3 = new DeleteObjectRequest(bucketName, in_folderName+ SUFFIX  + sFileName);
							s3.deleteObject(param3);
				
						  	// Need to write this back to a file and store in outbox in S3.
						    StringBuilder stringBuilder = new StringBuilder();
						    stringBuilder.append(sContent);
					    	ByteArrayInputStream inputStream = null;
					    	sFileName=sFileName.replace("_req.txt", "_resp.txt");
					    	inputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes());
					        s3.putObject(bucketName, out_folderName + SUFFIX + sFileName, inputStream, new ObjectMetadata());

				}
				
			}


		    try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			
	}	
	
	
	
    
}
