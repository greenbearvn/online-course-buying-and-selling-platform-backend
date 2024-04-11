CREATE TABLE blog (
                      blog_id INT AUTO_INCREMENT PRIMARY KEY,
                      blog_name VARCHAR(500) NOT NULL,
                      content VARCHAR(500),
                      thumbnail VARCHAR(500) NOT NULL,
                      date_publish DATE,
                      cateid INT NOT NULL,
                      user_id INT NOT NULL,
                      status INT NOT NULL
);
