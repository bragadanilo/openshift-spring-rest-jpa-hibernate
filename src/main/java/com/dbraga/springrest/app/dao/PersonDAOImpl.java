package com.dbraga.springrest.app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dbraga.springrest.app.domain.Person;

@Repository("personDAO")
@Transactional
public class PersonDAOImpl extends GenericDAOImp<Person, Long> implements PersonDAO {

	@Override
	public List<Person> getAll() {
		return this.all();
	}

	

	

}
