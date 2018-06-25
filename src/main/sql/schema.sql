CREATE DATABASE IF NOT EXISTS dbseckill;
USE dbseckill;

-- user table
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(120) NOT NULL,
  encrypt_password VARCHAR(60) DEFAULT NULL,
  user_token VARCHAR(60) DEFAULT NULL,
  fb_token VARCHAR(256) DEFAULT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id),
  UNIQUE KEY (user_token),
  UNIQUE KEY (username)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT 'user_table';

-- seckill product table
DROP TABLE IF EXISTS seckill_product;

CREATE TABLE IF NOT EXISTS seckill_product (
  product_id  BIGINT       NOT NULL AUTO_INCREMENT,
  title       VARCHAR(120) NOT NULL,
  number      INT          NOT NULL,
  start_time  TIMESTAMP    NOT NULL,
  -- default value for the first NOT NULL TIMESTAMP will be set as CURRENT_TIMESTAMP
  -- however the second will be set as zero time which may cause "invalid default value" error
  end_time    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (product_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8
  COMMENT 'seckill_product_table';

-- test data for stock table
INSERT INTO seckill_product (title, number, start_time, end_time)
VALUES
  ('Brand new iPad 2 for 300AUD', 100, '2018-06-15 00:00:00', '2018-07-01 00:00:00'),
  ('Brand new iPhone 6 for 400AUD', 200, '2018-06-15 00:00:00', '2018-07-01 00:00:00'),
  ('Brand new MacBook Air for 1000AUD', 300, '2018-07-01 00:00:00', '2018-07-02 00:00:00'),
  ('Brand new MacBook Pro for 1600AUD', 400, '2018-07-01 00:00:00', '2018-07-02 00:00:00');

-- seckill order table
DROP TABLE IF EXISTS seckill_order;

CREATE TABLE IF NOT EXISTS seckill_order (
  product_id  BIGINT    NOT NULL,
  username  VARCHAR(120),
  state       TINYINT   NOT NULL DEFAULT -1
  COMMENT '-1: invalid, 0: success, 1: paid, 2: shipped',
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (product_id, username),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT 'seckill_order_table';