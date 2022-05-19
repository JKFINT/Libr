package Libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/*---------------------------------------------------------------------------------------------------------
* Class Name : Driver
* Use : This is the main class to start the execution.
* Designed By :
* Last Modified Date : 24-06-2021
--------------------------------------------------------------------------------------------------------*/
public class Driver {

	@SuppressWarnings("rawtypes")
	public static ThreadLocal<List<Map>> TestData = new ThreadLocal<List<Map>>();
	public static ThreadLocal<String> tblname = new ThreadLocal<String>();
	public static ThreadLocal<String> logfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> tcid = new ThreadLocal<String>();
	public static ThreadLocal<String> bpath = new ThreadLocal<String>();
	public static ThreadLocal<String> storeproperties = new ThreadLocal<String>();
	public static ThreadLocal<String> Result_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> tmtpth = new ThreadLocal<String>();
	public static ThreadLocal<String> scbpth = new ThreadLocal<String>();
	public static ThreadLocal<String> Templete_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> Dunning = new ThreadLocal<String>();
	public static ThreadLocal<String> runid = new ThreadLocal<String>();
	public static ThreadLocal<String> Stat = new ThreadLocal<String>();
	public static ThreadLocal<String> execRefId = new ThreadLocal<String>();
	public static ThreadLocal<String> scpath = new ThreadLocal<String>();
	public static ThreadLocal<String> UseCaseName = new ThreadLocal<String>();
	public static ThreadLocal<String> Environment = new ThreadLocal<String>();
	public static ThreadLocal<String> environment = new ThreadLocal<String>();
	public static ThreadLocal<String> billDate = new ThreadLocal<String>();
	public static ThreadLocal<Integer> cddataindex = new ThreadLocal<Integer>();
	public static ThreadLocal<String> Acc_Number = new ThreadLocal<String>();
	public static HashMap<String, String> LineItemData = new HashMap<String, String>();
	public static HashMap<String, String> BillSchedule = new HashMap<String, String>();
	public static HashMap<String, String> DunningSchedule = new HashMap<String, String>();
	public static ThreadLocal<String> Total_DueAmt = new ThreadLocal<String>();
	public static ThreadLocal<List<Map>> database = new ThreadLocal<List<Map>>();
	public static ThreadLocal<Dictionary> ValidateDT = new ThreadLocal<Dictionary>();
	public static ThreadLocal<String> Def_Smart_limit = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseN = new ThreadLocal<String>();
	public static ThreadLocal<String> New_Account = new ThreadLocal<String>();
	public static ThreadLocal<String> contact = new ThreadLocal<String>();
	public static ThreadLocal<String> fullTestDataString = new ThreadLocal<String>();
	public static ThreadLocal<String> Billprofile_No = new ThreadLocal<String>();
	public static ThreadLocal<String> OrderDate = new ThreadLocal<String>();
	public static ThreadLocal<String> SalesOrder_No = new ThreadLocal<String>();
	public static ThreadLocal<String> Planname = new ThreadLocal<String>();
	public static ThreadLocal<String> projname = new ThreadLocal<String>();
	public static ThreadLocal<String> username = new ThreadLocal<String>();
	public static ThreadLocal<String> nodeName = new ThreadLocal<String>();
	public static ThreadLocal<String> Test_Cs_Id = new ThreadLocal<String>();
	public static ThreadLocal<String> TC_Id = new ThreadLocal<String>();
	public static ThreadLocal<String> URL = new ThreadLocal<String>();
	public static ThreadLocal<String> os = new ThreadLocal<String>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> inputvalstr = new ThreadLocal<String>();
	public static ThreadLocal<String> outputvalstr = new ThreadLocal<String>();
	public static ThreadLocal<String> execMode = new ThreadLocal<String>();
	public static HashMap<String, String> RTBOutputData = new HashMap<String, String>();
	public static ThreadLocal<String> XMLfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW = new ThreadLocal<String>();
	public static ThreadLocal<String> browser = new ThreadLocal<String>();
	public static ThreadLocal<Boolean> Continue = new ThreadLocal<Boolean>();
	public static ThreadLocal<WebDriver> cDriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> nodeurl = new ThreadLocal<String>();
	public static ThreadLocal<String> starttime = new ThreadLocal<String>();
	public static ThreadLocal<String> WorkingDir = new ThreadLocal<String>();
	public static ThreadLocal<String> Usecase = new ThreadLocal<String>();
	public static HashMap<String, String> Testdata = new HashMap<String, String>();
	public static HashMap<String, String> ValidationData = new HashMap<String, String>();
	public static ThreadLocal<String> tcStartTime = new ThreadLocal<String>();
	public static ThreadLocal<String> tcEndTime = new ThreadLocal<String>();
	public static ThreadLocal<String> env = new ThreadLocal<String>();
	public static ThreadLocal<String> Defect_ID = new ThreadLocal<String>();
	public static ThreadLocal<String> Test_Execution_ID = new ThreadLocal<String>();
	public static ThreadLocal<String> Release_name = new ThreadLocal<String>();
	public static ThreadLocal<String> seqNo = new ThreadLocal<String>();
	public static String Status1 = "Pass";
	public static String Status2 = "Pass";
	public static ThreadLocal<String> Stat1 = new ThreadLocal<String>();
	public static ThreadLocal<String> Stat2 = new ThreadLocal<String>();
	public static ThreadLocal<String> user_id = new ThreadLocal<String>();
	public static ThreadLocal<Boolean> closeDriver = new ThreadLocal<Boolean>();
	public static ThreadLocal<Boolean> methodScreenShotTaken = new ThreadLocal<Boolean>();
	public static HashMap<String, String> DBpair = new HashMap<String, String>();
	public static ThreadLocal<String> Tes_c = new ThreadLocal<String>();
	public static ThreadLocal<String> Upd_c = new ThreadLocal<String>();
	public static ThreadLocal<String> fullOutDataString = new ThreadLocal<String>();
	public static int waitTimeOut = 200;
	public static int waitMTimeOut = 200;
	public static int waitTime = 5000;
	public static int implicitWait = 20;
	public static logs clog = new logs();
	public static String trfold = "";

	@Test(groups = { "Testfactory" })
	@Parameters({ "Run_ID", "executionMode", "runNode" })
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : TestRun
	* Arguments : String Run_ID, String executionMode, String runNode
	* Use : This method is the starting point of the execution. Once initiate the execution it create the Run_Id in the server and execute test case.
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void TestRun(String Run_ID, String executionMode, String runNode) {
		try {
			String vmName = ManagementFactory.getRuntimeMXBean().getName();
			int p = vmName.indexOf("@");
			String pid = vmName.substring(0, p);
			execMode.set(executionMode);
			WorkingDir.set(System.getProperty("user.dir").replace("\\", "/"));
			DateFormat For = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String SRT_Time = For.format(cal.getTime()).toString();
			tmtpth.set("");
			String pth = Paths.get(".").toAbsolutePath().normalize().toString();
			bpath.set(pth);
			runid.set(Run_ID);
			nodeName.set(runNode);
			String[] spltbth = bpath.get().split("/");
			for (int curre = 0; curre < spltbth.length - 1; curre++) {
				tmtpth.set(tmtpth.get() + spltbth[curre] + "/");
			}
			scbpth.set(tmtpth.get() + "ScreenShots/");
			Connection con = DriverManager.getConnection(
					Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
			String updatePidSql = "update test_execution set `process_id` = '" + pid
					+ "' where run_id = '" + runid.get() + "' and node = '"+nodeName.get()+"'";
			Statement updatePid = con.createStatement();
			updatePid.executeUpdate(updatePidSql);
			updatePid.close();
			Statement stmt1 = con.createStatement();
			if (!executionMode.equalsIgnoreCase("develop")) {
				ResultSet rs1 = stmt1.executeQuery("select * from test_execution where run_id ='" + Run_ID
						+ "' and executionType = 'Automated' LIMIT 1");
				clog.info(executionMode);
				while (rs1.next()) {
					Result.fCreateReportFiles();
					clog.info(pid);
					clog.info("<---------------- " + Run_ID + " ----------------->");
					runid.set(rs1.getString("run_id").toString());
					Result.createInitialJSON();
					System.out.println("Run Id: " + runid.get());
					String q1 = "SELECT a.*, b.`Test Case ID`, c.projectname, e.`Release Name`, CONCAT(d.first_name,' ',d.last_name) as UserName"
							+ " FROM test_execution a, testcase b, project c, user_table d, release_table e"
							+ " WHERE b.id = a.testcase_ref_id and c.id = a.projectID and e.id = a.`Release ID` and d.id = a.created_by and a.run_id = '"
							+ runid.get() + "' and node = '" + runNode + "' order by seqnumber";

					System.out.println("First Query: " + q1);
					Statement stmt2 = con.createStatement();
					ResultSet rs2 = stmt2.executeQuery(q1);
					while (rs2.next()) {
						try {
							Continue.set(true);
							Status1 = "Pass";
							execRefId.set(rs2.getString("a.id"));
							LocalDateTime sttime = LocalDateTime.now();
							projname.set(rs2.getString("projectname"));
							username.set(rs2.getString("UserName"));
							Release_name.set(rs2.getString("Release Name"));
							user_id.set(rs2.getString("updated_by"));
							System.out.println("Release Name ------> " + Release_name.get());
							Test_Cs_Id.set(rs2.getString("testcase_ref_id"));
							Stat.set("In Progress");
							TC_Id.set(rs2.getString("Test Case ID"));
							tcStartTime.set(sttime.toString());
							tcEndTime.set("");
							clog.startTestCase(rs2.getString("Test Case ID"));
							Blob testdatab = rs2.getBlob("testdata");
							byte[] bdata = testdatab.getBytes(1, (int) testdatab.length());
							String testdata = new String(bdata);
							Result.updateJSONTCStatus("In Progress", tcStartTime.get(), "", "");
							String vdata = rs2.getString("TestOutData");
							vdata = vdata.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");
							String selqr = "select a.*,b.* from scripts_testcases a, automationscripts b where a.script_id = b.id and testcase_id="
									+ rs2.getString("testcase_ref_id") + " order by sequence";
							System.out.println(selqr);
							Statement scstatement = con.createStatement();
							ResultSet rs3 = scstatement.executeQuery(selqr);
							testdata = testdata.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");
							fullTestDataString.set(testdata);
							System.out.println(testdata);
							clog.info("check td "+testdata);
							JsonParser jsonParser = new JsonParser();
							JsonObject responseObj = (JsonObject) jsonParser.parse(testdata);
							if (vdata == null) {
								vdata = "";
							}
							clog.info("#########3" + vdata);
							JsonObject vdataObj = (JsonObject) jsonParser.parse(vdata);
							fullOutDataString.set(vdata);
							String useold = "empty";
							scpath.set(bpath.get() + "/report/" + runid.get() + "/");
							Result.CreatePdf(username.get(), runid.get(),
									scpath.get() + rs2.getString("Test Case ID") + ".pdf");
							Usecase.set(null);
							Boolean stat = true;
							if (rs2.getString("dependent") != null) {
								if (rs2.getString("dependent").equalsIgnoreCase("yes")) {
									stat = Utlities.getParentStatus(rs2.getString("dependentTCId"));
								}
							}
							if (stat == true) {
								while (rs3.next()) {
									Testdata.clear();
									JsonObject tcdata = responseObj
											.get(rs3.getString("sequence") + "-" + rs3.getString("keywordName"))
											.getAsJsonObject();
									JsonObject vddata = vdataObj
											.get(rs3.getString("sequence") + "-" + rs3.getString("keywordName"))
											.getAsJsonObject();
									Usecase.set(rs3.getString("keywordName"));
									seqNo.set(rs3.getString("sequence"));
									Result.updateJsonFunctionStatus("In Progress");
									System.out.println(rs3.getString("keywordName"));
									System.out.println(tcdata);
									Set<Map.Entry<String, JsonElement>> entries = tcdata.entrySet();
									for (Map.Entry<String, JsonElement> entry : entries) {
										System.out.println(entry.getKey());
										System.out.println(entry.getValue().getAsString());
										if (entry.getValue().getAsString().toLowerCase().contains("fetchdata:")) {
											String nTdVal = entry.getValue().getAsString();
											String[] spliVal = nTdVal.split(":");
											String getTC = spliVal[1];
											String keyWordNam = spliVal[2];
											String keyVal = spliVal[3];
											Testdata.put(entry.getKey(),
													Utlities.pullFromData(getTC, keyVal, keyWordNam));
										} else {
											Testdata.put(entry.getKey(), entry.getValue().getAsString());
										}
									}
									Set<Map.Entry<String, JsonElement>> nentries = vddata.entrySet();
									for (Map.Entry<String, JsonElement> entry : nentries) {
										System.out.println(entry.getKey());
										System.out.println(entry.getValue().getAsString());
										ValidationData.put(entry.getKey(), entry.getValue().getAsString());
									}
									String Class_Method = Usecase.get();
									clog.info("Executing :" + Class_Method);
									closeDriver.set(false);
									Class_Method = Class_Method.replaceAll(" ", "_");
									try {
										System.out.println("Class_Name: " + Class_Method);
										starttime.set(LocalDateTime.now().toString());
										clog.info("StartTime: " + sttime);
										String sqlst = "update test_execution set `Start Time` = '" + sttime
												+ "' where run_id = '" + runid.get() + "' and testcase_ref_id = "
												+ Test_Cs_Id.get();
										Statement stmt3 = con.createStatement();
										stmt3.executeUpdate(sqlst);
										stmt3.close();
										methodScreenShotTaken.set(false);
										Class<?> noparams[] = {};
										Class<?> cls = Class.forName("Execution_Modules." + Class_Method);
										Object obj = cls.newInstance();
										java.lang.reflect.Method method = cls.getDeclaredMethod(Class_Method, noparams);
										if (Continue.get() == true) {
											method.invoke(obj);
										} else {
											clog.info("Dependency Fail, Function Failed as Previous Function Failed");
											Result.updateCapturevalue("Failure Reason",
													"Dependency Fail, Function Failed as Previous Function Failed");

										}
										if (methodScreenShotTaken.get() == false) {
											Result.Methodscreenshot();
										}
										if (closeDriver.get()) {
										}
										if (Continue.get()) {
											Result.updateJsonFunctionStatus("Pass");
										} else {
											Result.updateJsonFunctionStatus("Fail");
										}
										Stat1.set(Status1);
									} catch (Exception e) {
										System.out.println("Wrapper exception: " + e);
										System.out.println("Underlying exception: " + e.getCause());
										Status1 = "Fail";
										Stat1.set(Status1);
										Continue.set(false);
										Result.updateJsonFunctionStatus("Fail");
									}
								}
								LocalDateTime endtime = LocalDateTime.now();
								String tcStatus = "";
								tcEndTime.set(endtime.toString());
								Duration timeElapsed = Duration.between(sttime, endtime);
								if (Continue.get()) {
									Result.updateJSONTCStatus("Pass", tcStartTime.get(), tcEndTime.get(),
											String.valueOf(timeElapsed.getSeconds()) + " Sec");
									tcStatus = "Pass";
								} else {
									Result.updateJSONTCStatus("Fail", tcStartTime.get(), tcEndTime.get(),
											String.valueOf(timeElapsed.getSeconds()) + " Sec");
									tcStatus = "Fail";
								}
								String sqlst1 = "update test_execution set status = '" + tcStatus + "', `End Time` = '"
										+ sttime + "' where run_id = '" + runid.get() + "' and testcase_ref_id = "
										+ Test_Cs_Id.get();
								Statement stmt4 = con.createStatement();
								PdfReportGenerator.pdfReport();
								stmt4.executeUpdate(sqlst1);
								stmt4.close();
								Result.Update_Test_Execution_Status(tcStatus);
							} else {
								while (rs3.next()) {
									Usecase.set(rs3.getString("keywordName"));
									Result.updateCapturevalue("Failure Reason",
											"Dependency Fail, The test case Failed as Parent Test Case "
													+ rs2.getString("dependentTCId") + "  Failed");
									Result.updateJsonFunctionStatus("Fail");
								}
								LocalDateTime endtime = LocalDateTime.now();
								clog.info("Test Case Failed Because the Parent test case "
										+ rs2.getString("dependentTCId") + " Failed");
								tcEndTime.set(endtime.toString());
								Duration timeElapsed = Duration.between(sttime, endtime);
								Result.updateJSONTCStatus("Fail", tcStartTime.get(), tcEndTime.get(),
										String.valueOf(timeElapsed.getSeconds()) + " Sec");
								String tcStatus = "Fail";
								String sqlst1 = "update test_execution set status = '" + tcStatus + "', `End Time` = '"
										+ sttime + "' where run_id = '" + runid.get() + "' and testcase_ref_id = "
										+ Test_Cs_Id.get();
								Statement stmt4 = con.createStatement();
								PdfReportGenerator.pdfReport();
								stmt4.executeUpdate(sqlst1);
								stmt4.close();
								Result.Update_Test_Execution_Status(tcStatus);
							}
						} catch (Exception e) {
							clog.error(Result.getStackMsg(e));
						}
						clog.endTestCase(Test_Cs_Id.get());
						Thread.sleep(10000);
						if (Stat.get().equalsIgnoreCase("Fail")) {
						}
					}
					rs2.close();
					stmt2.close();
				}
				rs1.close();
			} else {
				Result.fCreateReportFiles();
				String qry = "select a.*,b.* from quick_run a,automationscripts b where a.script_ref_id = b.id and  a.user_ref_id='"
						+ Run_ID + "' and a.active = 1 order by sequence";
				ResultSet rs1 = stmt1.executeQuery(qry);
				user_id.set(Run_ID);
				Test_Cs_Id.set(Run_ID + "_Dummy");
				Continue.set(true);
				while (rs1.next()) {
					ValidationData.clear();
					Testdata.clear();
					JsonParser jsonParser = new JsonParser();
					String tData = rs1.getString("test_data");
					String vData = rs1.getString("test_out_data");
					if (vData == null) {
						vData = "{}";
					}
					if (tData == null) {
						tData = "{}";
					}
					tData = tData.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");
					vData = vData.replace("\\", "").replace(":\"{", ":{").replaceAll("}\"", "}");
					JsonObject tcdata = (JsonObject) jsonParser.parse(tData);
					JsonObject vddata = (JsonObject) jsonParser.parse(vData);
					Set<Map.Entry<String, JsonElement>> entries = tcdata.entrySet();
					for (Map.Entry<String, JsonElement> entry : entries) {
						System.out.println(entry.getKey());
						System.out.println(entry.getValue().getAsString());
						if (entry.getValue().getAsString().toLowerCase().contains("fetchdata:")) {
							String nTdVal = entry.getValue().getAsString();
							String[] spliVal = nTdVal.split(":");
							String getTC = spliVal[1];
							String keyWordNam = spliVal[2];
							String keyVal = spliVal[2];
							Testdata.put(entry.getKey(), Utlities.pullFromData(getTC, keyVal, keyWordNam));
						} else {
							Testdata.put(entry.getKey(), entry.getValue().getAsString());
						}
					}
					Set<Map.Entry<String, JsonElement>> nentries = vddata.entrySet();
					for (Map.Entry<String, JsonElement> entry : nentries) {
						System.out.println(entry.getKey());
						System.out.println(entry.getValue().getAsString());
						ValidationData.put(entry.getKey(), entry.getValue().getAsString());
					}
					Class<?> noparams[] = {};
					Class<?> cls = Class.forName("Execution_Modules." + rs1.getString("keywordName"));
					Object obj = cls.newInstance();
					java.lang.reflect.Method method = cls.getDeclaredMethod(rs1.getString("keywordName"), noparams);
					method.invoke(obj);
					Result.Methodscreenshot();
				}
			}
			stmt1.close();
			con.close();
		} catch (
		Exception e) {
			e.printStackTrace();
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : getdata
	* Arguments : String columnName
	* Use : This method is used to get the column name of the test data.
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String getdata(String columnName) {
		String c = "";
		try {
			c = TestData.get().get(0).get(columnName).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : pulldata
	* Arguments : String columnName
	* Use : This method is used to get the column name from the database.
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String pulldata(String columnName) {
		String c = "";
		try {
			c = database.get().get(cddataindex.get()).get(columnName).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : Validatedata
	* Arguments : String colname
	* Use : This method is used to validate the data.
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String Validatedata(String columnName) {
		String c = "";
		try {
			c = ValidateDT.get().get(columnName).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : validateString
	* Arguments : String Objname, ExtentTest test
	* Use : This method is used to validate the .
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public boolean validateString(String Objname, ExtentTest test) throws IOException {

		String confString = getString(Objname, test).substring(1, getString(Objname, test).length() - 1)
				.replaceAll("\n", "").replaceAll("\\\\", "").replaceAll("<b>", "").replaceAll("</b>", "");
		clog.info("Object Name : " + Objname);
		clog.info("Gateway Configuration String : " + confString);
		String appstring = driver.get().findElement(By.xpath(getObject(Objname))).getText();
		clog.info("Application String : " + appstring);
		String Statusstr = "<b>Object Name :</b>" + Objname + "<br><b>Gateway Configuration String :</b>" + confString
				+ "<br><b> Application String :</b>" + appstring;
		if (appstring.trim().equals(confString.trim())) {
			test.log(Status.PASS, Statusstr);
			return true;

		} else if (appstring.trim().equals(Objname.trim())) {
			test.log(Status.PASS, Statusstr);
			return true;
		} else {
			test.log(Status.FAIL, Statusstr);
			return false;
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : getString
	* Arguments : String Objname, ExtentTest test
	* Use : This method is used to get .
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public String getString(String objname, ExtentTest test) {
		String translation = "\"" + objname + "\"";
		try {
			String basedir = System.getProperty("user.dir");
			JsonParser jsonParser = new JsonParser();
			String apppth = "en";
			File file = new File(basedir + "/Resources/" + apppth + "/strings.json");
			FileReader fr = new FileReader(file);
			JsonObject obj = (JsonObject) jsonParser.parse(fr);
			if (obj.get(objname) != null) {
				translation = obj.get(objname).toString();
			} else {
				test.log(Status.WARNING, "String not configured in Gateway for : " + objname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return translation;
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : getObject
	* Arguments : String Objname
	* Use : This method is used to get value from the properties file.
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String getObject(String Objname) throws IOException {
		String fpath = "";
		if ((Testdata.get("OS")).equalsIgnoreCase("Android")) {
			fpath = System.getProperty("user.dir") + "/Drivers/Config/ObjectRepo.properties";
		} else if ((Testdata.get("OS")).equalsIgnoreCase("iOS")) {
			fpath = System.getProperty("user.dir") + "/Drivers/Config/ObjectRepoiOS.properties";
		}
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);
		prop.load(input);
		return prop.getProperty(Objname);
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : takescreenshot
	* Arguments :
	* Use : This method is used to take the screenshot .
	* Designed By :
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public String takescreenshot() {
		// Capture screenshot.
		String destDir = trfold + "/screenshots";
		File scrFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;

	}


}
