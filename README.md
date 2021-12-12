
# Class Enrollment Application

This application allows an admin to add students, courses, semesters, enroll students in courses, drop courses, and retrieve course and student information.

## Setting up the application


### Prerequisites

- Java 8 needs to be installed.
- Clone this project onto the system you want to run from. This will require GIT
- On a windows machine, you will want to make sure you have an IDE installed that you can launch the project from
- Should be able to run curl commands

### Building and Running Application
- Run the following commands in a Linux Environment to build and run the application:
```
$ cd student-course-registration-api
$ mvn clean install 
$ mvn spring-boot:run
```
- If in a windows environment, you will need to setup the project in an IDE, such as eclipse, and run the application from there.
- Open another terminal or application to inject curl commands

##Details about implementation:
- Java 8
- Spring Boot
- H2 In-Memory DB

## Testing API
- test_commands.sh contains a set of test cases that setup the system with test data and perform actions like add course and retrieve students/courses
- Supported APIs are 
### /addStudent
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
firstName - First name of student
lastName - Last name of student
```
- The response object is: IdResponse

### /updateStudent
- This api allows the admin to update the first/last name of a student in the system
- The allowed parameters are:
```
studentId - Unique id for student
firstName - First name of student
lastName - Last name of student
```
- The response object is: IdResponse

### /addStudentToCourse
- This api allows the admin to enroll a student in the course
- The allowed parameters are:
```
studentId - Unique id of student
courseCode - Code for course that student is signing up for
semesterCode - Code for semester that course is part of
```
- The response object is: IdResponse

### /getCoursesForStudent
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
studentId - Unique id of student
semesterCode (optional) - Code for semester that applicable courses should be returned for
```
- The response object is: CourseResponse

### /addCourse
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
courseCode - Code for course
courseName - Name of course
credits - Number of credits the course provides
semesterCode - Code of semester the course will be available in
```
- The response object is: IdResponse

### /addSemester
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
semesterCode - Unique code of semester
```
- The response object is: IdResponse

### /getStudentsForCourse
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
courseCode - Code of course
semesterCode - Code for semester that course is in
```
- The response object is: StudentResponse

### /dropCourse
- This api allows the admin to add a student to the system
- The allowed parameters are:
```
studentId - Unique Id for student
courseCode - Code of course to be dropped from student's schedule
```
- The response object is: IdResponse

### /getAllStudents
- This api allows the admin retrieve all students in the system
- The response object is: StudentResponse

### /getAllCourses
- This api allows the admin to retrieve all courses in the system
- The response object is: CourseResponse

### /getAllSemesters
- This api allows the admin to retrieve all semesters in the system
- The response object is: SemesterResponse

### Response Objects
-The IdResponse object has the following fields:
```
isSuccess - success status of corresponding request
errorMessage - message detailing error, if applicable
id - Id of object that was added or updated
```

- The StudentResponse object has the following fields:
```
isSuccess - success status of corresponding request
errorMessage - message detailing error, if applicable
students - List of students pertaining to the request
```

-The CourseResponse object has the following fields:
```
isSuccess - success status of corresponding request
errorMessage - message detailing error, if applicable
courses - List of courses pertaining to the request
```

-The SemesterResponse object has the following fields:
```
isSuccess - success status of corresponding request
errorMessage - message detailing error, if applicable
semesters - List of semesters pertaining to the request
```

## Developer

* Amit Penmetcha

## Resources

* [Spring Boot Rest API](https://spring.io/guides/gs/rest-service/)
