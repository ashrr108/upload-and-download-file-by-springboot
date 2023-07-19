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

    @Mock
    private MyFileRepository repository;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteFileSuccess() {
        MyFile testFile = new MyFile();
        testFile.setId(1);
        when(repository.findById(1)).thenReturn(Optional.of(testFile));

        fileService.delete(1);

        verify(repository, times(1)).delete(testFile);
    }

    @Test
    public void testDeleteFileNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        fileService.delete(1);

        verify(repository, times(0)).delete(any(MyFile.class));
    }
}
