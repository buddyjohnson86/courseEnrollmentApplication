package com.bullish.class_enrollment.repository;
import org.springframework.data.repository.CrudRepository;

import com.bullish.class_enrollment.data.Student;

/**
 * Use springframework's implementation of a repository
 */
public interface StudentRepository extends CrudRepository<Student, Long> {

}