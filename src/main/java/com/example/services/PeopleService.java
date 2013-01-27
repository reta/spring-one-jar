package com.example.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.model.Person;

@Service
public class PeopleService {
	public Collection< Person > getPeople( int page, int pageSize ) {
		Collection< Person > persons = new ArrayList< Person >( pageSize );
		
		for( int index = 0; index < pageSize; ++index ) {
			persons.add( new Person( String.format( "person+%d@at.com", ( pageSize * ( page - 1 ) + index + 1 ) ) ) );
		}
		
		return persons;
	}

	public Person addPerson( String email ) {
		return new Person( email );
	}
}
