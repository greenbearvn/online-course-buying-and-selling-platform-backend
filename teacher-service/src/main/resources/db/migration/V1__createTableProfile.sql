CREATE TABLE profile (
                         profile_id INT AUTO_INCREMENT PRIMARY KEY,
                         profile_name VARCHAR(500) NOT NULL,
                         email VARCHAR(500) NOT NULL,
                         phone_number VARCHAR(500) NOT NULL,
                         avatar VARCHAR(500) NOT NULL,
                         description VARCHAR(500) NOT NULL,
                         cate_id INT NOT NULL,
                         is_teacher TINYINT NOT NULL
);
