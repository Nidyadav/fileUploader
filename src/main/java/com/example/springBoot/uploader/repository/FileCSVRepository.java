package com.example.springBoot.uploader.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBoot.uploader.model.FileCSV;

public interface FileCSVRepository extends JpaRepository<FileCSV,Long>{
	 Optional<FileCSV> findByCode(String code);
}
