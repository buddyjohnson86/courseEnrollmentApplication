package com.bullish.class_enrollment.response;

import java.util.HashSet;
import java.util.Set;

import com.bullish.class_enrollment.data.Course;

public class CourseResponse extends UpdateResponse {

	Set<Course> courses = new HashSet<Course>();
	
	/**
	 * @param courses
	 */
	public CourseResponse(Set<Course> courses) {
		super(true, null);
		this.courses = courses;
	}
	
	/**
	 * @param message
	 */
	public CourseResponse(String message) {
		super(false, message);
	}

	/**
	 * @return
	 */
	public Set<Course> getCourses() {
		return courses;
	}
}
