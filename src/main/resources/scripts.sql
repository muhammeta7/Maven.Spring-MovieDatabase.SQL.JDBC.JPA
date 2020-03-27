

SELECT * FROM movies WHERE genre = 'Sci-Fi';
SELECT * FROM moveies WHERE imdb_score > 6.4;
SELECT * FROM movies WHERE rating IN('G', 'PG') AND runtime < 100;
SELECT AVG(runtime), genre FROM movies WHERE imdb_score < 7.5 GROUP BY genre;
UPDATE movies SET rating = 'R' WHERE title = 'Starship Troopers';
SELECT id, rating FROM movies WHERE genre IN('Horror','Documentary');
SELECT rating, AVG(imdb_score), MAX(imdb_score), MIN(imdb_score) FROM movies GROUP BY rating;
SELECT rating, AVG(imdb_score), MAX(imdb_score), MIN(imdb_score) FROM movies GROUP BY rating HAVING COUNT(*) > 1;
DELETE FROM movies WHERE rating = 'R';



