package Libraries;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Types;
import java.util.stream.Stream;

public class DBMS extends Driver{
	
	//public static String[] getDBMSOutPut(String function,String input ,String environment){
		
		
		public static void main(String[] args) {
	String[] responseArray=null;
	String strUserID = "QAMAVERIC";
	String strPassword = "Qwerty_9999"; 
	String hostName = "10.14.170.65";
	String port = "1531";
	String SIDName = "BRMINTG";
	
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			//Connection c=DriverManager.getConnection("jdbc:oracle:thin:@10.14.170.65:1531:BRMINTG","QAMAVERIC","Qwerty_9999");
			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@"+hostName+":"+port+":"+SIDName,strUserID,strPassword);
			Statement s=c.createStatement();  
			
			String query="declare retv varchar2(500); begin PIN.MSISDN_BALANCE_CC_NEW('100128141735415', 'TRUE', retv); dbms_output.put_line(retv); end;";
//			String query=DB_Validation.DataBseproperties(function+"_Query");
//			if(query.contains("input1")){
//				query=query.replace("input1", input);
//				}
//			
			clog.info("---> Executing Query.... " + query);
			  
			   try {
				   s.executeUpdate("begin dbms_output.enable(); end;"); 
				
				   s.executeUpdate(query); 
				   try(CallableStatement call = c.prepareCall
						   ("declare  num integer := 1000;"+
						   "begin dbms_output.get_lines(?, num);"
						  + "end;"))
				   {
					   call.registerOutParameter(1, Types.ARRAY,"DBMSOUTPUT_LINESARRAY");
					   call.execute(); 
					   java.sql.Array array = null;
					   
			
					   try {
							 array = call.getArray(1);
							 Stream.of((Object[]) array.getArray()).forEach(System.out::println);
							 
							  responseArray = (String[])array.getArray();
							// System.out.println(responseArray[0]);
							 		 
							 }
					   catch(Exception f)
						{
							f.printStackTrace();
						}
//					   finally {
//							 if (array != null)
//							/*this array.free throwing exception*/ array.free(); 
//							 }
				   }
			   }finally {
				   s.executeUpdate("begin dbms_output.disable; end;");
			   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println(responseArray[1]);
		printResponse(responseArray);
		//return responseArray;
		
	}
	  
	public static void printResponse(String[] responses){
		
		for (String response : responses) 
		{ 
		   System.out.println(response);
		   clog.info(response);
		}
		
	}
	
	}


