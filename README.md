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
```

### 2. Backend Setup
```bash
cd smart-resume-backend
./mvnw spring-boot:run
```
⚙️ Make sure to update application.properties with the correct path to resume_analyzer.py.

### 3. Frontend Setup
``bash
cd smart-resume-frontend
npm install
npm start
## 📤 Usage

- Open the frontend at http://localhost:3000

- Upload a resume file

- Enter comma-separated required skills (e.g. Java, Spring, React)

- Click Analyze

- View results: match percentage, matched & missing skills

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to improve.

## 📜 License

License © 2025 Anshu Dalal
