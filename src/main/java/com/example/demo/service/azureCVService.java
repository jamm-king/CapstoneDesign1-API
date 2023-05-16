package com.example.demo.service;

import com.example.demo.dto.urlImageDTO;

public interface azureCVService {
    public String CaptionImageV4(urlImageDTO urlImage);
    public String OCRImageV4(urlImageDTO urlImage);
}
