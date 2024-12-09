package app.components;

import java.util.List;

import app.entities.Quote;
import app.entities.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface TwilioRequests {

	@POST
	@FormUrlEncoded
	public Call<ResponseBody> testSMS(@Field("To") String to, 
						  @Field("MessagingServiceSid") String messageService, 
						  @Field("Body") String body,
						  @Header("Authorization") String creds,
						  @Url String url);
	
	@GET("http://localhost:9998/message/getquote")
	public Call<Quote> testMessage(@Query("c") String category);
	
	
}
