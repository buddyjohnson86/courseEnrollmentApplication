package com.bullish.class_enrollment.api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.class_enrollment.data.Course;
import com.bullish.class_enrollment.data.Semester;
import com.bullish.class_enrollment.data.Student;
import com.bullish.class_enrollment.exception.CourseEnrollmentException;
import com.bullish.class_enrollment.response.CourseResponse;
import com.bullish.class_enrollment.response.IdResponse;
import com.bullish.class_enrollment.response.SemesterResponse;
import com.bullish.class_enrollment.response.StudentResponse;
import com.bullish.class_enrollment.service.CourseService;
import com.bullish.class_enrollment.service.SemesterService;
import com.bullish.class_enrollment.service.StudentService;

@RestController
public class RestCourseEnrollmentController {

	private final static Logger LOG = LoggerFactory.getLogger(RestCourseEnrollmentController.class);

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SemesterService semesterService;

	/**
	 * API to add new student. Returns the generated unique id for that student.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return unique id for the student
	 */
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public @ResponseBody IdResponse addStudent(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {
		LOG.info("/addStudent endpoint accessed");
		IdResponse idResponse = null;
		try {
			Long studentId = studentService.addStudent(firstName, lastName);
			idResponse = new IdResponse(studentId);
		} catch (DataAccessException ex) {
			LOG.error("Error occurred adding student", ex);
			idResponse = new IdResponse("Error occurred adding student");
		}

		return idResponse;
	}

	/**
	 * API to update student. Returns the unique id for that student
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @return generic response object with success status and error message, if
	 *         applicable.
	 */
	@RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
	public @ResponseBody IdResponse updateStudent(@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		LOG.info("/updateStudent endpoint accessed");
		IdResponse response = null;
		try {
			Long id = studentService.updateStudent(studentId, firstName, lastName);
			response = new IdResponse(id);
		} catch (DataAccessException ex) {
			LOG.error("Error occurred updating student", ex);
			response = new IdResponse("Unable to update student");
		} catch (CourseEnrollmentException e1) {
			LOG.error("Error occurred updating student: " + e1.getMessage());
			response = new IdResponse(e1.getMessage());
		}

		return response;
	}

	/**
	 * API to enroll a student into a course for a particular semester
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param semesterCode
	 * @return generic response object with success status and error message, if
	 *         applicable
	 */
	@RequestMapping(value = "/addStudentToCourse", method = RequestMethod.POST)
	public @ResponseBody IdResponse addStudentToCourse(@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "semesterCode") String semesterCode) {
		LOG.info("/addStudentToCourse endpoint accessed");
		IdResponse idResponse = null;
		try {
			studentService.registerCourse(studentId, courseCode, semesterCode);
			idResponse = new IdResponse(studentId);
		} catch (DataAccessException ex) {
			LOG.error("Error occurred when registering student for course", ex);
			idResponse = new IdResponse("Error adding student to course");
		} catch (CourseEnrollmentException e) {
			LOG.error("Error occurred when registering student for course", e);
			idResponse = new IdResponse(e.getMessage());
		}

		return idResponse;

	}

	/**
	 * API to get the list of classes for a particular student for a semester, or
	 * the fully history of classes enrolled.
	 * 
	 * @param studentId
	 * @param semesterCode
	 * @return corresponding courses that the student is registered for
	 */
	@RequestMapping(value = "/getCoursesForStudent", method = RequestMethod.GET)
	public @ResponseBody CourseResponse getCoursesForStudent(@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "semesterCode", required = false) String semesterCode) {
		LOG.info("/getCoursesForStudent endpoint accessed");
		CourseResponse courseResponse = null;
		try {
			Set<Course> courses = studentService.getCoursesForStudent(studentId, semesterCode);
			courseResponse = new CourseResponse(courses);
		} catch (DataAccessException e1) {
			LOG.error("Error occurred retrieving courses for student", e1);
			courseResponse = new CourseResponse("Error occurred retrieving courses for student");
		} catch (CourseEnrollmentException e2) {
			LOG.error("Error occurred retrieving courses for student", e2);
			courseResponse = new CourseResponse(e2.getMessage());
		}

		return courseResponse;
	}

	/**
	 * API to add course
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param credits
	 * @param semesterCode
	 * @return unique id of course added
	 */
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public IdResponse addCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "courseName") String courseName, @RequestParam(value = "credits") Long credits,
			@RequestParam(value = "semesterCode") String semesterCode) {
		LOG.info("/addCourse endpoint accessed");
		IdResponse idResponse = null;
		try {
			Long courseId = courseService.addCourse(courseCode, courseName, credits, semesterCode);
			idResponse = new IdResponse(courseId);
		} catch (DataAccessException e1) {
			LOG.error("Error occurred adding course", e1);
			idResponse = new IdResponse("Error occurred adding course");
		} catch (CourseEnrollmentException e2) {
			LOG.error("Error occurred adding course: " + e2.getMessage());
			idResponse = new IdResponse(e2.getMessage());
		}

		return idResponse;
	}

	/**
	 * API to create new semester
	 * 
	 * @param semesterCode
	 * @return unique id of semester that was added
	 */
	@RequestMapping(value = "/addSemester", method = RequestMethod.POST)
	public IdResponse addSemester(@RequestParam(value = "semesterCode") String semesterCode) {
		LOG.info("/addSemester endpoint accessed");
		IdResponse idResponse = null;
		try {
			Long semesterId = semesterService.addSemester(semesterCode);
			idResponse = new IdResponse(semesterId);
		} catch (DataAccessException e1) {
			LOG.error("Error occurred adding semester", e1);
			idResponse = new IdResponse("Erorr occurred adding semester");
		}
		return idResponse;
	}

	/**
	 * API to get the list of students enrolled in a course for a particular
	 * semester.
	 * 
	 * @param courseCode
	 * @param semesterCode
	 * @return list of students enrolled in course
	 */
	@RequestMapping(value = "/getStudentsForCourse", method = RequestMethod.GET)
	public @ResponseBody StudentResponse getStudentsForCourse(@RequestParam(value = "courseCode") String courseCode,
			@RequestParam(value = "semesterCode") String semesterCode) {
		LOG.info("/getStudentsForCourse endpoint accessed");
		StudentResponse studentResponse = null;
		try {
			Set<Student> students = courseService.getStudentsForCourse(courseCode, semesterCode);
			studentResponse = new StudentResponse(students);
		} catch (DataAccessException e1) {
			LOG.error("Error occurred retrieving students for course", e1);
			studentResponse = new StudentResponse("Error occurred retrieving students for course");
		} catch (CourseEnrollmentException e2) {
			LOG.error(e2.getMessage());
			studentResponse = new StudentResponse(e2.getMessage());
		}

		return studentResponse;
	}

	/**
	 * API to drop a student from a course.
	 * 
	 * @param studentId
	 * @param courseCode
	 * @return response with success status and error message, if applicable
	 */
	@RequestMapping(value = "/dropCourse", method = RequestMethod.POST)
	public @ResponseBody IdResponse dropCourse(@RequestParam(value = "studentId") Long studentId,
			@RequestParam(value = "courseCode") String courseCode) {
		LOG.info("/dropCourse endpoint accessed");
		IdResponse response = null;
		try {
			studentService.dropCourse(studentId, courseCode);
			response = new IdResponse(studentId);
		} catch (DataAccessException e1) {
			response = new IdResponse("Error occurred dropping course");
		} catch (CourseEnrollmentException e2) {
			response = new IdResponse(e2.getMessage());
		}

		return response;
	}

	/**
	 * API to retrieve all students in the system
	 * 
	 * @return list of all students
	 */
	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
	public @ResponseBody StudentResponse getAllStudents() {
		LOG.info("/getAllStudents endpoint accessed");
		StudentResponse studentResponse = null;
		try {
			Iterable<Student> students = studentService.getStudents();
			studentResponse = new StudentResponse(copyIterable(students));
		} catch (DataAccessException e1) {
			LOG.error("Error retrieving all students", e1);
			studentResponse = new StudentResponse("Error retrieving all students");
		}

		return studentResponse;
	}

	/**
	 * API to retrieve all courses in the system
	 * 
	 * @return list of all courses
	 */
	@RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
	public @ResponseBody CourseResponse getAllCourses() {
		LOG.info("/getAllCourses endpoint accessed");
		CourseResponse courseResponse = null;
		try {
			Iterable<Course> courses = courseService.getAllCourses();
			courseResponse = new CourseResponse(copyIterable(courses));
		} catch (DataAccessException e1) {
			LOG.error("Error retrieving all courses", e1);
			courseResponse = new CourseResponse("Error retrieving all courses");
		}

		return courseResponse;
	}

	/**
	 * API to retrieve all semesters set up in the system
	 * 
	 * @return list of all semesters
	 */
	@RequestMapping(value = "/getAllSemesters", method = RequestMethod.GET)
	public @ResponseBody SemesterResponse getAllSemesters() {
		LOG.info("/getAllSemesters endpoint accessed");
		SemesterResponse semesterResponse = null;
		try {
			Iterable<Semester> semesters = semesterService.getAllSemesters();
			semesterResponse = new SemesterResponse(copyIterable(semesters));
		} catch (DataAccessException e1) {
			LOG.error("Error retrieving all semesters", e1);
			semesterResponse = new SemesterResponse("Error retrieving all semesters");
		}

		return semesterResponse;
	}

	private <T> Set<T> copyIterable(Iterable<T> iterable) {
		Iterator<T> iter = iterable.iterator();
		Set<T> copy = new HashSet<T>();
		while (iter.hasNext()) {
			copy.add(iter.next());
		}

		return copy;
	}

}
