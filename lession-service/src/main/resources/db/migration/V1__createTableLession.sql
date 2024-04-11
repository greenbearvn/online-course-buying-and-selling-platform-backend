CREATE TABLE lessions (
                          lession_id INT AUTO_INCREMENT PRIMARY KEY,
                          lession_name VARCHAR(500),
                          lession_duration VARCHAR(500),
                          course_id INT NOT NULL
);