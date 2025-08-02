# 🧠 Smart Resume Analyzer

A full-stack application that intelligently analyzes resumes against a list of required skills using NLP and fuzzy matching. Built for recruiters, HR teams, and job seekers to evaluate resume relevance in real time.

---

## 🚀 Features

- Upload resumes in `.pdf`, `.docx`, or `.doc` format
- Analyze resume content using Python NLP
- Match against custom skill sets
- View match percentage, matched skills, and missing skills
- Clean React frontend + Spring Boot backend + Python integration

---

## 🛠️ Tech Stack

| Layer       | Technology                     |
|------------|---------------------------------|
| Frontend   | React.js                        |
| Backend    | Spring Boot (Java)              |
| NLP Engine | Python (fuzzy matching, regex)  |
| File Parsing | Apache POI, PDFBox            |

---

## 📦 Installation

### 1. Clone the repo

```bash
git clone https://github.com/Anshudalal941/smart_resume_analyser.git
cd smart_resume_analyser

### 2. Backend Setup
cd smart-resume-backend
./mvnw spring-boot:run

### 3. Frontend Setup
cd smart-resume-frontend
npm install
npm start
