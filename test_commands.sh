#!/bin/bash

#
# Add students
#
curl -X POST localhost:8080/addStudent -d "firstName=John&lastName=Doe"
curl -X POST localhost:8080/addStudent -d "firstName=Jane&lastName=Doe"
curl -X POST localhost:8080/addStudent -d "firstName=John&lastName=Buck"
curl -X POST localhost:8080/addStudent -d "firstName=Jake&lastName=Buck"
curl localhost:8080/getAllStudents

#
# Add semester
#
curl -X POST localhost:8080/addSemester -d "semesterCode=F2021"
curl -X POST localhost:8080/addSemester -d "semesterCode=W2022"
curl -X POST localhost:8080/addSemester -d "semesterCode=S2022"
curl localhost:8080/getAllSemesters

#
# Add courses
#
curl -X POST localhost:8080/addCourse -d "courseCode=CS101&courseName=ComputerScience&credits=4&semesterCode=F2021"
curl -X POST localhost:8080/addCourse -d "courseCode=CS101&courseName=ComputerScience&credits=4&semesterCode=W2022"
curl -X POST localhost:8080/addCourse -d "courseCode=CS101&courseName=ComputerScience&credits=4&semesterCode=S2022"
curl -X POST localhost:8080/addCourse -d "courseCode=PHY101&courseName=Physics&credits=3&semesterCode=F2021"
curl -X POST localhost:8080/addCourse -d "courseCode=PHY101&courseName=Physics&credits=3&semesterCode=W2022"
curl -X POST localhost:8080/addCourse -d "courseCode=PHY101&courseName=Physics&credits=3&semesterCode=S2022"
curl -X POST localhost:8080/addCourse -d "courseCode=MATH101&courseName=Math&credits=3&semesterCode=F2021"
curl -X POST localhost:8080/addCourse -d "courseCode=MATH101&courseName=Math&credits=3&semesterCode=W2022"
curl -X POST localhost:8080/addCourse -d "courseCode=MATH101&courseName=Math&credits=3&semesterCode=S2022"
curl -X POST localhost:8080/addCourse -d "courseCode=ECON101&courseName=Economics&credits=3&semesterCode=F2021"
curl -X POST localhost:8080/addCourse -d "courseCode=ECON101&courseName=Economics&credits=3&semesterCode=W2022"
curl -X POST localhost:8080/addCourse -d "courseCode=ECON101&courseName=Economics&credits=3&semesterCode=S2022"
curl localhost:8080/getAllCourses

#
# Enroll student in course
#
curl -X POST localhost:8080/addStudentToCourse -d "studentId=1&courseCode=CS101&semesterCode=F2021"
curl -X POST localhost:8080/addStudentToCourse -d "studentId=1&courseCode=PHY101&semesterCode=F2021"
curl -X POST localhost:8080/addStudentToCourse -d "studentId=1&courseCode=MATH101&semesterCode=F2021"
curl -X POST localhost:8080/addStudentToCourse -d "studentId=1&courseCode=ECON101&semesterCode=F2021"
curl -X POST localhost:8080/addStudentToCourse -d "studentId=1&courseCode=ECON101&semesterCode=S2022"

#
# Get courses for student
#
curl localhost:8080/getCoursesForStudent?studentId=1
curl localhost:8080/getCoursesForStudent?studentId=1&semesterCode=S2022
curl localhost:8080/getCoursesForStudent?studentId=1&semesterCode=F2021
