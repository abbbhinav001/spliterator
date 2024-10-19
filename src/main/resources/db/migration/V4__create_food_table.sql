-- Food table
CREATE TABLE IF NOT EXISTS `foods` (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    rest_id INT,
    price FLOAT,
    PRIMARY KEY (id),
    FOREIGN KEY (rest_id) REFERENCES restaurants(id)
)
