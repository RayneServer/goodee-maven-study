USE maven_database;

CREATE TABLE IF NOT EXISTS  `member` (
	`member_id` VARCHAR(255) NOT NULL,
  `member_pw` VARCHAR(255) NOT NULL,
  `member_name` VARCHAR(255) NOT NULL,
  `member_email` VARCHAR(255) NOT NULL,
  `member_phone` VARCHAR(255) NOT NULL,
  `member_birth` DATE NOT NULL,
  `member_non_expired` BIT(1) NOT NULL,
  `member_non_locked` BIT(1) NOT NULL,
  `credential_non_expired` BIT(1) NOT NULL,
  `enabled` BIT(1) NOT NULL,
  
  PRIMARY KEY (`member_id`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `profile` (
  `member_id` VARCHAR(255) NOT NULL,
  `origin_name` VARCHAR(255) NOT NULL,
  `saved_name` VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (`member_id`),
  FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `notice` (
  `board_num` BIGINT NOT NULL AUTO_INCREMENT,
  `board_title` VARCHAR(255),
  `board_content` LONGTEXT,
  `board_writer` VARCHAR(255),
  `board_date` DATETIME ,
  `board_hit` BIGINT,
  PRIMARY KEY (`board_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `notice_attach` (
  `file_num` BIGINT NOT NULL AUTO_INCREMENT,
  `board_num` BIGINT NOT NULL,
  `origin_name` VARCHAR(255) NOT NULL,
  `saved_name` VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (`file_num`),
  FOREIGN KEY (`board_num`) REFERENCES `notice` (`board_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `qna` (
  `board_num` BIGINT NOT NULL AUTO_INCREMENT,
  `board_title` VARCHAR(255) NOT NULL,
  `board_content` longtext NOT NULL,
  `board_writer` VARCHAR(255) NOT NULL,
  `board_date` datetime NOT NULL,
  `board_hit` BIGINT NOT NULL,
  `board_ref` BIGINT NOT NULL,
  `board_step` BIGINT NOT NULL,
  `board_depth` BIGINT NOT NULL,
  
  PRIMARY KEY (`board_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `qna_attach` (
  `file_num` BIGINT NOT NULL AUTO_INCREMENT,
  `board_num` BIGINT NOT NULL,
  `origin_name` VARCHAR(255) NOT NULL,
  `saved_name` VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (`file_num`),
  FOREIGN KEY (`board_num`) REFERENCES `qna` (`board_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `product_kind` (
  `product_kind_num` BIGINT NOT NULL AUTO_INCREMENT,
  `product_kind_name` VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (`product_kind_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `product` (
  `product_num` BIGINT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255) NOT NULL,
  `product_content` longtext NOT NULL,
  `product_date` datetime NOT NULL,
  `product_rate` decimal(4,2) NOT NULL,
  `product_kind_num` BIGINT DEFAULT NULL,
  
  PRIMARY KEY (`product_num`),
  FOREIGN KEY (`product_kind_num`) REFERENCES `product_kind` (`product_kind_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `cart` (
  `member_id` VARCHAR(255) NOT NULL,
  `product_num` BIGINT NOT NULL,
  PRIMARY KEY (`member_id`,`product_num`),
  FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  FOREIGN KEY (`product_num`) REFERENCES `product` (`product_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `account` (
  `account_num` VARCHAR(255) NOT NULL,
  `member_id` VARCHAR(255) NOT NULL,
  `product_num` BIGINT NOT NULL,
  `account_date` date NOT NULL,
  `account_balance` BIGINT NOT NULL,
  
  PRIMARY KEY (`account_num`),
  FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  FOREIGN KEY (`product_num`) REFERENCES `product` (`product_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `role` (
  `role_num` BIGINT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(255) NOT NULL,
  
  PRIMARY KEY (`role_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;


CREATE TABLE IF NOT EXISTS `member_role` (
  `member_id` VARCHAR(255) NOT NULL,
  `role_num` BIGINT NOT NULL,
  
  PRIMARY KEY (`member_id`,`role_num`),
  FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  FOREIGN KEY (`role_num`) REFERENCES `role` (`role_num`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4;
