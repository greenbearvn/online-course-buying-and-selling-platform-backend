CREATE TABLE detailorder (
      detail_order_id INT AUTO_INCREMENT PRIMARY KEY,
      order_id INT NOT NULL,
      course_id INT NOT NULL,
      teacher_id INT NOT NULL,
      level_id INT NOT NULL,
      price DOUBLE NOT NULL
);