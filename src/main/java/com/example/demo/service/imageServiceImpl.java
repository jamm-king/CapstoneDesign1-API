package com.example.demo.service;

import com.example.demo.dto.base64ImageDTO;
import com.example.demo.dto.imageDTO;
import com.example.demo.dto.urlImageDTO;
import com.example.demo.mapper.imageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class imageServiceImpl implements imageService {
    @Autowired
    private imageMapper imageMapper;
    @Autowired
    azureCVService azureCVService;
    @Value("${azure.computer-vision.key}")
    String key;
    @Value("${azure.computer-vision.endpoint}")
    String endpoint;

    @Override
    public imageDTO createImage(urlImageDTO urlImage) throws Exception {
        imageDTO image = new imageDTO();
        String captionText = azureCVService.CaptionImageV4(urlImage);
        String ocrText = azureCVService.OCRImageV4(urlImage);
        image.setType("text");
        image.setContent(urlImage.getUrl());
        image.setAltText(captionText);
        imageMapper.createImage(image);
        return image;
    }
    @Override
    public imageDTO createImage(base64ImageDTO base64Image) throws Exception {
        imageDTO image = new imageDTO();
        imageMapper.createImage(image);
        return image;
    }
}
