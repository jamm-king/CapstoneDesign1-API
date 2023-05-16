package com.example.demo.controller;

import java.net.URI;

import com.example.demo.dto.base64ImageDTO;
import com.example.demo.dto.imageDTO;
import com.example.demo.dto.urlImageDTO;
import com.example.demo.service.imageService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class imageController {
    @Autowired
    private imageService imageService;

    @PostMapping("/urlImage")
    public imageDTO createImage(@RequestBody urlImageDTO urlImage) throws Exception {
        return imageService.createImage(urlImage);
    }
    @PostMapping("/base64Image")
    public imageDTO createImage(@RequestBody base64ImageDTO base64Image) throws Exception {
        return imageService.createImage(base64Image);
    }

}
