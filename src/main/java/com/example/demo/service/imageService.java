package com.example.demo.service;

import com.example.demo.dto.base64ImageDTO;
import com.example.demo.dto.imageDTO;
import com.example.demo.dto.urlImageDTO;
import org.springframework.stereotype.Service;

public interface imageService {
    imageDTO createImage(urlImageDTO urlImage) throws Exception;
    imageDTO createImage(base64ImageDTO base64Image) throws Exception;
}
