CREATE TABLE players_sports (
    player_email VARCHAR(255) NOT NULL,
    sport_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (player_email, sport_name),
    FOREIGN KEY (player_email) REFERENCES players(email),
    FOREIGN KEY (sport_name) REFERENCES sports(name)
);