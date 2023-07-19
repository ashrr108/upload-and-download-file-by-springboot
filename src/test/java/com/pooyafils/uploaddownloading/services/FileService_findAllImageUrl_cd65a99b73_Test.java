package com.pooyafils.uploaddownloading.services;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.repository.MyFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;


public class FileService_findAllImageUrl_cd65a99b73_Test {

    @InjectMocks
    private FileService fileService;

    @Mock
    private MyFileRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllImageUrl() {
        MyFile file1 = new MyFile();
        file1.setUrl("http://example.com/image1.jpg");

        MyFile file2 = new MyFile();
        file2.setUrl("http://example.com/image2.jpg");

        when(repository.findAll()).thenReturn(Arrays.asList(file1, file2));

        List<MyFile> result = fileService.findAllImageUrl();

        assertEquals(2, result.size());
        assertEquals("http://example.com/image1.jpg", result.get(0).getUrl());
        assertEquals("http://example.com/image2.jpg", result.get(1).getUrl());
    }

    @Test
    public void testFindAllImageUrl_empty() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<MyFile> result = fileService.findAllImageUrl();

        assertEquals(0, result.size());
    }
}
