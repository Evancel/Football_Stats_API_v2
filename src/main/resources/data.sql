-- Drop existing data (optional for resetting state)
TRUNCATE TABLE goal, match, coach, player, team RESTART IDENTITY CASCADE;

-- Insert Manchester City
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Manchester City', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Ruben', 'Amorim', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Manchester City'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
    ('Ederson', 'Moraes', (SELECT id FROM team_id), 0, 0),
    ('Ruben', 'Dias', (SELECT id FROM team_id), 0, 0),
    ('Joao', 'Cancelo', (SELECT id FROM team_id), 0, 0),
    ('Rodri', 'Hernandez', (SELECT id FROM team_id), 0, 0),
    ('Kevin', 'De Bruyne', (SELECT id FROM team_id), 0, 0),
    ('Phil', 'Foden', (SELECT id FROM team_id), 0, 0),
    ('Jack', 'Grealish', (SELECT id FROM team_id), 0, 0),
    ('Ilkay', 'Gundogan', (SELECT id FROM team_id), 0, 0),
    ('Erling', 'Haaland', (SELECT id FROM team_id), 0, 0),
    ('Riyad', 'Mahrez', (SELECT id FROM team_id), 0, 0),
    ('Bernardo', 'Silva', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Liverpool
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Liverpool', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Arne', 'Slot', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Liverpool'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Alisson', 'Becker', (SELECT id FROM team_id), 0, 0),
    ('Virgil', 'van Dijk', (SELECT id FROM team_id), 0, 0),
    ('Andrew', 'Robertson', (SELECT id FROM team_id), 0, 0),
    ('Trent', 'Alexander-Arnold', (SELECT id FROM team_id), 0, 0),
    ('Fabinho', 'Tavares', (SELECT id FROM team_id), 0, 0),
    ('Thiago', 'Alcantara', (SELECT id FROM team_id), 0, 0),
    ('Mohamed', 'Salah', (SELECT id FROM team_id), 0, 0),
    ('Luis', 'Diaz', (SELECT id FROM team_id), 0, 0),
    ('Cody', 'Gakpo', (SELECT id FROM team_id), 0, 0),
    ('Darwin', 'Nunez', (SELECT id FROM team_id), 0, 0),
    ('Diogo', 'Jota', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Real Madrid
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Real Madrid', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Carlo', 'Ancelotti', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Real Madrid'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Thibaut', 'Courtois', (SELECT id FROM team_id), 0, 0),
    ('Eder', 'Militao', (SELECT id FROM team_id), 0, 0),
    ('David', 'Alaba', (SELECT id FROM team_id), 0, 0),
    ('Ferland', 'Mendy', (SELECT id FROM team_id), 0, 0),
    ('Luka', 'Modric', (SELECT id FROM team_id), 0, 0),
    ('Toni', 'Kroos', (SELECT id FROM team_id), 0, 0),
    ('Federico', 'Valverde', (SELECT id FROM team_id), 0, 0),
    ('Vinicius', 'Junior', (SELECT id FROM team_id), 0, 0),
    ('Rodrygo', 'Goes', (SELECT id FROM team_id), 0, 0),
    ('Karim', 'Benzema', (SELECT id FROM team_id), 0, 0),
    ('Marco', 'Asensio', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert FC Barcelona
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('FC Barcelona', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Hansi', 'Flick', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'FC Barcelona'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Marc-André', 'ter Stegen', (SELECT id FROM team_id), 0, 0),
    ('Jules', 'Kounde', (SELECT id FROM team_id), 0, 0),
    ('Ronald', 'Araujo', (SELECT id FROM team_id), 0, 0),
    ('Andreas', 'Christensen', (SELECT id FROM team_id), 0, 0),
    ('Alejandro', 'Balde', (SELECT id FROM team_id), 0, 0),
    ('Sergio', 'Busquets', (SELECT id FROM team_id), 0, 0),
    ('Frenkie', 'de Jong', (SELECT id FROM team_id), 0, 0),
    ('Gavi', 'Paez', (SELECT id FROM team_id), 0, 0),
    ('Pedri', 'Gonzalez', (SELECT id FROM team_id), 0, 0),
    ('Ousmane', 'Dembele', (SELECT id FROM team_id), 0, 0),
    ('Robert', 'Lewandowski', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Bayern Munich
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Bayern Munich', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Vincent', 'Kompany', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Bayern Munich'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Manuel', 'Neuer', (SELECT id FROM team_id), 0, 0),
    ('Matthijs', 'de Ligt', (SELECT id FROM team_id), 0, 0),
    ('Lucas', 'Hernandez', (SELECT id FROM team_id), 0, 0),
    ('Alphonso', 'Davies', (SELECT id FROM team_id), 0, 0),
    ('Joshua', 'Kimmich', (SELECT id FROM team_id), 0, 0),
    ('Leon', 'Goretzka', (SELECT id FROM team_id), 0, 0),
    ('Thomas', 'Muller', (SELECT id FROM team_id), 0, 0),
    ('Kingsley', 'Coman', (SELECT id FROM team_id), 0, 0),
    ('Sadio', 'Mane', (SELECT id FROM team_id), 0, 0),
    ('Serge', 'Gnabry', (SELECT id FROM team_id), 0, 0),
    ('Harry', 'Kane', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Ajax
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Ajax', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Francesco', 'Farioli', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Ajax'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Pascal', 'Struijk', (SELECT id FROM team_id), 0, 0),
    ('Jurrien', 'Timber', (SELECT id FROM team_id), 0, 0),
    ('Daley', 'Blind', (SELECT id FROM team_id), 0, 0),
    ('Edson', 'Alvarez', (SELECT id FROM team_id), 0, 0),
    ('Davy', 'Klaassen', (SELECT id FROM team_id), 0, 0),
    ('Steven', 'Bergwijn', (SELECT id FROM team_id), 0, 0),
    ('Antony', 'Martinez', (SELECT id FROM team_id), 0, 0),
    ('Mohammed', 'Kudus', (SELECT id FROM team_id), 0, 0),
    ('Brian', 'Brovets', (SELECT id FROM team_id), 0, 0),
    ('Lassina', 'Traore', (SELECT id FROM team_id), 0, 0),
    ('Kasper', 'Dolberg', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Inter Milan
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Inter Milan', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Simone', 'Inzagh', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Inter Milan'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Samir', 'Handanovic', (SELECT id FROM team_id), 0, 0),
    ('Milan', 'Skriniar', (SELECT id FROM team_id), 0, 0),
    ('Alessandro', 'Bastoni', (SELECT id FROM team_id), 0, 0),
    ('Denzel', 'Dumfries', (SELECT id FROM team_id), 0, 0),
    ('Nicolo', 'Barella', (SELECT id FROM team_id), 0, 0),
    ('Marcelo', 'Brozovic', (SELECT id FROM team_id), 0, 0),
    ('Hakan', 'Calhanoglu', (SELECT id FROM team_id), 0, 0),
    ('Lautaro', 'Martinez', (SELECT id FROM team_id), 0, 0),
    ('Romelu', 'Lukaku', (SELECT id FROM team_id), 0, 0),
    ('Joaquin', 'Correa', (SELECT id FROM team_id), 0, 0),
    ('Edin', 'Dzeko', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);


-- Insert Paris Saint-Germain
WITH team_insert AS (
    INSERT INTO team (name, goal_scored) VALUES ('Paris Saint-Germain', 0)
    RETURNING id
)
INSERT INTO coach (first_name, last_name, team_id, played_matches)
SELECT 'Luis', 'Enrique', id, 0 FROM team_insert;

WITH team_id AS (
    SELECT id FROM team WHERE name = 'Paris Saint-Germain'
)
INSERT INTO player (first_name, last_name, team_id, goals, played_matches)
SELECT * FROM (
    VALUES
('Gianluigi', 'Donnarumma', (SELECT id FROM team_id), 0, 0),
    ('Achraf', 'Hakimi', (SELECT id FROM team_id), 0, 0),
    ('Marquinhos', 'Correa', (SELECT id FROM team_id), 0, 0),
    ('Sergio', 'Ramos', (SELECT id FROM team_id), 0, 0),
    ('Presnel', 'Kimpembe', (SELECT id FROM team_id), 0, 0),
    ('Marco', 'Verratti', (SELECT id FROM team_id), 0, 0),
    ('Vitinha', 'Machado', (SELECT id FROM team_id), 0, 0),
    ('Neymar', 'Jr', (SELECT id FROM team_id), 0, 0),
    ('Kylian', 'Mbappe', (SELECT id FROM team_id), 0, 0),
    ('Lionel', 'Messi', (SELECT id FROM team_id), 0, 0),
    ('Fabian', 'Ruiz', (SELECT id FROM team_id), 0, 0)
) AS players(first_name, last_name, team_id, goals, played_matches);

COMMIT;