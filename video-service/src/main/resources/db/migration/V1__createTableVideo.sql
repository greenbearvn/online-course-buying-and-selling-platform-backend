CREATE TABLE videos (
                        video_id INT AUTO_INCREMENT PRIMARY KEY,
                        video_name VARCHAR(500),
                        video_content VARCHAR(500),
                        video_link VARCHAR(500),
                        video_duration VARCHAR(500),
                        lession_id INT NOT NULL
);