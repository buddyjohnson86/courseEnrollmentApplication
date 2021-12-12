package com.bullish.class_enrollment.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UniqueCourseCodeAndSemesterCode", columnNames = { "course_code", "semester_code" }) })
public class Course implements Serializable {

	private static final long serialVersionUID = -8494992158673312751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", unique = true, nullable = false)
	private Long courseId;

	@Column(name = "course_code")
	private String courseCode;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "course_credits")
	private Long courseCredits;

	@Column(name = "semester_code")
	private String semesterCode;

	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;

	/**
	 * Generic constructor
	 */
	public Course() {

	}

	/**
	 * Constructor with arguments
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param courseCredits
	 * @param semesterCode
	 */
	public Course(String courseCode, String courseName, Long courseCredits, String semesterCode) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseCredits = courseCredits;
		this.semesterCode = semesterCode;
	}

	/**
	 * Getter for course id
	 * 
	 * @return course id
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * Getter for course code
	 * 
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Getter for course name
	 * 
	 * @return course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Setter for course name
	 * 
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Getter for course credits
	 * 
	 * @return course credits
	 */
	public Long getCourseCredits() {
		return courseCredits;
	}

	/**
	 * Setter for course credits
	 * 
	 * @param courseCredits
	 */
	public void setCourseCredits(Long courseCredits) {
		this.courseCredits = courseCredits;
	}

	/**
	 * Getter for semester code
	 * 
	 * @return semester code
	 */
	public String getSemesterCode() {
		return semesterCode;
	}

	/**
	 * Setter for semester code
	 * 
	 * @param semesterCode
	 */
	public void setSemesterCode(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * Getter for students enrolled in course
	 * 
	 * @return set of students enrolled in course
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * Setter for students
	 * 
	 * @param students
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	/**
	 * Add student to set
	 * 
	 * @param student
	 * @return true if student was added
	 */
	public boolean addStudent(Student student) {
		return students.add(student);
	}

	/**
	 * Override hashcode function
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId != null) ? courseId.hashCode() : 0);
		result = prime * result + ((courseCode != null) ? courseCode.hashCode() : 0);
		result = prime * result + ((courseName != null) ? courseName.hashCode() : 0);
		result = prime * result + ((courseCredits != null) ? courseCredits.hashCode() : 0);
		result = prime * result + ((semesterCode != null) ? semesterCode.hashCode() : 0);
		return result;
	}

	/**
	 * Override equals function
	 */
	@Override
	public boolean equals(Object o2) {
		if (this == o2) {
			return true;
		}

		if (o2 == null) {
			return false;
		}

		if (getClass() != o2.getClass()) {
			return false;
		}

		Course o2Course = (Course) o2;

		if (courseId == null && o2Course.courseId != null) {
			return false;
		} else if (!courseId.equals(o2Course.courseId)) {
			return false;
		}

		if (courseCode == null && o2Course.courseCode != null) {
			return false;
		} else if (!courseCode.equals(o2Course.courseCode)) {
			return false;
		}

		if (courseName == null && o2Course.courseName != null) {
			return false;
		} else if (!courseName.equals(o2Course.courseName)) {
			return false;
		}

		if (courseCredits == null && o2Course.courseCredits != null) {
			return false;
		} else if (!courseCredits.equals(o2Course.courseCredits)) {
			return false;
		}

		if (semesterCode == null && o2Course.semesterCode != null) {
			return false;
		} else if (!semesterCode.equals(o2Course.semesterCode)) {
			return false;
		}

		return true;
	}
}