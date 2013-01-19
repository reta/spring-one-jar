package com.example.rs;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.model.Person;
import com.example.services.PeopleService;

@Path( "/people" ) 
public class PeopleRestService {
	@Inject private PeopleService peopleService;
	
	@Produces( { "application/json" } )
	@GET
	public Collection< Person > getPeople( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
		return peopleService.getPeople( page, 5 );
	}
		
	@Produces( { "application/json" } )
	@PUT
	public Person addPerson( @FormParam( "email" ) final String email ) {
		return peopleService.addPerson( email );
	}
}
