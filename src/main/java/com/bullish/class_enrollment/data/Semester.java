package com.bullish.class_enrollment.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author apenmetcha
 *
 */
@Entity
public class Semester {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "semester_id", unique = true, nullable = false)
	private Long semesterId;

	@Column(name = "semester_code", unique = true, nullable = false)
	private String semesterCode;

	/**
	 * Default constructor
	 */
	public Semester() {

	}

	/**
	 * Constructor with args
	 * 
	 * @param semesterCode
	 */
	public Semester(String semesterCode) {
		this.semesterCode = semesterCode;
	}

	/**
	 * Getter for semester id
	 * 
	 * @return semester id
	 */
	public Long getSemesterId() {
		return semesterId;
	}

	/**
	 * Setter for semester id
	 * 
	 * @param semesterId
	 */
	public void setSemesterId(Long semesterId) {
		this.semesterId = semesterId;
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
}