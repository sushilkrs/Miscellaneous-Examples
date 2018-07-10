package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ThreadLocalRandom;

public class MyProjectMain {

	public static void main(String[] args) {
		
		// String stringTimestamp1 = "2016-07-16 10:23:15";
		// String stringTimestamp2 = null;
		// Timestamp timestampTimestamp = null;
		// if (stringTimestamp1 != null)
		// timestampTimestamp = Timestamp.valueOf(stringTimestamp1);
		// System.out.println(stringTimestamp1);
		// System.out.println(timestampTimestamp);

		// if (stringTimestamp2.toString().equals("null")) {
		// System.out.println("dotequals is null");
		// }
		// System.out.println("OTP is "+genOtp());
		// System.out.println("encoded msg is: "+encodeURL("Hello there, How are
		// you? your OTP is 123223"));
		
		String response = SendSMSUsingMSG91("9876543210", "Hello there, Thanks for Using MSG91? your OTP is 123223", "SV-OTP");
		System.out.println("Response for send OTP is " + response);
	}

	public static int genOtp() {
		int otp = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
		System.out.println("OTP is: " + otp);
		return otp;
	}

	public static String SendSMSUsingMSG91(String mobileNo, String message, String senderId) {

		String returnResponse = null;

		// Your authentication key
		String authkey = "YOUR_AUTH_KEY";

		// Multiple mobile numbers separated by comma
		String mobiles = mobileNo;

		// define route
		String route = "1";

		// Prepare Url
		URLConnection myURLConnection = null;
		URL myURL = null;
		BufferedReader reader = null;

		// encoding message
		String encoded_message = encodeURL(message);

		// Send SMS API
		String mainUrl = "https://control.msg91.com/api/sendhttp.php?";

		// Prepare parameter string
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		sbPostData.append("authkey=" + authkey);
		sbPostData.append("&mobiles=" + mobiles);
		sbPostData.append("&message=" + encoded_message);
		sbPostData.append("&route=" + route);
		sbPostData.append("&sender=" + senderId);

		// final string
		mainUrl = sbPostData.toString();
		try {
			// prepare connection
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			System.out.println("reader" + reader);
			// reading response
			String response;
			while ((response = reader.readLine()) != null) {
				// print response
				System.out.println("Response " + response);
				returnResponse = response;
			}

			// finally close connection
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnResponse;
	}

	public static String encodeURL(String url) {
		String encodedUrl = null;
		try {
			encodedUrl = URLEncoder.encode(url, "UTF-8");
			System.out.println("Encoded URL " + encodedUrl);
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
		}
		return encodedUrl;
	}

}
