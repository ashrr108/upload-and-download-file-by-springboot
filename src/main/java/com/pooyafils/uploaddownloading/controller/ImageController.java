package com.pooyafils.uploaddownloading.controller;

import com.pooyafils.uploaddownloading.domain.MyFile;
import com.pooyafils.uploaddownloading.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    private FileService service;

    @PostMapping
    ResponseEntity<MyFile> saveImage(@RequestParam("file") MultipartFile file, // post file
                                     @RequestParam("description") String description) {
        return ResponseEntity.ok(service.saveImage(file, description));
    }

    @GetMapping
    ResponseEntity<List<MyFile>> findAllImageUrl() { //show all file info

        return ResponseEntity.ok(service.findAllImageUrl());
    }

    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_PNG_VALUE) //show picture but if you change it to MediaType.APPLICATION_PDF_VALUE you can also show pdf file
    public @ResponseBody
    byte[] downloaderImage(@PathVariable String imageName) throws Exception {
        return service.downloaderImage(imageName);
    }

    @GetMapping("/{id}")
    ResponseEntity<InputStreamResource> getImageById(@PathVariable int id) throws IOException { //download file
        MyFile myFile = service.getImageById(id);
        String address = myFile.getPath() + myFile.getName();
        File file = new File(address);
        InputStream inputStream = new FileInputStream(file);
        InputStreamResource a = new InputStreamResource(inputStream);


        HttpHeaders httpHeaders = new HttpHeaders();
        // httpHeaders.put("Content-Disposition", Collections.singletonList("attachmen"+image.getName())); //download link
        httpHeaders.setContentType(MediaType.valueOf("application/pdf"));

        httpHeaders.set("Content-Disposition", "attachment; filename=" + myFile.getName()); // best for download

        return new ResponseEntity<InputStreamResource>(a, httpHeaders, HttpStatus.ACCEPTED);
    }

    @PostMapping("/description") // posting more than one file once
    public ResponseEntity<List<MyFile>> uploadMultipleFiles(@RequestParam("description") String description,
                                                            @RequestParam("file") MultipartFile[] files) {
        List<MultipartFile> listfiles = Arrays.asList(files);
        for (int i = 0; i < listfiles.size(); i++) {
            service.saveImage(listfiles.get(i), description);
        }
        return ResponseEntity.ok(service.findAllImageUrl());


    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) { //delete file

        service.delete(id);
        return ResponseEntity.ok("item has been deleted");
    }
}
