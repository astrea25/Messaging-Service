package app.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.entities.User;
import app.repositories.UserRepository;

@Component
public class UserComponent {
	@Autowired
	private UserRepository repo;
	
	public User findUser(Long pk) {
		User u = repo.findByPk(pk);
		return u;
	}
	
	
	public List<User> findAllUsers(){
		return repo.findAll();
	}
}
