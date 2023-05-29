package com.example.demo.service;

import com.example.demo.dto.base64ImageDTO;
import com.example.demo.dto.imageDTO;
import com.example.demo.dto.urlImageDTO;
import com.example.demo.mapper.imageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Math.min;

@Service
public class imageServiceImpl implements imageService {
    @Autowired
    private imageMapper imageMapper;
    @Autowired
    azureCVService azureCVService;
    @Autowired
    papagoService papagoService;
    @Autowired
    base64Service base64Service;
    @Value("${azure.computer-vision.key}")
    String key;
    @Value("${azure.computer-vision.endpoint}")
    String endpoint;

    @Override
    @Transactional
    public void createImage(urlImageDTO urlImage) throws Exception {
        String url = urlImage.getUrl();
        String content = url.substring(0, min(200, url.length()));

        imageDTO image = readImageByContent(content);
        if(image != null) return;

        image = new imageDTO();
        String text = azureCVService.captionImageV4(urlImage);
        if(text == "text") {
            text = azureCVService.ocrImageV4(urlImage);
            image.setType("text");
        }
        else {
            text = papagoService.translate(text);
            image.setType("image");
        }
        image.setContent(content);
        image.setAltText(text);
        imageMapper.createImage(image);
    }
    @Override
    @Transactional
    public void createImage(base64ImageDTO base64Image) throws Exception {
        String base64String = base64Image.getBase64String();
        String content = base64String.substring(0, min(200, base64String.length()));

        imageDTO image = readImageByContent(content);
        if(image != null) return;

        image = new imageDTO();
        base64Service.store(base64Image);
        String text = azureCVService.captionImageV4();
        if(text == "text") {
            text = azureCVService.ocrImageV4();
            image.setType("text");
        }
        else {
            text = papagoService.translate(text);
            image.setType("image");
        }
        image.setContent(content);
        image.setAltText(text);
        imageMapper.createImage(image);
    }
    @Override
    public imageDTO readImageByContent(String content) throws Exception {
        return imageMapper.readImageByContent(content.substring(0, min(200, content.length())));
    }
}
