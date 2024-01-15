package com.example.backend.utils;

import com.example.backend.dto.StudentDto;
import com.example.backend.model.Student;

import java.util.Date;

public class StudentConverter {
    public static StudentDto mapToStudentDto(Student student) {
        int Age = findAge(student.getTanggal_lahir());
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getNim(),
                student.getNamaDepan()+" "+student.getNamaBelakang(),
                Age
        );
        return studentDto;
    }

    public static int findAge(Date tanggal_lahir) {
        try {
            Date current = new Date();
            long diff = current.getTime() - tanggal_lahir.getTime();
            int age = (int) Math.floor(diff / (1000L * 60 * 60 * 24 * 365));
            return age;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
