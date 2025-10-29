-- follow these in order to make cascading deletes on relational entities

CREATE TABLE Matched_User_backup AS SELECT * FROM Matched_User;

-- run a SELECT * FROM Matched_User_backup to make sure the data is still there

ALTER TABLE Matched_User
DROP FOREIGN KEY Matched_User_ibfk_1,
DROP FOREIGN KEY Matched_User_ibfk_2;

ALTER TABLE Matched_User
ADD CONSTRAINT fk_matched_user_user
FOREIGN KEY (`User`) REFERENCES `User`(`User_Id`) ON DELETE CASCADE,
ADD CONSTRAINT fk_matched_user_liked_user
FOREIGN KEY (`Liked_User`) REFERENCES `User`(`User_Id`) ON DELETE CASCADE;

CREATE TABLE UserGames_backup AS SELECT * FROM UserGames;

-- run a SELECT * FROM UserGames to check data is still there

ALTER TABLE UserGames
DROP FOREIGN KEY UserGames_ibfk_1,
DROP FOREIGN KEY UserGames_ibfk_2;

ALTER TABLE UserGames
ADD CONSTRAINT fk_usergames_user
FOREIGN KEY (`User`) REFERENCES `User`(`User_Id`) ON DELETE CASCADE,
ADD CONSTRAINT fk_usergames_games
FOREIGN KEY (`Game`) REFERENCES `Game`(`Game_Id`) ON DELETE CASCADE;

-- double check constraints are added

SHOW CREATE TABLE Matched_User; -- should see constaints
SHOW CREATE TABLE UserGames; -- should see constaints