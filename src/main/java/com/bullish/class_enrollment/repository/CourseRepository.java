package com.bullish.class_enrollment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bullish.class_enrollment.data.Course;

/**
 * Use springframework's implementation of a repository
 */
public interface CourseRepository extends CrudRepository<Course, Long> {

	/**
	 * Find course using course code and semester code
	 * 
	 * @param courseCode
	 * @param semesterCode
	 * @return course
	 */
	public Optional<Course> findCourseByCourseCodeAndSemesterCode(String courseCode, String semesterCode);
}