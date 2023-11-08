##초기 시작 테이블 스크립트

CREATE TABLE `user` (
    user_no INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(20) NOT NULL,
    login_type TINYINT NOT NULL
);
CREATE TABLE user_profile ( ##유저 정보를 담는 테이블
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT UNIQUE,
    user_id VARCHAR(20) NOT NULL,
    user_password VARCHAR(128) NOT NULL,
    join_date DATETIME NOT NULL,
    update_date DATETIME,
    FOREIGN KEY (user_no) REFERENCES `user`(user_no)
);
CREATE TABLE favorite (##즐겨찾기 테이블
    fav_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    Type TINYINT,
    create_date DATETIME NOT NULL,
    update_date DATETIME,
    FOREIGN KEY (user_no) REFERENCES `user`(user_no)
);

-- 더미 데이터 추가

-- user 테이블에 더미 데이터 추가
INSERT INTO `user` (user_email, login_type) VALUES
    ('user1@example.com', 1),
    ('user2@example.com', 2),
    ('user3@example.com', 1);


-- user_profile 테이블에 더미 데이터 추가
INSERT INTO user_profile (user_no, user_id, user_password, join_date, update_date) VALUES
    (1, 'user1_id', 'user1_password', NOW(), NOW()),
    (2, 'user2_id', 'user2_password', NOW(), NOW()),
    (3, 'user3_id', 'user3_password', NOW(), NOW());

-- favorite 테이블에 더미 데이터 추가
INSERT INTO favorite (user_no, Type, create_date, update_date) VALUES
    (1, 1, NOW(), NOW()),
    (2, 2, NOW(), NOW()),
    (3, 1, NOW(), NOW());