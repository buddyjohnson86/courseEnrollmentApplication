package com.bullish.class_enrollment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.class_enrollment.data.Semester;
import com.bullish.class_enrollment.exception.CourseEnrollmentException;
import com.bullish.class_enrollment.repository.SemesterRepository;

@Service
public class SemesterService {
	private final static Logger LOG = LoggerFactory.getLogger(SemesterService.class);

	@Autowired
	private SemesterRepository semesterRepository;

	/**
	 * Add semester to repository
	 * 
	 * @param semesterCode
	 * @return unique id for semester
	 */
	public Long addSemester(String semesterCode) {
		Semester semester = new Semester(semesterCode);
		semester = semesterRepository.save(semester);
		LOG.info("Saved semester: " + semester.getSemesterId());
		return semester.getSemesterId();
	}

	/**
	 * Getter for all semesters
	 * 
	 * @return list of all semesters
	 */
	public Iterable<Semester> getAllSemesters() {
		return semesterRepository.findAll();
	}

	/**
	 * Get semester corresponding to semester code
	 * 
	 * @param semesterCode
	 * @return corresponding semester
	 * @throws CourseEnrollmentException 
	 */
	public Semester getSemester(String semesterCode) throws CourseEnrollmentException {
		Semester retrievedSemester = null;
		Optional<Semester> semester = semesterRepository.findSemesterBySemesterCode(semesterCode);
		if (semester.isPresent()) {
			LOG.info("Found and returning semester");
			retrievedSemester = semester.get();
		} else {
			throw new CourseEnrollmentException("Semester not found. Check semester code");
		}

		return retrievedSemester;

	}
}
