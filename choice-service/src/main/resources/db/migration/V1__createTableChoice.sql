CREATE TABLE choice (
         choice_id INT AUTO_INCREMENT PRIMARY KEY,
         question_id INT NOT NULL,
         choice_content VARCHAR(500),
         corrected INT NOT NULL
);
