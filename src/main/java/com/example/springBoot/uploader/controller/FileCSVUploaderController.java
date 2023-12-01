package com.example.springBoot.uploader.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springBoot.uploader.model.FileCSV;
import com.example.springBoot.uploader.repository.FileCSVRepository;

@RestController
@RequestMapping("/")
public class FileCSVUploaderController {

	@Autowired
	FileCSVRepository repository;

	@PostMapping("upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			List<FileCSV> entities = parseCSV(file);
			repository.saveAll(entities);
			return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading the file.");
		}
	}

	@GetMapping("fetch-all")
	public List<FileCSV> fetchAllData() {
		return repository.findAll();
	}

	@GetMapping("fetchByCode/{code}")
	public ResponseEntity<FileCSV> getDataByCode(@PathVariable String code) {
		return repository.findByCode(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("delete-all")
	public ResponseEntity<String> deleteAllData() {
		repository.deleteAll();
		return ResponseEntity.ok("All data deleted successfully!");
	}

	private List<FileCSV> parseCSV(MultipartFile file) throws IOException {
		List<FileCSV> entities = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			reader.lines().skip(1).forEach(line -> {
				String[] values = line.split(",");
				FileCSV entity = new FileCSV();
				// entity.setId(Long.parseLong(values[0]));
				entity.setSource(values[0]);
				entity.setCodeListCode(values[1]);
				entity.setCode(values[2]);
				entity.setDisplayValue(values[3]);
				entity.setLongDescription(values[4]);
				entity.setFromDate(values[5]);
				entity.setToDate(values[6]);
				entity.setSortingPriority(values[7]);
				entities.add(entity);
			});
		}
		return entities;
	}
}
