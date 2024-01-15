package com.example.backend.service.impl;

import com.example.backend.dto.StudentDto;
import com.example.backend.entity.Response;
import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import com.example.backend.service.StudentService;
import com.example.backend.utils.StudentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentConverter::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public void insertStudent(Student student) {
            studentRepository.save(student);
    }

    @Override
    public ResponseEntity<Response> updateStudent(Student student) {
        Optional<Student> data = studentRepository.findById(student.getId());
        if (data.isPresent()){
            try{
                Student studentUpdate = data.get();
                studentUpdate.setNim(student.getNim());
                studentUpdate.setNamaDepan(student.getNamaDepan());
                studentUpdate.setNamaBelakang(student.getNamaBelakang());
                studentUpdate.setTanggal_lahir(student.getTanggal_lahir());
                studentRepository.save(student);
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

    @Override
    public ResponseEntity<Response> deleteStudent(long id) {
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
