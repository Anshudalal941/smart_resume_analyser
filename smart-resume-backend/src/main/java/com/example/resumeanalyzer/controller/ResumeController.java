package com.example.resumeanalyzer.controller;

import com.example.resumeanalyzer.model.AnalysisResult;
import com.example.resumeanalyzer.service.ResumeAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeAnalyzerService analyzerService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("requiredSkills") String requiredSkills
    ) {
        try {
            AnalysisResult result = analyzerService.analyzeResume(file, requiredSkills);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace(); // log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
