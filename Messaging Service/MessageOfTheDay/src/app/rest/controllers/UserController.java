package app.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.UserComponent;
import app.entities.User;

@Component
@Path("/user")
public class UserController {
	@Autowired
	UserComponent md;
	
	@GET
	@Path("/finduser")
	public User getUser(@QueryParam("p") Long pk) {
		return md.findUser(pk);
	}
	
	@GET
	@Path("/findusers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		
		List<User> list = md.findAllUsers();
		
		return list;
	}
}
