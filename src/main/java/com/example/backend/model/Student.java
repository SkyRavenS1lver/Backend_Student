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
	@Column(name = "nama_depan", nullable = false)
	private String nama_depan;
	@Column(name = "nama_belakang")
	private String nama_belakang;
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tanggal_lahir;

	//Constructor


	public Student() {
	}


	public Student(String nim, String nama_depan, String nama_belakang, Date tanggal_lahir) {
		this.nim = nim;
		this.nama_depan = nama_depan;
		this.nama_belakang = nama_belakang;
		this.tanggal_lahir = tanggal_lahir;
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

	public String getNama_depan() {
		return nama_depan;
	}

	public void setNama_depan(String nama_depan) {
		this.nama_depan = nama_depan;
	}

	public String getNama_belakang() {
		return nama_belakang;
	}

	public void setNama_belakang(String nama_belakang) {
		this.nama_belakang = nama_belakang;
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

	// Method to determine age
	private long findAge(Date birth) {
		try {
			Date current = new Date();
			long diff = current.getTime() - birth.getTime();
			long age = (diff / (1000L * 60 * 60 * 24 * 365));
			return age;
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}


	@Override
	public String toString() {
		return "Nomor Induk Mahasiswa = " + id +
				",Nama Lengkap = " + nama_depan +" "+ nama_belakang +
				",Usia = " + findAge(getTanggal_lahir());
	}

}
