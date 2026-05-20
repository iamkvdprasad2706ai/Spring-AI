package com.springboot.ai.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ai.service.PDFService;

@RestController
public class PDFController {
	
	@Autowired
	PDFService pdfService;
	
	@GetMapping("/read-pdf/{fileName}")
	public Map<String, String> readPDF(@PathVariable String fileName) {
		
		String filePath = "/Users/kvdprasad/Documents/" + fileName;
		try {
			return pdfService.readPDF(filePath);
		}catch(IOException ioException) {
			return null;
		}
		
	}

}
