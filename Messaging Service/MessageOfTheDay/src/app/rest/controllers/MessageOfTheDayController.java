package app.rest.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.components.MessageOfTheDayComponent;
import app.components.TwilioReply;

@Component
@Path("/message")
public class MessageOfTheDayController{
	
	@Autowired
	MessageOfTheDayComponent mc;
    
    @GET
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    public TwilioReply messagingCont(@QueryParam("p") Long pk, @QueryParam("c") String category) throws Exception{
    	return mc.messaging(pk, category);
    }
    
    @GET
    @Path("/sendall")
    @Produces(MediaType.APPLICATION_JSON)
    public String messageAll(@QueryParam("c") String category) throws Exception{
    	return mc.messageAll(category);
    }
    
    @GET
    @Path("/scheduled")
    @Produces(MediaType.APPLICATION_JSON)
    public String scheduled() throws Exception{
    	return mc.schedulemessage();
    }
}
