create user if not exists APP password 'APP';
CREATE TABLE if not exists DCT_LOCAL.IKT_ELECTRONIC_COURSES
(
    ID INT PRIMARY KEY NOT NULL,
    NAME VARCHAR2(300) NOT NULL,
    SHORTNAME VARCHAR2(80) NOT NULL,
    DEPARTMENT_ID INT NOT NULL);
CREATE UNIQUE INDEX IKT_INX ON DCT_LOCAL.IKT_ELECTRONIC_COURSES ( SHORTNAME );

INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4052, 'Дослідження операцій та методи оптимізації - 2 (6.051.04), доц. Чернова Н.Л., доц. Чаговець Л.О.', 'ДО та МО-2 (102)', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4053, 'Системи прийняття рішень (6.030502(04)), доц. Мілевський С.В.', 'МПР', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4054, 'Методи та моделі управління конкурентоспроможністю підприємств (8.051.020), доц. Мілевський С.В.', 'МтМУКП', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4056, 'Econometrics (6.03.073, 6.46.073), S. Prokopovych, S. Milevskyi', 'Econometrics', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4598, 'Проектний аналіз (спец. 6.030504(10) Економіка підприємства, 5 курс; 6.030504(12) Економіка підприємства, 3 курс; 6.051.011 Економіка підприємства, 2 курс) Іпполітова І. Я.', 'ПА(з)І.І.Я.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4095, 'Математичні методи та моделі ринкової економіки (8.051.04), доц. Івахненко О.В.', 'МММРЕ', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4134, 'Нелінійні моделі економічної динаміки (8.051.020), доц. Полякова О.Ю.', 'НМЭД', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4599, 'Корпоративне управління (спец. 8.051.060 Економіка підприємства, 1м курс) доц. Іпполітова І. Я.', 'КУ(з)І.І.Я.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4600, 'Системний аналіз в економіці (спец. 6.030504(12) Економіка підприємства, 3 курс; 6.051.011 Економіка підприємства, 2 курс) Котельникова Ю. М.', 'САвЕ(з)', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4601, 'Стратегія підприємства (спец. 6.051.062 Економіка підприємства, 1 курс; 6.051.063 Економіка підприємства, 1 курс) Котельникова Ю. М.', 'СП(з)К.Ю.М.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4602, 'Маркетинг (спец. 6.051.12 Економіка підприємства, 2 курс; 6.030504(10) Економіка підприємства, 4 курс; 6.030504(12) Економіка підприємства, 3 курс) Котельникова Ю. М.', 'М(з)К.Ю.М.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4603, 'Економіка підприємства ІІ (спец. 6.051.12 Економіка підприємства, 2 курс) Котельникова Ю. М.', 'ЕП(з)К.Ю.М.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4153, 'Електронна комерція (Вибіркова), доц. Яценко Р.М.', 'ЕлКомМАг', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4604, 'Проектний аналіз (спец. 6.030504(12) Економіка підприємства, 3 курс) Котельникова Ю. М.', 'ПА(з)К.Ю.М.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4605, 'Економічний аналіз (спец. 6.051.12 Економіка підприємства, 2 курс), доц. Ляліна Н. С.', 'ЕА(з)Л.Н.С.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4606, 'Економічне управління підприємством (спец. 8.051.060, Економіка підприємства, 1м курс), доц. Ляліна Н. С.', 'ЕУП(з)Л.Н.С.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4607, 'Антикризова діяльність підприємства (спец. 8.051.060 Економіка підприємства, 1м курс), доц. Мартіянова М. П.', 'АДП(з)', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4608, 'Потенціал і розвиток підприємства (спец. 6.030504(10) Економіка підприємства, 5 курс; 6.030504(12) Економіка підприємства, 3 курс; 6.051.11 Економіка підприємства, 2 курс) доц. Мартіянова М. П.', 'ПіРП(з)', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4199, 'Актуальні проблеми моделювання економіки (6.030502), доц. Прокопович С.В. ', 'АПМЕ', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4206, 'Методи та моделі дослідження економічних процесів (8.075.010) проф. Клебанова Т.С, доц. Чаговець Л.О.', 'ММДЕП', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4209, 'Математичні методи і моделі менеджменту персоналу (8.051.020), доц. Івахненко О.В.', 'Мат. методи і моделі менеджм. персоналу', 37);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3117, 'Страхування у зовнішньоекономічній діяльності (спец. 6.030601(47) - Менеджмент зовнішньоекономічної діяльності, курс 4), к. е. н., доц. Астахова І. Е.', 'СЗЕД030601', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3118, 'Міжнародна економічна діяльність України (спец. 6.030503(07) - Міжнародна економіка, курс 3), к.е.н., доц. Бестужева С. В.', 'МЕДУ030503', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3122, 'Менеджмент зовнішньоекономічної діяльності (спец. 8.073.060 - Менеджмент зовнішньоекономічної діяльності, курс 1), к.е.н., доц. Полякова Я. О.', 'МЗЕД03060104', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3124, 'Менеджмент і адміністрування (спец. 6.030601(47) - Менеджмент зовнішньоекономічної діяльності, курс 4), к.е.н., доц. Полякова Я. О.', 'МіА030601', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3130, 'Менеджмент (спец. 6.030503(07) - Міжнародна економіка, курс 3), к.е.н. Дзеніс О.О.', 'Менеджмент (сп."Міжнародна економіка"', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3187, 'Управління міжнародною конкурентоспроможністю підприємства (спец. 8.051.130 - Міжнародна економіка, курс 1), к.е.н., доц. Іванієнко', 'УМКП130', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3294, 'Митна справа (спец. 6.030601(47) - Менеджмент зовнішньоекономічної діяльності, курс 4), к.е.н., доц. Котиш О.М.', 'МС206', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3295, 'Зовнішньоекономічна діяльність (спец. 6.073.47 - Менеджмент зовнішньоекономічної діяльності, курс 2), к.е.н., доц. Котиш О. М.', 'ЗЕДП206', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3564, 'Глобальна економіка (спец. 8.051.130 - Міжнародна економіка, курс 1), д.е.н., проф. Липов В.В.', 'ГЕ 8.051', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (4609, 'Маркетинг (спец. 6.030504(12) Економіка підприємства, 2 курс; 6.051.12 Економіка підприємства, 3 курс) доц. Мартіянова М. П.', 'М(з)М.М.П.', 33);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3629, 'Міжнародний маркетинг (спец. 8.073.060 - Менеджмент зовнішньоекономічної діяльності, курс 1), д.е.н., проф. Шталь Т. В., к.е.н., доц. Гуржій Н. Г.', 'ММ1М206', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3630, 'Міжнародний менеджмент (спец. 8.051.130 - Міжнародна економіка, курс 1), к.е.н., доц. Гуржій Н. Г.', 'ММ1ММУ', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3631, 'Міжнародні бізнес-стратегії підприємства (спец. 8.073.060 - Менеджмент зовнішньоекономічної діяльності, курс 1),  д.е.н., проф. Шталь Т. В.', 'МБСП1М', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3632, 'Організація міжнародних маркетингових досліджень (спец. 6.030503(07) - Міжнародна економіка, курс 4), к.е.н., доц. Полякова Я.О.', 'ОММД4103', 41);
INSERT INTO DCT_LOCAL.IKT_ELECTRONIC_COURSES (ID, NAME, SHORTNAME, DEPARTMENT_ID) VALUES (3633, 'Рекламний менеджмент (спец. 6.030503(07) - Міжнародна економіка, курс 4), к.е.н., доц. Полякова Я. О.', 'РМ4103', 41);