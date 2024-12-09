package app.components;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.entities.Quote;
import app.entities.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@Component
public class MessageOfTheDayComponent {
	@Autowired
    private MessageComponent mc;

    @Autowired
    private UserComponent md;
    
    Quote m;
    
    @Autowired
    TwilioComponent tc;
    
    public TwilioReply messaging(Long pk, String category) throws Exception{
    	User u = md.findUser(pk);
    	if(u != null) {
    		m = mc.getQuote(category);
    	}
    	return tc.sendMessage(u.getCellphoneNumber(), "Hello "+u.getName()+ ", "+m.getMessage());
    }
    
    
    public String messageAll(String category) throws Exception{
    	m = mc.getQuote(category);
    	List<User> pks = md.findAllUsers();
    	int n = pks.size();
    	int i=0;
    	while(i<n) {
    		User u = md.findUser(pks.get(i).getPk());
    		tc.sendMessage(u.getCellphoneNumber(), "Hello "+u.getName()+ ", "+m.getMessage());
    		i+=1;
    	}
    	return "Messages sent: "+i;
    }
    
//    @Scheduled(fixedRate = 1000)
    public String schedulemessage() throws Exception{
    	m = mc.getQuote("Happy");
    	List<User> pks = md.findAllUsers();
    	int n = pks.size();
    	int i=0;
    	while(i<n) {
    		User u = md.findUser(pks.get(i).getPk());
    		i+=1;
    	}
    	return ""+i;
    }
}
