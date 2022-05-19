package Libraries;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/*---------------------------------------------------------------------------------------------------------
* Class Name : Assertion
* Use : An Assertion class used to testing the correctness of any assumptions that have been made in the program.
* Designed By : Guhan
* Last Modified Date : 23-06-2021
--------------------------------------------------------------------------------------------------------*/
public class Assertion extends Driver{

	public static logs clog = new logs();

	Result result = new Result();
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertStartAndEnd
	* Arguments : String vaidateString, String startValue, String endValue
	* Use : To check whether the passing value contains expected start value and end value
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertStartAndEnd(String vaidateString,String startValue,String endValue)
	{
		String Status = "Pass";
		if(vaidateString.startsWith(startValue) && vaidateString.endsWith(endValue))
		{
			Driver.Continue.set(true);
		}
		else
		{
			Status = "Fail";
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations("Starts With : "+startValue +" End Value:"+ endValue , vaidateString,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertTrue
	* Arguments : Boolean status, String message
	* Use : To check the boolean conditions and get the screenshot
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertTrue(Boolean status, String message) {
		if (status == false) {
			clog.info(message + " should displayed");
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertTrue
	* Arguments : int actual, int expected
	* Use : To check the boolean conditions of the actual value vs expected value and get the screenshot
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
    public static void assertTrue(int actual, int expected) {
	clog.info(actual + " text is  equal to " + expected);
		if ((actual!=expected)) {
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);		
		}
		if (methodScreenShotTaken.get() == false) {
			Result.Methodscreenshot();
		}
		
	}
    /*---------------------------------------------------------------------------------------------------------
	* Method Name : assertFalse
	* Arguments : int actual, int expected
	* Use : To check the boolean conditions whether actual value is equal to expected value and get the screenshot
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
    public static void assertFalse(int actual, int expected) {
	
		if ((actual==expected)) {
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);			
		}
		if (methodScreenShotTaken.get() == false) {
			Result.Methodscreenshot();
		}
	}
    /*---------------------------------------------------------------------------------------------------------
	* Method Name : assertTrue
	* Arguments : String actual, String expected
	* Use : To compares two strings irrespective of the case
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertTrue(String actual, String expected) {
		
		if (!actual.equalsIgnoreCase(expected)) {
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}


	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequals
	* Arguments : String actual, String expected
	* Use : To compares two strings for equality
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequals(String actual, String expected) {
		String Status = "Pass";
		if (!actual.trim().equals(expected.trim())) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertEqualsOr
	* Arguments : String actual,String expected1,String expected2String actual, String expected
	* Use : To compares actual string value with two expected string values irrespective of the case (upper or lower)
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertEqualsOr(String actual,String expected1,String expected2) {
		String Status = "Pass";
		if(actual.trim().equalsIgnoreCase(expected1.trim()))
		{
			Result.updateValidations(expected1, actual,Status);
		}
		else if(actual.trim().equalsIgnoreCase(expected2.trim()))
		{
			Result.updateValidations(expected2, actual,Status);
		}
		else
		{
			Status = "Fail";
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.updateValidations(expected1 +" OR "+expected2, actual,Status);

			
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertnotequals
	* Arguments : String actual, String expected
	* Use : To compares two strings for not equal
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertnotequals(String actual, String expected) {
		String Status = "Pass";
		if (actual.equals(expected)) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertFalse
	* Arguments : String actual, String expected
	* Use : To compares two strings irrespective of the case (upper or lower)
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertFalse(String actual, String expected) {
		String Status = "Pass";
		if (actual.equalsIgnoreCase(expected)) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertFalse
	* Arguments : Boolean status, String message
	* Use : To check the boolean condition is equal to true
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertFalse(Boolean status, String message) {
		if (status == true) {
			clog.info(message + " is displayed");
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContains
	* Arguments : String actual, String expected
	* Use : To check the boolean condition. whether expected value contains actual value or not
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContains(String actual, String expected) {
		String Status = "Pass";
		if (!actual.contains(expected)) {
			Status = "Fail";
			clog.info(actual + " text does not contain " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContainsText
	* Arguments : String actual, String expected
	* Use : To check the boolean condition. whether expected value contains actual value or not
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContainsText(String actual, String expected) {
		String Status = "Pass";
		if (!StringUtils.containsAny(actual, expected)) {
			Status = "Fail";
			clog.info(actual + " text does not contain " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertNotContains
	* Arguments : String actual, String expected
	* Use : To check the boolean condition. whether expected value not contains actual value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertNotContains(String actual, String expected) {
		String Status = "Pass";
		if (actual.contains(expected)) {
			Status = "Fail";
			clog.info(actual + " text is contains " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContainsOR
	* Arguments : String actual, String expected1, String expected2
	* Use : To check the boolean condition. whether any one of the expected value of the two values contains actual value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContainsOR(String actual, String expected1, String expected2) {
		if (!(actual.contains(expected1) || actual.contains(expected2))) {
			clog.info(actual + " text does not contain either " + expected1 + " or " + expected2);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContainsORthree
	* Arguments : String actual, String expected1, String expected2, String expected3
	* Use : To check the boolean condition. whether any one of the expected value of the three values contains actual value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContainsORthree(String actual, String expected1, String expected2, String expected3) {
		if (!(actual.contains(expected1) || actual.contains(expected2) || actual.contains(expected3))) {
			clog.info(actual + " text does not contain either " + expected1 + " or " + expected2 + " or " + expected3);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequalsignorecase
	* Arguments : String actual, String expected
	* Use : To compares two strings irrespective of the case
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequalsignorecase(String actual, String expected) {
		String Status = "Pass";
		clog.info(actual);
		clog.info(expected);
		if (!actual.equalsIgnoreCase(expected)) {
			clog.info(actual + " text is not equal to " + expected);
			Status = "Fail";
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayEquals
	* Arguments : String[] actualArray, String[] expectedArray
	* Use : To compares two strings equality of arrays for each valid value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayEquals(String[] actualArray, String[] expectedArray) {

		for (int i = 0; i <= actualArray.length; i++) {
			for (int j = 0; j <= expectedArray.length; j++) {
				if (!actualArray[i].equals(expectedArray[j])) {
					clog.info(actualArray[i] + " text is not equal to " + expectedArray[j]);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayEqualsignorecase
	* Arguments : String[] actualArray, String[] expectedArray
	* Use : To compares two strings of arrays irrespective of the case for each valid value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayEqualsignorecase(String[] actualArray, String[] expectedArray) {

		for (int i = 0; i <= actualArray.length; i++) {
			for (int j = 0; j <= expectedArray.length; j++) {
				if (!actualArray[i].equalsIgnoreCase(expectedArray[j])) {
					clog.info(actualArray[i] + " text is not equal to " + expectedArray[j]);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayContains
	* Arguments : String[] actualArray, String[] expectedArray
	* Use : To check the boolean condition. whether expected array value contains actual array value or not
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayContains(String[] actualArray, String[] expectedArray) {

		for (int i = 0; i <= actualArray.length; i++) {
			for (int j = 0; j <= expectedArray.length; j++) {
				if (!actualArray[i].contains(expectedArray[j])) {
					clog.info(actualArray[i] + " text is not equal to " + expectedArray[j]);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayEquals
	* Arguments : String[] actualArray, String expected
	* Use : To compares actual string array is equal to expected string value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayEquals(String[] actualArray, String expected) {

		for (int i = 0; i <= actualArray.length; i++) {
			if (!actualArray[i].equals(expected)) {
			} else {
				if (i == actualArray.length) {
					clog.info(actualArray[i] + " text is not equal to " + expected);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayEqualsignorecase
	* Arguments : String[] actualArray, String expected
	* Use : To compares actual string array is irrespective of the case of expected string value 
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayEqualsignorecase(String[] actualArray, String expected) {

		for (int i = 0; i <= actualArray.length; i++) {
			if (!actualArray[i].equalsIgnoreCase(expected)) {

			} else {
				if (i == actualArray.length) {
					clog.info(actualArray[i] + " text is not equal to " + expected);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertArrayContains
	* Arguments : String[] actualArray, String expected
	* Use : To check the boolean condition. whether expected value contains actual array value or not
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertArrayContains(String[] actualArray, String expected) {

		for (int i = 0; i <= actualArray.length; i++) {
			if (!actualArray[i].contains(expected)) {

			} else {
				if (i == actualArray.length) {
					clog.info(actualArray[i] + " text is not equal to " + expected);
					Driver.Continue.set(false);
					if (methodScreenShotTaken.get() == false) {
						Result.Methodscreenshot();
					}
				}
			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequalsMultiple
	* Arguments : String actual, String expected
	* Use : To check the boolean condition. whether expected value is equal and irrespective case of actual value or not
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequalsMultiple(String actual, String expected) {
		String Status = "Pass";
		String data = "";
		String stat = "";
		if(expected.contains(";")) {
			String[] datas = expected.split(";");
			int len = datas.length;
			for(int i = 0; i<=len-1; i++) {
//				clog.info(actual + " text is not equal to " + datas[k]);
				if(actual.trim().equals(datas[i].trim())) {
					expected = datas[i];
					stat = "pass";
				}
			}
			if(!stat.equalsIgnoreCase("pass")) {
				Status = "Fail";
			}
		}else {
			if (!actual.equals(expected)) {
				Status = "Fail";
				clog.info(actual + " text is not equal to " + expected);
				Driver.Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}

			}
		}
		Result.updateValidations(expected, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequals
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether expected value is equal to actual value with removing unwanted spaces
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequals(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (!actual.trim().equals(expected.trim())) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertEqualsOr
	* Arguments : String validateText,String actual,String expected1,String expected2
	* Use : To check the boolean condition. whether any of the expected value of the two values is irrespective case of actual value with removing unwanted spaces and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertEqualsOr(String validateText,String actual,String expected1,String expected2) {
		String Status = "Pass";
		if(actual.trim().equalsIgnoreCase(expected1.trim()))
		{
			Result.updateValidations(validateText,expected1, validateText,actual,Status);
		}
		else if(actual.trim().equalsIgnoreCase(expected2.trim()))
		{
			Result.updateValidations(validateText,expected2,validateText, actual,Status);
		}
		else
		{
			Status = "Fail";
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.updateValidations(validateText,expected1 +" OR "+expected2,validateText, actual,Status);

			
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertnotequals
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value is not equal to actual value and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertnotequals(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (actual.equals(expected)) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertFalse
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value is not irrespective case of actual value and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertFalse(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (actual.equalsIgnoreCase(expected)) {
			Status = "Fail";
			clog.info(actual + " text is not equal to " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContains
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value contains actual value or not and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContains(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (!actual.contains(expected)) {
			Status = "Fail";
			clog.info(actual + " text does not contain " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertContainsText
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value contains any of actual value or not and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertContainsText(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (!StringUtils.containsAny(actual, expected)) {
			Status = "Fail";
			clog.info(actual + " text does not contain " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertNotContains
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value not contains actual value and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertNotContains(String validateText, String actual, String expected) {
		String Status = "Pass";
		if (actual.contains(expected)) {
			Status = "Fail";
			clog.info(actual + " text is contains " + expected);
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequalsignorecase
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether the expected value is irrespective case of actual value and also compare with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequalsignorecase(String validateText, String actual, String expected) {
		String Status = "Pass";
		clog.info(actual);
		clog.info(expected);
		if (!actual.equalsIgnoreCase(expected)) {
			clog.info(actual + " text is not equal to " + expected);
			Status = "Fail";
			Driver.Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : assertequalsMultiple
	* Arguments : String validateText, String actual, String expected
	* Use : To check the boolean condition. whether expected value is equal and irrespective case of actual value or not and also check with valid text.
	* Designed By : Guhan
	* Last Modified Date : 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void assertequalsMultiple(String validateText, String actual, String expected) {
		String Status = "Pass";
		String data = "";
		String stat = "";
		if(expected.contains(";")) {
			String[] datas = expected.split(";");
			int len = datas.length;
			for(int i = 0; i<=len-1; i++) {
				if(actual.trim().equals(datas[i].trim())) {
					expected = datas[i];
					stat = "pass";
				}
			}
			if(!stat.equalsIgnoreCase("pass")) {
				Status = "Fail";
			}
		}else {
			if (!actual.equals(expected)) {
				Status = "Fail";
				clog.info(actual + " text is not equal to " + expected);
				Driver.Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
			}
		}
		Result.updateValidations(validateText,expected,validateText, actual,Status);
	}
	

}
