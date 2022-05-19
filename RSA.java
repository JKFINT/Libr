package Libraries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;


public class RSA extends Driver{
	
public static logs clog = new logs();

public static String getRSA(String userName,String password) {
		
		//int lengthOfOTP=0;
	
	

		String hostName = "imap.mobily.com.sa";

		 clog.info("hostname :"+hostName);
		 clog.info("userName :"+userName);
		 clog.info("password :"+password);

		int messageCount;

		//int unreadMsgCount;

		String emailSubject;

		Message emailMessage;

		String searchText = null;

		Properties props = System.getProperties();

		 props.setProperty("mail.store.protocol", "imaps");
         props.setProperty("mail.imap.ssl.enable", "true");
         props.put("mail.pop3.host", hostName);
         props.put("mail.imap.port", "993");
         props.setProperty("mail.imap.port", "993");
         props.setProperty("mail.imap.connectiontimeout", "5000");
         props.setProperty("mail.imap.timeout", "5000");
         props.put("mail.pop3.starttls.enable", "true");

		try {

			Thread.sleep(20000);
			
			Session session = Session.getInstance(props, null);

			Store store = session.getStore();

			store.connect(hostName, userName, password);

			Folder emailBox = store.getFolder("INBOX");

			emailBox.open(Folder.READ_WRITE);

			messageCount = emailBox.getMessageCount();
			
			clog.info("Total Message Count: "+messageCount);

			int unreadMsgCount = emailBox.getNewMessageCount();

			clog.info("Unread Emails count : "+unreadMsgCount);

			for (int i = messageCount; i > (messageCount - unreadMsgCount); i--)

			{

				emailMessage = emailBox.getMessage(i);

				emailSubject = emailMessage.getSubject();

				if (emailSubject.contains("Tokencode for Mobily"))

				{
					
					clog.info("RSA Tokencode mail found");

					String line;

					StringBuffer buffer = new StringBuffer();

					BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));

					while ((line = reader.readLine()) != null) {

						buffer.append(line);

					}
		
					
					String messageContent = "Mobily";

					String message = buffer.toString();
						
					clog.info("mail content found : " + message);
					
					String result = message.substring(message.indexOf(messageContent));
					
					searchText = result.substring(8, 14);

					System.out.println("Tocken Found : " + searchText);

					emailMessage.setFlag(Flags.Flag.SEEN, true);

					break;

				}

				emailMessage.setFlag(Flags.Flag.SEEN, true);

			}

			emailBox.close(true);

			store.close();

		} catch (Exception mex) {

			mex.printStackTrace();

			System.out.println("OTP Not found ");

		}
		return searchText;

		

	}

public static int getUnreadMailCount(String userName,String password) {
	
	

	String hostName = "imap.mobily.com.sa";

	 clog.info("hostname :"+hostName);
	 clog.info("userName :"+userName);
	 clog.info("password :"+password);

	int messageCount;

	int unreadMsgCount=0;


	

	Properties props = System.getProperties();

	 props.setProperty("mail.store.protocol", "imaps");
     props.setProperty("mail.imap.ssl.enable", "true");
     props.put("mail.pop3.host", hostName);
     props.put("mail.imap.port", "993");
     props.setProperty("mail.imap.port", "993");
     props.setProperty("mail.imap.connectiontimeout", "5000");
     props.setProperty("mail.imap.timeout", "5000");
     props.put("mail.pop3.starttls.enable", "true");

	try {

		Thread.sleep(20000);
		
		Session session = Session.getInstance(props, null);

		Store store = session.getStore();

		store.connect(hostName, userName, password);

		Folder emailBox = store.getFolder("INBOX");

		emailBox.open(Folder.READ_WRITE);

		messageCount = emailBox.getMessageCount();
		
		clog.info("Total Message Count: "+messageCount);

		unreadMsgCount = emailBox.getNewMessageCount();

		clog.info("Unread Emails count : "+unreadMsgCount);

		emailBox.close(true);

		store.close();

	} catch (Exception mex) {

		mex.printStackTrace();

		System.out.println("OTP Not found ");

	}
	return unreadMsgCount;

	

}


}
