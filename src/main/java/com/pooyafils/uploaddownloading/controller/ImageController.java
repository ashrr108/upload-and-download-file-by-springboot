package com.pooyafils.uploaddownloading.controller;

import com.pooyafils.uploaddownloading.domain.Image;
import com.pooyafils.uploaddownloading.services.ImageServic;
import jdk.nashorn.internal.ir.ReturnNode;
import jdk.net.SocketFlow;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.rmi.Remote;
import java.util.*;

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
    ResponseEntity<InputStreamResource> getImageById(@PathVariable int id) throws IOException {
       Image image= servic.getImageById(id);
       String address=image.getPath()+image.getName();
       File file=new File(address);
       InputStream inputStream=new FileInputStream(file);
       InputStreamResource a=new InputStreamResource(inputStream);


        HttpHeaders httpHeaders=new HttpHeaders();
      // httpHeaders.put("Content-Disposition", Collections.singletonList("attachmen"+image.getName())); //download link
        httpHeaders.setContentType(MediaType.valueOf("application/pdf"));

       httpHeaders.set("Content-Disposition", "attachment; filename="+image.getName()); // best for download

       return new ResponseEntity<InputStreamResource>(a,httpHeaders,HttpStatus.ACCEPTED);
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
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id){

        servic.delete(id);
        return ResponseEntity.ok("item has been deleted");
    }
}
