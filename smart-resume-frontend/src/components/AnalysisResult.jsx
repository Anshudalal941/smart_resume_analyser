import React from 'react';

const AnalysisResult = ({ data }) => {
  return (
    <div className="mt-4">
      <h4>Analysis Result</h4>
      <p><strong>Match Score:</strong> {data.matchPercentage}%</p>

      <h5>Matched Skills:</h5>
      <ul>
        {data.matchedSkills && data.matchedSkills.map((skill, index) => (
          <li key={index}>{skill}</li>
        ))}
      </ul>

      <h5>Missing Skills:</h5>
      <ul>
        {data.missingSkills && data.missingSkills.map((skill, index) => (
          <li key={index}>{skill}</li>
        ))}
      </ul>

      {data.feedback && (
        <div className="alert alert-info mt-3">
          <strong>Feedback:</strong> {data.feedback}
        </div>
      )}
    </div>
  );
};

export default AnalysisResult;
