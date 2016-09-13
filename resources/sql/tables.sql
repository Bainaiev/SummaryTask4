-- -----------------------------------------------------
-- Schema java_course
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS java_course;
CREATE SCHEMA IF NOT EXISTS java_course
  DEFAULT CHARACTER SET utf8;
USE java_course;

-- -----------------------------------------------------
-- Table java_course.user
-- -----------------------------------------------------
DROP TABLE IF EXISTS java_course.user;

CREATE TABLE IF NOT EXISTS java_course.user (
  id         INT(11)     NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name  VARCHAR(45) NOT NULL,
  login      VARCHAR(45) NOT NULL,
  password   VARCHAR(45) NOT NULL,
  email      VARCHAR(45) NOT NULL,
  status     VARCHAR(45) NOT NULL DEFAULT 'ACTIVE',
  image      VARCHAR(45) NOT NULL DEFAULT 'noimage.jpg',
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC),
  UNIQUE INDEX login_UNIQUE (login ASC),
  UNIQUE INDEX email_UNIQUE (email ASC),
  INDEX login_password_index (login ASC, password ASC)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.test
-- -----------------------------------------------------
DROP TABLE IF EXISTS java_course.test;

CREATE TABLE IF NOT EXISTS java_course.test (
  id           INT(11)     NOT NULL AUTO_INCREMENT,
  title        VARCHAR(45) NOT NULL,
  complexity   INT(11)     NOT NULL,
  time_passing INT(11)     NOT NULL,
  subject    VARCHAR(45) NOT NULL ,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE(id ASC)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.user_tests
-- -----------------------------------------------------
DROP TABLE IF EXISTS java_course.user_tests;

CREATE TABLE IF NOT EXISTS java_course.user_tests (
  test_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  PRIMARY KEY (test_id, user_id),
  UNIQUE INDEX user_test (test_id ASC, user_id ASC),
  CONSTRAINT fk_user_tests_test1
  FOREIGN KEY (test_id)
  REFERENCES java_course.test (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_user_tests_user1
  FOREIGN KEY (user_id)
  REFERENCES java_course.user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.question
-- -----------------------------------------------------
DROP TABLE IF EXISTS java_course.question;

CREATE TABLE IF NOT EXISTS java_course.question (
  id            INT(11)      NOT NULL AUTO_INCREMENT,
  question_text VARCHAR(400) NOT NULL,
  test_id       INT(11)      NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE(id ASC),
  CONSTRAINT fk_question_test
  FOREIGN KEY (test_id)
  REFERENCES java_course.test (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE

)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.answer
-- -----------------------------------------------------
DROP TABLE IF EXISTS java_course.answer;

CREATE TABLE IF NOT EXISTS java_course.answer (
  id          INT(11)     NOT NULL AUTO_INCREMENT,
  content     VARCHAR(45) NOT NULL,
  correct     TINYINT(1)  NOT NULL DEFAULT '0',
  question_id INT(11)     NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE(id ASC),
  UNIQUE INDEX content_question_id (content ASC ,question_id  ASC),
  CONSTRAINT fk_answer_question
  FOREIGN KEY (question_id)
  REFERENCES java_course.question (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE

)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.user_roles
-- -----------------------------------------------------

DROP TABLE IF EXISTS java_course.user_roles;

CREATE TABLE IF NOT EXISTS java_course.user_roles (
  user_id INT(11)     NOT NULL,
  role    VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_id, role),
  UNIQUE INDEX user_role (user_id ASC, role ASC),
  CONSTRAINT fk_user_roles_user
  FOREIGN KEY (user_id)
  REFERENCES java_course.user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table java_course.user_storage
-- -----------------------------------------------------

DROP TABLE IF EXISTS java_course.storage;

CREATE TABLE IF NOT EXISTS java_course.storage (
  user_id INT(11)     NOT NULL,
  test_id INT(11)     NOT NULL,
  result  VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_id, test_id),
  UNIQUE INDEX user_role (user_id ASC, test_id ASC),
  CONSTRAINT fk_user_id
  FOREIGN KEY (user_id)
  REFERENCES java_course.user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT fk_test_id
  FOREIGN KEY (test_id)
  REFERENCES java_course.test (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;