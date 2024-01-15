package com.example.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNamaDepanLike(String namaDepan);
    List<Student> findByNamaBelakangLike(String namaBelakang);
    List<Student> findByNimLike(String nim);
}
