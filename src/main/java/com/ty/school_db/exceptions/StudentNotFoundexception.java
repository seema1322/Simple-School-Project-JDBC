package com.ty.school_db.exceptions;

public class StudentNotFoundexception extends RuntimeException {
	
	public String getMessage() {
		return "Student details not found";
	}
}
