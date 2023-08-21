// Test generated by RoostGPT for test java-sping-test2 using AI Type Vertex AI and AI Model text-bison

package com.pooyafils.uploaddownloading.services;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.repository.MyFileRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FileService.class)
public class FileService_saveImage_5cde7a2848_Test {

    @Value("${upload.path}")
    private String path;

    @Value("${upload.url}")
    private String url;

    @Mock
    private MyFileRepository repository;

    @InjectMocks
    private FileService fileService;

    @Test
    public void testSaveImage_success() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        String description = "test description";

        // when
        MyFile myFile = fileService.saveImage(file, description);

        // then
        assertNotNull(myFile);
        assertEquals(file.getOriginalFilename(), myFile.getName());
        assertEquals(description, myFile.getDescription());
        assertEquals(path, myFile.getPath());
        assertEquals(url + file.getOriginalFilename(), myFile.getUrl());
    }

    @Test
    public void testSaveImage_fileNotFound() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        String description = "test description";

        // when
        when(repository.save(any())).thenThrow(new IOException("File not found"));
        MyFile myFile = fileService.saveImage(file, description);

        // then
        assertNull(myFile);
    }
}
