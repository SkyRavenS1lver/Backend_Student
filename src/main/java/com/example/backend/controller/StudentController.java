package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.backend.entity.Response;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Student;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/")
	public ResponseEntity<Response> getAllStudents(
			@RequestParam(required = false) String name
	){
		try {
			List<Student> data = new ArrayList<>();
			if (name == null)
				data.addAll(studentRepository.findAll());
			if (data.isEmpty())
				return new ResponseEntity<>(
						new Response(false, "No Data!"),
						HttpStatus.NO_CONTENT
				);
			return new ResponseEntity<>(
					new Response(true, "Get Data Success", data),
					HttpStatus.OK
			);
		}catch (Exception e){
			return new ResponseEntity<>(
					new Response(false, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	public ResponseEntity<Response> insertStudent(
			@RequestBody Student student
	){
		try {
			Student studentSave = new Student(
					student.getNim(),
					student.getNama_depan(),
					student.getNama_belakang(),
					student.getTanggal_lahir()
			);
			studentRepository.save(
					studentSave
			);
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
		Optional<Student> data = studentRepository.findById(student.getId());
		if (data.isPresent()){
			try{
				Student studentUpdate = data.get();
				studentUpdate.setNim(student.getNim());
				studentUpdate.setNama_depan(student.getNama_depan());
				studentUpdate.setNama_belakang(student.getNama_belakang());
				studentUpdate.setTanggal_lahir(student.getTanggal_lahir());
				studentRepository.save(studentUpdate);
				return new ResponseEntity<>(
						new Response(true, "Student Updated Successfully!"),
						HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(
						new Response(false, e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>(
					new Response(false, "Data Not Found!"),
					HttpStatus.NOT_FOUND
			);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteStudent(
			@PathVariable long id
	){
		try {
			Optional<Student> data = studentRepository.findById(id);
			if (data.isPresent()){
				studentRepository.deleteById(id);
				return new ResponseEntity<>(
						new Response(true, "Student Deleted Successfully!"),
						HttpStatus.OK
				);
			}
			else {
				return new ResponseEntity<>(
						new Response(false, "Data Not Found!"),
						HttpStatus.NOT_FOUND
				);
			}

		}catch (Exception e){
			return new ResponseEntity<>(
					new Response(false, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}

}
