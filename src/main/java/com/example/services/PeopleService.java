package com.example.services;

import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.model.Person;
import com.sun.istack.NotNull;

@Path( "/people" )
public class PeopleService {
	@Produces( { "application/json" } )
	@GET
	public Collection< Person > getPeople( @QueryParam( "page") @DefaultValue( "1" ) final int page ) {
		return Collections.emptyList();
	}
		
	@Consumes( { "application/json" } )
	@Produces( { "application/json" } )
	@PUT
	public Person addPerson( @QueryParam( "email" ) @NotNull final String email ) {
		return new Person( email );
	}
}
