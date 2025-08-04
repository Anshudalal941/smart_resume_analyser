import sys
import json
import re
from difflib import get_close_matches

def extract_keywords(text):
    text = text.lower()
    return set(re.findall(r'\b[a-z]{3,}\b', text))

def fuzzy_match(skills, keywords, cutoff=0.8):
    matched = set()
    for skill in skills:
        matches = get_close_matches(skill, keywords, n=1, cutoff=cutoff)
        if matches:
            matched.add(skill)
    return matched 

def analyze_resume(resume_text, required_skills):
    resume_keywords = extract_keywords(resume_text)
    required_skills_set = set([s.lower().strip() for s in required_skills])

    matched = fuzzy_match(required_skills_set, resume_keywords)
    missing = required_skills_set - matched

    result = {
        "matchPercentage": int((len(matched) / len(required_skills_set)) * 100) if required_skills_set else 0,
        "matchedSkills": list(matched),
        "missingSkills": list(missing)
    }

    print(json.dumps(result))

if __name__ == "__main__":
    data = json.loads(sys.stdin.read())
    resume_text = data.get("resumeText", "")
    required_skills = data.get("requiredSkills", [])
    analyze_resume(resume_text, required_skills)
