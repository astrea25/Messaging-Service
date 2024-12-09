package app.components;

import java.util.Base64;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class TwilioComponent {
	ObjectMapper om = new ObjectMapper();
	
    public TwilioReply sendMessage(String number, String message) throws Exception {
    	final String creds = "ACb65f0d39e30d89fa940737dbbcd18f20:ef8bc9ac1048c273e75dd98687467cdf";
    	final String msgsid = "MG8238d5bdc4af2f485caae8fc9d4665c8";
    	final String url = "https://api.twilio.com/2010-04-01/Accounts/ACb65f0d39e30d89fa940737dbbcd18f20/Messages.json";
    	
    	Retrofit retrofit = new Retrofit.Builder()
	               .baseUrl("https://bogus")
//	               .addConverterFactory(GsonConverterFactory.create())
	               .build();

		// Basic Authentication
		byte[] encodedAuth= Base64.getEncoder().encode(creds.getBytes());
		final String authorization = "Basic " + new String(encodedAuth);
		
		TwilioRequests req = retrofit.create(TwilioRequests.class);
		Call<ResponseBody> call = req.testSMS(number, 
								msgsid,
								message,
								authorization,
								url);
		
		Response<ResponseBody> resp = call.execute();
		
//		System.out.println(resp.code());
		
		if (resp.code()==201)
		{
			
			TwilioReply reply = om.readValue(resp.body().string(), TwilioReply.class);
			return reply;		}
		else
		{
			TwilioReply reply = om.readValue(resp.errorBody().string(),TwilioReply.class);
			return reply;
			
		}
	}
}
