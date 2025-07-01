-- Drop existing data (optional for resetting state)
TRUNCATE TABLE goal, match, coach, player, team RESTART IDENTITY CASCADE;

-- Insert Manchester City
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Manchester City', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Ruben', 'Amorim', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Manchester City')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Ederson', 'Moraes', (SELECT id FROM team_id)),
             ('Ruben', 'Dias', (SELECT id FROM team_id)),
             ('Joao', 'Cancelo', (SELECT id FROM team_id)),
             ('Rodri', 'Hernandez', (SELECT id FROM team_id)),
             ('Kevin', 'De Bruyne', (SELECT id FROM team_id)),
             ('Phil', 'Foden', (SELECT id FROM team_id)),
             ('Jack', 'Grealish', (SELECT id FROM team_id)),
             ('Ilkay', 'Gundogan', (SELECT id FROM team_id)),
             ('Erling', 'Haaland', (SELECT id FROM team_id)),
             ('Riyad', 'Mahrez', (SELECT id FROM team_id)),
             ('Bernardo', 'Silva', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Liverpool
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Liverpool', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Arne', 'Slot', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Liverpool')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Alisson', 'Becker', (SELECT id FROM team_id)),
             ('Virgil', 'van Dijk', (SELECT id FROM team_id)),
             ('Andrew', 'Robertson', (SELECT id FROM team_id)),
             ('Trent', 'Alexander-Arnold', (SELECT id FROM team_id)),
             ('Fabinho', 'Tavares', (SELECT id FROM team_id)),
             ('Thiago', 'Alcantara', (SELECT id FROM team_id)),
             ('Mohamed', 'Salah', (SELECT id FROM team_id)),
             ('Luis', 'Diaz', (SELECT id FROM team_id)),
             ('Cody', 'Gakpo', (SELECT id FROM team_id)),
             ('Darwin', 'Nunez', (SELECT id FROM team_id)),
             ('Diogo', 'Jota', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Real Madrid
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Real Madrid', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Carlo', 'Ancelotti', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Real Madrid')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Thibaut', 'Courtois', (SELECT id FROM team_id)),
             ('Eder', 'Militao', (SELECT id FROM team_id)),
             ('David', 'Alaba', (SELECT id FROM team_id)),
             ('Ferland', 'Mendy', (SELECT id FROM team_id)),
             ('Luka', 'Modric', (SELECT id FROM team_id)),
             ('Toni', 'Kroos', (SELECT id FROM team_id)),
             ('Federico', 'Valverde', (SELECT id FROM team_id)),
             ('Vinicius', 'Junior', (SELECT id FROM team_id)),
             ('Rodrygo', 'Goes', (SELECT id FROM team_id)),
             ('Karim', 'Benzema', (SELECT id FROM team_id)),
             ('Marco', 'Asensio', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert FC Barcelona
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('FC Barcelona', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Hansi', 'Flick', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'FC Barcelona')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Marc-André', 'ter Stegen', (SELECT id FROM team_id)),
             ('Jules', 'Kounde', (SELECT id FROM team_id)),
             ('Ronald', 'Araujo', (SELECT id FROM team_id)),
             ('Andreas', 'Christensen', (SELECT id FROM team_id)),
             ('Alejandro', 'Balde', (SELECT id FROM team_id)),
             ('Sergio', 'Busquets', (SELECT id FROM team_id)),
             ('Frenkie', 'de Jong', (SELECT id FROM team_id)),
             ('Gavi', 'Paez', (SELECT id FROM team_id)),
             ('Pedri', 'Gonzalez', (SELECT id FROM team_id)),
             ('Ousmane', 'Dembele', (SELECT id FROM team_id)),
             ('Robert', 'Lewandowski', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Bayern Munich
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Bayern Munich', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Vincent', 'Kompany', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Bayern Munich')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Manuel', 'Neuer', (SELECT id FROM team_id)),
             ('Matthijs', 'de Ligt', (SELECT id FROM team_id)),
             ('Lucas', 'Hernandez', (SELECT id FROM team_id)),
             ('Alphonso', 'Davies', (SELECT id FROM team_id)),
             ('Joshua', 'Kimmich', (SELECT id FROM team_id)),
             ('Leon', 'Goretzka', (SELECT id FROM team_id)),
             ('Thomas', 'Muller', (SELECT id FROM team_id)),
             ('Kingsley', 'Coman', (SELECT id FROM team_id)),
             ('Sadio', 'Mane', (SELECT id FROM team_id)),
             ('Serge', 'Gnabry', (SELECT id FROM team_id)),
             ('Harry', 'Kane', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Ajax
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Ajax', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Francesco', 'Farioli', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Ajax')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Pascal', 'Struijk', (SELECT id FROM team_id)),
             ('Jurrien', 'Timber', (SELECT id FROM team_id)),
             ('Daley', 'Blind', (SELECT id FROM team_id)),
             ('Edson', 'Alvarez', (SELECT id FROM team_id)),
             ('Davy', 'Klaassen', (SELECT id FROM team_id)),
             ('Steven', 'Bergwijn', (SELECT id FROM team_id)),
             ('Antony', 'Martinez', (SELECT id FROM team_id)),
             ('Mohammed', 'Kudus', (SELECT id FROM team_id)),
             ('Brian', 'Brovets', (SELECT id FROM team_id)),
             ('Lassina', 'Traore', (SELECT id FROM team_id)),
             ('Kasper', 'Dolberg', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Inter Milan
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Inter Milan', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Simone', 'Inzagh', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Inter Milan')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Samir', 'Handanovic', (SELECT id FROM team_id)),
             ('Milan', 'Skriniar', (SELECT id FROM team_id)),
             ('Alessandro', 'Bastoni', (SELECT id FROM team_id)),
             ('Denzel', 'Dumfries', (SELECT id FROM team_id)),
             ('Nicolo', 'Barella', (SELECT id FROM team_id)),
             ('Marcelo', 'Brozovic', (SELECT id FROM team_id)),
             ('Hakan', 'Calhanoglu', (SELECT id FROM team_id)),
             ('Lautaro', 'Martinez', (SELECT id FROM team_id)),
             ('Romelu', 'Lukaku', (SELECT id FROM team_id)),
             ('Joaquin', 'Correa', (SELECT id FROM team_id)),
             ('Edin', 'Dzeko', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);


-- Insert Paris Saint-Germain
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Paris Saint-Germain', 0)
        RETURNING id)
INSERT
INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Luis', 'Enrique', id, 0
FROM team_insert;

WITH team_id AS (SELECT id
                 FROM team
                 WHERE name = 'Paris Saint-Germain')
INSERT
INTO player (first_name, last_name, team_id)
SELECT *
FROM (VALUES ('Gianluigi', 'Donnarumma', (SELECT id FROM team_id)),
             ('Achraf', 'Hakimi', (SELECT id FROM team_id)),
             ('Marquinhos', 'Correa', (SELECT id FROM team_id)),
             ('Sergio', 'Ramos', (SELECT id FROM team_id)),
             ('Presnel', 'Kimpembe', (SELECT id FROM team_id)),
             ('Marco', 'Verratti', (SELECT id FROM team_id)),
             ('Vitinha', 'Machado', (SELECT id FROM team_id)),
             ('Neymar', 'Jr', (SELECT id FROM team_id)),
             ('Kylian', 'Mbappe', (SELECT id FROM team_id)),
             ('Lionel', 'Messi', (SELECT id FROM team_id)),
             ('Fabian', 'Ruiz', (SELECT id FROM team_id))
             ) AS players(first_name, last_name, team_id);
COMMIT;

-- Insert MATCHES and GOALS
-- MATCH 1: Manchester City vs. Liverpool (no goals)
-- Create MATCH 1: Manchester City vs. Liverpool (no goals)
WITH home_id AS (
    SELECT id FROM team WHERE name = 'Manchester City'
),
away_id AS (
    SELECT id FROM team WHERE name = 'Liverpool'
),
inserted_match AS (
    INSERT INTO match (home_team_id, away_team_id, home_score, away_score, match_date)
    SELECT home_id.id, away_id.id, 0, 0, '2023-10-15 15:00:00'
    FROM home_id, away_id
    RETURNING id
),
players_home AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM player p, team t, inserted_match m
    WHERE p.team_id = t.id AND t.name = 'Manchester City'
),
players_away AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM player p, team t, inserted_match m
    WHERE p.team_id = t.id AND t.name = 'Liverpool'
)
INSERT INTO player_match (player_id, match_id)
SELECT player_id, match_id FROM players_home
UNION ALL
SELECT player_id, match_id FROM players_away;

-- MATCH 2: Real Madrid vs. FC Barcelona (with goals + participants)

WITH home_id AS (
    SELECT id FROM team WHERE name = 'Real Madrid'
),
away_id AS (
    SELECT id FROM team WHERE name = 'FC Barcelona'
),
inserted_match AS (
    INSERT INTO match (home_team_id, away_team_id, home_score, away_score, match_date)
    SELECT home_id.id, away_id.id, 3, 1, '2023-09-25 20:45:00'
    FROM home_id, away_id
    RETURNING id
),

-- Insert goals
vinicius AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Vinicius' AND p.last_name = 'Junior'
    JOIN team t ON p.team_id = t.id AND t.name = 'Real Madrid'
),
benzema AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Karim' AND p.last_name = 'Benzema'
    JOIN team t ON p.team_id = t.id AND t.name = 'Real Madrid'
),
lewandowski AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Robert' AND p.last_name = 'Lewandowski'
    JOIN team t ON p.team_id = t.id AND t.name = 'FC Barcelona'
),
goals_inserted AS (
    INSERT INTO goal (match_id, player_id)
    SELECT match_id, player_id FROM vinicius
    UNION ALL
    SELECT match_id, player_id FROM vinicius  -- 2nd goal
    UNION ALL
    SELECT match_id, player_id FROM benzema
    UNION ALL
    SELECT match_id, player_id FROM lewandowski
),

-- Insert participants
players_real AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'Real Madrid')
),
players_barca AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'FC Barcelona')
)
INSERT INTO player_match (player_id, match_id)
SELECT player_id, match_id FROM players_real
UNION ALL
SELECT player_id, match_id FROM players_barca;

-- MATCH 3: Bayern Munich vs. Ajax
WITH home_id AS (
    SELECT id FROM team WHERE name = 'Bayern Munich'
),
away_id AS (
    SELECT id FROM team WHERE name = 'Ajax'
),
inserted_match AS (
    INSERT INTO match (home_team_id, away_team_id, home_score, away_score, match_date)
    SELECT home_id.id, away_id.id, 4, 0, '2023-09-20 18:30:00'
    FROM home_id, away_id
    RETURNING id
),

-- Insert goals
kane AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Harry' AND p.last_name = 'Kane'
    JOIN team t ON p.team_id = t.id AND t.name = 'Bayern Munich'
),
muller AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Thomas' AND p.last_name = 'Muller'
    JOIN team t ON p.team_id = t.id AND t.name = 'Bayern Munich'
),
goretzka AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Leon' AND p.last_name = 'Goretzka'
    JOIN team t ON p.team_id = t.id AND t.name = 'Bayern Munich'
),
goals_inserted AS (
    INSERT INTO goal (match_id, player_id)
    SELECT match_id, player_id FROM kane
    UNION ALL
    SELECT match_id, player_id FROM kane  -- second goal
    UNION ALL
    SELECT match_id, player_id FROM muller
    UNION ALL
    SELECT match_id, player_id FROM goretzka
),

-- Insert participants
players_bayern AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'Bayern Munich')
),
players_ajax AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'Ajax')
)
INSERT INTO player_match (player_id, match_id)
SELECT player_id, match_id FROM players_bayern
UNION ALL
SELECT player_id, match_id FROM players_ajax;

-- MATCH 4: Liverpool vs. Inter Milan
WITH home_id AS (
    SELECT id FROM team WHERE name = 'Liverpool'
),
away_id AS (
    SELECT id FROM team WHERE name = 'Inter Milan'
),
inserted_match AS (
    INSERT INTO match (home_team_id, away_team_id, home_score, away_score, match_date)
    SELECT home_id.id, away_id.id, 2, 1, '2023-10-05 19:00:00'
    FROM home_id, away_id
    RETURNING id
),

-- Goal scorers
salah AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Mohamed' AND p.last_name = 'Salah'
    JOIN team t ON p.team_id = t.id AND t.name = 'Liverpool'
),
nunez AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Darwin' AND p.last_name = 'Nunez'
    JOIN team t ON p.team_id = t.id AND t.name = 'Liverpool'
),
lautaro AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.first_name = 'Lautaro' AND p.last_name = 'Martinez'
    JOIN team t ON p.team_id = t.id AND t.name = 'Inter Milan'
),
goals_inserted AS (
    INSERT INTO goal (match_id, player_id)
    SELECT match_id, player_id FROM salah
    UNION ALL
    SELECT match_id, player_id FROM nunez
    UNION ALL
    SELECT match_id, player_id FROM lautaro
),

-- Insert all players as match participants
players_liverpool AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'Liverpool')
),
players_inter AS (
    SELECT p.id AS player_id, m.id AS match_id
    FROM inserted_match m
    JOIN player p ON p.team_id = (SELECT id FROM team WHERE name = 'Inter Milan')
)
INSERT INTO player_match (player_id, match_id)
SELECT player_id, match_id FROM players_liverpool
UNION ALL
SELECT player_id, match_id FROM players_inter;

COMMIT;