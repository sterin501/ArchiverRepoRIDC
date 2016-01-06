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

public class GetArchiverStaus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IdcClientManager manager = new IdcClientManager ();

                  	Properties prop = new Properties();
	                InputStream input = null;
                    
               

                    
		try{
			

               input = new FileInputStream("config.properties");
 
		// load a properties file
		prop.load(input);


       // Create a new IdcClient Connection using idc protocol (i.e. socket connection to Content Server)
			IdcClient idcClient = manager.createClient (prop.getProperty("url"));

                       IdcContext userContext = new IdcContext (prop.getProperty("user"),prop.getProperty("password"));

			// Create an HdaBinderSerializer; this is not necessary, but it allows us to serialize the request and response data binders
			HdaBinderSerializer serializer = new HdaBinderSerializer ("UTF-8", idcClient.getDataFactory ());
			
			// Create a new binder for submitting a search
			DataBinder dataBinder = idcClient.createBinder();
       
            dataBinder.putLocal("IdcService", "GET_ARCHIVES");
            dataBinder.putLocal("IDC_Name",prop.getProperty("IDC_Name"));
            //dataBinder.putLocal("aArchiveName",prop.getProperty("aArchiveName"));
           // dataBinder.putLocal("aTargetArchive",prop.getProperty("aTargetArchive"));
                           
            // Write the data binder for the request to stdout
            ServiceResponse response = idcClient.sendRequest(userContext,dataBinder);
            
            // Get the data binder for the response from Content Server
            DataBinder responseData = response.getResponseAsBinder();
            // Write the response data binder to stdout
         //   serializer.serializeBinder (System.out, responseData);
            // Retrieve the SearchResults ResultSet from the response

            DataResultSet resultSet = responseData.getResultSet("ArchiveData");
           
                String [] tokens = {""};
                String [] TotalTrans = {""};
                String [] count = {""};
      
            // Iterate over the ResultSet, retrieve properties from the content items
            for (DataObject dataObject : resultSet.getRows ()) {

                              if (dataObject.get("aArchiveName").equals(prop.getProperty("aArchiveName")))
                                           {

                                            System.out.println(dataObject.get ("aArchiveData"));

                                         tokens = dataObject.get ("aArchiveData").split("\\n+");

                                                       //    System.out.println(tokens[12]);

                                            }
                           

            } 



for ( String st  : tokens  )

{

if ( st.contains("aTotalTransferedOut="))
       {
                  TotalTrans = st.split("\\=+");
                     
                  System.out.println(TotalTrans[1]);
                      
                  count = TotalTrans[1].split("\\s+");
                         System.out.println(count[0]);
             int k = Integer.valueOf(count[0]);         
       }


}

           			
		} catch (IdcClientException ice){
			ice.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

}
