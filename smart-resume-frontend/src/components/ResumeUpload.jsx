import React, { useState } from 'react';
import axios from 'axios';
import LoadingSpinner from './LoadingSpinner';
import AnalysisResult from './AnalysisResult';

const ResumeUpload = () => {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);
  const [requiredSkills, setRequiredSkills] = useState("");

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
  e.preventDefault();

  if (!file) {
    alert("Please upload a resume.");
    return;
  }

  const formData = new FormData();
  formData.append("file", file);
  formData.append("requiredSkills", requiredSkills); // ✨ Send this too

  try {
    setLoading(true);
    const response = await axios.post("http://localhost:8080/api/resume/analyze", formData, {
      headers: { "Content-Type": "multipart/form-data" }
    });
    setResult(response.data);
  } catch (error) {
    alert("Error analyzing resume. Please try again.");
    console.error(error);
  } finally {
    setLoading(false);
  }
};


  return (
    <div className="container mt-5">
      <h2>Smart Resume Analyzer</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="skills" className="form-label">Enter Required Skills (comma-separated)</label>
          <input
            type="text"
            className="form-control"
            placeholder="e.g. Java, React, Python"
            value={requiredSkills}
            onChange={(e) => setRequiredSkills(e.target.value)}
          />
        </div>

        <input type="file" accept=".pdf,.doc,.docx" onChange={handleFileChange} className="form-control mb-3" />
        <button type="submit" className="btn btn-primary">Analyze Resume</button>
      </form>

      {loading && <LoadingSpinner />}
      {result && <AnalysisResult data={result} />}
    </div>
  );
};

export default ResumeUpload;
