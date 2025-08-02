package com.example.resumeanalyzer.model;

import java.util.List;

public class AnalysisResult {
    private boolean success;
    private String feedback;
    private int matchPercentage;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public AnalysisResult() {}
    // existing fields and methods

    public void setMatchPercentage(int matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public int getMatchPercentage() {
        return matchPercentage;
    }
    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }
    public AnalysisResult(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
