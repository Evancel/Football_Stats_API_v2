-- Cleaning up the database at start up:
TRUNCATE TABLE goal, match, player, coach, team RESTART IDENTITY CASCADE;

-- Manchester City

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Manchester City', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Ruben',
    'Amorim'
FROM team
WHERE name = 'Manchester City';


WITH base AS (
    SELECT * FROM team WHERE name = 'Manchester City'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Ederson', 'Moraes'),
                               ('Ruben', 'Dias'),
                               ('Joao', 'Cancelo'),
                               ('Rodri', 'Hernandez'),
                               ('Kevin', 'De Bruyne'),
                               ('Phil', 'Foden'),
                               ('Jack', 'Grealish'),
                               ('Ilkay', 'Gundogan'),
                               ('Erling', 'Haaland'),
                               ('Riyad', 'Mahrez'),
                               ('Bernardo', 'Silva')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Liverpool

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Liverpool', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Arne',
    'Slot'
FROM team
WHERE name = 'Liverpool';

WITH base AS (
    SELECT * FROM team WHERE name = 'Liverpool'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Alisson', 'Becker'),
                               ('Virgil', 'van Dijk'),
                               ('Andrew', 'Robertson'),
                               ('Trent', 'Alexander-Arnold'),
                               ('Fabinho', 'Tavares'),
                               ('Thiago', 'Alcantara'),
                               ('Mohamed', 'Salah'),
                               ('Luis', 'Diaz'),
                               ('Cody', 'Gakpo'),
                               ('Darwin', 'Nunez'),
                               ('Diogo', 'Jota')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Real Madrid

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Real Madrid', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Carlo',
    'Ancelotti'
FROM team
WHERE name = 'Real Madrid';

WITH base AS (
    SELECT * FROM team WHERE name = 'Real Madrid'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Thibaut', 'Courtois'),
                               ('Eder', 'Militao'),
                               ('David', 'Alaba'),
                               ('Ferland', 'Mendy'),
                               ('Luka', 'Modric'),
                               ('Toni', 'Kroos'),
                               ('Federico', 'Valverde'),
                               ('Vinicius', 'Junior'),
                               ('Rodrygo', 'Goes'),
                               ('Karim', 'Benzema'),
                               ('Marco', 'Asensio')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;


-- FC Barcelona

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'FC Barcelona', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Hansi',
    'Flick'
FROM team
WHERE name = 'FC Barcelona';

-- Insert FC Barcelona players
WITH base AS (
    SELECT * FROM team WHERE name = 'FC Barcelona'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Marc-André', 'ter Stegen'),
                               ('Jules', 'Kounde'),
                               ('Ronald', 'Araujo'),
                               ('Andreas', 'Christensen'),
                               ('Alejandro', 'Balde'),
                               ('Sergio', 'Busquets'),
                               ('Frenkie', 'de Jong'),
                               ('Gavi', 'Paez'),
                               ('Pedri', 'Gonzalez'),
                               ('Ousmane', 'Dembele'),
                               ('Robert', 'Lewandowski')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Bayern Munich

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Bayern Munich', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Vincent',
    'Kompany'
FROM team
WHERE name = 'Bayern Munich';

WITH base AS (
    SELECT * FROM team WHERE name = 'Bayern Munich'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Manuel', 'Neuer'),
                               ('Matthijs', 'de Ligt'),
                               ('Lucas', 'Hernandez'),
                               ('Alphonso', 'Davies'),
                               ('Joshua', 'Kimmich'),
                               ('Leon', 'Goretzka'),
                               ('Thomas', 'Muller'),
                               ('Kingsley', 'Coman'),
                               ('Sadio', 'Mane'),
                               ('Serge', 'Gnabry'),
                               ('Harry', 'Kane')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Ajax

INSERT INTO team(id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Ajax', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Francesco',
    'Farioli'
FROM team
WHERE name = 'Ajax';

WITH base AS (
    SELECT * FROM team WHERE name = 'Ajax'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Pascal', 'Struijk'),
                               ('Jurrien', 'Timber'),
                               ('Daley', 'Blind'),
                               ('Edson', 'Alvarez'),
                               ('Davy', 'Klaassen'),
                               ('Steven', 'Bergwijn'),
                               ('Antony', 'Martinez'),
                               ('Mohammed', 'Kudus'),
                               ('Brian', 'Brovets'),
                               ('Lassina', 'Traore'),
                               ('Kasper', 'Dolberg')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Inter Milan

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Inter Milan', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Simone',
    'Inzaghi'
FROM team
WHERE name = 'Inter Milan';


WITH base AS (
    SELECT * FROM team WHERE name = 'Inter Milan'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Samir', 'Handanovic'),
                               ('Milan', 'Skriniar'),
                               ('Alessandro', 'Bastoni'),
                               ('Denzel', 'Dumfries'),
                               ('Nicolo', 'Barella'),
                               ('Marcelo', 'Brozovic'),
                               ('Hakan', 'Calhanoglu'),
                               ('Lautaro', 'Martinez'),
                               ('Romelu', 'Lukaku'),
                               ('Joaquin', 'Correa'),
                               ('Edin', 'Dzeko')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- Paris Saint-Germain

INSERT INTO team (id, name, goal_scored)
SELECT COALESCE(MAX(id), 0) + 1, 'Paris Saint-Germain', 0
FROM team;

INSERT INTO coach(id, played_matches, team_id, first_name, last_name)
SELECT
    (SELECT COALESCE(MAX(id), 0) + 1 FROM coach),  -- max coach ID + 1
    0,
    team.id,
    'Luis',
    'Enrique'
FROM team
WHERE name = 'Paris Saint-Germain';

WITH base AS (
    SELECT * FROM team WHERE name = 'Paris Saint-Germain'
),
     players AS (
         SELECT * FROM (
                           VALUES
                               ('Gianluigi', 'Donnarumma'),
                               ('Achraf', 'Hakimi'),
                               ('Marquinhos', 'Correa'),
                               ('Sergio', 'Ramos'),
                               ('Presnel', 'Kimpembe'),
                               ('Marco', 'Verratti'),
                               ('Vitinha', 'Machado'),
                               ('Neymar', 'Jr'),
                               ('Kylian', 'Mbappe'),
                               ('Lionel', 'Messi'),
                               ('Fabian', 'Ruiz')
                       ) AS p(first_name, last_name)
     ),
     max_id AS (
         SELECT COALESCE(MAX(id), 0) AS current_max FROM player
     ),
     numbered AS (
         SELECT
             p.first_name,
             p.last_name,
             ROW_NUMBER() OVER () AS rownum
         FROM players p
     )
INSERT INTO player(id, team_id, first_name, last_name, played_matches, goals)
SELECT
    m.current_max + n.rownum AS id,
    b.id AS team_id,
    n.first_name,
    n.last_name,
    0 AS played_matches,
    0 AS goals
FROM numbered n
         CROSS JOIN base b
         CROSS JOIN max_id m;

-- MATCH 1

WITH home_team AS (
    SELECT id AS home_id FROM team WHERE name = 'Manchester City'
),
     away_team AS (
         SELECT id AS away_id FROM team WHERE name = 'Liverpool'
     ),
     insert_match AS (
         INSERT INTO match(id, home_team_id, away_team_id, match_date, home_score, away_score)
             SELECT
                 (SELECT COALESCE(MAX(id), 0) + 1 FROM match),
                 h.home_id,
                 a.away_id,
                 '2023-10-15T15:00:00',
                 0,
                 0
             FROM home_team h, away_team a
             RETURNING id
     )
SELECT * FROM insert_match;


-- MATCH 2 (Real Madrid vs. FC Barcelona)
--------------------------------------------------------------------------------
-- 1) Insert the match row
INSERT INTO match (
    id,
    home_team_id,
    away_team_id,
    match_date,
    home_score,
    away_score
)
VALUES (
           (SELECT COALESCE(MAX(id), 0) + 1 FROM match),  -- next match ID
           (SELECT id FROM team WHERE name = 'Real Madrid'),
           (SELECT id FROM team WHERE name = 'FC Barcelona'),
           '2023-09-25T20:45:00',  -- match timestamp
           3,                      -- Real Madrid goals
           1                       -- Barcelona goals
       );

-- 2) Insert goal for Vinicius Junior (Real Madrid)
INSERT INTO goal (match_id, player_id)
SELECT
    (SELECT MAX(id) FROM match),  -- the match we just added
    p.id
FROM player p
         JOIN team t ON t.id = p.team_id
WHERE p.first_name = 'Vinicius'
  AND p.last_name  = 'Junior'
  AND t.name       = 'Real Madrid'
LIMIT 1;

-- 3) Insert goal for Karim Benzema (Real Madrid)
INSERT INTO goal (match_id, player_id)
SELECT
    (SELECT MAX(id) FROM match),
    p.id
FROM player p
         JOIN team t ON t.id = p.team_id
WHERE p.first_name = 'Karim'
  AND p.last_name  = 'Benzema'
  AND t.name       = 'Real Madrid'
LIMIT 1;

-- 4) Insert goal for Robert Lewandowski (Barcelona)
INSERT INTO goal (match_id, player_id)
SELECT
    (SELECT MAX(id) FROM match),
    p.id
FROM player p
         JOIN team t ON t.id = p.team_id
WHERE p.first_name = 'Robert'
  AND p.last_name  = 'Lewandowski'
  AND t.name       = 'FC Barcelona'
LIMIT 1;

-- MATCH 3 (Bayern Munich vs. Ajax)
INSERT INTO match(id, home_team_id, away_team_id, match_date, home_score, away_score)
VALUES (
           (SELECT COALESCE(MAX(id),0) + 1 FROM match),
           (SELECT id FROM team WHERE name = 'Bayern Munich'),
           (SELECT id FROM team WHERE name = 'Ajax'),
           '2023-09-20T18:30:00',
           4,
           0
       );

-- goals for that match…
INSERT INTO goal(match_id, player_id)
SELECT (SELECT MAX(id) FROM match), p.id
FROM player p JOIN team t ON t.id = p.team_id
WHERE p.first_name='Harry' AND p.last_name='Kane' AND t.name='Bayern Munich'
LIMIT 2;  -- two goals

-- MATCH 4 (Liverpool vs. Inter Milan)
INSERT INTO match(id, home_team_id, away_team_id, match_date, home_score, away_score)
VALUES (
           (SELECT COALESCE(MAX(id),0) + 1 FROM match),
           (SELECT id FROM team WHERE name = 'Liverpool'),
           (SELECT id FROM team WHERE name = 'Inter Milan'),
           '2023-10-05T19:00:00',
           2,
           1
       );

-- goals…
INSERT INTO goal(match_id, player_id)
SELECT (SELECT MAX(id) FROM match), p.id
FROM player p JOIN team t ON t.id = p.team_id
WHERE p.first_name='Mohamed' AND p.last_name='Salah' AND t.name='Liverpool'
LIMIT 1;

INSERT INTO goal(match_id, player_id)
SELECT (SELECT MAX(id) FROM match), p.id
FROM player p JOIN team t ON t.id = p.team_id
WHERE p.first_name='Darwin' AND p.last_name='Nunez' AND t.name='Liverpool'
LIMIT 1;

INSERT INTO goal(match_id, player_id)
SELECT (SELECT MAX(id) FROM match), p.id
FROM player p JOIN team t ON t.id = p.team_id
WHERE p.first_name='Lautaro' AND p.last_name='Martinez' AND t.name='Inter Milan'
LIMIT 1;
