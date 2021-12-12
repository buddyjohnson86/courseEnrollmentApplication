package com.bullish.class_enrollment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bullish.class_enrollment.data.Semester;

/**
 * Use springframework's implementation of a repository
 */
public interface SemesterRepository extends CrudRepository<Semester, Long> {

	/**
	 * Find semester based on semester code
	 * 
	 * @param semesterCode
	 * @return semester
	 */
	public Optional<Semester> findSemesterBySemesterCode(String semesterCode);

}
