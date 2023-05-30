package com.example.demo.controller;

import com.example.demo.dto.base64ImageDTO;
import com.example.demo.dto.imageDTO;
import com.example.demo.dto.urlImageDTO;
import com.example.demo.service.imageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class imageController {
    @Autowired
    private imageService imageService;

    @PostMapping("/urlImage")
    public ResponseEntity<imageDTO> createImage(@RequestHeader HttpHeaders requestHeader, @RequestBody urlImageDTO urlImage) throws Exception {
        imageService.createImage(urlImage);
        imageDTO responseBody = imageService.readImageByContent(urlImage.getUrl());
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Access-Control-Allow-Origin", requestHeader.getOrigin());
        return new ResponseEntity<imageDTO>(responseBody, responseHeader, HttpStatus.OK);
    }
    @PostMapping("/base64Image")
    public ResponseEntity<imageDTO> createImage(@RequestHeader HttpHeaders requestHeader, @RequestBody base64ImageDTO base64Image) throws Exception {
        imageService.createImage(base64Image);
        imageDTO responseBody = imageService.readImageByContent(base64Image.getBase64String());
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Access-Control-Allow-Origin", requestHeader.getOrigin());
        return new ResponseEntity<imageDTO>(responseBody, responseHeader, HttpStatus.OK);
    }
}
