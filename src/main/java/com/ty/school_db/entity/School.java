package com.ty.school_db.entity;

public class School {

	private int id;
	private String name;
	
	public School() {
		super();
	}
	

	public School(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
