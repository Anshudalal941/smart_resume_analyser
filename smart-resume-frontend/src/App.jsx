import React from 'react';
import ResumeUpload from './components/ResumeUpload';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <header className="bg-primary text-white text-center py-3">
        <h1>Smart Resume Analyzer</h1>
        <p className="lead">Upload your resume and get instant feedback</p>
      </header>
      <main className="container">
        <ResumeUpload />
      </main>
      <footer className="text-center text-muted py-3 mt-5">
        &copy; {new Date().getFullYear()} Smart Resume Analyzer
      </footer>
    </div>
  );
}

export default App;
