CREATE OR REPLACE ROLE MEW_GM_DB_Views;
GRANT SELECT ON MEW_GM_DB.* TO MEW_GM_DB_Views;

USE MEW_GM_DB;

-- DROP TABLE IF EXISTS Genre;

CREATE TABLE Genre (

    Genre_Id TINYINT PRIMARY KEY AUTO_INCREMENT NOT NULL
    , Genre_Name VARCHAR(30) NOT NULL
    , Genre_Description VARCHAR(254)
);

-- DROP TABLE IF EXISTS Console;

CREATE TABLE Console (

    Console_Id TINYINT PRIMARY KEY AUTO_INCREMENT NOT NULL
    , Console_Name VARCHAR(10) NOT NULL
);

-- DROP TABLE IF EXISTS Game;

CREATE TABLE Game (

    Game_Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL
    , Game_Title VARCHAR(50) NOT NULL
    , Single_Player BOOLEAN DEFAULT 1 NOT NULL
    , Multi_Player BOOLEAN DEFAULT 0 NOT NULL
    , Primary_Genre TINYINT NOT NULL
    , FOREIGN KEY (Primary_Genre) REFERENCES Genre(Genre_Id)
);

-- DROP TABLE IF EXISTS User;

CREATE TABLE User (

    User_Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL
    , First_Name VARCHAR(15) NOT NULL
    , Last_Name VARCHAR(20)
    , Dob DATE NOT NULL
    , Email VARCHAR(50) NOT NULL
    , Gamertag VARCHAR(25) NOT NULL
    , Preferred_Console TINYINT NOT NULL
    , About_User VARCHAR(500)
    , FOREIGN KEY (Preferred_Console) REFERENCES Console(Console_Id)
);

-- DROP TABLE IF EXISTS UserGames;

CREATE TABLE UserGames (

    User_Id INT NOT NULL
    , Game_Id INT NOT NULL
    , PRIMARY KEY (User_Id, Game_Id)
    , FOREIGN KEY (User_Id) REFERENCES User(User_Id)
    , FOREIGN KEY (Game_Id) REFERENCES Game(Game_Id)
);

-- DROP TABLE IF EXISTS Matched_User;

CREATE TABLE Matched_User (

    User_Id INT NOT NULL
    , Liked_User_Id INT NOT NULL
    , Is_Matched BOOLEAN DEFAULT 0
    , PRIMARY KEY (User_Id, Liked_User_Id)
    , FOREIGN KEY (User_Id) REFERENCES User(User_Id)
    , FOREIGN KEY (Liked_User_Id) REFERENCES User(User_Id)
);
