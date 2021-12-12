package com.bullish.class_enrollment.response;

import java.util.HashSet;
import java.util.Set;

import com.bullish.class_enrollment.data.Semester;

public class SemesterResponse extends UpdateResponse {

	Set<Semester> semesters = new HashSet<Semester>();
	
	/**
	 * @param semesters
	 */
	public SemesterResponse(Set<Semester> semesters) {
		super(true, null);
		this.semesters = semesters;
	}
	
	/**
	 * @param message
	 */
	public SemesterResponse(String message) {
		super(false, message);
	}

	/**
	 * @return
	 */
	public Set<Semester> getSemesters() {
		return semesters;
	}
}
