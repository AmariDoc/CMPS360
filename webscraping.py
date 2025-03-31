import requests
from bs4 import BeautifulSoup
import json

def scrape_indeed_jobs(job_title, location, num_pages):
    base_url = "https://www.indeed.com/jobs"
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"
    }
    
    jobs = []

    for page in range(num_pages):
        params = {"q": job_title, "l": location, "start": page * 10}
        response = requests.get(base_url, headers=headers, params=params)
        print(response.text)  # This will print the entire HTML of the page
        soup = BeautifulSoup(response.text, 'html.parser')

        job_cards = soup.find_all("div", class_="job_seen_beacon")
        print(job_cards)  # This will print the found job cards


        for job in job_cards:
            title_elem = job.find("h2", class_="jobTitle")
            company_elem = job.find("span", class_="companyName")
            location_elem = job.find("div", class_="companyLocation")
            link_elem = job.find("a", class_="jcs-JobTitle")

            if title_elem and company_elem and location_elem and link_elem:
                job_title = title_elem.text.strip()
                company = company_elem.text.strip()
                location = location_elem.text.strip()
                job_link = "https://www.indeed.com" + link_elem["href"]

                jobs.append({
                    "Title": job_title,
                    "Company": company,
                    "Location": location,
                    "URL": job_link
                })

    return jobs

def save_to_json(jobs, filename):
    with open(filename, "w", encoding="utf-8") as f:
        json.dump(jobs, f, indent=4)

if __name__ == "__main__":
    job_title = input("Enter Job Title: ")
    location = input("Enter Job Location: ")
    num_pages = int(input("Enter number of pages: "))

    jobs = scrape_indeed_jobs(job_title, location, num_pages)
    save_to_json(jobs, "indeed_jobs.json")
    print(f"Scraped {len(jobs)} job listings. Saved to indeed_jobs.json")
