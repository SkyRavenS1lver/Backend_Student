package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.backend.dto.StudentDto;
import com.example.backend.entity.Response;
import com.example.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Student;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class StudentController {
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public ResponseEntity<Response> allStudent(){
		List<StudentDto> data = studentService.getAllStudent();
		if (data.isEmpty()){
			return new ResponseEntity<>(
						new Response(true, "No Data!"),
						HttpStatus.NO_CONTENT
				);
		}
		return new ResponseEntity<>(
					new Response(true, "Get Data Success", data),
					HttpStatus.OK
			);
	}
	@PostMapping("/")
	public ResponseEntity<Response> insertStudent(
			@RequestBody Student student
	){
		try {
			studentService.insertStudent(student);
			return new ResponseEntity<>(
					new Response(true, "Student Added Successfully!"),
					HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(
					new Response(false, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	public ResponseEntity<Response> updateStudent(
			@RequestBody Student student
	){
			return studentService.updateStudent(student);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteStudent(
			@PathVariable long id
	){
		return studentService.deleteStudent(id);
	}

}
