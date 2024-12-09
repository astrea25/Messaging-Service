package app.components;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.Quote;
import app.repositories.MessageRepository;

@Component
public class MessageComponent {
	@Autowired
	private MessageRepository repo;
	
	public Quote getQuote(String cat) {
		List<Quote>  ms = repo.findAllByCategory(cat);
		if(ms==null || ms.isEmpty()) {
			ms = repo.findAllByCategory("Generic");
		}
		Random rand = new Random();
        int randomIndex = rand.nextInt(ms.size());
        return ms.get(randomIndex);
	}
	
}
