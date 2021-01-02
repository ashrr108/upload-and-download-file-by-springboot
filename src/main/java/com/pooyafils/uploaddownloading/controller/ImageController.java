package com.pooyafils.uploaddownloading.controller;

import com.pooyafils.uploaddownloading.domain.Image;
import com.pooyafils.uploaddownloading.services.ImageServic;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageServic servic;

    @PostMapping
    ResponseEntity<Image> saveImage(@RequestParam("file") MultipartFile file,
                                    @RequestParam("description") String description) {
        return ResponseEntity.ok(servic.saveImage(file, description));
    }

    @GetMapping
    ResponseEntity<List<Image>> findAllImageUrl() {
        return ResponseEntity.ok(servic.findAllImageUrl());
    }

    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] downloaderImage(@PathVariable String imageName) throws Exception {
        return servic.downloaderImage(imageName);
    }
    @GetMapping("/{id}")
    ResponseEntity<Image> getImageById(@PathVariable int id){
        return ResponseEntity.ok( servic.getImageById(id));
    }
    @PostMapping("/description")
    public ResponseEntity<List<Image>>   uploadMultipleFiles(  @RequestParam("description") String description,
    @RequestParam("file") MultipartFile[] files){
        List<MultipartFile> listfiles=Arrays.asList(files);
        for(int i=0;i<listfiles.size();i++){
            servic.saveImage(listfiles.get(i),description);
        }
        return ResponseEntity.ok(servic.findAllImageUrl());


    }
}
