package com.example.demo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        String filePath = System.getProperty("user.dir") + "/Uploads" + File.separator + file.getOriginalFilename();
        String fileUploadStatus;

        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            fout.write(file.getBytes());
            fout.close();
            fileUploadStatus = "File Uploaded Successfully";
        } catch (Exception e){
            e.printStackTrace();
            fileUploadStatus = "Error in uploading file: "+e;
        }
        return fileUploadStatus;
    }

    @GetMapping("/getFiles")
    public String[] getFiles(){
        String folderPath = System.getProperty("user.dir") + "/Uploads";
        File directory = new File(folderPath);
        String[] filenames = directory.list();
        return filenames;
    }

    @GetMapping("/download/{path:.+}")
    public ResponseEntity dounloadFile(@PathVariable("path") String filename) throws FileNotFoundException{
        String fileUploadpath = System.getProperty("downloads.dir") +"/Uploads";
        String[] filenames = this.getFiles();
        boolean contains = Arrays.asList(filenames).contains(filename);
        if(!contains) {
            return new ResponseEntity("FIle Not Found", HttpStatus.NOT_FOUND);
        }

        // Setting up the filepath
        String filePath = fileUploadpath+File.separator+filename;

        // Creating new file instance
        File file= new File(filePath);

        // Creating a new InputStreamResource object
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // Creating a new instance of HttpHeaders Object
        HttpHeaders headers = new HttpHeaders();

        // Setting up values for contentType and headerValue
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }
}

