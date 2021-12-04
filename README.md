# upload-downloading-file
### Application Overview
one of the common feature that applications have is processing  files that api received from client. in this repository we show how we can store and return files by spring-boot
### Development note
1. for this project we use gradle as build tool, so we need to add these dependencies 
```    implementation 'commons-io:commons-io:2.6'
       implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
       implementation 'org.springframework.boot:spring-boot-starter-web'
       implementation 'org.apache.poi:poi:4.1.1'
       implementation 'org.apache.poi:poi-ooxml:4.1.1'
       implementation'fr.opensagres.xdocreport:fr.opensagres.poi.xwpf.converter.pdf:2.0.2'
       compileOnly 'org.projectlombok:lombok'
       runtimeOnly 'mysql:mysql-connector-java'
       annotationProcessor 'org.projectlombok:lombok'
```
2. we need to write the model ``MyFile``for the file that we want to store in the local disc with the essential fields like path,url,etc
3. after creating a repository for ``MyFile`` model we need to write the controller to enable a client to save file (upload) by our application
```
   @PostMapping
    ResponseEntity<MyFile> saveImage(@RequestParam("file") MultipartFile file, // post file
                                     @RequestParam("description") String description) {
        return ResponseEntity.ok(service.saveImage(file, description));
    }
```
now it's a time for set up our service class ``FileService`` to save client file in the local disc
```
    public MyFile saveImage(MultipartFile file, String description) {
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
```
in the try clause we reviled the client file and than write in the place that we want to save client files.after that we save file information such as url,path,description,etc in the database
4. another feature that our application needs to have can be showing client file in the browser suxh as pdf or image files so for this purpose we write the controller as below
```
    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_PNG_VALUE) //show picture but if you change it to MediaType.APPLICATION_PDF_VALUE you can also show pdf file
    public @ResponseBody
    byte[] downloaderImage(@PathVariable String imageName) throws Exception {
        return service.downloaderImage(imageName);
    } 
```
5. there is more features in this application such as how to provide download link for a client, multiple file upload in one http request by the client and more ...