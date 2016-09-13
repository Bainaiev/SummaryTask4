-- USER
INSERT INTO java_course.user (first_name, last_name, login, password, email)
VALUES ('Aleksandr', 'Bainaiev', 'adminadmin', 'adminadmin', 'www.kabal.com@mail.ru'),
  ('Anton', 'Boone', 'useruser', 'qwerty123', 'rishyepyg@infobiz.com'),
  ('Boris', 'Shepherd', 'Hatora', 'gomunkul534', 'vjer_1995@nettaxi.com'),
  ('Semen', 'Johns', 'Derlaiseo', 'kas13ters', 'sjeshe@lycos.com'),
  ('Ben', 'Knight', 'Veerva', 'swedfgthh', 'keefyod@orc.ru');

-- USER -roles

INSERT INTO java_course.user_roles (user_id, role)
VALUES (1, 'ADMIN'), (2, 'STUDENT'), (3, 'STUDENT'), (4, 'STUDENT'), (5, 'STUDENT');

-- SUBJECT
#INSERT INTO java_course.subject (title) VALUES ('Biology');
# INSERT INTO java_course.subject (title) VALUES ('biology'), ('mathematics'), ('physics');

-- TEST
INSERT INTO java_course.test (title, complexity, time_passing, subject)
VALUES
  ('testOnBiology1', 1, 10, 'BIOLOGY'),
  ('testOnBiology2', 2, 20, 'BIOLOGY'),
  ('testOnBiology3', 5, 30, 'BIOLOGY');
-- SUBJECT

#INSERT INTO java_course.subject (title) VALUES ('Mathematics');

-- TEST
INSERT INTO java_course.test (title, complexity, time_passing, subject)
VALUES
  ('Тест по арифметике (простой)',     1, 15, 'MATHEMATICS'),
  ('Тест по арифметике (продвинутый)', 2, 20, 'MATHEMATICS'),
  ('Дроби (простой)', 2, 20, 'MATHEMATICS');

-- QUESTION
INSERT INTO java_course.question (question_text, test_id)
VALUES
  ('Сколько всего сторон у трёх треугольников и одного квадрата?', 4),
  ('На цирковой арене выступали 2 клоуна, 4 обезьяны, 5 собачек, 2 кошки, 3 кролика и дрессировщица.Сколько животных было на арене?', 4),
  ('Чему равняется 3 + 2 ?',  4),
  ('Чему равняется 1 + 10 ?', 4),
  ('Чему равняется 2 + 13 ?', 4),
  ('Чему равняется 3 - 2 ?',  4),
  ('Чему равняется 3 * 2 ?',  4),
  ('Чему равняется 7 + 1 ?',  4),
  ('Чему равняется 6 / 3 ?',  4),
  ('Чему равняется 12 - 7 ?', 4);

  -- ANSWER
INSERT INTO java_course.answer (content, correct, question_id)
VALUES
  ('14', FALSE, 1), ('13', TRUE,  1), ('12', FALSE, 1), ('16', FALSE, 1),
  ('13', FALSE, 2), ('16', FALSE, 2), ('17', FALSE, 2), ('14', TRUE,  2),
  ('4',  FALSE, 3), ('3',  FALSE, 3), ('6',  FALSE, 3), ('5',  TRUE,  3),
  ('10', FALSE, 4), ('11', TRUE , 4), ('12', FALSE, 4), ('13', FALSE, 4),
  ('15', TRUE,  5), ('16', FALSE, 5), ('17', FALSE, 5), ('18', FALSE, 5),
  ('2',  FALSE, 6), ('-1', FALSE, 6), ('1',  TRUE , 6), ('0',  FALSE, 6),
  ('5',  FALSE, 7), ('6',  TRUE , 7), ('7',  FALSE, 7), ('8',  FALSE, 7),
  ('6',  FALSE, 8), ('7',  FALSE, 8), ('8',  TRUE , 8), ('5',  FALSE, 8),
  ('1',  FALSE, 9), ('2',  TRUE , 9), ('3',  FALSE, 9), ('6',  FALSE, 9),
  ('2',  FALSE, 10), ('3',  FALSE, 10), ('4',  FALSE, 10), ('5',  TRUE,  10);

-- QUESTION
INSERT INTO java_course.question (question_text, test_id)
VALUES
  ('Чему равняется 13 * 13 ?',        5),
  ('Чему равняется 128 + 13 * 7 ?',   5),
  ('Чему равняется 125 + 17 ?',       5),
  ('Чему равняется 283 -  342 / 3 ?', 5),
  ('Чему равняется 28 * 7 / 4 ?',     5),
  ('Чему равняется 33 / 5 * 25 ?',    5),
  ('Чему равняется 22 - 17 + 195?',   5),
  ('Чему равняется 22 * 22 ?',        5),
  ('Чему равняется 121 / 11 * 13 ?',  5),
  ('Чему равняется 126 / 9 ?',        5);

-- ANSWER
INSERT INTO java_course.answer (content, correct, question_id)
VALUES
  ('156', FALSE, 11), ('169', TRUE,  11), ('143', FALSE, 11), ('159', FALSE, 11),
  ('987', FALSE, 12), ('201', FALSE, 12), ('187', FALSE, 12), ('219', TRUE,  12),
  ('143', FALSE, 13), ('142', TRUE,  13), ('147', FALSE, 13), ('133', FALSE, 13),
  ('169', TRUE,  14), ('-20', FALSE, 14), ('181', FALSE, 14), ('177', FALSE, 14),
  ('39',  FALSE, 15), ('25',  FALSE, 15), ('56',  FALSE, 15), ('49',  TRUE,  15),
  ('165', TRUE,  16), ('135', FALSE, 16), ('175', FALSE, 16), ('145', FALSE, 16),
  ('201', FALSE, 17), ('200', TRUE,  17), ('202', FALSE, 17), ('203', FALSE, 17),
  ('464', FALSE, 18), ('424', FALSE, 18), ('484', TRUE,  18), ('444', FALSE, 18),
  ('171', FALSE, 19), ('135', FALSE, 19), ('153', FALSE, 19), ('143', TRUE,  19),
  ('14',  TRUE,  20), ('16',  FALSE, 20), ('18',  FALSE, 20), ('15',  FALSE, 20);

-- QUESTION
INSERT INTO java_course.question (question_text, test_id)
VALUES
  ('Чему равняется 1/2 + 2/3 ?',       6),
  ('Чему равняется 1/2 - 1/3 ?',       6),
  ('Чему равняется 3/2 * 4/9  ?',      6),
  ('Чему равняется 1/2 / 3/5 ?',       6),
  ('Чему равняется 1/2 * 1/4 ?',       6),
  ('Чему равняется 11/3 - 5/6 ?',      6),
  ('Чему равняется 1/3 * 3/7 - 1/7 ?', 6),
  ('Чему равняется 17/5  * 15/17 ?',   6),
  ('Чему равняется 4 * 2/3 ?',         6),
  ('Чему равняется 7/11 - 11/7 ?',     6);

-- ANSWER
INSERT INTO java_course.answer (content, correct, question_id)
VALUES
  ('7/6',     TRUE,   21), ('3/5',   FALSE,  21), ('4/3',     FALSE, 21), ('3/2',    FALSE,  21),
  ('-2/3',    FALSE,  22), ('-1/3',  FALSE,  22), ('1/3',     FALSE, 22), ('1/6',    TRUE,   22),
  ('3/2',     FALSE,  23), ('2/3',   TRUE,   23), ('7/11',    FALSE, 23), ('3/4',    FALSE,  23),
  ('3/10',    FALSE,  24), ('5/6',   TRUE,   24), ('6/5',     FALSE, 24), ('3/2',    FALSE,  24),
  ('1/6',     FALSE,  25), ('1/8',   TRUE,   25), ('3/4',     FALSE, 25), ('3/2',    FALSE,  25),
  ('16/9',    FALSE,  26), ('8/3',   FALSE,  26), ('14/6',    FALSE, 26), ('17/6',   TRUE,   26),
  ('1/2',     FALSE,  27), ('2/21',  FALSE,  27), ('3/10',    FALSE, 27), ('0',      TRUE,   27),
  ('3',       TRUE,   28), ('2',     FALSE,  28), ('4',       FALSE, 28), ('2/5',    FALSE,  28),
  ('6/3',     FALSE,  29), ('8/3',   TRUE,   29), ('4/3',     FALSE, 29), ('2/3',    FALSE,  29),
  ('-63/77',  FALSE,  30), ('13/77', FALSE,  30), ('-75-77',  TRUE,  30), ('-61/77', FALSE,  30);

