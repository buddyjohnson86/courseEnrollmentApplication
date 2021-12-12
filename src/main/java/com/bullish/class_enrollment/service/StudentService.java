package com.bullish.class_enrollment.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullish.class_enrollment.data.Course;
import com.bullish.class_enrollment.data.Student;
import com.bullish.class_enrollment.exception.CourseEnrollmentException;
import com.bullish.class_enrollment.repository.StudentRepository;

@Service
public class StudentService {
	private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);

	private final double MAX_CREDITS_PER_SEMESTER = 20.0;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseService courseService;

	/**
	 * Add student to repository
	 * 
	 * @param firstName
	 * @param lastName
	 * @return id for student
	 */
	public Long addStudent(String firstName, String lastName) {
		Student student = new Student(firstName, lastName);
		student = studentRepository.save(student);
		LOG.info("Saved new student: " + firstName + " " + lastName);
		return student.getStudentId();
	}

	/**
	 * Update student in repository
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return id of student
	 * @throws CourseEnrollmentException
	 */
	public Long updateStudent(Long id, String firstName, String lastName) throws CourseEnrollmentException {
		Student student = studentRepository.findOne(id);
		if (student != null) {
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student = studentRepository.save(student);
			return student.getStudentId();
		} else {
			LOG.info("Unable to find student");
			throw new CourseEnrollmentException("Unable to find student");
		}
	}

	/**
	 * Get all students in repository
	 * 
	 * @return list of students
	 */
	public Iterable<Student> getStudents() {
		return studentRepository.findAll();
	}

	/**
	 * Register student with course
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param semesterCode
	 * @throws CourseEnrollmentException
	 */
	public void registerCourse(Long studentId, String courseCode, String semesterCode)
			throws CourseEnrollmentException {
		Student student = studentRepository.findOne(studentId);
		if (student == null) {
			LOG.error("Unable to find student");
			throw new CourseEnrollmentException("Unable to find student. Check that the id is correct.");
		}
		double credits = 0;
		if (student.getCourses() != null) {
			for (Course course : student.getCourses()) {
				if (course.getSemesterCode().equals(semesterCode)) {
					credits = credits + course.getCourseCredits();
				}
			}
		}
		Optional<Course> course = courseService.findCourse(courseCode, semesterCode);
		if (course.isPresent()) {
			Course retrievedCourse = course.get();
			if (retrievedCourse.getCourseCredits() + credits >= MAX_CREDITS_PER_SEMESTER) {
				LOG.warn("Cannot add course. Taking too many credits");
				throw new CourseEnrollmentException("Student is taking to many credits. Cannot add additional course");
			} else {
				student.addCourse(retrievedCourse);
				studentRepository.save(student);
				LOG.info("Added " + courseCode + " for " + student.getFirstName() + " " + student.getLastName());
			}
		} else {
			LOG.warn("Unable to add course. Course cannot be found");
			throw new CourseEnrollmentException("Unable to find course. Check that the provided info is correct");
		}
	}

	/**
	 * Drop course from students schedule
	 * 
	 * @param studentId
	 * @param courseCode
	 * @throws CourseEnrollmentException
	 */
	public void dropCourse(Long studentId, String courseCode) throws CourseEnrollmentException {
		Student student = studentRepository.findOne(studentId);

		if (student == null) {
			throw new CourseEnrollmentException("Unable to find student. Check id");
		}

		if (student.getCourses() != null) {
			Iterator<Course> iterator = student.getCourses().iterator();
			while (iterator.hasNext()) {
				Course course = iterator.next();
				if (course.getCourseCode().equals(courseCode)) {
					LOG.info("Found course to drop");
					iterator.remove();
				}
			}
		}
		studentRepository.save(student);
	}

	/**
	 * Get students enrolled in course
	 * 
	 * @param courseCode
	 * @param semesterCode
	 * @return set of students enrolled in course
	 * @throws CourseEnrollmentException 
	 */
	public Set<Student> getStudentsByCourseCode(String courseCode, String semesterCode) throws CourseEnrollmentException {
		Optional<Course> course = courseService.findCourse(courseCode, semesterCode);
		if (!course.isPresent()) {
			LOG.info("Did not find corresponding course");
			throw new CourseEnrollmentException("Unable to find course. Check search criteria");
		}

		Set<Student> students = course.get().getStudents();
		students.forEach(student -> student.setCourses(null));
		return students;
	}

	/**
	 * Get courses student is enrolled in
	 * 
	 * @param studentId
	 * @param semesterCode
	 * @return list of students enrolled in course
	 * @throws CourseEnrollmentException
	 */
	public Set<Course> getCoursesForStudent(Long studentId, String semesterCode) throws CourseEnrollmentException {
		Set<Course> courses = new HashSet<Course>();
		Student student = studentRepository.findOne(studentId);
		if (student == null) {
			throw new CourseEnrollmentException("Student not found. Check id");
		}

		if ((semesterCode == null || semesterCode.equals("")) && student.getCourses() != null) {
			LOG.info("No search criteria provided, so returning all courses");
			courses = student.getCourses();
		} else {
			if (student.getCourses() != null) {
				for (Course course : student.getCourses()) {
					if (course.getSemesterCode().equals(semesterCode)) {
						courses.add(course);
					}
				}
			}
		}

		return courses;

	}

}
