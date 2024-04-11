CREATE TABLE courses (
                         course_id INT AUTO_INCREMENT PRIMARY KEY,
                         course_name VARCHAR(500) NOT NULL,
                         course_thumbnail VARCHAR(500),
                         short_des VARCHAR(500),
                         full_des VARCHAR(500) NOT NULL,
                         time_published DATE NOT NULL,
                         course_duration VARCHAR(500) NOT NULL,
                         old_price DOUBLE NOT NULL,
                         percent_sale DOUBLE NOT NULL,
                         new_price DOUBLE NOT NULL,
                         id_level INT NOT NULL,
                         profile_id int not null,
                         status int not null,
                         id_detail_cate INT NOT NULL
);
