package com.example.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nim", unique = true, nullable = false)
	private String nim;
	@Column(name = "namaDepan", nullable = false)
	private String namaDepan;
	@Column(name = "namaBelakang")
	private String namaBelakang;
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggal_lahir;

	//Constructor


	public Student() {
	}

	public Student(String nim, String namaDepan, String namaBelakang, Date tanggal_lahir) {
		this.nim = nim;
		this.namaDepan = namaDepan;
		this.namaBelakang = namaBelakang;
		setTanggal_lahir(tanggal_lahir);
	}


	//Getter and Setter


	public long getId() {
		return id;
	}

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getNamaDepan() {
		return namaDepan;
	}

	public void setNamaDepan(String namaDepan) {
		this.namaDepan = namaDepan;
	}

	public String getNamaBelakang() {
		return namaBelakang;
	}

	public void setNamaBelakang(String namaBelakang) {
		this.namaBelakang = namaBelakang;
	}

	public Date getTanggal_lahir() {
		return tanggal_lahir;
	}

	public void setTanggal_lahir(Date tanggal_lahir) {
		//Check if the date is in "correct" order
		if (tanggal_lahir.after(new Date())){
			tanggal_lahir = new Date();
		}
		this.tanggal_lahir = tanggal_lahir;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", nim='" + nim + '\'' +
				", namaDepan='" + namaDepan + '\'' +
				", namaBelakang='" + namaBelakang + '\'' +
				", tanggal_lahir=" + tanggal_lahir +
				'}';
	}
}
