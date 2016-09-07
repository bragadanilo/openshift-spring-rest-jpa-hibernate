package com.dbraga.springrest.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	@GeneratedValue
    private int id;
    private String name;
    private String email;
     
    public Person() {
    }
 
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "contact: {id:" + id + ", name:" + name + ", email:" + email + "}";
	}
 
    
    
}