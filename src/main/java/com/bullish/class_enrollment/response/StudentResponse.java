package com.bullish.class_enrollment.response;

import java.util.HashSet;
import java.util.Set;

import com.bullish.class_enrollment.data.Student;

public class StudentResponse extends UpdateResponse {

	Set<Student> students = new HashSet<Student>();
	
	/**
	 * @param students
	 */
	public StudentResponse(Set<Student> students) {
		super(true, null);
		this.students = students;
	}
	
	/**
	 * @param message
	 */
	public StudentResponse(String message) {
		super(false, message);
	}
	
	/**
	 * @return
	 */
	public Set<Student> getStudents() {
		return students;
	}
}
