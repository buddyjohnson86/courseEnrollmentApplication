package com.bullish.class_enrollment.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = -2495524129514554243L;

	@Id
	@Column(name = "student_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> courses;

	/**
	 * Default constructor
	 */
	public Student() {

	}

	/**
	 * Constructor with args
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Getter for student id
	 * 
	 * @return student id
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * Getter for first name
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for first name
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for last name
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for last name
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for courses student is enrolled in
	 * 
	 * @return courses student is enrolled in
	 */
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * Setter for courses
	 * 
	 * @param courses
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	/**
	 * Add course to list of courses
	 * 
	 * @param course
	 */
	public void addCourse(Course course) {
		if (courses == null) {
			courses = new HashSet<Course>();
		}
		courses.add(course);
	}

	/**
	 * hashcode function
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentId != null) ? studentId.hashCode() : 0);
		result = prime * result + ((firstName != null) ? firstName.hashCode() : 0);
		result = prime * result + ((lastName != null) ? lastName.hashCode() : 0);
		return result;
	}

	/**
	 * 
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

		Student o2Student = (Student) o2;
		if (studentId == null && o2Student.studentId != null) {
			return false;
		} else if (!studentId.equals(o2Student.studentId)) {
			return false;
		}

		if (firstName == null && o2Student.firstName != null) {
			return false;
		} else if (!firstName.equals(o2Student.firstName)) {
			return false;
		}

		if (lastName == null && o2Student.lastName != null) {
			return false;
		} else if (!lastName.equals(o2Student.lastName)) {
			return false;
		}
		
		return true;
	}
}