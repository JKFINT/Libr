package Libraries;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import java.sql.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import Libraries.DB_Validation;

public class DB_Validation extends Driver {

	public static Connection conn = null;
	public String stmtQuery = "";
	public String Success = "";
	public String getColumnName = "";

	public DB_Validation(String stmtQuery, String Success, String getColumnName) {
		this.stmtQuery = stmtQuery;
		this.Success = Success;
		this.getColumnName = getColumnName;
	}
	
	public static String DataBseproperties(String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Config/DataBase.properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);
		prop.load(input);
		return prop.getProperty(propname);
	}

	public static Connection OracleDBConnector(String DBname) throws SQLException, IOException {
		
		String dbURL = null;
		String strUserID = DBname + "_UserID";
		String strPassword = DBname + "_Password";
		String hostName = DBname + "_hostName";
		String port = DBname + "_port";
		String SIDName = DBname + "_SIDName";
		String ServiceName = DBname + "_ServiceName";

		strUserID = DBproperties(strUserID);
		strPassword = DBproperties(strPassword);
		hostName = DBproperties(hostName);
		port = DBproperties(port);
		SIDName = DBproperties(SIDName);
		ServiceName = DBproperties(ServiceName);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			if (SIDName != null) {
				dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST =" + hostName + ")(PORT=" + port
						+ "))(CONNECT_DATA=(SID=" + SIDName + ")))";
			} else if (ServiceName != null) {
				dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST =" + hostName + ")(PORT=" + port
						+ "))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=" + ServiceName + ")))";
			}
			clog.info("jdbcurl : " + dbURL);
			conn = DriverManager.getConnection(dbURL, strUserID, strPassword);

		} catch (Exception e) {
			clog.error(e.getMessage());
			System.out.println(e);
		}
		clog.info("Data base connection success : " + dbURL);
		return conn;

	}

	public static String Validate(String DB_Name, String function, String input) throws SQLException, IOException {

		String Result = "";
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		DB_Validation db_Validation =  getQueryDetails(function, input);
		String Query = db_Validation.stmtQuery;
		String Success = db_Validation.Success;
		String getColumnName = db_Validation.getColumnName;

		try {
			stmt = conn.createStatement();

			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);

			while (Execute_Result.next()) {
				Result = Execute_Result.getString(getColumnName);
			}

			if (Result.equalsIgnoreCase("1") || Result.equalsIgnoreCase("2") || Result.equalsIgnoreCase("4")
					|| Result.equalsIgnoreCase("5") || Result.equalsIgnoreCase("6") || Result.equalsIgnoreCase("7")) {
				clog.info(Success + " as '" + Result + "'");
			} else if (Result.equalsIgnoreCase("60")) {
				clog.info(Success + " as '" + Result + "'");
			} else {
				clog.error("Status has not been verified as '" + Result + "'");
			}
			//printDB(Execute_Result);

			conn.close();
		} catch (Exception e) {
			clog.error(e.getMessage());
		}
		return Result;
	}

	public static String Update(String DB_Name, String function, String input) throws SQLException, IOException {
		String Result = "";
		String Query = "";
		String Success = "";
		DB_Validation db_Validation = null;

		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;

		try {
			stmt = conn.createStatement();
			clog.info("------- Start ------------");
			clog.info("---> Updating Query.... " + Query);
			stmt.executeUpdate(Query);
			clog.info("------- END ------------");
			conn.setAutoCommit(false);
			conn.commit();
			clog.info("------- Commit ------------");
			stmt.close();

			clog.info(Success);
			conn.close();

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;

	}

	public static String getDataFromQueryTable(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = "";
		String Query = "";
		String Success = "";
		String getColumnName = "";
		String getColumnName2 = "";
		String[] columnName = null;
		DB_Validation db_Validation = null;

		conn = OracleDBConnector(DB_Name);
		Statement stmt;

		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;

		if (!DB_Name.toLowerCase().contains("_billing")) {
			columnName = db_Validation.getColumnName.split(";");
			int len = columnName.length;
			if (len > 1) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
			} else {
				getColumnName = columnName[0];
			}
		}

		try {
			DBpair.clear();
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);

			if (!DB_Name.toLowerCase().contains("_billing")) {
				while (Execute_Result.next()) {
					if (!getColumnName2.equalsIgnoreCase("") && function.equalsIgnoreCase("Get_OTP")) {
						String getStatus = Execute_Result.getString(getColumnName2);
						if (getStatus.equalsIgnoreCase("OTPGENERATED")) {
							Result = Execute_Result.getString(getColumnName);
							break;
						}
					} else if (getColumnName2.equalsIgnoreCase("")
							&& function.equalsIgnoreCase("EAI_PrePaidReAssign_GetOTP")) {
						clog.info("running OTP");
						Result = Execute_Result.getString(getColumnName);

						if (Result.split(" ")[0].matches("^[a-zA-Z0-9]*$")) {
							// clog.info(Result.split(" ")[0]);
							// clog.info("it is not arabic");
							break;
						} else {
							Execute_Result.next();
							Result = Execute_Result.getString(getColumnName);

							break;
						}
					} else if (!getColumnName2.equalsIgnoreCase("")
							&& function.equalsIgnoreCase("Get_SRID_WithCurrTimeStamp")) {
						String getTimeStamp = Execute_Result.getString(getColumnName2);
						System.out.println("Time Stamp : " + getTimeStamp);
						Result = Execute_Result.getString(getColumnName);
						break;
					} else if (getColumnName2.equalsIgnoreCase("")) {
						// clog.info("running OTP");
						Result = Execute_Result.getString(getColumnName);
						break;
					}
				}
			} else {
				try {
					DBColoumnValues(Execute_Result);
				} catch (Exception e) {
					System.out.println("Error occurs: " + e);
				}
				Result = DBpair.toString();
			}

			if (Result.equalsIgnoreCase("")) {
				return null;
			} else {
				clog.info(function + " : " + Result);
			}
			//printDB(Execute_Result);
			clog.info(Success);
			conn.close();
		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getMultipleValFromQueryTbl(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = null;
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		String getColumnName = "";
		String getColumnName2 = "";
		String getColumnName3 = "";
		String expectedFuncName = "";
		String getColVal1 = "";
		String getColVal2 = "";
		String getColVal3 = "";
		String[] columnName = null;
		DB_Validation db_Validation = null;
		clog.info("####" + DB_Name);
		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;
		columnName = db_Validation.getColumnName.split(";");
		if (!DB_Name.toLowerCase().contains("_pgw")) {
			int len = columnName.length;
			if (len > 2) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
				getColumnName3 = columnName[2];
				if (len == 4) {
					expectedFuncName = columnName[3];
				}
			} else if (len > 1) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
			} else {
				getColumnName = columnName[0];
			}
		}
		try {
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			if (!DB_Name.toLowerCase().contains("_pgw")) {
				boolean columnValueIsPresent;
				columnValueIsPresent = false;
				// clog.info(getColVal3);
				while (Execute_Result.next()) {
					clog.info("##");
					Thread.sleep(1000);
					//clog.info("---> Execute_Result.... " + Execute_Result.toString());
					getColVal1 = Execute_Result.getString(getColumnName);
					if (getColVal1.contains(expectedFuncName)) {
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						break;
					} else if (!getColumnName3.contains("") && expectedFuncName.contains("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						break;
					} else if (!getColumnName2.contains("") && expectedFuncName.contains("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						columnValueIsPresent = true;
						break;
					}
				}
				//printDB(Execute_Result);
				// clog.info(function+" : "+Result);
				if (columnValueIsPresent == true) {
					clog.info(Success);
					conn.close();

					List<String> dataResult = new ArrayList<>();
					dataResult.add(getColVal1);
					dataResult.add(getColVal2);
					if (!getColVal3.equalsIgnoreCase("")) {
						dataResult.add(getColVal3);
					}

					StringBuilder strbul = new StringBuilder();
					for (String str : dataResult) {
						strbul.append(str);
						strbul.append(";");
					}

					String strVal = strbul.toString();
					Result = strVal;
					clog.info(Result);
				} else {
					clog.error("Multiple values have not been successfully received");
				}
			} else {

				try {
					DBpair.clear();

					try {
						DBColoumnValues(Execute_Result);
					} catch (Exception e) {
						System.out.println("Error occurs: " + e);
					}
					clog.info(Success);
					conn.close();

				} catch (Exception e) {
					clog.error(e.getMessage());
				}

				Result = DBpair.toString();

			}

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}
	
	
	
	
	
	
	
	
	
	
	
	public static String getMultipleRowValFromQueryTb(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = null;
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		String getColumnName = "";
		String getColumnName2 = "";
		String getColumnName3 = "";
		String expectedFuncName = "";
		String getColVal1 = "";
		String getColVal2 = "";
		String getColVal3 = "";
		String[] columnName = null;
		DB_Validation db_Validation = null;
		clog.info("####" + DB_Name);
		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;
		columnName = db_Validation.getColumnName.split(";");
		if (!DB_Name.toLowerCase().contains("_pgw")) {
			int len = columnName.length;
			if (len > 2) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
				getColumnName3 = columnName[2];
				if (len == 4) {
					expectedFuncName = columnName[3];
				}
			} else if (len > 1) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
			} else {
				getColumnName = columnName[0];
			}
		}
		try {
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			if (!DB_Name.toLowerCase().contains("_pgw")) {
				boolean columnValueIsPresent;
				columnValueIsPresent = false;
				// clog.info(getColVal3);
				while (Execute_Result.next()) {
					clog.info("##");
					//clog.info("---> Executing Query.... " + Execute_Result);
					getColVal1 = Execute_Result.getString(getColumnName);
					if (getColVal1.contains(expectedFuncName)) {
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						//break;
					} else if (!getColumnName3.contains("") && expectedFuncName.contains("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						//break;
					} else if (!getColumnName2.contains("") && expectedFuncName.contains("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						columnValueIsPresent = true;
						//break;
					}
				}
				//printDB(Execute_Result);
				// clog.info(function+" : "+Result);
				if (columnValueIsPresent == true) {
					clog.info(Success);
					conn.close();

					List<String> dataResult = new ArrayList<>();
					dataResult.add(getColVal1);
					dataResult.add(getColVal2);
					if (!getColVal3.equalsIgnoreCase("")) {
						dataResult.add(getColVal3);
					}

					StringBuilder strbul = new StringBuilder();
					for (String str : dataResult) {
						strbul.append(str);
						strbul.append(";");
					}

					String strVal = strbul.toString();
					Result = strVal;
					clog.info(Result);
				} else {
					clog.error("Multiple values have not been successfully received");
				}
			} else {

				try {
					DBpair.clear();

					try {
						DBColoumnValues(Execute_Result);
					} catch (Exception e) {
						System.out.println("Error occurs: " + e);
					}
					clog.info(Success);
					conn.close();

				} catch (Exception e) {
					clog.error(e.getMessage());
				}

				Result = DBpair.toString();

			}

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getCompleteTblFromQuery(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = "";
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		DB_Validation db_Validation = null;

		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;

		try {
			stmt = conn.createStatement();
			DBpair.clear();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			try {
				DBColoumnValues(Execute_Result);
			} catch (Exception e) {
				System.out.println("Error occurs: " + e);
			}
			Result = DBpair.toString();
			if (Result != "{}") {
				clog.info(Success);
			} else {
				clog.error("FAIL - No record found in the table query");
			}
			//printDB(Execute_Result);
			clog.info(Success);
			conn.close();

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getMultValFromFirstRow(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = "";
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		String columnName = "";
		String getColVal = "";
		DB_Validation db_Validation = null;
		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;

		try {
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);

			ResultSetMetaData rsMetaData = Execute_Result.getMetaData();
			int ColCount = rsMetaData.getColumnCount();
			SetMultimap<String, String> multimap = HashMultimap.create();

			boolean recordIsPresent;
			recordIsPresent = false;
			int rowCount = 0;

			while (Execute_Result.next()) {
				for (int i = 1; i < ColCount + 1; i++) {
					columnName = rsMetaData.getColumnName(i);
					getColVal = Execute_Result.getString(columnName);
					// System.out.println("Column Name of '"+columnName+"' : "+getColVal);
					if (null == getColVal) {
						getColVal = "";
					} else {
						multimap.put(columnName, getColVal.trim());
					}
				}

				System.out.println(rowCount);
				if (rowCount == 0) {
					recordIsPresent = true;
					break;
				}
				rowCount++;

			}

			// System.out.println(multimap.toString());
			if (recordIsPresent == true) {
				Result = multimap.toString();
				clog.info(Success);
			} else {
				clog.error("FAIL - Multiple values of first row have not been received");
			}
			//printDB(Execute_Result);
			conn.close();

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getMultValFromEachColQuery(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = "";
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		DB_Validation db_Validation = null;

		db_Validation = getQueryDetails(function, input);
		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;

		try {
			DBpair.clear();
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			try {
				DBColoumnValues(Execute_Result);
			} catch (Exception e) {
				System.out.println("Error occurs: " + e);
			}
			Result = DBpair.toString();

			clog.info(Success);
			//printDB(Execute_Result);
			conn.close();

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getMultValWithKey(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = null;
		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		String Query = "";
		String Success = "";
		String getColumnName = "";
		String getColumnName2 = "";
		String getColumnName3 = "";
		String expectedFuncName = "";
		String getColVal1 = "";
		String getColVal2 = "";
		String getColVal3 = "";
		String[] columnName = null;
		DB_Validation db_Validation = null;

		db_Validation = getQueryDetails(function, input);

		Query = db_Validation.stmtQuery;
		Success = db_Validation.Success;
		if (!DB_Name.toLowerCase().contains("_billing")) {
			columnName = db_Validation.getColumnName.split(";");
			int len = columnName.length;
			if (len > 2) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
				getColumnName3 = columnName[2];
				if (len == 4) {
					expectedFuncName = columnName[3];
				}
			} else if (len > 1) {
				getColumnName = columnName[0];
				getColumnName2 = columnName[1];
			} else {
				getColumnName = columnName[0];
			}
		}

		try {
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			if (!DB_Name.toLowerCase().contains("_billing")) {
				boolean columnValueIsPresent;
				columnValueIsPresent = false;
				while (Execute_Result.next()) {
					getColVal1 = Execute_Result.getString(getColumnName);
					if (getColVal1.equalsIgnoreCase(expectedFuncName)) {
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						break;
					} else if (!getColumnName3.equalsIgnoreCase("") && expectedFuncName.equalsIgnoreCase("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						getColVal3 = Execute_Result.getString(getColumnName3);
						columnValueIsPresent = true;
						break;
					} else if (!getColumnName2.equalsIgnoreCase("") && expectedFuncName.equalsIgnoreCase("")) {
						getColVal1 = Execute_Result.getString(getColumnName);
						getColVal2 = Execute_Result.getString(getColumnName2);
						columnValueIsPresent = true;
						break;
					}
				}
			//	printDB(Execute_Result);
				// clog.info(function+" : "+Result);
				if (columnValueIsPresent == true) {
					clog.info(Success);
					conn.close();

					SetMultimap<String, String> multimap = HashMultimap.create();
					multimap.put(getColumnName, getColVal1);
					multimap.put(getColumnName2, getColVal2);
					if (!getColVal3.equalsIgnoreCase("")) {
						multimap.put(getColumnName3, getColVal3);
					}

					Result = multimap.toString();
				} else {
					clog.error("Multiple values have not been successfully received");
				}
			} else {
				try {
					DBpair.clear();

					try {
						DBColoumnValues(Execute_Result);
					} catch (Exception e) {
						System.out.println("Error occurs: " + e);
					}
					clog.info(Success);
					conn.close();

				} catch (Exception e) {
					clog.error(e.getMessage());
				}

				Result = DBpair.toString();
			}

		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}

	public static String getDataFromLastRow(String DB_Name, String function, String input)
			throws SQLException, IOException {
		String Result = "";
		String Query = "";
		String Success = "";
		String columnName = "";
		DB_Validation db_Validation = null;

		conn = OracleDBConnector(DB_Name);
		Statement stmt;
		clog.info("---> Executing _Eportal.... " + function);
		
		db_Validation = getQueryDetails(function, input);
		
		Query = db_Validation.stmtQuery;
		clog.info("---> Executing Query.... " + Query);
		Success = db_Validation.Success;
		columnName = db_Validation.getColumnName;

		try {
			stmt = conn.createStatement();
			clog.info("---> Executing Query.... " + Query);
			ResultSet Execute_Result = stmt.executeQuery(Query);
			int rowCount = 0;

			while (Execute_Result.next()) {
				// clog.info("Row Count of '"+columnName+"' : "+rowCount);
				if (!(columnName.equalsIgnoreCase(""))) {
					Result = Execute_Result.getString(columnName);
				}
				rowCount++;
			}
			//printDB(Execute_Result);
			if (Result.equalsIgnoreCase("")) {
				return null;
			} else {
				clog.info(function + " of last row '" + rowCount + "' : " + Result);
			}

			clog.info(Success);
			conn.close();
		} catch (Exception e) {
			clog.error(e.getMessage());
		}

		return Result;
	}


	public static String unbilled_details(String MSISDN, String DBName) {
		String line = "";
		try {
			Connection conn1 = OracleDBConnector(DBName);

			CallableStatement cstmt = conn1.prepareCall("{call dbms_output.enable(32000) }");
			cstmt.execute();

			// run your PL/SQL block
			Statement stmt = conn1.createStatement();
			String sql = "declare " +

					"retv varchar2(500); " +

					"begin " +

					"PIN.MSISDN_BALANCE_CC_NEW('" + MSISDN + "', 'TRUE', retv); " +

					"dbms_output.put_line(retv); " + "end; ";
			stmt.execute(sql);

			// retrieve the messages written with dbms_output
			cstmt = conn1.prepareCall("{call dbms_output.get_line(?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);

			int status = 0;
			while (status == 0) {
				cstmt.execute();
				line = line + "\r\n" + cstmt.getString(1);
				status = cstmt.getInt(2);

			}
			// System.out.println(line);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	
	public static String DBproperties(String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Config/DB_Details.properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);

		return prop.getProperty(propname);
	}

	public static HashMap<String, String> DBColoumnValues(ResultSet Execute_Result) throws SQLException {
		String testdata = "";
		ResultSetMetaData rsMetaData = Execute_Result.getMetaData();
		int ColCount = rsMetaData.getColumnCount();
		while (Execute_Result.next()) {
			for (int i = 1; i < ColCount + 1; i++) {
				String columnName = "";
				String getColVal = "";
				try {
					columnName = rsMetaData.getColumnName(i);
					getColVal = Execute_Result.getString(columnName);
//				 System.out.println("Column Name of '"+columnName+"' : "+getColVal);
					if (getColVal.equalsIgnoreCase("null")) {
						getColVal = "";
					} else {
						DBpair.put(columnName, getColVal);
//					System.out.println("Hashmap: "+DBpair.get(columnName));
					}

					try {
						testdata = ValidationData.get(columnName);
					} catch (Exception e) {
						System.out.println(e);
						System.out.println(testdata + " is empty");
					}
					if (!testdata.equalsIgnoreCase("")) {
						Assertion.assertequalsMultiple(getColVal, testdata);
						/*
						 * clog.info("For coloumn " + columnName + " Actual Value = " + getColVal +
						 * " and Expected Value = " + testdata);
						 */
					} else {
						// clog.info(columnName + " is empty and adding to test data");
						Utlities.saveToData1(columnName, getColVal);
					}
				} catch (Exception e) {
					// clog.info(columnName + "and its value " + getColVal + " is not utilised!!");
				}
			}
			clog.info("Column names: " + DBpair);
		}
		return DBpair;
	}
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name    : getQueryDetails
     * Arguments      : String , String
     * Use            : get the query details from property file and update the inputs 
     * Designed By    : JJ
     * Designed Date     : 20-07-2021
    --------------------------------------------------------------------------------------------------------*/

 
	
	public static DB_Validation getQueryDetails(String function, String input) throws IOException {
		String Query = null;
		String SuccessMessage = null;
		String input2 = null;
		String input3 = null;
		String ColumnName = null;
		String arrInput[] = input.split(";");
		int inputLen = arrInput.length;
		System.out.println(inputLen);

		if (inputLen > 2) {
			input = arrInput[0];
			input2 = arrInput[1];
			input3 =arrInput[2];
		} 
		else if(inputLen > 1){
			input = arrInput[0];
			input2 = arrInput[1];
		}else {
			input = arrInput[0];
		}
		
		Query=DataBseproperties(function+"_Query");
		clog.info("Query : " + Query);
		if(Query.contains("input1")){
			 Query=Query.replace("input1", input);	
			}
		if(Query.contains("input2")){
		 Query=Query.replace("input2", input2);	
		}
		if(Query.contains("input3")){
			 Query=Query.replace("input3", input3);	
			}
		clog.info("Updated Query : " + Query);
		String Success=DataBseproperties(function+"_Success");
		clog.info("Fetched Success Message : " + Success);
		String Success1[]=Success.split(",");
		SuccessMessage=Success1[0];
		clog.info("Success Message  : " + SuccessMessage);
		if(Success1.length>1){
		ColumnName=Success1[1];
		clog.info("ColumnName   : " + ColumnName);
		if(ColumnName.contains("input1")){
			clog.info("ColumnName   : " + ColumnName);
			 ColumnName=ColumnName.replace("input1", input2);
			}
		}
		
		return new DB_Validation(Query, SuccessMessage, ColumnName);

	}
}
