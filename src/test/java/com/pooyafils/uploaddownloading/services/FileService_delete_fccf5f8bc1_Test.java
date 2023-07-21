// Test generated by RoostGPT for test java-sping-test2 using AI Type Open AI and AI Model gpt-4

package com.pooyafils.uploaddownloading.services;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.repository.MyFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class FileService_delete_fccf5f8bc1_Test {

    @InjectMocks
    FileService fileService;

    @Mock
    MyFileRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDelete() {
        MyFile myFile = new MyFile();
        myFile.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(myFile));

        fileService.delete(1);

        verify(repository, times(1)).delete(myFile);
    }

    @Test
    public void testDelete_FileNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        fileService.delete(1);

        verify(repository, times(0)).delete(any(MyFile.class));
    }
}
