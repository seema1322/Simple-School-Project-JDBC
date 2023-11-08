package com.ty.school_db.entity;

public class Student {

	private int id;
	private int school_id;
	private String name;
	private int age;
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", school_id=" + school_id + ", name=" + name + ", age=" + age + ", gender="
				+ gender + "]";
	}

	public Student(int id, int school_id, String name, int age, String gender) {
		super();
		this.id = id;
		this.school_id = school_id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

}
