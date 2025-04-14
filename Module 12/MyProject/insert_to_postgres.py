import psycopg2
import json

# Load JSON data
with open("games.json") as f:
    games = json.load(f)

# Connect to PostgreSQL
conn = psycopg2.connect(
    dbname="sports_games",
    user="postgres",
    password="1234",
    host="localhost",
    port="5432"
)
cur = conn.cursor()

# Create table (if it doesn't exist)
cur.execute("""
CREATE TABLE IF NOT EXISTS games (
    title TEXT,
    genre TEXT,
    release_year TEXT,
    developer TEXT,
    publisher TEXT,
    director TEXT,
    platform TEXT,
    price NUMERIC,
    available_on_switch BOOLEAN,
    cover_athlete TEXT,
    game_modes TEXT
)
""")

# Insert data
for game in games:
    cur.execute("""
        INSERT INTO games (
            title, genre, release_year, developer, publisher, director,
            platform, price, available_on_switch, cover_athlete, game_modes
        ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    """, (
        game.get("title"),
        game.get("genre"),
        game.get("release_year"),
        game.get("developer"),
        game.get("publisher"),
        game.get("director"),
        game.get("platform"),
        float(game.get("price")),
        game.get("available_on_switch") == True,  # Corrected line
        game.get("cover_athlete"),
        game.get("game_modes")
    ))

conn.commit()
cur.close()
conn.close()
