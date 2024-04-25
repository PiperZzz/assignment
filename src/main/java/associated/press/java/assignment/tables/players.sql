CREATE TABLE players (
    email VARCHAR(255) NOT NULL,
    level INT NOT NULL CHECK (level BETWEEN 1 AND 10),
    age INT NOT NULL,
    gender ENUM('male', 'female') NOT NULL,
    PRIMARY KEY (email)
);
