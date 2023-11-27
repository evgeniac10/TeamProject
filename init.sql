CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    userid VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE user_role (
    user_id BIGINT PRIMARY KEY,
    role_name VARCHAR(10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);
CREATE TABLE bookmark (
    bookmark_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    departure_station VARCHAR(255) NOT NULL,
    arrival_station VARCHAR(255) NOT NULL,
    time VARCHAR(255),
    cost DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 더미 데이터 삽입
INSERT INTO user (email, userid, password) VALUES
    ('user1@example.com', 'user1', 'password1'),
    ('user2@example.com', 'user2', 'password2'),
    ('user3@example.com', 'user3', 'password3'),
    ('user4@example.com', 'user4', 'password4'),
    ('user5@example.com', 'user5', 'password5');

INSERT INTO user_role (user_id, role_name) VALUES
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_USER'),
    (4, 'ROLE_USER'),
    (5, 'ROLE_USER');

INSERT INTO bookmark (user_id, departure_station, arrival_station, time, cost) VALUES
    (1, '101', '104', '10:00', 2000),
    (1, '301', '102', '14:30', 1500),
    (2, '402', '201', '09:45', 1800),
    (3, '112', '701', '12:15', 2230),
    (4, '123', '302', '16:00', 1250);

