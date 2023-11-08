package com.ty.school_db.exceptions;

public class SchoolNotFoundException extends RuntimeException {
	public String getMessage() {
		
		return "School details are not found";
	}
}
