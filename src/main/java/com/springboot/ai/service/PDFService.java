package com.springboot.ai.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class PDFService {
	
	public Map<String, String> readPDF(String pdfFilePath) throws IOException{
		String pdfData = "";
	
		PDDocument document = PDDocument.load(new File(pdfFilePath));
		PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfData = pdfStripper.getText(document);
		document.close();
		return extractKeyValuePairs(pdfData);
		
	}
	
	private Map<String, String> extractKeyValuePairs(String text){
		Map<String, String> keyValuePair = new HashMap<>();
		// Use a HashSet to store unique values
		Set<String> uniqueValues = new HashSet<>();
	
		Arrays.stream(text.split("\n"))
				.filter(line -> line.contains(":"))
				.map(line -> line.split(":", 2))
				.filter(parts -> parts.length == 2)
				.forEach(parts -> {
		            String key = parts[0].trim();
		            String value = parts[1].trim();

		            // Check if the value is already in the set
		            if (uniqueValues.add(value)) {
		            	keyValuePair.put(key, value);
		            }
		            
				});
		// Using Gson library
        Gson gson = new Gson();
        String json = gson.toJson(keyValuePair);
        System.out.println(json);
		return keyValuePair;
	}

}
