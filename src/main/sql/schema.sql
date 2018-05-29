CREATE DATABASE dbseckill;
USE dbseckill;

-- seckill product table
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
INSERT INTO seckill_product (name, number, start_time, end_time)
VALUES
  ('Brand new iPad 2 for 300AUD', 100, '2018-06-01 00:00:00', '2018-06-02 00:00:00'),
  ('Brand new iPhone 6 for 400AUD', 200, '2018-06-01 00:00:00', '2018-06-02 00:00:00'),
  ('Brand new MacBook Air for 1000AUD', 300, '2018-06-01 00:00:00', '2018-06-02 00:00:00'),
  ('Brand new MacBook Pro for 1600AUD', 400, '2018-06-01 00:00:00', '2018-06-02 00:00:00');

-- seckill order table
CREATE TABLE IF NOT EXISTS seckill_order (
  product_id  BIGINT    NOT NULL,
  user_phone  BIGINT    NOT NULL,
  state       TINYINT   NOT NULL DEFAULT -1
  COMMENT '-1: invalid, 0: success, 1: paid, 2: shipped',
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (product_id, user_phone),
  KEY idx_create_time(create_time)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT 'seckill_order_table';