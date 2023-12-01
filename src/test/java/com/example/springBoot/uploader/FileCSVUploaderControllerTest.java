package com.example.springBoot.uploader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springBoot.uploader.model.FileCSV;
import com.example.springBoot.uploader.repository.FileCSVRepository;

@WebMvcTest
class FileCSVUploaderControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	FileCSVRepository fileRepository;

	@Test
	    public void shouldReturnNullOnFetchAllIfNoData() throws Exception {
	       
	        when(fileRepository.findAll()).thenReturn(Collections.emptyList());

	    
	        mockMvc.perform(MockMvcRequestBuilders.get("/fetch-all"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
	    }

	@Test
	void shouldReturnAllDataOnFetchAllDataIfPresent() throws Exception {
		FileCSV entity1 = new FileCSV();
		entity1.setCode("CODE1");
		entity1.setLongDescription("mockeddata1");

		FileCSV entity2 = new FileCSV();
		entity2.setCode("CODE2");
		entity2.setLongDescription("mockeddata2");

		List<FileCSV> mockEntities = List.of(entity1, entity2);

		when(fileRepository.findAll()).thenReturn(mockEntities);

		mockMvc.perform(MockMvcRequestBuilders.get("/fetch-all")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

	}

	@Test
	public void testFileUpload() throws Exception {

		InputStream inputStream = getClass().getResourceAsStream("/test.csv");
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", inputStream);

		mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(file))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("File uploaded successfully!"));

	}
	 @Test
	    public void testDeleteAllData() throws Exception {
	        // Perform the request to delete all data
	        mockMvc.perform(MockMvcRequestBuilders.delete("/delete-all"))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(MockMvcResultMatchers.content().string("All data deleted successfully!"))
	                ;

	        
	        verify(fileRepository).deleteAll();
	    }
}
