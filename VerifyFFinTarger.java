import java.io.*;
import oracle.stellent.ridc.*;
import oracle.stellent.ridc.model.*;
import oracle.stellent.ridc.protocol.*;
import oracle.stellent.ridc.protocol.intradoc.*;
import oracle.stellent.ridc.common.log.*;
import oracle.stellent.ridc.model.serialize.*;
import java.util.Properties;

/*
 * @author Sterin- Oracle Inc
 * 
 * This is a class used to test the basic functionality
 * of submitting a search to Content Server using RIDC.  
 * The response is then used to retrieve metadata about
 * the content items.  
 */

public class VerifyFFinTarger {

	/**
	 * @param args
	 */
	public static boolean verifyFFinTargetMethod(String  folderpath) {
		// TODO Auto-generated method stub
		IdcClientManager manager = new IdcClientManager ();

                  	Properties prop = new Properties();
	                InputStream input = null;
                    
               
boolean status=true;
                    
		try{
			

               input = new FileInputStream("config.properties");
 
		// load a properties file
		prop.load(input);


       // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
			IdcClient idcClient = manager.createClient (prop.getProperty("targerturl"));

                       IdcContext userContext = new IdcContext (prop.getProperty("user"),prop.getProperty("password"));

			// Create an HdaBinderSerializer; this is not necessary, but it allows us to serialize the request and response data binders
			HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
			
			// Create a new binder for submitting a search
			DataBinder dataBinder = idcClient.createBinder();
       
            dataBinder.putLocal("IdcService", "FLD_BROWSE");
            dataBinder.putLocal("path",folderpath);

            ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
            
            // Get the data binder for the response from Content Server
            DataBinder responseData = response.getResponseAsBinder();
     

           




           			
		} catch (IdcClientException ice){
			// ice.printStackTrace();
                       System.out.println(ice.getMessage());  
                      status=false;
                         

		} catch (IOException ioe){
			ioe.printStackTrace();
                        status=false;
		}finally{

                   return status;
                         } 

                    
	}

}
