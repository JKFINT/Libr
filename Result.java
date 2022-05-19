package Libraries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Result extends Driver {
	static JSONArray jsoarr = new JSONArray();

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: fUpdateLog
	 * Arguments			: logmessage
	 * Use 					: to update the log for each case
	 * Designed By			: AG
	 * Last Modified Date 	: 23-Aug-2017
	--------------------------------------------------------------------------------------------------------*/
	public static void fUpdateLog(String logmessage) {
		// XXX--update log file path--XXX//
		File logfile = new File(logfilepth.get());
		FileWriter fw;
		try {
			fw = new FileWriter(logfile.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println(logmessage);
			bw.write(logmessage + "\r\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: Takescreenshot
	 * Arguments			: screenshot name
	 * Use 					: Take screenshot and save it in screen shots folder
	 * Designed By			: Mugaz
	 * Last Modified Date 	: 12-Dec-2019
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static void takescreenshot(String LogMessage) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				System.out.println("Path --->" + scpath.get() + LogMessage);
				// Set folder name to store screenshots.
				String destDir = scpath.get() + "Screenshots/";
				// Capture screenshot.
				File scrFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
				// Create folder under project with name "screenshots" provided to destDir.
				new File(destDir).mkdirs();
				// Set file name using current date time.
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
				LocalDateTime now = LocalDateTime.now();
				String destFile = dtf.format(now) + ".png";

				try {
					// Copy paste file at destination folder location
					FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
					Updatepdf(LogMessage, destFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: Takescreenshot
	 * Arguments			: screenshot name
	 * Use 					: Take screenshot and save it in screen shots folder
	 * Designed By			: Mugaz
	 * Last Modified Date 	: 12-Dec-2019
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static void Methodscreenshot() {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				// Set folder name to store screenshots.
				String destDir = bpath.get() + "/report/" + runid.get() + "/methodScreenShots";
				// Capture screenshot.
				File scrFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
				// Create folder under project with name "screenshots" provided to destDir.
				new File(destDir).mkdirs();
				// Set file name using current date time.
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
				LocalDateTime now = LocalDateTime.now();
				String destFile = Usecase.get() + "_" + dtf.format(now) + ".png";
				try {
					// Copy paste file at destination folder location
					FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
					updateJsonScreenShot(destFile);

				} catch (IOException e) {
					e.printStackTrace();
				}
				methodScreenShotTaken.set(true);
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
	}

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: CreateDBHTMLReport
	 * Arguments			: logmessage
	 * Use 					: To Update HTML Content from DB
	 * Designed By			: Zeeshan
	 * Last Modified Date 	: 19-OCT-2019
	--------------------------------------------------------------------------------------------------------
	public static void CreateDBHTMLReport(String DBContent) {
	    //UCscreenfilepth.get() + "/" +
	    File DBHTMLFile = new File(UCscreenfilepth.get() + "/" + TestCaseN.get() + ".html");
	    System.out.println(DBHTMLFile);
	    FileWriter fw;
	    try {
	        fw = new FileWriter(DBHTMLFile.getAbsoluteFile(), true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        System.out.println(DBContent);
	        bw.write(DBContent + "\r\n");
	        bw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
	}
	*/
	public static void fCreateReportFiles() {
		try {
			File resfold = new File(bpath.get() + "/report/");
			if ((!resfold.exists()))
				resfold.mkdir();
			File nres = new File(bpath.get() + "/report/" + runid.get());
			if ((nres.exists())) {
				nres.delete();
				nres.mkdir();
			}

			File nscfold = new File(bpath.get() + "/report/" + runid.get() + "/methodScreenShots");
			if ((!nscfold.exists()))
				nscfold.mkdir();
			File logsFold = new File(bpath.get() + "/report/" + runid.get() + "/Logs");
			if ((!logsFold.exists()))
				logsFold.mkdir();
			System.setProperty("logfilename",
					bpath.get() + "/report/" + runid.get() + "/Logs/" + nodeName.get() + "Logs");
			DOMConfigurator.configure(bpath.get() + "/log4j.xml");
			// logfilepth.set(Result_FLD.get() + "/" + runid.get() + "_Logs.txt");

		} catch (Exception e) {
			clog.info(Result.getStackMsg(e));
		}
	}

	public static String getStackMsg(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString();
		return sStackTrace;
	}

	/*
	 * public static void fCreateReportFiles() { try {
	 * 
	 * 
	 * File resfold = new File(bpath.get()+"/report/"); if ((!resfold.exists()))
	 * resfold.mkdir(); File nres = new File(bpath.get()+"/report/"+runid.get()); if
	 * ((!nres.exists())) nres.mkdir();
	 * System.setProperty("logfilename",bpath.get()+"/report/"+runid.get()+"/Logs");
	 * DOMConfigurator.configure(bpath.get()+"/log4j.xml");
	 * //logfilepth.set(Result_FLD.get() + "/" + runid.get() + "_Logs.txt"); //File
	 * logfile = new File(logfilepth.get()); //if (!logfile.exists()) { //
	 * logfile.createNewFile(); //}
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	@SuppressWarnings("unchecked")
	public static void createInitialJSON() {
		try {
			File nfile = new File(bpath.get() + "/report/" + runid.get() + "/Report.json");
			if (!nfile.exists()) {
				nfile.createNewFile();
				JSONArray mainJson = new JSONArray();
				Connection con = DriverManager.getConnection(
						Utlities.readToolProperties("url"), Utlities.readToolProperties("username"), Utlities.readToolProperties("password"));
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery(
						"SELECT a.*, b.`Test Case ID`, c.projectname, e.`Release Name`, CONCAT(d.first_name,' ',d.last_name) as UserName"
								+ " FROM test_execution a, testcase b, project c, user_table d, release_table e"
								+ " WHERE b.id = a.testcase_ref_id and c.id = a.projectID and e.id = a.`Release ID` and d.id = a.created_by and a.run_id = '"
								+ runid.get() + "'");
				while (rs1.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("Test Case ID", rs1.getString("Test Case ID") + "|" + rs1.getString("a.id"));
					String tcref = rs1.getString("testcase_ref_id");
					String selqr = "select a.*,b.* from scripts_testcases a, automationscripts b where a.script_id = b.id and testcase_id="
							+ tcref + " order by sequence";
					Statement scstatement = con.createStatement();
					ResultSet rs3 = scstatement.executeQuery(selqr);
					jsonObject.put("status", "No Run");
					jsonObject.put("Start Time", "");
					jsonObject.put("End Time", "");
					jsonObject.put("Duration", "");
					JSONArray functions = new JSONArray();
					while (rs3.next()) {
						JSONObject jsonObjectFun = new JSONObject();
						jsonObjectFun.put("name", rs3.getString("sequence") + "-" + rs3.getString("keywordName"));
						jsonObjectFun.put("status", "No Run");
						jsonObjectFun.put("screenShotName", "");
						JSONArray validations = new JSONArray();
						jsonObjectFun.put("Validation", validations);
						JSONArray captureValues = new JSONArray();
						jsonObjectFun.put("CaptureValues", captureValues);
						functions.add(jsonObjectFun);

					}
					jsonObject.put("functions", functions);
					mainJson.add(jsonObject);
				}
				// if(!nfile.exists())
				{
					/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
					file.write(mainJson.toJSONString());
					file.close();*/
					File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
					FileUtils.write(file, mainJson.toJSONString(), "UTF-8");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void updateCapturevalue(String key, String nval) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(reader);
				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					// System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						JSONArray currTCFun = (JSONArray) currJTc.get("functions");
						for (int cfun = 0; cfun < currTCFun.size(); cfun++) {
							JSONObject funObj = (JSONObject) currTCFun.get(cfun);
							String cfunName = funObj.get("name").toString();
							JSONArray cvalidatArray = (JSONArray) funObj.get("CaptureValues");
							System.out.println(cfunName);
							String nfun = seqNo.get() + "-" + Usecase.get();
							if (nfun.equals(cfunName)) {
								JSONObject nvalobject = new JSONObject();
								nvalobject.put("key", key);
								nvalobject.put("val", nval);
								cvalidatArray.add(nvalobject);
								break;
							}
						}
						break;

					}

				}
				// System.out.println(array.toJSONString());
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateJsonFunctionStatus(String status) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(reader);
				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						JSONArray currTCFun = (JSONArray) currJTc.get("functions");
						for (int cfun = 0; cfun < currTCFun.size(); cfun++) {
							JSONObject funObj = (JSONObject) currTCFun.get(cfun);
							String cfunName = funObj.get("name").toString();
							System.out.println(cfunName);
							String nfun = seqNo.get() + "-" + Usecase.get();

							if (nfun.equals(cfunName)) {
								funObj.put("status", status);

								break;
							}
						}
						break;

					}

				}
				// System.out.println(array.toJSONString());
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateJsonScreenShot(String screenshotname) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(reader);
				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						JSONArray currTCFun = (JSONArray) currJTc.get("functions");
						for (int cfun = 0; cfun < currTCFun.size(); cfun++) {
							JSONObject funObj = (JSONObject) currTCFun.get(cfun);
							String cfunName = funObj.get("name").toString();
							System.out.println(cfunName);
							String nfun = seqNo.get() + "-" + Usecase.get();
							if (nfun.equals(cfunName)) {
								funObj.put("screenShotName", screenshotname);

								break;
							}
						}
						break;

					}

				}
				// System.out.println(array.toJSONString());
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateValidations(String Expected, String Actual, String vStatus) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(reader);
				String valStatus = vStatus;

				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					// System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						JSONArray currTCFun = (JSONArray) currJTc.get("functions");
						for (int cfun = 0; cfun < currTCFun.size(); cfun++) {
							JSONObject funObj = (JSONObject) currTCFun.get(cfun);
							String cfunName = funObj.get("name").toString();
							JSONArray cvalidatArray = (JSONArray) funObj.get("Validation");
							System.out.println(cfunName);
							String nfun = seqNo.get() + "-" + Usecase.get();
							if (nfun.equals(cfunName)) {
								JSONObject nvalobject = new JSONObject();
								nvalobject.put("Actual", Actual);
								nvalobject.put("Expected", Expected);
								nvalobject.put("status", valStatus);
								cvalidatArray.add(nvalobject);
								break;
							}
						}
						break;

					}

				}
				// System.out.println(array.toJSONString());
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateValidations(String expectedText, String Expected, String actualText,String Actual, String vStatus) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				JSONArray array = (JSONArray) parser.parse(reader);
				String valStatus = vStatus;

				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					// System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						JSONArray currTCFun = (JSONArray) currJTc.get("functions");
						for (int cfun = 0; cfun < currTCFun.size(); cfun++) {
							JSONObject funObj = (JSONObject) currTCFun.get(cfun);
							String cfunName = funObj.get("name").toString();
							JSONArray cvalidatArray = (JSONArray) funObj.get("Validation");
							System.out.println(cfunName);
							String nfun = seqNo.get() + "-" + Usecase.get();
							if (nfun.equals(cfunName)) {
								JSONObject nvalobject = new JSONObject();
								//nvalobject.put("Actual "+actualText, Actual);
								//nvalobject.put("Expected "+expectedText, Expected);
								nvalobject.put("Actual", actualText+" : "+Actual);
								nvalobject.put("Expected", expectedText+" : "+Expected);
								nvalobject.put("status", valStatus);
								cvalidatArray.add(nvalobject);
								break;
							}
						}
						break;

					}

				}
				// System.out.println(array.toJSONString());
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateJSONTCStatus(String status, String starttime, String endTime, String duration) {
		try {
			if (!execMode.get().equalsIgnoreCase("develop")) {
				String ctestcase = TC_Id.get() + "|" + execRefId.get();
				String fileName = bpath.get() + "/report/" + runid.get() + "/Report.json";
				Path path = Paths.get(fileName);
				Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
				JSONParser parser = new JSONParser();
				// System.out.println(reader.toString());
				JSONArray array = (JSONArray) parser.parse(reader);
				for (int crtc = 0; crtc < array.size(); crtc++) {
					JSONObject currJTc = (JSONObject) array.get(crtc);
					String ctcid = currJTc.get("Test Case ID").toString();
					System.out.println(ctcid);
					if (ctcid.equals(ctestcase)) {
						currJTc.put("status", status);
						currJTc.put("Start Time", starttime);
						currJTc.put("End Time", endTime);
						currJTc.put("Duration", duration);
						break;

					}
				}
				/*FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(array.toJSONString());
				file.close();*/
				File file = new File(bpath.get() + "/report/" + runid.get() + "/Report.json"); 
				FileUtils.write(file, array.toJSONString(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public static void JsonReport(ArrayList expected, ArrayList actual) {
		try {
			// Creating a JSONObject object
			for (int cobj = 0; cobj < jsoarr.size(); cobj++) {
				JSONObject ccobj = (JSONObject) jsoarr.get(cobj);
				if (ccobj.get("Test Case ID").equals(TC_Id.get())) {
					jsoarr.remove(cobj);
					break;

				}
			}
			JSONObject test = new JSONObject();

			// Inserting key-value pairs into the json object
			JSONArray functions = new JSONArray();

			JSONObject function = new JSONObject();

			// JSONObject validation = new JSONObject();

			JSONArray validations = new JSONArray();

			for (int i = 0; i <= expected.size() - 1; i++) {

				JSONObject validation = new JSONObject();
				validation.put("expected", expected.get(i));
				validation.put("actual", actual.get(i));
				validations.add(validation);
				function.put("Validation", validations);

			}

			test.put("functions", functions);

			function.put("name", Usecase.get());
			function.put("status", Stat1.get());
			functions.add(function);

			test.put("Test Case ID", TC_Id.get());
			test.put("status", Stat2.get());
			test.put("Start Time", tcStartTime.get());
			test.put("End Time", tcEndTime.get());

			jsoarr.add(test);

			System.out.println("JSON file created: " + test);

			File resfold = new File(bpath.get() + "/report/");
			if ((!resfold.exists()))
				resfold.mkdir();
			File nres = new File(bpath.get() + "/report/" + runid.get());
			if ((!nres.exists()))
				nres.mkdir();
			try {
				FileWriter file = new FileWriter(bpath.get() + "/report/" + runid.get() + "/Report.json");
				file.write(jsoarr.toJSONString());
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * public static String getStackMsg(Exception e) { StringWriter sw = new
	 * StringWriter(); PrintWriter pw = new PrintWriter(sw); e.printStackTrace(pw);
	 * String sStackTrace = sw.toString(); return sStackTrace; }
	 */

	public static void CreatePdf(String Author, String title, String path) {
		try {
			PDDocument document = new PDDocument();
			PDPage blankPage = new PDPage();
			document.addPage(blankPage);
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			PDDocumentInformation pdd = document.getDocumentInformation();
			pdd.setAuthor(Author);
			pdd.setTitle("BSS Test Pro - Report");
			pdd.setSubject("Report for run id - " + title);
			Calendar date = new GregorianCalendar();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String da = dtf.format(now);
			String[] dat = da.split("-");
			System.out.println(dat[0] + dat[1] + dat[2]);
			date.set(Integer.parseInt(dat[0]), Integer.parseInt(dat[1]), Integer.parseInt(dat[2]));
			pdd.setCreationDate(date);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 18);
			contentStream.newLineAtOffset(150, 500);
			contentStream.showText("Executed Date: " + dtf1.format(now));
			contentStream.newLineAtOffset(0, 40);
			contentStream.showText("Run ID: " + title);
			contentStream.newLineAtOffset(0, 40);
			// contentStream.showText("Executed by: " + Author);
			contentStream.newLineAtOffset(0, 100);
			contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 25);
			contentStream.showText("                 BSS Test Pro");
			contentStream.newLine();
			contentStream.close();
			document.save(path);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Updatepdf(String screenshot_text, String Screenshot_name) {
		try {
			File file = new File(scpath.get() + TC_Id.get() + ".pdf");
			PDDocument document = PDDocument.load(file);
			PDPage blankPage = new PDPage();
			document.addPage(blankPage);
			PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.TIMES_ITALIC, 16);
			contentStream.newLineAtOffset(50, 720);
			contentStream.showText(screenshot_text);
			contentStream.newLine();
			contentStream.endText();
			PDImageXObject pdImage = PDImageXObject.createFromFile(scpath.get() + "Screenshots\\" + Screenshot_name,
					document);
			if (env.get().equalsIgnoreCase("mobile")) {
				contentStream.drawImage(pdImage, 125, 65, pdImage.getWidth() / 4, pdImage.getHeight() / 4);
			} else {
				contentStream.drawImage(pdImage, 75, 250, pdImage.getWidth() / 4, pdImage.getHeight() / 3);
			}
			contentStream.close();
			System.out.println("Content added");
			document.save(new File(scpath.get() + TC_Id.get() + ".pdf"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void updateJira(String tcid, String status) {
//		try {
//			int statid = 31;
//			String comment = "";
//			Runtime rt = Runtime.getRuntime();
//			// curl -u username:password -X POST --data '{"transition":{"id":"11"}}' -H
//			// "Content-Type: application/json"
//			// http://jira/rest/api/2/issue/TEST-1/transitions
//			clog.info("#####Hi###");
//			System.out.println("Status: " + status);
//			if (status.equalsIgnoreCase("Pass")) {
//				statid = 31;
//				comment = "Test case passed without any issue";
//			} else {
//				statid = 31;
//				comment = "Test case Failed due to some issue";
//				AddJiraDefect(tcid, "AUT");
//			}
//			String curlcmd = "curl -u 80082833:Maveric@123 -X POST --data \"{\\\"transition\\\":{\\\"id\\\":\\\""
//					+ statid
//					+ "\\\"}}\" -H \"Content-Type: application/json\" http://eecsaruh2hor289.prod.mobily.lan:8080/rest/api/2/issue/"
//					+ tcid + "/transitions";
//			System.out.println(curlcmd);
//			Process proc = rt.exec(curlcmd);
//			String curlcmd2 = "curl -D- -u 80082833:Maveric@123 -X POST --data \"{\\\"body\\\": \\\"" + comment
//					+ "\\\"}\" -H \"Content-Type: application/json\" http://eecsaruh2hor289.prod.mobily.lan:8080/rest/api/2/issue/"
//					+ tcid + "/comment";
//			System.out.println(curlcmd2);
//			Process proc2 = rt.exec(curlcmd2);
//			proc.waitFor();
//			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//			String s = "";
//			String finalop = "";
//			String nonce = "";
//			String jwt = "";
//			while ((s = stdInput.readLine()) != null) {
//				finalop = finalop + s;
//				clog.info(finalop);
//			}
//			while ((s = stdError.readLine()) != null) {
//
//				System.out.println(s);
//				clog.info(s);
//			}
//		} catch (Exception e) {
//			clog.error(getStackMsg(e));
//		}
//	}

//	public static void AddJiraDefect(String tcid, String Project_name) {
//		try {
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
//			LocalDateTime now = LocalDateTime.now();
//			String Bug_Time = dtf.format(now);
//			String summary = "Auto - Test Case ID: " + TC_Id.get() + " | " + Usecase.get() + " | " + Bug_Time;
//			String Description = "just for testing description";
//			String Test_Environment = "Integration";
//			int Total_Est_Effort = 1;
//			String Impacted_System = "Siebel CRM";
//
//			Runtime rt = Runtime.getRuntime();
//			String curlcmd = "curl -u 80082833:Maveric@123 -X POST --data \"{\\\"fields\\\":{\\\"project\\\":{\\\"key\\\":\\\""
//					+ Project_name + "\\\"},\\\"summary\\\": \\\"" + summary + "\\\", \\\"description\\\": \\\""
//					+ Description + "\\\","
//					+ "\\\"issuetype\\\":{\\\"name\\\":\\\"Bug\\\"},\\\"customfield_11821\\\":{\\\"value\\\":\\\""
//					+ Test_Environment + "\\\"},\\\"customfield_11528\\\":[{\\\"value\\\":\\\"" + Impacted_System
//					+ "\\\"}],\\\"customfield_10421\\\":" + Total_Est_Effort
//					+ "}}\" -H \"Content-Type: application/json\" http://eecsaruh2hor289.prod.mobily.lan:8080/rest/api/2/issue/";
//			System.out.println(curlcmd);
//			Process proc = rt.exec(curlcmd);
//			proc.waitFor();
//			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//			String s = "";
//			String finalop = "";
//			String nonce = "";
//			String jwt = "";
//			while ((s = stdInput.readLine()) != null) {
//				finalop = finalop + s;
//				clog.info(finalop);
//				String[] kvPairs = finalop.split(",");
//				System.out.println(kvPairs[1]);
//				String[] kv = kvPairs[1].split(":");
//				String key = kv[0];
//				String value = kv[1];
//				Defect_ID.set(value.replace("\"", ""));
//				System.out.println(Defect_ID.get());
//				clog.info("Defect ID - " + value + " is created for the Test Case ");
//			}
//			while ((s = stdInput.readLine()) != null) {
//				finalop = finalop + s;
//				clog.info(finalop);
//			}
//			while ((s = stdError.readLine()) != null) {
//
//				System.out.println(s);
//				clog.info(s);
//			}
//		} catch (Exception e) {
//			clog.error(getStackMsg(e));
//		}
//	}

	public static void AddJiraDefect() {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String Bug_Time = dtf.format(now);
			String summary = "Automation: Just for testing please Ignore this - Defect for " + TC_Id.get() + " | "
					+ Test_Execution_ID.get() + " | " + Usecase.get() + " | " + Bug_Time;
			Runtime rt = Runtime.getRuntime();
			String Project = "QA";
			String Usability_Issue = "No";
			String For_Project = Release_name.get();
			String Actual_Result = "Just for testing Actual Result";
			String QA_Analysis = "Just for testing QA Analysis";
			String Steps_to_Recreate = "Just for testing Steps to Recreate";
			String Impacted_System = "(TBD)";
			String Test_Data = "Test Data";
			String Defect_Phase = "QA";
			String Defect_Track = "Automation";
			String Expected_Result = "Just for testing Expected Result";
			String Defect_Severity = "1-Low";
			String Scenario = "Just for testing Scenario";
			String DefectType = "Automation";
			String Filed_Against = "Automation Team";
			String Defect_Environment = "Integration";
			String Severity = "Cosmetic Issue";
			String Reason = "NA";
			String curlcmd = "curl -u 80082833:Maveric@123 -X POST --data \"{\\\"fields\\\":{\\\"project\\\":{\\\"key\\\":\\\""
					+ Project + "\\\"},\\\"summary\\\": \\\"" + summary
					+ "\\\", \\\"issuetype\\\":{\\\"name\\\":\\\"Defect\\\"},\\\"customfield_15100\\\":{\\\"value\\\":\\\""
					+ Usability_Issue + "\\\"},\\\"customfield_17104\\\":\\\"" + Release_name.get()
					+ "\\\",\\\"customfield_11520\\\":\\\"" + Actual_Result + "\\\",\\\"customfield_11522\\\":\\\""
					+ QA_Analysis + "\\\",\\\"customfield_11521\\\":\\\"" + Steps_to_Recreate
					+ "\\\",\\\"customfield_11414\\\":{\\\"value\\\":\\\"" + Impacted_System
					+ "\\\"},\\\"customfield_11523\\\":\\\"" + Test_Data
					+ "\\\",\\\"customfield_11404\\\":{\\\"value\\\":\\\"" + Defect_Phase
					+ "\\\"},\\\"customfield_11428\\\":{\\\"value\\\":\\\"" + Defect_Track
					+ "\\\"},\\\"customfield_11519\\\":\\\"" + Expected_Result
					+ "\\\",\\\"customfield_10704\\\":{\\\"value\\\":\\\"" + Defect_Severity
					+ "\\\"},\\\"customfield_11518\\\":\\\"" + Scenario
					+ "\\\",\\\"customfield_11529\\\":{\\\"value\\\":\\\"" + DefectType
					+ "\\\", \\\"child\\\":{\\\"value\\\":\\\"" + Filed_Against
					+ "\\\"}},\\\"customfield_10707\\\":{\\\"value\\\":\\\"" + Defect_Environment
					+ "\\\"},\\\"customfield_13243\\\":{\\\"value\\\":\\\"" + Severity
					+ "\\\"},\\\"customfield_13244\\\":{\\\"value\\\":\\\"" + Reason
					+ "\\\"}}}}\" -H \"Content-Type: application/json\" http://jira.prod.mobily.lan:8443/rest/api/2/issue";
//			System.out.println(curlcmd);
			Process proc = rt.exec(curlcmd);
			proc.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = "";
			String finalop = "";
			String nonce = "";
			String jwt = "";
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
				String[] kvPairs = finalop.split(",");
//				System.out.println(kvPairs[1]);
				String[] kv = kvPairs[1].split(":");
				String key = kv[0];
				String value = kv[1];
				Defect_ID.set(value.replace("\"", ""));
//				System.out.println(Defect_ID.get());
				clog.info("Defect ID - " + value + " is created for the Test Run " + Test_Execution_ID.get());
			}
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError.readLine()) != null) {
//				System.out.println(s);
//				clog.info(s);
			}
			curlcmd = "curl -H \"Content-Type: application/json\" -X PUT -u 80082833:Maveric@123 --data \"{\\\"update\\\":{\\\"issuelinks\\\":[{\\\"add\\\":{\\\"type\\\":{\\\"name\\\":\\\"Tests\\\",\\\"inward\\\":\\\"Test Execution\\\",\\\"outward\\\":\\\"Test Execution\\\"},\\\"outwardIssue\\\":{\\\"key\\\":\\\""
					+ Test_Execution_ID.get() + "\\\"}}}]}}\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/"
					+ Defect_ID.get();
//			System.out.println(curlcmd);
			proc = rt.exec(curlcmd);
			proc.waitFor();
			stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError.readLine()) != null) {
//				System.out.println(s);
//				clog.info(s);
			}
		} catch (Exception e) {
			clog.error(getStackMsg(e));
		}
	}

	public static void Jira_AttachFile() {
		try {
			Runtime rt = Runtime.getRuntime();
			String curlcmd = "curl -D- -u 80082833:Maveric@123 -X POST -H \"X-Atlassian-Token: nocheck\" -F \"file=@"
					+ scpath.get() + TC_Id.get() + ".pdf" + "\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/"
					+ Defect_ID.get() + "/attachments";
//			System.out.println(curlcmd);
//			clog.info(curlcmd);
			Process proc = rt.exec(curlcmd);
			proc.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = "";
			String finalop = "";
			String nonce = "";
			String jwt = "";
			String filelog = "Logs.log";
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError.readLine()) != null) {

//				System.out.println(s);
//				clog.info(s);
			}
			Thread.sleep(30000);
			String curlcmd2 = "curl -D- -u 80082833:Maveric@123 -X POST -H \"X-Atlassian-Token: nocheck\" -F \"file=@"
					+ scpath.get() + filelog + "\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/" + Defect_ID.get()
					+ "/attachments";
//			System.out.println(curlcmd2);
//			clog.info(curlcmd2);
			Process proc2 = rt.exec(curlcmd2);
			proc2.waitFor();
			BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
			BufferedReader stdError2 = new BufferedReader(new InputStreamReader(proc2.getErrorStream()));
			s = "";
			finalop = "";
			while ((s = stdInput2.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError2.readLine()) != null) {

//				System.out.println(s);
//				clog.info(s);
			}
		} catch (Exception e) {
			clog.error(getStackMsg(e));
		}
	}

	public static void Jira_Test_Case_AttachFile() {
		try {
			Runtime rt = Runtime.getRuntime();
			String curlcmd = "curl -D- -u 80082833:Maveric@123 -X POST -H \"X-Atlassian-Token: nocheck\" -F \"file=@"
					+ scpath.get() + TC_Id.get() + ".pdf" + "\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/"
					+ Test_Execution_ID.get() + "/attachments";
//			System.out.println(curlcmd);
//			clog.info(curlcmd);
			Process proc = rt.exec(curlcmd);
			proc.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = "";
			String finalop = "";
			String nonce = "";
			String jwt = "";
			String filelog = "Logs.log";
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError.readLine()) != null) {

//				System.out.println(s);
//				clog.info(s);
			}
			String curlcmd2 = "curl -D- -u 80082833:Maveric@123 -X POST -H \"X-XSS-Protection:1\" -H \"X-Atlassian-Token: nocheck\" -F \"file=@"
					+ scpath.get() + filelog + "\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/"
					+ Test_Execution_ID.get() + "/attachments";
//			System.out.println(curlcmd2);
//			clog.info(curlcmd2);
			Process proc2 = rt.exec(curlcmd2);
			proc2.waitFor();
			BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
			BufferedReader stdError2 = new BufferedReader(new InputStreamReader(proc2.getErrorStream()));
			s = "";
			finalop = "";
			while ((s = stdInput2.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError2.readLine()) != null) {

//				System.out.println(s);
//				clog.info(s);
			}
		} catch (Exception e) {
			clog.error(getStackMsg(e));
		}
	}

	public static void Create_Test_Execution(String ReleaseID) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String Execu_time = dtf.format(now);
			Runtime rt = Runtime.getRuntime();
//			clog.info("Creating new Test Execution ...");
			String curlcmd = "curl -X POST -u 80082833:Maveric@123 -H \"Content-Type: application/json\" --data \"{\\\"fields\\\": {\\\"project\\\" : {\\\"key\\\": \\\"QA\\\"}, \\\"summary\\\" : \\\"Test Execution for "
					+ ReleaseID + " | " + TC_Id.get() + " - " + Execu_time
					+ "\\\", \\\"issuetype\\\": {\\\"name\\\": \\\"Test Execution\\\"}, \\\"customfield_17104\\\": \\\""
					+ ReleaseID + "\\\"}}\" http://jira.prod.mobily.lan:8443/rest/api/2/issue";
//			System.out.println(curlcmd);
			Process proc = rt.exec(curlcmd);
			proc.waitFor();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String s = "";
			String finalop = "";
			String nonce = "";
			String jwt = "";
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info("Actual Response: " + finalop);
				String[] kvPairs = finalop.split(",");
				System.out.println(kvPairs[1]);
				String[] kv = kvPairs[1].split(":");
				String key = kv[0];
				String value = kv[1];
				Test_Execution_ID.set(value.replace("\"", ""));
//				System.out.println(Test_Execution_ID.get());
				clog.info("Test Execution - " + value + " is created for " + ReleaseID);
			}
			while ((s = stdError.readLine()) != null) {
//
//				System.out.println(s);
//				clog.info(s);
			}
			clog.info("Adding Tests " + ReleaseID + " for the Test Execution " + Test_Execution_ID.get());
			curlcmd = "curl -u 80082833:Maveric@123 -k -X POST -H \"Content-Type: application/json\" --data \"{\\\"add\\\": [\\\""
					+ TC_Id.get()
					+ "\\\"],\\\"remove\\\": [ ]}\" http://jira.prod.mobily.lan:8443/rest/raven/1.0/api/testexec/"
					+ Test_Execution_ID.get() + "/test";
//			System.out.println(curlcmd);
			Process proc2 = rt.exec(curlcmd);
			proc2.waitFor();
			stdInput = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(proc2.getErrorStream()));
			while ((s = stdInput.readLine()) != null) {
				finalop = finalop + s;
//				clog.info(finalop);
			}
			while ((s = stdError.readLine()) != null) {

//				System.out.println(s);
//				clog.info(s);
			}
		} catch (Exception e) {
			clog.error(getStackMsg(e));
		}
	}
	


	public static void Update_Test_Execution_Status(String Status) {
		/*
		 * try { Runtime rt = Runtime.getRuntime(); String Exe_ID = "";
		 * clog.info("Getting Test Execution ID ..."); String curlcmd =
		 * "curl -u 80082833:Maveric@123 -X GET -H \"Content-Type: application/json\" http://jira.prod.mobily.lan:8443/rest/api/2/issue/"
		 * + Test_Execution_ID.get() + "?fields=customfield_14920"; //
		 * System.out.println(curlcmd); Process proc = rt.exec(curlcmd); proc.waitFor();
		 * BufferedReader stdInput = new BufferedReader(new
		 * InputStreamReader(proc.getInputStream())); BufferedReader stdError = new
		 * BufferedReader(new InputStreamReader(proc.getErrorStream())); String s = "";
		 * String finalop = ""; String nonce = ""; String jwt = ""; while ((s =
		 * stdInput.readLine()) != null) { finalop = finalop + s; //
		 * clog.info("Actual Response: " + finalop); String[] kvPairs =
		 * finalop.split("c\":"); // System.out.println(kvPairs[1]); Exe_ID =
		 * kvPairs[1].substring(0, kvPairs[1].length() - 4); //
		 * System.out.println("Test Execution ID is: " + Exe_ID); //
		 * clog.info("Test Execution ID is " + Exe_ID); } while ((s =
		 * stdError.readLine()) != null) {
		 * 
		 * // System.out.println(s); // clog.info(s); }
		 * clog.info("Updating the status for the Test Execution " +
		 * Test_Execution_ID.get()); // if (Status.equalsIgnoreCase("PASS")) { //
		 * curlcmd =
		 * "curl -H \"Content-Type: application/json\" -X PUT -u 80082833:Maveric@123 http://jira.prod.mobily.lan:8443/rest/raven/1.0/api/testrun/"
		 * // + Exe_ID + "/status?status=PASS"; // } else if
		 * (Status.equalsIgnoreCase("Fail")) { // AddJiraDefect(); // curlcmd =
		 * "curl -H \"Content-Type: application/json\" -X PUT -u 80082833:Maveric@123 http://jira.prod.mobily.lan:8443/rest/raven/1.0/api/testrun/"
		 * // + Exe_ID + "/status?status=FAIL"; // // } else { // curlcmd =
		 * "curl -H \"Content-Type: application/json\" -X PUT -u 80082833:Maveric@123 http://jira.prod.mobily.lan:8443/rest/raven/1.0/api/testrun/"
		 * // + Exe_ID + "/status?status=EXECUTING"; // // } //
		 * System.out.println(curlcmd); // Process proc2 = rt.exec(curlcmd);
		 * //proc2.waitFor(); //stdInput = new BufferedReader(new
		 * InputStreamReader(proc2.getInputStream())); //stdError = new
		 * BufferedReader(new InputStreamReader(proc2.getErrorStream())); /*while ((s =
		 * stdInput.readLine()) != null) { finalop = finalop + s; // clog.info(finalop);
		 * } while ((s = stdError.readLine()) != null) {
		 * 
		 * // System.out.println(s); // clog.info(s); }
		 */
		/*
		 * } catch (Exception e) { // clog.error(getStackMsg(e)); // }
		 */
	}

}
