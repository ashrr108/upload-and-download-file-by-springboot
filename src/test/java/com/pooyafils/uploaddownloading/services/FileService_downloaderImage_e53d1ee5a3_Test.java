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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileService_downloaderImage_e53d1ee5a3_Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService_downloaderImage_e53d1ee5a3_Test.class);

    @Mock
    private MyFileRepository myFileRepository;

    @InjectMocks
    private FileService fileService;

    @Value("${upload.path}")
    private String path;

    @Test
    public void downloaderImage_success() throws Exception {
        // given
        String imageName = "test.jpg";
        byte[] expected = IOUtils.toByteArray(new FileInputStream(path + imageName));

        // when
        byte[] actual = fileService.downloaderImage(imageName);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void downloaderImage_notFound() throws Exception {
        // given
        String imageName = "not_found.jpg";

        // when
        byte[] actual = fileService.downloaderImage(imageName);

        // then
        assertEquals(null, actual);
    }
}
