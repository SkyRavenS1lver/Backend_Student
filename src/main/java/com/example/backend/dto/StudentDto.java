package com.example.backend.dto;

public class StudentDto {
    private long id;
    private String nim;
    private String nama;
    private int Age;

    public StudentDto(long id, String nim, String nama, int age) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        Age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
