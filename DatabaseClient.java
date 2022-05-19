package Libraries;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class DatabaseClient extends Driver {
	public static Connection conn = null;



	public static String DB_Validation(String DB_Name, String Query, String function, String input) {


		String getStrVal = null;
		try {
			switch (Query) {

				case "Validate":
					DB_Validation.Validate(DB_Name, function, input);
					break;
				case "UpdateQuery":
					DB_Validation.Update(DB_Name, function, input);
					break;
				case "GetDataFromQuery":
					getStrVal = DB_Validation.getDataFromQueryTable(DB_Name, function, input);
					break;
				case "GetMultipleValFromQuery":

					getStrVal = DB_Validation.getMultipleValFromQueryTbl(DB_Name, function, input);
					break;
				case "GetCompleteTblFromQuery":
					getStrVal = DB_Validation.getCompleteTblFromQuery(DB_Name, function, input);
					break;
				case "GetMultValFromFirstRow":
					getStrVal = DB_Validation.getMultValFromFirstRow(DB_Name, function, input);
					break;
				case "GetMultValFromEachColQuery":
					getStrVal = DB_Validation.getMultValFromEachColQuery(DB_Name, function, input);
					break;
				case "GetMultValWithKey":
					getStrVal = DB_Validation.getMultValWithKey(DB_Name, function, input);
					break;
				case "GetDataFromLastRow":
					getStrVal = DB_Validation.getDataFromLastRow(DB_Name, function, input);
					break;
				case "GetMultipleRowValFromQueryTb":
					getStrVal = DB_Validation.getMultipleRowValFromQueryTb(DB_Name, function, input);
					break;
				case "GetDBMSOutput":
					getStrVal = DB_Validation.getMultipleRowValFromQueryTb(DB_Name, function, input);
					break;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			clog.error(Result.getStackMsg(e));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			clog.error(Result.getStackMsg(e));
		}

		return getStrVal;

	}

	public static void DB_wait()
	{
		try {
			Thread.sleep(20000);
		}
		catch(Exception e)
		{

		}
	}

	public static void dbAssertionWithKeyValue(String getCompleteTbl, String expectedKey, String expectedValue) {
		String key = "";
		String value = "";

		 getCompleteTbl = StringUtils.substringBetween(getCompleteTbl,"{","}").replace("],","];");
		 //System.out.println(getCompleteTbl);
		 String[] keyValuePairs = getCompleteTbl.split(";");

		 SetMultimap<String, String> multiMap = HashMultimap.create();

		 for(String pair : keyValuePairs) {
		     String[] entry = pair.split("=");
			 for (int j=0;j<entry.length;j++) {
				 if(j == 0) {
					key = entry[j].trim();
				 } else {
					entry[j] = StringUtils.substringBetween(entry[j],"[","]");
					if (entry[j] != null) {
						String[] arrValue = entry[j].split(",");
						for (int k=0;k<arrValue.length;k++) {
							value = arrValue[k];
							multiMap.put(key.trim(),value.trim());
						}
					}
				 }
			 }
		 }

		 //System.out.println(multiMap);
		 String actualKey = null;
		 String actualValue = null;
		 boolean expectedValueIsVerified;
		 expectedValueIsVerified = false;
		 for(Entry<String , String> entry:multiMap.entries()) {
			 actualKey = entry.getKey();
			 actualValue = entry.getValue();
			 //System.out.println(actualKey + " : "+actualValue);
			 if (actualKey.equalsIgnoreCase(expectedKey) && actualValue.equalsIgnoreCase(expectedValue)) {
				 expectedValueIsVerified = true;
				 break;
			 }
		 }

		 if (expectedValueIsVerified == true) {
			 clog.info("PASS - Key of '"+actualKey+"' has been verified in the table as '"+actualValue+"'");
		 } else {
			 clog.error("FAIL - Key of '"+actualKey+"' has not been verified in the table as '"+actualValue+"'");
		 }

	}

	public static String getValuePairFromMultiMap(String getMultiVal, String expectedKey) {
		String key = "";
		String value = "";

		getMultiVal = StringUtils.substringBetween(getMultiVal,"{","}").replace("],","];");
		 String[] keyValuePairs = getMultiVal.split(";");

		 SetMultimap<String, String> multiMap = HashMultimap.create();

		 for(String pair : keyValuePairs) {

		     String[] entry = pair.split("=");
			 for (int j=0;j<entry.length;j++) {
				 if(j == 0) {
					key = entry[j].trim();
				 } else {
					entry[j] = StringUtils.substringBetween(entry[j],"[","]");
					if (entry[j] != null) {
						String[] arrValue = entry[j].split(",");
						for (int k=0;k<arrValue.length;k++) {
							value = arrValue[k];
							multiMap.put(key.trim(),value.trim());
						}
					}
				 }
			 }
		 }
		 //System.out.println(multiMap);
		 String actualKey = null;
		 String actualValue = null;
		 for(Entry<String , String> entry:multiMap.entries()) {
			 actualKey = entry.getKey();
			 if (actualKey.equalsIgnoreCase(expectedKey)) {
				 actualValue = entry.getValue();
				 break;
			 }
		 }

		 return actualValue;

	}

	public static String getValuePairFromResponse(String getMultiVal, String expectedKey) {
		String key = "";
		String value = "";
		checkValidResponse(getMultiVal);
		getMultiVal = StringUtils.substringBetween(getMultiVal,"{","}").replace("],","];");
		 //clog.info("getMultiVal - Value of '"+getMultiVal);
		 String[] keyValuePairs = getMultiVal.split(";");

		 SetMultimap<String, String> multiMap = HashMultimap.create();

		 for(String pair : keyValuePairs) {
		     String[] entry = pair.split(",");
		     //clog.info("pair - Value of '"+pair);

		     for(int j=0;j<entry.length;j++) {
		    	String[] findValue=entry[j].split("=");
		    	key = findValue[0].trim();
		    	value = findValue[1].trim();
		    	//clog.info("key - Value of '"+key+"value - Value of '"+value );
		    	multiMap.put(key,value);

		     }
		 }

		 String actualKey = null;
		 String actualValue = null;
		 for(Entry<String , String> entry:multiMap.entries()) {
			 actualKey = entry.getKey();
			 if (actualKey.equalsIgnoreCase(expectedKey)) {
				 actualValue = entry.getValue();
				 break;
			 }
		 }
		 //clog.info("actualValue - Value of '"+actualValue);
		 return actualValue;

	}

	public static void checkValidResponse(String response) {
		int n=response.length();
		if(n<=2) {
			clog.info("Invalid or Empty Response retrived");
			Driver.Continue.set(false);

			//Assert.fail(actual + " text is not equal to " + expected);
		}
		if (methodScreenShotTaken.get() == false) {
			Result.Methodscreenshot();
		}
		}



	public static void dbAssertionStatus(String getMultiVal, String expectedKey, String expectedValue) {
		String actualStatus = getValuePairFromMultiMap(getMultiVal, expectedKey);

		if (actualStatus.equalsIgnoreCase(expectedValue)) {
		//if (actualStatus.equalsIgnoreCase("1") || actualStatus.equalsIgnoreCase("2") || actualStatus.equalsIgnoreCase("3")) {
			clog.info("PASS - Key of '"+expectedKey+"' has been verified as '"+actualStatus+"'");
		}  else {
			clog.error("FAIL - Key of '"+expectedKey+"' has not been verified as '"+actualStatus+"'");
		}

		/*else if (actualStatus.equalsIgnoreCase("645") || actualStatus.equalsIgnoreCase("503") || actualStatus.equalsIgnoreCase("541")) {
			clog.info("PASS - Key of '"+expectedKey+"' has been verified as '"+actualStatus+"'");
		} */
	}

	public static void waitForDelay(String DB_Name, int delay) throws SQLException, IOException {

		conn = DB_Validation.OracleDBConnector(DB_Name);

		try {
			clog.info("Date - Start before delay : "+getCurrDate());
			TimeUnit.MINUTES.sleep(delay);
			clog.info("Date - End after delay '"+delay+"' min : "+getCurrDate());

			conn.close();
		} catch (Exception e) {
			clog.error(e.getMessage());
		}
	}

	public static java.sql.Timestamp getCurrDate() throws SQLException, IOException {
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp sqlCurrDate = new java.sql.Timestamp(calendar.getTime().getTime());
		return sqlCurrDate;

	}

	public static String getOTPFromEAIQuery(String QueryValue, String MSISDN) {
		String OTP = null;

		if (QueryValue.contains("If you like to proceed, please send ")) {
			String rep1 = "You have requested to transfer the ownership of the number ("+MSISDN+"). If you like to proceed, please send ";
			OTP = QueryValue.replace("Dear Customer","").replace(rep1, "").replace(" to 1100.", "").trim();
		} else {
			clog.error("OTP Value is not found in the Message column");
		}

		OTP = OTP.replace("<", "").replace(">", "").trim();

		return OTP;
	}

	public static String getOTPFromMobileApp(String QueryValue) {
		String OTP = null;

		String[] sp_OTP = QueryValue.split(";");
		//966563886293;2413;68737964
	      OTP=sp_OTP[1];

//		String[] sp_OTP = QueryValue.split(";");
//		//966547121395;824863;<#>,2149,B0cLpW/zz6i
//		String[] gOTP = sp_OTP[2].split(",");
//		String fOTP = gOTP[1].trim();
//
//		try {
//			OTP = Integer.parseInt(fOTP);
//		} catch (NumberFormatException e) {
//			clog.error("OTP is not correct : " +fOTP );
//		}
//
		return OTP;
	}
	public static String getComplaintOTPFromMobileApp(String QueryValue) {
		String OTP = null;

		String[] sp_OTP = QueryValue.split(";");
		//966563886293;2413;68737964
	      OTP=sp_OTP[1];

//		String[] sp_OTP = QueryValue.split(";");
//		//966547121395;824863;<#>,2149,B0cLpW/zz6i
//		String[] gOTP = sp_OTP[2].split(",");
//		String fOTP = gOTP[1].trim();
//
//		try {
//			OTP = Integer.parseInt(fOTP);
//		} catch (NumberFormatException e) {
//			clog.error("OTP is not correct : " +fOTP );
//		}
//
		return OTP;
	}
	public static String getComplaintOTPorMobile(String QueryValue) {
		String OTP = null;

		String[] sp_OTP = QueryValue.split(";");
		//966563886293;2413;68737964
	      OTP=sp_OTP[2];

//		String[] sp_OTP = QueryValue.split(";");
//		//966547121395;824863;<#>,2149,B0cLpW/zz6i
//		String[] gOTP = sp_OTP[2].split(",");
//		String fOTP = gOTP[1].trim();
//
//		try {
//			OTP = Integer.parseInt(fOTP);
//		} catch (NumberFormatException e) {
//			clog.error("OTP is not correct : " +fOTP );
//		}
//
		return OTP;
	}

}
