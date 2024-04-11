CREATE TABLE `orders` (
    `order_id` INT AUTO_INCREMENT PRIMARY KEY,
     `user_id` INT NOT NULL,
     `status` INT,
     `create_at` DATETIME,
     `money_total` DOUBLE
);
