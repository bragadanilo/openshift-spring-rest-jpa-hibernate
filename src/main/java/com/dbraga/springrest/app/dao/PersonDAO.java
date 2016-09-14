package com.dbraga.springrest.app.dao;

import java.util.List;

import com.dbraga.springrest.app.domain.Person;

public interface PersonDAO extends GenericDAO<Person, Long> {
	public List<Person> getAll();
}
