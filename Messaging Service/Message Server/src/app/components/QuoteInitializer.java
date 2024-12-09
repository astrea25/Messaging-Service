package app.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Quote;
import app.repositories.MessageRepository;

@Component
public class QuoteInitializer {
	@Autowired
	private MessageRepository repo;
	
	@PostConstruct
	public void init() {
		if(repo.count()==0) {
			Quote m1 = new Quote();
			m1.setMessage("If you fail to plan, you are planning to fail.");
			m1.setCategory("Encouraging");
			repo.save(m1);
			
			Quote m2 = new Quote();
			m2.setMessage("The greatest pain that comes from love is loving someone you can never have.");
			m2.setCategory("Sad");
			repo.save(m2);
			
			Quote m3 = new Quote();
			m3.setMessage("The greatest joy in life is the experience of being alive.");
			m3.setCategory("Happy");
			repo.save(m3);
			
			Quote m4 = new Quote();
			m4.setMessage("No pain, no gain");
			m4.setCategory("Generic");
			repo.save(m4);
			
			Quote m5 = new Quote();
			m5.setMessage("Either you run the day or the day runs you");
			m5.setCategory("Encouraging");
			repo.save(m5);
			
		}
	}
}
