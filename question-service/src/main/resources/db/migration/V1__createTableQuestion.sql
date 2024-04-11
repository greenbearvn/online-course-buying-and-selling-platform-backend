CREATE TABLE question (
                          question_id INT AUTO_INCREMENT PRIMARY KEY,
                          test_id INT NOT NULL,
                          question_description VARCHAR(500),
                          suggestion VARCHAR(500)
);
