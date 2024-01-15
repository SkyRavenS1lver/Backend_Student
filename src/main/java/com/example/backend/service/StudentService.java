package com.example.backend.service;

import com.example.backend.dto.StudentDto;
import com.example.backend.entity.Response;
import com.example.backend.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudent();
    void insertStudent(Student student);
    ResponseEntity<Response> updateStudent(Student student);
    ResponseEntity<Response> deleteStudent(long id);
}
