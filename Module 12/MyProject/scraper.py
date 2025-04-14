import requests
from bs4 import BeautifulSoup
import json

# Define the URL of your static website pages
urls = ['nba2k24.html', 'madden24.html', 'mlbtheshow24.html']

# List to store scraped game data
games_data = []

# Loop through each URL and scrape the data
for url in urls:
    with open(url, 'r') as file:
        page = file.read()
    
    # Parse the HTML with BeautifulSoup
    soup = BeautifulSoup(page, 'html.parser')
    
    # Extract data from the HTML
    game = {
        'title': soup.find('h1').text,
        'genre': soup.find('li', text='Genre:').text.split(': ')[1],
        'release_year': soup.find('li', text='Release Year:').text.split(': ')[1],
        'developer': soup.find('li', text='Developer:').text.split(': ')[1],
        'publisher': soup.find('li', text='Publisher:').text.split(': ')[1],
        'director': soup.find('li', text='Director:').text.split(': ')[1],
        'platform': soup.find('li', text='Platform:').text.split(': ')[1],
        'price': soup.find('li', text='Price:').text.split(': ')[1],
        'available_on_switch': soup.find('li', text='Available on Switch:').text.split(': ')[1].lower() == 'true',
        'cover_athlete': soup.find('li', text='Cover Athlete:').text.split(': ')[1],
        'game_modes': soup.find('li', text='Game Modes:').text.split(': ')[1],
    }
    
    # Add the game data to the list
    games_data.append(game)

# Save the data to a JSON file
with open('games.json', 'w') as json_file:
    json.dump(games_data, json_file, indent=4)

print("Scraping complete. Data saved to games.json")
