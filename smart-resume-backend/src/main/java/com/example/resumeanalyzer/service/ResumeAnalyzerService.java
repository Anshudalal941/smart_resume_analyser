package com.example.resumeanalyzer.service;

import com.example.resumeanalyzer.model.AnalysisResult;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeAnalyzerService {

    @Value("${resume.analyzer.script.path}")
    private String scriptPath;

    public AnalysisResult analyzeResume(MultipartFile file, String skillsCsv) throws IOException {
        String resumeText = extractTextFromFile(file);
        List<String> requiredSkills = List.of(skillsCsv.split(","));

        System.out.println("Required Skills: " + requiredSkills);
        System.out.println("Resume Text Length: " + resumeText.length());

        File scriptFile = new File(scriptPath);
        if (!scriptFile.exists()) {
            throw new IOException("Python script not found at: " + scriptFile.getAbsolutePath());
        }

        ProcessBuilder pb = new ProcessBuilder("python", scriptFile.getAbsolutePath());
        Process process = pb.start();

        // Prepare input for Python
        JSONObject input = new JSONObject();
        input.put("resumeText", resumeText);
        input.put("requiredSkills", requiredSkills);

        // Send input to Python
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(input.toString());
            writer.flush();
        }

        // Read output from Python
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = reader.lines().collect(Collectors.joining());
        System.out.println("Python Output:\n" + output);

        // Read error stream AFTER execution
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorOutput = errorReader.lines().collect(Collectors.joining());
        if (!errorOutput.isEmpty()) {
            throw new IOException("Python error: " + errorOutput);
        }

        // Parse result
        try {
            JSONObject resultJson = new JSONObject(output);
            AnalysisResult result = new AnalysisResult();
            result.setMatchPercentage(resultJson.getInt("matchPercentage"));
            result.setMatchedSkills(resultJson.getJSONArray("matchedSkills").toList().stream().map(Object::toString).toList());
            result.setMissingSkills(resultJson.getJSONArray("missingSkills").toList().stream().map(Object::toString).toList());
            result.setSuccess(true);
            result.setFeedback("Resume analyzed successfully.");
            return result;
        } catch (Exception e) {
            throw new IOException("Failed to parse Python output: " + e.getMessage());
        }
    }

    private String extractTextFromFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) throw new IOException("File name is null");

        if (fileName.endsWith(".pdf")) {
            try (PDDocument document = PDDocument.load(file.getInputStream())) {
                String text = new PDFTextStripper().getText(document);
                if (text == null || text.trim().isEmpty()) {
                    throw new IOException("PDF content is empty or unreadable.");
                }
                System.out.println("Extracted PDF Text:\n" + text);
                return text;
            }
        } else if (fileName.endsWith(".docx")) {
            try (XWPFDocument docx = new XWPFDocument(file.getInputStream())) {
                String text = docx.getParagraphs().stream().map(p -> p.getText()).collect(Collectors.joining("\n"));
                if (text == null || text.trim().isEmpty()) {
                    throw new IOException("DOCX content is empty or unreadable.");
                }
                return text;
            }
        } else if (fileName.endsWith(".doc")) {
            try (HWPFDocument doc = new HWPFDocument(file.getInputStream())) {
                WordExtractor extractor = new WordExtractor(doc);
                String text = extractor.getText();
                if (text == null || text.trim().isEmpty()) {
                    throw new IOException("DOC content is empty or unreadable.");
                }
                return text;
            }
        } else {
            throw new IOException("Unsupported file format");
        }
    }
}
