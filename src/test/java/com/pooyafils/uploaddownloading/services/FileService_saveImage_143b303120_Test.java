package com.pooyafils.uploaddownloading.services;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.repository.MyFileRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileService_saveImage_143b303120_Test {

    @InjectMocks
    private FileService fileService;

    @Mock
    private MyFileRepository repository;

    private String path = "/images/";
    private String url = "http://localhost:8080/images/";
    private String description = "Test Image";
    private String originalFileName = "test.jpg";

    @BeforeEach
    public void setUp() {
        fileService = new FileService();
    }

    @Test
    public void testSaveImage() throws IOException {
        MultipartFile file = new MockMultipartFile("test", originalFileName, "image/jpg", "test image content".getBytes());

        MyFile expectedFile = new MyFile();
        expectedFile.setName(originalFileName);
        expectedFile.setDescription(description);
        expectedFile.setPath(path);
        expectedFile.setUrl(url + originalFileName);

        when(repository.save(any(MyFile.class))).thenReturn(expectedFile);

        MyFile actualFile = fileService.saveImage(file, description);

        verify(repository, times(1)).save(any(MyFile.class));

        assertEquals(expectedFile, actualFile);
    }

    @Test
    public void testSaveImage_IOException() throws IOException {
        MultipartFile file = new MockMultipartFile("test", originalFileName, "image/jpg", "test image content".getBytes());

        doThrow(IOException.class).when(file).getBytes();

        MyFile actualFile = fileService.saveImage(file, description);

        verify(repository, times(0)).save(any(MyFile.class));

        assertEquals(null, actualFile);
    }
}
