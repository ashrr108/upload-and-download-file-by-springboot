package com.pooyafils.uploaddownloading.services;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.repository.MyFileRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {

    @Value("${image.path}")
    private String path;
    @Value("${image.url}")
    private String url;
    @Autowired
    private MyFileRepository repository;
    Logger logger = LoggerFactory.getLogger(FileService.class);

    public MyFile saveImage(MultipartFile file, String description) {
        logger.info("============saving a picture================");

        try {

            byte[] bytes = file.getBytes();

            Path pathImage = Paths.get(path + file.getOriginalFilename());
            Files.write(pathImage, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.getOriginalFilename();
        MyFile myFile = MyFile.builder()
                .name(file.getOriginalFilename())
                .description(description)
                .path(path)
                .url(url + file.getOriginalFilename())
                .build();
        repository.save(myFile);
        return myFile;
    }

    public List<MyFile> findAllImageUrl() {
        return repository.findAll();
    }

    public byte[] downloaderImage(String imageName) throws Exception {
        InputStream in = new FileInputStream(path + imageName);
        return IOUtils.toByteArray(in);
    }

    public MyFile getImageById(int id) {
        return repository.findById(id);
    }


    public void delete(int id) {
        MyFile deleteMyFile = repository.findById(id);
        repository.delete(deleteMyFile);
    }
}
