package Libraries;

public class EportalCommonMethods extends Driver{
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name            : readTextAndAssert
     * Arguments            : objectName  objectType and dataName
     * Use                     : find the element based on the locator and get text from then element then stores in the variable
     * Designed By            : Sathya LeninEARC
     * Last Modified Date     : 18-06-2021
    --------------------------------------------------------------------------------------------------------*/
	
	public static void readTextAndAssert(String objectName,String objectType,String dataName) throws Exception 
	{
	        String[] objectProperty = Utlities.FindObject(objectName, objectType);
	        String data = WebAction.readText(Web.getWebElement(objectProperty)); 
	        Utlities.saveToData(dataName, data); 
	        Result.takescreenshot(dataName);	 
	        if (ValidationData.get(dataName).equals("")) {
	            if (Utlities.pullData(TC_Id.get(), dataName).equals("")) {
	                clog.info(
	                    dataName + " Validation is not Provided for this Account"
	                );
	            } else {
	                Assertion.assertequals(
	                    Utlities.pullData(TC_Id.get(), dataName),
	                    data
	                );
	            }
	        } else {
	            Assertion.assertequals(ValidationData.get(dataName), data);
	        }
	    }

}
