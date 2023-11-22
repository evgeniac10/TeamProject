-- init.sql

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
USE wetro;

INSERT INTO user (id, userid, password, email) VALUES
    (1, 'user1', '1234', 'user1@naver.com'),
    (2, 'user2', '1234', 'user2@naver.com'),
    (3, 'user3', '1234', 'user3@naver.com'),
    (4, 'user4', '1234', 'user4@naver.com'),
    (5, 'user5', '1234', 'user5@naver.com');