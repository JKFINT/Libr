package Libraries;

import java.io.File;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;

public class messageQueue extends Driver{
	public static logs clog = new logs();
	
	public static void pushXMLRequest(String queue,String xmlname,String SR_ID) {
		
		 try {
		        int openOptions = CMQC.MQOO_INQUIRE | CMQC.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;
		        MQQueueManager qMgr;
		        if(Utlities.pullData(TC_Id.get(), "EAI_Env").contains("PreProd")) {
		        	 	MQEnvironment.hostname = "10.14.170.219";
				        clog.info("hostname :"+ MQEnvironment.hostname);
				        qMgr = new MQQueueManager("EEAX206ID");
				        clog.info("MQQueueManager :"+ "EEAX206ID");
		        }else {
		        MQEnvironment.hostname = "10.14.170.217";
		        clog.info("hostname :"+ MQEnvironment.hostname);
		        qMgr = new MQQueueManager("EEAX206I");
		        clog.info("MQQueueManager :"+ "EEAX206I");
		        }
		        MQEnvironment.port = 1415;
		        clog.info("port :"+ "1415");
		        MQEnvironment.channel = "SYSTEM.ADMIN.SVRCONN"; 
		        clog.info("channel :"+  "SYSTEM.ADMIN.SVRCONN");
		        MQEnvironment.properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES);
		        clog.info("Queue Name :"+ queue);
		       // MQQueue destQueue = qMgr.accessQueue("MOBILY.MNP.PG.RECIPIENT.INIT.NPRACKREPLY", openOptions);
		        MQQueue destQueue = qMgr.accessQueue(queue, openOptions);
		        MQMessage newMQMsg = new MQMessage();
		        String request=readXmlFile(xmlname,SR_ID);
		        newMQMsg.writeString(request);
		        MQPutMessageOptions pmo = new MQPutMessageOptions();  
		        destQueue.put(newMQMsg, pmo);
		        destQueue.close();
		        qMgr.disconnect();
		        clog.info("<------XML Pushed Successfully------>");
		      //  System.out.println("------------------------success...");            
		    } catch (Exception e) {
		    	//clog.info(e.printStackTrace()); 
		    	e.printStackTrace();
		    	clog.info("<------XML Push UnSuccessfully------>");  	
    }
	}	
		public static String readXmlFile(String fileName,String requestId)
		{
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer;
		    String xmlString=null;
		    String xml =null;
		    try {
		    	
		    	//File file = new File("C:/Users/jayakumarj/Desktop/MQ/push.xml");
		    //	File file = new File("src/main/resources/"+fileName+".xml");
		    	File file = new File(WorkingDir.get() + "/Files/MqMessage/"+fileName+".xml");
		    	//File file = new File("E:/develop1/backend/Files/AutomationScripts/Java/TMT_Automation/Tafeely_XML_File/"+Testdata.get("fileName")+".xml");
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document document = db.parse(file);
		        transformer = tf.newTransformer();
		         
		        // Uncomment if you do not require XML declaration
		        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		         
		        //A character stream that collects its output in a string buffer, 
		        //which can then be used to construct a string.
		        StringWriter writer = new StringWriter();
		 
		        //transform document to string 
		        transformer.transform(new DOMSource(document), new StreamResult(writer));
		        
		         xml = new String(
		        	    Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
		 
		         xmlString = writer.getBuffer().toString(); 
		         
		       clog.info("old string"+xmlString);
		         
		         int Srid=xmlString.indexOf("SR_MNP_");
		         String oldServiceRequest=xmlString.substring(Srid, Srid+17);
		      //   System.out.println("csr string"+csr);
		         
		        // clog.info("Old SR ID------>"+oldServiceRequest);
		        // String nsr="SR_MNP_1153939555";
		         xmlString=xmlString.replace(oldServiceRequest, requestId);
		         clog.info("<------XML Updated Successfully------>"+xmlString);
		      //  System.out.println("new string"+xml);
		    } 
		    catch (TransformerException e) 
		    {
		        e.printStackTrace();
		    }
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		    }
			return xmlString;
		}
		
		//////*****************Tafeely******************************///
		public static void pushXMLRequest_TAF(String REQUESTQUEUEMANAGER,String REQUESTQUEUENAME) {
			
			 try {
				 
				 int openOptions = CMQC.MQOO_INQUIRE | CMQC.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;
			        //needd to pass
			        MQEnvironment.hostname = "10.14.170.217";
			        clog.info("hostname :"+ MQEnvironment.hostname);
			      //needd to pass
			        MQEnvironment.port = 1415;
			        clog.info("port :"+ "1415");
			      //needd to pass
			        MQEnvironment.channel = "SYSTEM.ADMIN.SVRCONN"; 
			        clog.info("channel :"+  "SYSTEM.ADMIN.SVRCONN");
			        MQEnvironment.properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES);
			        MQQueueManager qMgr;
			        //reply qmanager need to pass
			        qMgr = new MQQueueManager(REQUESTQUEUEMANAGER);
			        clog.info("MQQueueManager :"+ REQUESTQUEUEMANAGER);
			        clog.info("Queue Name :"+ REQUESTQUEUENAME);
			       // MQQueue destQueue = qMgr.accessQueue("MOBILY.MNP.PG.RECIPIENT.INIT.NPRACKREPLY", openOptions);	        
			        MQQueue destQueue = qMgr.accessQueue(REQUESTQUEUENAME, openOptions);
			        MQMessage newMQMsg = new MQMessage();
			        String request=readXmlFile_TAF();
			        newMQMsg.writeString(request);
			        MQPutMessageOptions pmo = new MQPutMessageOptions();  
			        destQueue.put(newMQMsg, pmo);
			        destQueue.close();
			        qMgr.disconnect();
			        clog.info("<------XML Pushed Successfully------>");
			      //  System.out.println("------------------------success...");            
			    } catch (Exception e) {
			    	//clog.info(e.printStackTrace()); 
			    	e.printStackTrace();
			    	clog.info("<------XML Push UnSuccessfully------>");  		   	
	    }
		}	
			public static String readXmlFile_TAF( )
			{
			    TransformerFactory tf = TransformerFactory.newInstance();
			    Transformer transformer;
			    String xmlString=null;
			    String xml =null;
			    try {			    	
			    	//File file = new File("C:/Users/jayakumarj/Desktop/MQ/push.xml")/TMT_Automation/Tafeely_XML_File/Tafeely_XML.xml;
			    	//E:/develop1/backend/Files/AutomationScripts/Java/TMT_Automation/Tafeely_XML_File
			    	File file = new File("E:/develop1/backend/Files/AutomationScripts/Java/TMT_Automation/Tafeely_XML_File/"+Testdata.get("fileName")+".xml");
			    	clog.info("FileSelectedfrom"+file);
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document document = db.parse(file);
			        transformer = tf.newTransformer();			         
			        // Uncomment if you do not require XML declaration
			        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");			         
			        //A character stream that collects its output in a string buffer, 
			        //which can then be used to construct a string.
			        StringWriter writer = new StringWriter();			 
			        //transform document to string 
			        transformer.transform(new DOMSource(document), new StreamResult(writer));			        
			         xml = new String(
			        	    Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);			 
			         xmlString = writer.getBuffer().toString(); 		         
			         clog.info("old string"+xmlString);
				        // OrderDate
			         int orderDateIndex=xmlString.indexOf("<OrderDate>");
			         int orderDateIndexEnd=xmlString.indexOf("</OrderDate>");
			         String oldOrderDate=xmlString.substring(orderDateIndex+11, orderDateIndexEnd);
			         xmlString=xmlString.replace(oldOrderDate,Testdata.get("OrderDate"));
			         clog.info("<---------OrderDate Updated Successfully------->");		         
			         //RequestorUserId
			         int Requestid=xmlString.indexOf("<RequestorUserId>");
			         int RequestidEnd=xmlString.indexOf("</RequestorUserId>");
			         String oldRequestid=xmlString.substring(Requestid+17, RequestidEnd); 
			         xmlString=xmlString.replace(oldRequestid,Testdata.get("RequestorUserId"));
			         clog.info("<------Requestid Updated Successfully------>");
			       //DealerId
			         int Dealerid=xmlString.indexOf("<DealerId>");
			         int DealeridEnd=xmlString.indexOf("</DealerId>");
			         String oldDealerid=xmlString.substring(Dealerid+10, DealeridEnd); 
			         xmlString=xmlString.replace(oldDealerid, Testdata.get("DealerId"));
			         clog.info("<------Dealerid Updated Successfully------>"+Testdata.get("DealerId"));
			         clog.info("<------Dealerid Updated Successfully------>"+xmlString);
			       //ShopId
			        int Shopid=xmlString.indexOf("<ShopId>");
			        int ShopidEnd=xmlString.indexOf("</ShopId>");
			         String oldShopid=xmlString.substring(Shopid+8, ShopidEnd); 
			         xmlString=xmlString.replace(oldShopid, Testdata.get("ShopId"));
			         clog.info("<------ShopId Updated Successfully------>"+xmlString);
			       //AgentId
			         int Agentid=xmlString.indexOf("<AgentId>");
			         int AgentidEnd=xmlString.indexOf("</AgentId>");
			         String oldAgentid=xmlString.substring(Agentid+9, AgentidEnd); 
			         xmlString=xmlString.replace(oldAgentid, Testdata.get("AgentId"));
			         clog.info("<------AgentId Updated Successfully------>"+xmlString);
			       //OrderCreationDate
			         int Ordercreationdate=xmlString.indexOf("<OrderCreationDate>");
			         int OrdercreationdateEnd=xmlString.indexOf("</OrderCreationDate>");
			         String oldOrdercreationdate=xmlString.substring(Ordercreationdate+19, OrdercreationdateEnd); 
			         xmlString=xmlString.replace(oldOrdercreationdate, Testdata.get("OrderCreationDate"));
			         clog.info("<------OrderCreationDate Updated Successfully------>"+xmlString);
			       //eSalesOrderNumber
			         int eSalesordernumber=xmlString.indexOf("<eSalesOrderNumber>");
			         int eSalesordernumberEnd=xmlString.indexOf("</eSalesOrderNumber>");
			         String oldSalesordernumber=xmlString.substring(eSalesordernumber+19, eSalesordernumberEnd); 
			         xmlString=xmlString.replace(oldSalesordernumber, Testdata.get("eSalesOrderNumber"));
			         clog.info("<------eSalesOrderNumber Updated Successfully------>"+xmlString);
			       //SubOrderNumber
			         int Subordernumber=xmlString.indexOf("<SubOrderNumber>");
			         int SubordernumberEnd=xmlString.indexOf("</SubOrderNumber>");
			         String oldsubordernumber=xmlString.substring(Subordernumber+16, SubordernumberEnd); 
			         xmlString=xmlString.replace(oldsubordernumber, Testdata.get("SubOrderNumber"));
			         clog.info("<------SubOrderNumber Updated Successfully------>"+xmlString);
			       //CustomerAccountNo
			         int CustomeraccountNo=xmlString.indexOf("<CustomerAccountNo>");
			         int CustomeraccountNoEnd=xmlString.indexOf("</CustomerAccountNo>");
			         String oldcustomeraccountNo=xmlString.substring(CustomeraccountNo+19, CustomeraccountNoEnd); 
			         xmlString=xmlString.replace(oldcustomeraccountNo, Testdata.get("CustomerAccountNo"));
			         clog.info("<------CustomerAccountNo Updated Successfully------>"+xmlString);
			       //IsReseller
			         int Isreseller=xmlString.indexOf("<IsReseller>");
			         int IsresellerEnd=xmlString.indexOf("</IsReseller>");
			         String oldisreseller=xmlString.substring(Isreseller+12, IsresellerEnd); 
			         xmlString=xmlString.replace(oldisreseller, Testdata.get("IsReseller"));
			         clog.info("<------IsReseller Updated Successfully------>"+xmlString);
			       //ResellerMSISDN
			         int Resellermsisdn=xmlString.indexOf("<ResellerMSISDN>");
			         int ResellermsisdnEnd=xmlString.indexOf("</ResellerMSISDN>");
			         String oldresellermsisdn=xmlString.substring(Resellermsisdn+16, ResellermsisdnEnd); 
			         xmlString=xmlString.replace(oldresellermsisdn, Testdata.get("ResellerMSISDN"));
			         clog.info("<------ResellerMSISDN Updated Successfully------>"+xmlString);
			       //ResellerWalletDeduction
			         int Resellerwalletdeduction=xmlString.indexOf("<ResellerWalletDeduction>");
			         int ResellerwalletdeductionEnd=xmlString.indexOf("</ResellerWalletDeduction>");
			         String oldResellerwalletdeduction=xmlString.substring(Resellerwalletdeduction+25, ResellerwalletdeductionEnd); 
			         xmlString=xmlString.replace(oldResellerwalletdeduction, Testdata.get("ResellerWalletDeduction"));
			         clog.info("<------ResellerWalletDeduction Updated Successfully------>"+xmlString);
			       //IDDocumentType
			         int IDdocumenttype=xmlString.indexOf("<IDDocumentType>");
			         int IDdocumenttypeEnd=xmlString.indexOf("</IDDocumentType>");
			         String oldIDdocumenttype=xmlString.substring(IDdocumenttype+16, IDdocumenttypeEnd); 
			         xmlString=xmlString.replace(oldIDdocumenttype, Testdata.get("IDDocumentType"));
			         clog.info("<------IDDocumentType Updated Successfully------>"+xmlString);
			       //IDDocumentNumber
			         int IDdocumentnumber=xmlString.indexOf("<IDDocumentNumber>");
			         int IDdocumentnumberEnd=xmlString.indexOf("</IDDocumentNumber>");
			         String oldIDdocumentnumber=xmlString.substring(IDdocumentnumber+18, IDdocumentnumberEnd); 
			         xmlString=xmlString.replace(oldIDdocumentnumber, Testdata.get("IDDocumentNumber"));
			         clog.info("<------IDDocumentNumber Updated Successfully------>"+xmlString);
			       //StreetAddress
			         int Streetaddress=xmlString.indexOf("<StreetAddress>");
			         int StreetaddressEnd=xmlString.indexOf("</StreetAddress>");
			         String oldStreetaddress=xmlString.substring(Streetaddress+15, StreetaddressEnd); 
			         xmlString=xmlString.replace(oldStreetaddress, Testdata.get("StreetAddress"));
			         clog.info("<------StreetAddress Updated Successfully------>"+xmlString);
			       //PackageName
			         int Packagename=xmlString.indexOf("<PackageName>");
			         int PackagenameEnd=xmlString.indexOf("</PackageName>");
			         String oldPackagename=xmlString.substring(Packagename+13, PackagenameEnd); 
			         xmlString=xmlString.replace(oldPackagename, Testdata.get("PackageName"));
			         clog.info("<------PackageName Updated Successfully------>"+xmlString);  
				   //PromotionName			         
			         int Promotionname=xmlString.indexOf("<PromotionName>");
			         int PromotionnameEnd=xmlString.indexOf("</PromotionName>");
			         String oldPromotionname=xmlString.substring(Promotionname+15, PromotionnameEnd); 
			         xmlString=xmlString.replace(oldPromotionname, Testdata.get("PromotionName"));
			         clog.info("<------PromotionName Updated Successfully------>"+xmlString);
			       //PromoCode
			         int Promocode=xmlString.indexOf("<PromoCode>");
			         int PromocodeEnd=xmlString.indexOf("</PromoCode>");
			         String oldPromocode=xmlString.substring(Promocode+11, PromocodeEnd); 
			         xmlString=xmlString.replace(oldPromocode, Testdata.get("PromoCode"));
			         clog.info("<------PromoCode Updated Successfully------>"+xmlString);
			       //BundleAddOnName
			         int BundleAddOnname=xmlString.indexOf("<BundleAddOnName>");
			         int BundleAddOnnameEnd=xmlString.indexOf("</BundleAddOnName>");
			         String oldBundleAddOnname=xmlString.substring(BundleAddOnname+17, BundleAddOnnameEnd); 
			         xmlString=xmlString.replace(oldBundleAddOnname, Testdata.get("BundleAddOnName"));
			         clog.info("<------BundleAddOnName Updated Successfully------>"+xmlString);
			       //AddOnPrice
			         int AddOnprice=xmlString.indexOf("<AddOnPrice>");
			         int AddOnpriceEnd=xmlString.indexOf("</AddOnPrice>");
			         String oldAddOnprice=xmlString.substring(AddOnprice+12, AddOnpriceEnd); 
			         xmlString=xmlString.replace(oldAddOnprice, Testdata.get("AddOnPrice"));
			         clog.info("<------AddOnPrice Updated Successfully------>"+xmlString);
			       //MSISDN
			         int msisdn=xmlString.indexOf("<MSISDN>");
			         int msisdnEnd=xmlString.indexOf("</MSISDN>");
			         String oldMSISDN=xmlString.substring(msisdn+8, msisdnEnd); 
			         xmlString=xmlString.replace(oldMSISDN, Testdata.get("MSISDN"));
			         clog.info("<------MSISDN Updated Successfully------>"+xmlString);
			       //SIM
			         int sim=xmlString.indexOf("<SIM>");
			         int simEnd=xmlString.indexOf("</SIM>");
			         String oldSIM=xmlString.substring(sim+5, simEnd); 
			         xmlString=xmlString.replace(oldSIM, Testdata.get("SIM"));
			         clog.info("<------SIM Updated Successfully------>"+xmlString);  
			       //SIMType
			         int SIMtype=xmlString.indexOf("<SIMType>");
			         int SIMtypeEnd=xmlString.indexOf("</SIMType>");
			         String oldSIMtype=xmlString.substring(SIMtype+9, SIMtypeEnd); 
			         xmlString=xmlString.replace(oldSIMtype, Testdata.get("SIMType"));
			         clog.info("<------SIMType Updated Successfully------>"+xmlString);
			       //FingerPrintTimeStamp
			         int FingerPrinttimestamp=xmlString.indexOf("<FingerPrintTimeStamp>");
			         int FingerPrinttimestampEnd=xmlString.indexOf("</FingerPrintTimeStamp>");
			         String oldFingerPrinttimestamp=xmlString.substring(FingerPrinttimestamp+22, FingerPrinttimestampEnd); 
			         xmlString=xmlString.replace(oldFingerPrinttimestamp, Testdata.get("FingerPrintTimeStamp"));
			         clog.info("<------FingerPrintTimeStamp Updated Successfully------>");
			         clog.info("<------XML Updated Successfully------>"+xmlString);    
			         
			    } 
			    catch (TransformerException e) 
			    {
			        e.printStackTrace();
			    }
			    catch (Exception e) 
			    {
			        e.printStackTrace();
			    }
				return xmlString;
			}
			
			public static void pushXMLRequest_EportalNeqatona(String REQUESTQUEUEMANAGER,String REQUESTQUEUENAME) {
		           
	             try {
	                
	                 int openOptions = CMQC.MQOO_INQUIRE | CMQC.MQOO_INPUT_AS_Q_DEF | MQConstants.MQOO_OUTPUT;
	                    //needd to pass
	                    MQEnvironment.hostname = "10.14.11.77";
	                    clog.info("hostname :"+ MQEnvironment.hostname);
	                  //needd to pass
	                    MQEnvironment.port = 1415;
	                    clog.info("port :"+ "1415");
	                  //needd to pass
	                    MQEnvironment.channel = "SYSTEM.ADMIN.SVRCONN";
	                    clog.info("channel :"+  "SYSTEM.ADMIN.SVRCONN");
	                    MQEnvironment.properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES);
	                    MQQueueManager qMgr;
	                    //reply qmanager need to pass
	                    qMgr = new MQQueueManager(REQUESTQUEUEMANAGER);
	                    clog.info("MQQueueManager :"+ REQUESTQUEUEMANAGER);
	                    clog.info("Queue Name :"+ REQUESTQUEUENAME);
	                   // MQQueue destQueue = qMgr.accessQueue("MOBILY.MNP.PG.RECIPIENT.INIT.NPRACKREPLY", openOptions);           
	                    MQQueue destQueue = qMgr.accessQueue(REQUESTQUEUENAME, openOptions);
	                    MQMessage newMQMsg = new MQMessage();
	                    String request=readXmlFile_TAF();
	                    newMQMsg.writeString(request);
	                    MQPutMessageOptions pmo = new MQPutMessageOptions();
	                    destQueue.put(newMQMsg, pmo);
	                    destQueue.close();
	                    qMgr.disconnect();
	                    clog.info("<------XML Pushed Successfully------>");
	                  //  System.out.println("------------------------success...");           
	                } catch (Exception e) {
	                    //clog.info(e.printStackTrace());
	                    e.printStackTrace();
	                    clog.info("<------XML Push UnSuccessfully------>");                
	        }
	        }
			
			public static String readXmlFile_EportalNeqatona( )
            {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer;
                String xmlString=null;
                String xml =null;
                try {                   
                    //File file = new File("C:/Users/jayakumarj/Desktop/MQ/push.xml")/TMT_Automation/Tafeely_XML_File/Tafeely_XML.xml;
                    //E:/develop1/backend/Files/AutomationScripts/Java/TMT_Automation/Tafeely_XML_File
                	
                //	File file = new File(WorkingDir.get() + "/Files/MqMessage/"+fileName+".xml");
                    File file = new File("E:/develop1/backend/Files/AutomationScripts/Java/TMT_Automation/Eportal_XML_File/"+Testdata.get("fileName")+".xml");
                    clog.info("FileSelectedfrom"+file);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document document = db.parse(file);
                    transformer = tf.newTransformer();                    
                    // Uncomment if you do not require XML declaration
                    // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");                    
                    //A character stream that collects its output in a string buffer,
                    //which can then be used to construct a string.
                    StringWriter writer = new StringWriter();            
                    //transform document to string
                    transformer.transform(new DOMSource(document), new StreamResult(writer));                   
                     xml = new String(
                            Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);            
                     xmlString = writer.getBuffer().toString();                 
                     clog.info("old string"+xmlString);
                    
                     // SrDate
                     int orderDateIndex=xmlString.indexOf("<SrDate>");
                     int orderDateIndexEnd=xmlString.indexOf("</SrDate>");
                     String oldOrderDate=xmlString.substring(orderDateIndex+11, orderDateIndexEnd);
                     xmlString=xmlString.replace(oldOrderDate,Testdata.get("SrDate"));
                     clog.info("<---------SrDate Updated Successfully------->");       
                    
                   //MSISDN
                     int msisdn=xmlString.indexOf("<MSISDN>");
                     int msisdnEnd=xmlString.indexOf("</MSISDN>");
                     String oldMSISDN=xmlString.substring(msisdn+8, msisdnEnd);
                     xmlString=xmlString.replace(oldMSISDN, Testdata.get("MSISDN"));
                     clog.info("<------MSISDN Updated Successfully------>"+xmlString);
                }
                catch (TransformerException e)
                {
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return xmlString;
            }
			

}

			         