package app.components;

import org.springframework.stereotype.Component;

import app.entities.Quote;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class MessageComponent {

    private Retrofit retrofit;

    public Quote getQuote(String cat) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://localhost:9999/") 
                .addConverterFactory(GsonConverterFactory.create())  
                .build();

        TwilioRequests service = retrofit.create(TwilioRequests.class);
        Call<Quote> call = service.testMessage(cat);
        Response<Quote> response = call.execute();

        return response.body();
    }
}
