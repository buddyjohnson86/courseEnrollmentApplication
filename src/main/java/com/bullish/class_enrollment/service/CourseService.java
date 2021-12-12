package com.bullish.class_enrollment.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.class_enrollment.data.Course;
import com.bullish.class_enrollment.data.Semester;
import com.bullish.class_enrollment.data.Student;
import com.bullish.class_enrollment.exception.CourseEnrollmentException;
import com.bullish.class_enrollment.repository.CourseRepository;

@Service
public class CourseService {
	private final static Logger LOG = LoggerFactory.getLogger(CourseService.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private SemesterService semesterService;

	/**
	 * Add course to repository
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param credits
	 * @param semesterCode
	 * @return unique id for course
	 * @throws CourseEnrollmentException 
	 */
	public Long addCourse(String courseCode, String courseName, Long credits, String semesterCode) throws CourseEnrollmentException {
		Course course = new Course(courseCode, courseName, credits, semesterCode);
		Semester semester = semesterService.getSemester(semesterCode);

		if (semester != null) {
			course = courseRepository.save(course);
			LOG.info("Course: {} has been successfully added. ", course.getCourseName());
		} else {
			LOG.error("Course: {} failed to be added. ", courseName);
			throw new CourseEnrollmentException("Unable to find semester. Check semester code is correct");
		}

		return course.getCourseId();
	}

	/**
	 * Get list of all courses
	 * 
	 * @return list of all courses
	 */
	public Iterable<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	/**
	 * Remove course from repository
	 * 
	 * @param courseId
	 */
	public void removeCourse(Long courseId) {
		courseRepository.delete(courseId);
	}

	/**
	 * Find course
	 * 
	 * @param courseCode
	 * @param semesterCode
	 * @return course that corresponds to search criteria
	 */
	public Optional<Course> findCourse(String courseCode, String semesterCode) {
		return courseRepository.findCourseByCourseCodeAndSemesterCode(courseCode, semesterCode);
	}

	/**
	 * Get students that are enrolled for a course
	 * 
	 * @param courseCode
	 * @param semesterCode
	 * @return students enrolled in course
	 * @throws CourseEnrollmentException 
	 */
	public Set<Student> getStudentsForCourse(String courseCode, String semesterCode) throws CourseEnrollmentException {
		Optional<Course> course = courseRepository.findCourseByCourseCodeAndSemesterCode(courseCode, semesterCode);
		Set<Student> students = new HashSet<Student>();
		if (course.isPresent()) {
			LOG.info("Found course. Returning students enrolled in course");
			students = course.get().getStudents();
		} else {
			throw new CourseEnrollmentException("Course not found. Check search criteria");
		}

		return students;

	}

}
