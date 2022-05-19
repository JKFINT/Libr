package Libraries;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

import groovy.json.JsonSlurper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utlities extends Driver {

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FindObject
	 * Arguments			: Object Name and Object Type
	 * Use 					: Read the object property from the Object DataBase  excel and return the value
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 14-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String[] FindObject(String name, String objtype) {
		try {
			Connection ORconn = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement orstmt = ORconn.createStatement();
			ResultSet orrs = orstmt.executeQuery("select * from object_repository  where ObjectType = '" + objtype
					+ "' and ObjectName = '" + name + "'");
			String cellval1 = "blank";
			String cellval2 = "blank";
			String cellval3 = "blank";
			String cellval4 = "blank";
			String cellval5 = "blank";
			while (orrs.next()) {
				cellval1 = orrs.getString("Xpath");
				cellval2 = orrs.getString("html_name");
				cellval3 = orrs.getString("html_id");
				cellval4 = orrs.getString("html_classname");
				cellval5 = orrs.getString("LinkText");

			}

			String[] retval = { cellval1, cellval2, cellval3, cellval4, cellval5 };

			orrs.close();
			ORconn.close();
			return retval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FindObjectname
	 * Arguments			: Object Name 
	 * Use 					: Read the object property from the Object DataBase  excel and return the value
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 14-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String FindObjectname(String name) {
		try {
			Connection ORconn = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement orstmt = ORconn.createStatement();
			ResultSet orrs = orstmt.executeQuery("select * from object_repository  where ObjectName = '" + name + "'");
			String cellval1 = "blank";
			while (orrs.next()) {
				cellval1 = orrs.getString("Xpath");
			}

			String retval = cellval1;

			orrs.close();
			ORconn.close();
			return retval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: floadUseCases
	 * Arguments			: 
	 * Use 					: return all the usecases and testcases list
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 14-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	// @SuppressWarnings("deprecation")
	/*
	 * public static ArrayList<String[]> floadUseCases(String Batch) { try {
	 * ArrayList<String[]> addresses = new ArrayList<String[]>(); Fillo nfillo = new
	 * Fillo(); Connection connection = nfillo.getConnection(TestDataDB_File.get());
	 * // String strQuery = "Select * from TestData where Control = \'YES\' ORDER BY
	 * // SeqNo ASC"; String strQuery =
	 * "Select * from TestData where RunControl = 'YES' and ParallelRun='" + Batch +
	 * "' ORDER BY SeqNo ASC"; Recordset rs = connection.executeQuery(strQuery);
	 * rs.moveFirst();
	 * 
	 * String[] IDP = new String[rs.getCount()]; String[] UseCases = new
	 * String[rs.getCount()]; String[] Testcase = new String[rs.getCount()];
	 * String[] TC_Description = new String[rs.getCount()]; String[] data = new
	 * String[rs.getCount()]; String[] ValidationData = new String[rs.getCount()];
	 * 
	 * for (int currs = 1; currs <= rs.getCount(); currs++) { IDP[currs - 1] =
	 * rs.getField(3).value(); UseCases[currs - 1] = rs.getField(4).value();
	 * Testcase[currs - 1] = rs.getField(5).value(); TC_Description[currs - 1] =
	 * rs.getField(6).value(); data[currs - 1] = rs.getField(7).value();
	 * ValidationData[currs - 1] = rs.getField(8).value(); if (rs.hasNext()) {
	 * rs.moveNext(); }
	 * 
	 * } addresses.add(0, IDP); addresses.add(1, UseCases); addresses.add(2,
	 * Testcase); addresses.add(3, TC_Description); addresses.add(4, data);
	 * addresses.add(5, ValidationData); rs.close(); connection.close(); return
	 * addresses; } catch (Exception ex) { System.err.print("Exception: ");
	 * System.err.println(ex.getMessage()); } return null; }
	 */

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: floadKeywords
	 * Arguments			: The current Usecase that is being executed.
	 * Use 					: Returns the list of keywords,DataBinding,Description mapped under the Usecase 
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static String[] floadKeywords(String currentUseCase) {
		try {

			String arr = "";
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select Keyword from usecase_t where UseCase  = '" + currentUseCase + "' and Status = 'Yes'");

			while (rs.next()) {
				if (arr.equals("")) {
					arr = rs.getString("Keyword");
				} else {
					arr = arr + "," + rs.getString("Keyword");
				}

			}
			String[] modarrs = arr.split(",");
			return modarrs;
		} catch (Exception ex) {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
			return null;
		}

	}

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: freaddata
	 * Arguments			: Data for the particular usecase
	 * Use 					: To split data with delimiter and assiging to hashmap
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	/*
	 * public static Object freaddata(String data) { Dictionary<String, String> dict
	 * = new Hashtable<String, String>(); String[] DataSap = data.split("\\|\\|");
	 * 
	 * for (int readloop = 0; readloop < DataSap.length; readloop++) { String[]
	 * Sapdata = DataSap[readloop].split("--"); if (Sapdata.length == 2) {
	 * 
	 * if (Sapdata[1].equals("Fetch#IDP")) { String value =
	 * FetchStoredValue(UseCaseIDP.get(), TestCaseIDP.get(), Sapdata[0]);
	 * dict.put(Sapdata[0], value); } else if (Sapdata[1].equals("Fetch#DP")) {
	 * String value = FetchStoredValue(UseCaseDP.get(), TestCaseDP.get(),
	 * Sapdata[0]); dict.put(Sapdata[0], value); } else { dict.put(Sapdata[0],
	 * Sapdata[1]); }
	 * 
	 * } else { dict.put(Sapdata[0], ""); }
	 * 
	 * } return dict; }
	 */

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: fdatabase
	 * Arguments			: The current Usecase that is being executed.
	 * Use 					: to fetch data form database workbook
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static Object fdatabase() {
		List<Map> dict = null;
		try {
			String arr = "";
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select SampleData from commondirectory_t where UseCaseName  = '" + UseCaseName.get() + "'");
			JsonSlurper jsonParser = new JsonSlurper();
			String tcname = "";
			while (rs.next()) {
				String lis = rs.getString("SampleData");
				@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
				List<Map> parsedData = (List<Map>) jsonParser.parseText(lis);
				for (int curdat = 0; curdat < parsedData.size(); curdat++) {

					tcname = parsedData.get(curdat).get("TestCase").toString();
					if (tcname.equals(TestCaseN.get())) {
						cddataindex.set(curdat);
						break;
					}
				}
				System.out.println(tcname);
				dict = parsedData;

			}

			return dict;
		} catch (Exception e) {
			e.printStackTrace();
			Result.fUpdateLog("No data is available in database sheet for particular Usecase : ");
			return null;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: freaddata_diff
	 * Arguments			: The current Keyword that is being executed.
	 * Use 					: fetch data form the TestdataDb workbook form Details sheet
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	/*
	 * public static Object freaddata_diff(String data) { try { String Screen =
	 * "LoginDetails"; Dictionary<String, String> dict = new Hashtable<String,
	 * String>();
	 * 
	 * Fillo fillo = new Fillo(); Connection connection =
	 * fillo.getConnection(TestDataDB_File.get()); String strQuery =
	 * "Select * from " + Screen + "  Where Application_Details = \'" + data + "\'";
	 * 
	 * Recordset rs = connection.executeQuery(strQuery); int noOfColumns =
	 * rs.getFieldNames().size(); ArrayList<String> fieldnames = rs.getFieldNames();
	 * rs.moveNext(); for (int readloop = 0; readloop < noOfColumns; readloop++) {
	 * String colname = fieldnames.get(readloop); // if
	 * (!colname.equals("Application_Details")) { String dat =
	 * rs.getField(readloop).value(); if (dat == null) { dict.put(colname, ""); }
	 * else { dict.put(colname, dat); } // } } rs.close(); connection.close();
	 * return dict; } catch (Exception e) { e.printStackTrace(); return null; } }
	 */
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: StoreValue
	 * Arguments			: Dynamic_Name,Dynamic_Value
	 * Use 					: to store the data to storeDb
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static void StoreValue(String Name, String Value) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			Result.fUpdateLog(Name + " : " + Value);
			output = new FileOutputStream(storeproperties.get());
			prop.setProperty(Name, Value);
			prop.store(output, null);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FetchStoredValue
	 * Arguments			: -
	 * Use 					: to get the data from the storeDb
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String FetchStoredValue(String Usecase, String Testcase, String Name) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String returnValue = null;
			input = new FileInputStream(storeproperties.get());
			prop.load(input);
			returnValue = prop.getProperty(Name);
			return returnValue;

		} catch (Exception e) {
			return null;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: randname
	 * Arguments			: -
	 * Use 					: Picks random names from the name DB
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String randname() {
		try {
			Random rn = new Random();
			int Min = 2;
			int Max = 859;
			String refname = "";
			int randnumber = rn.nextInt((Max - Min) + 1) + Min;
			String refid = "Name" + randnumber;
			Connection connection = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement orstmt = connection.createStatement();
			// String NameDBpth = Storage_FLD.get() + "/NameDB.xlsx";
			// Fillo fillo = new Fillo();
			// Connection connection = fillo.getConnection(NameDBpth);
			String strQuery = "Select * from NameDB Where RefID ='" + refid + "'";
			ResultSet rs = orstmt.executeQuery(strQuery);
			while (rs.next()) {
				refname = rs.getString(1);
			}

			rs.close();
			connection.close();
			return refname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FetchStoredValue
	 * Arguments			: -
	 * Use 					: to get the data from the Product catalog sheet
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static ArrayList<String> FetchProcuctCatalogData() {
		ArrayList<String> PCSAll = new ArrayList<String>();
		try {
			String BundleID;
			// Fillo fillo = new Fillo();
			// Connection connection = fillo.getConnection(StoreDBpth);
			Connection connection = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement orstmt = connection.createStatement();
			// ResultSet orrs=orstmt.executeQuery("select * from object_repository where
			// ObjectType = '"+objtype+"' and ObjectName = '"+name+"'");
			/*
			 * Planname.set("Postpaid Basic Promotion"); LineItemData.put("0",
			 * "Mobile Service Bundle"); LineItemData.put("1", "Postpaid Basic");
			 * LineItemData.put("2", "SIM Card"); LineItemData.put("3",
			 * "Vodafone Passport");
			 */
			// LineItemData.put("5", "Unlimited Family Calls");
			// LineItemData.put("6", "Bill Manager");
			// LineItemData.put("7", "Smart Limit");

			// LineItemData.put("0", "Vodafone Passport");
			int k = 0;
			for (int i = 0; i < LineItemData.size(); i++) {
				String Product = LineItemData.get(Integer.toString(i));
				switch (Product) {
				case "Smart Limit":
				case "SIM Card":
				case "Mobile Voicemail":
				case "Bill Manager":
				case "Mobile Service Bundle":
					System.out.println("Item available in Line items");
					break;
				default:
					if (!(Product.equalsIgnoreCase(Planname.get()))) {
						String StrQuery = "Select * from automationproductcatalog where `Siebel_Plan_Name`='" + Product
								+ "'";
						ResultSet rs = orstmt.executeQuery(StrQuery);
						while (rs.next()) {
							if (!rs.getString("Siebel_Description").isEmpty()) {
								if (!rs.getString("Siebel_Description").contains("Dummy")) {
									BundleID = rs.getString("BundleID");
									if (BundleID.isEmpty())
										BundleID = " ";
									String Type = rs.getString("SurePayID") + "||" + rs.getString("Benefit") + "||"
											+ rs.getString("BucketValue") + "||" + rs.getString("BucketUsageType")
											+ "||" + rs.getString("ProductValidity") + "||" + BundleID + "||"
											+ rs.getString("SubscriptionPrice") + "||"
											+ rs.getString("Siebel_Description");
									PCSAll.add(k, Type);
									k++;
								}
							}
						}
						rs.close();
					} else {
						Result.fUpdateLog("Plan name matches");
					}
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return PCSAll;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: FetchStoredValue
	 * Arguments			: -
	 * Use 					: to get the data from the storeDb
	 * Designed By			: Imran Baig
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String FetchSmartlimit() {
		try {
			String value = "";
			Connection connection = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement orstmt = connection.createStatement();
			String StrQuery = "Select * from Smartlimit where PlanName='" + Planname.get() + "'";
			ResultSet rs = orstmt.executeQuery(StrQuery);
			while (rs.next()) {
				value = rs.getString("DefaultSmartLimit");
			}
			rs.close();
			connection.close();
			return value;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getURL(String system) throws SQLException, IOException {
		Connection connection = DriverManager.getConnection(
				Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
		Statement orstmt = connection.createStatement();
		String StrQuery = "Select URL from environment_t where Environment='" + environment.get() + "' and System = '"
				+ system + "'";
		ResultSet rs = orstmt.executeQuery(StrQuery);
		String retval = "";
		while (rs.next()) {
			retval = rs.getString("URL");
		}
		rs.close();
		connection.close();

		return retval;
	}

	public static String getpassword(String system) throws SQLException, IOException {
		Connection connection = DriverManager.getConnection(
				Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
		Statement orstmt = connection.createStatement();
		String StrQuery = "Select Password from environment_t where Environment='" + environment.get()
				+ "' and System = '" + system + "'";
		ResultSet rs = orstmt.executeQuery(StrQuery);
		String retval = "";
		while (rs.next()) {
			retval = rs.getString("Password");
		}
		rs.close();
		connection.close();

		return retval;
	}

	public static String getusername(String system) throws SQLException, IOException {
		Connection connection = DriverManager.getConnection(
				Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
		Statement orstmt = connection.createStatement();
		String StrQuery = "Select `User Name` from environment_t where Environment='" + environment.get()
				+ "' and System = '" + system + "'";
		ResultSet rs = orstmt.executeQuery(StrQuery);
		String retval = "";
		while (rs.next()) {
			retval = rs.getString("User Name");
		}
		rs.close();
		connection.close();

		return retval;
	}
	
	
	public static Boolean getParentStatus(String parenTC)
	{
		Boolean stat = true;
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				
				
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				//System.out.println(reader.toString());
				JSONArray array = (JSONArray) parser.parse(reader);
				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					System.out.println(ctcid);
					if (ctcid.equals(parenTC)) {
						String pTCStat = currJTc.get("status").toString();
						if(pTCStat.equalsIgnoreCase("pass"))
						{
							stat = true;
						}
						else
						{
							stat = false;
						}
						break;

					}
				}
				FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stat;
		
	}

	public static void saveData(String key, String val) {
		try {
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			Statement stmt1 = con.createStatement();
			String test_c = TC_Id.get();
			String q1 = "select * from capture_values where storeKey = '" + key + "' and testcase_id = '" + test_c
					+ "' and run_id = '"+runid.get()+"'";
			System.out.println("First Query: " + q1);
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(q1);
			if (rs2.next()) {
				String sqlst = "update capture_values set val = '" + val + "' where storeKey = '" + key
						+ "' and testcase_id = '" + test_c + "' and run_id='"+runid.get()+"'";
				Statement stmt3 = con.createStatement();
				stmt3.executeUpdate(sqlst);
				stmt3.close();
			} else {
				String sqlst = "INSERT INTO capture_values (testcase_id,storeKey,val,run_id) VALUES ('" + test_c + "','" + key
						+ "','" + val + "','"+runid.get()+"')";
				Statement stmt3 = con.createStatement();
				stmt3.executeUpdate(sqlst);
				stmt3.close();
			}
			stmt2.close();
			rs2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String pullData(String test_c, String key) {
		String valret = "";
		try {
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			String q1 = "select * from capture_values where storeKey = '" + key + "' and testcase_id = '" + test_c
					+ "' and run_id='"+runid.get()+"'";

			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(q1);
			if (rs2.next()) {
				valret = rs2.getString("val");
			}
			stmt2.close();
			rs2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valret;
	}
	
	

	public static void saveToData(String key, String val) {
		try {
			saveData(key, val);
			// File nfile = new File("test");
			// String jsonval = FileUtils.readFileToString(nfile);
			// System.out.println(jsonval);
			String testData = fullTestDataString.get();
			// String testData = jsonval.replace("\\", "").replace(":\"{",
			// ":{").replaceAll("}\"", "}");;

			String currentKw = seqNo.get()+"-"+Usecase.get();
			clog.info(currentKw);
			JSONParser parser = new JSONParser();
			JSONObject currJTc = (JSONObject) parser.parse(testData);
			JSONObject currkwdata = (JSONObject) currJTc.get(currentKw);
			currkwdata.put(key, val);
			String upvalue = currJTc.toString();
			fullTestDataString.set(upvalue);
			// upvalue = upvalue.replaceAll("\"", "\\\\\"");
			clog.info(upvalue);
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));

			Statement stmt2 = con.createStatement();
			String sqlst = "update test_execution set TestData = '" + upvalue + "' where run_id= '" + runid.get()
					+ "' and id = '" + execRefId.get() + "'";
			stmt2.execute(sqlst);
			Result.updateCapturevalue(key,val);

		} catch (Exception e) {
			e.printStackTrace();
			;
		}

	}
	
	public static void saveToData1(String key, String val) {
		try {
			
			//File nfile = new File("test");
			//String jsonval = FileUtils.readFileToString(nfile);
			//System.out.println(jsonval);
			String testData = "";
			String upvalue = "";
			String testda="";
			try {
			if(!Tes_c.get().equalsIgnoreCase(Test_Cs_Id.get())) {
				Upd_c.set("");
				Tes_c.set("");
				testData = fullOutDataString.get();
				//String testData = jsonval.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");;
				String currentKw = seqNo.get()+"-"+Usecase.get();
				//clog.info(currentKw);
				JSONParser parser = new JSONParser();
				JSONObject currJTc = (JSONObject) parser.parse(testData);
				JSONObject currkwdata = (JSONObject) currJTc.get(currentKw);
				currkwdata.put(key, val);
				upvalue = currJTc.toString();
			}
			else{
				testData = Upd_c.get();
				//String testData = jsonval.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");;
				String currentKw = seqNo.get()+"-"+Usecase.get();
				//clog.info(currentKw);
				JSONParser parser = new JSONParser();
				JSONObject currJTc = (JSONObject) parser.parse(testData);
				JSONObject currkwdata = (JSONObject) currJTc.get(currentKw);
				currkwdata.put(key, val);
				upvalue = currJTc.toString();
				}
			}
			catch (Exception e){
				testData = fullOutDataString.get();
				//String testData = jsonval.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");;
				String currentKw = seqNo.get()+"-"+Usecase.get();
				//clog.info(currentKw);
				JSONParser parser = new JSONParser();
				JSONObject currJTc = (JSONObject) parser.parse(testData);
				JSONObject currkwdata = (JSONObject) currJTc.get(currentKw);
				currkwdata.put(key, val);
				upvalue = currJTc.toString();
			}
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			
			Statement stmt2 = con.createStatement();
			String sqlst = "update test_execution set TestOutData = '"+upvalue+"' where run_id= '"+runid.get()+"' and testcase_ref_id = '"+Test_Cs_Id.get()+"'";
			stmt2.execute(sqlst);
			Tes_c.set(Test_Cs_Id.get());
			Upd_c.set(upvalue);
			
			Result.updateCapturevalue(key,val);
			
		} catch (Exception e) {
			e.printStackTrace();;
		}

	}
	
	public static String readToolProperties(String key) throws IOException {
		
		
		String fpath = WorkingDir.get() + "/Drivers/Config/testfactory.properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);

		return prop.getProperty(key);
	
	
	
}

	public static String pullFromData(String test_c, String key, String kwName) {

		String retvalue = "";
		try {
			String[] p_tc_ref = test_c.split("\\|");
			String tcref ="";
			if(p_tc_ref.length >1)
			{
				tcref = p_tc_ref[1];
			}
			
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));			String q1 = "SELECT a.*, b.`Test Case ID`, c.projectname, e.`Release Name`, CONCAT(d.first_name,' ',d.last_name) as UserName"
					+ " FROM test_execution a, testcase b, project c, user_table d, release_table e"
					+ " WHERE b.id = a.testcase_ref_id and c.id = a.projectID and e.id = a.`Release ID` and d.id = a.created_by and a.run_id = '"
					+ runid.get() + "' and a.`id` = '" + tcref + "'";
			System.out.println(q1);

			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(q1);
			if (rs2.next()) {
				Blob testdatab = rs2.getBlob("TestData");
				byte[] bdata = testdatab.getBytes(1, (int) testdatab.length());
				String testdata = new String(bdata);
				testdata = testdata.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");
				System.out.println(testdata);
				JSONParser parser = new JSONParser();
				JSONObject currJTc = (JSONObject) parser.parse(testdata);

				// based on you key types
				JSONObject keyValue = (JSONObject) currJTc.get(kwName);

				System.out.println(keyValue.toString());
				if (keyValue.get(key) != null) {
					retvalue = keyValue.get(key).toString();
				}

			}
			stmt2.close();
			rs2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retvalue;
	}

//	public static String GetRSAToken_u () throws InterruptedException, MalformedURLException {
//        
//        String command = System.getProperty("user.dir") +"\\Drivers\\Winium.Desktop.Driver.exe";
//       
//        try
//        {
//            Process process = Runtime.getRuntime().exec(command);
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//       
//       
//        String command1 = System.getProperty("user.dir") +"\\Drivers\\SecurID.exe";
//        //String command1 = Root+"\\SecurID.exe";
//        //String command1 = "D:\\selenium Projects\\Framework_latest\\bsstest-pro-automation-framework\\BSSTestPro\\SecurID.exe";
//       
//        try
//        {
//            Process process = Runtime.getRuntime().exec(command1);
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        //Thread.sleep(3000);
//        DesktopOptions options=new DesktopOptions();
//       
//        options.setApplicationPath(command1);
//        //C:\Program Files (x86)\RSA SecurID Software Token\SecurID.exe
//        WiniumDriver driver=new WiniumDriver(new java.net.URL("http://localhost:9999"),options);
//        Thread.sleep(3000);
//        String output=driver.findElement(By.xpath("//*[contains(@Name,'Current code is')]")).getAttribute("Name");
//        String OriginalOP=output.substring(18).replaceAll("\\s+", "");
//        //String FinalRSAToken=OriginalOP.replaceAll("\\s+", "");
//        //System.out.println("Final RSA token: "+FinalRSAToken);
//       
//        return OriginalOP;
//    }
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getEnvironmentURLDetails
	 * Arguments			: -
	 * Use 					: to get the Environment Details based on the Env
	 * Designed By			: JJ
	 * Last Modified Date 	: 14-06-2021
	--------------------------------------------------------------------------------------------------------*/
	
	public static String getEnvironmentURLDetails(String environment ,String system) {
		
		String prop = environment+"_"+system+"_URL";
		String url=null;
		try {
			 url =ENVproperties(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
  }
	
   public static int getEnvironmentWaitTimeDetails(String environment) {
		
		String prop = environment+"_WaitTime";
		int waitTime=0;
		try {
			waitTime =Integer.parseInt(ENVproperties(prop));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waitTime;
}
   
   public static int getEnvironmentImplicitWaitTimeDetails(String environment) {
		
		String prop = environment+"_Implicit_WaitTime";
		int waitTime=0;
		try {
			waitTime =Integer.parseInt(ENVproperties(prop));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return waitTime;
}
	
	public static String ENVproperties(String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Config/Environments.properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);
		prop.load(input);
		return prop.getProperty(propname);
	}

}
