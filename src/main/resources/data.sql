INSERT INTO roles VALUES (1, 'Пациент', 'USER') ON CONFLICT DO NOTHING;
INSERT INTO roles VALUES (2, 'Врач', 'DOCTOR') ON CONFLICT DO NOTHING;

INSERT INTO users (id, "about", "address", "email", "name", "password", "phone", "edu", "prof")
VALUES(1, 'Инфо о себе', 'г. Саратов', 'user@gmail.com', 'Марина',
'$2a$10$XDNIBDS1AWxANKP9/o9VvOZsBaJ9xloPtU648IK07Hf7oT.hFwv2y', '+79381453123', '', '') ON CONFLICT DO NOTHING;
INSERT INTO users (id, "about", "address", "email", "name", "password", "phone", "edu", "prof")
VALUES(2, 'Инфо о себе', 'г. Вольск', 'doctor@gmail.com', 'Лариса',
'$2a$10$XDNIBDS1AWxANKP9/o9VvOZsBaJ9xloPtU648IK07Hf7oT.hFwv2y', '+79381453123', 'Высшее (Клиническая хирургия)', 'Хирург-ортопед') ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, roles_id) VALUES(1, 1) ON CONFLICT DO NOTHING;
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 2) ON CONFLICT DO NOTHING;

INSERT INTO symptoms VALUES (1, 'Боль в животе') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (2, 'Сыпь на коже') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (3, 'Высокая температура') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (4, 'Кашель') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (5, 'Боль в горле') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (6, 'Тошнота') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (7, 'Сонливость') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (8, 'Бессоница') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (9, 'Головокружение') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (10, 'Чихание') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (11, 'Изжога') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (12, 'Аномалии давления') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (13, 'Отеки') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (14, 'Кожный зуд') ON CONFLICT DO NOTHING;
INSERT INTO symptoms VALUES (15, 'Онемение') ON CONFLICT DO NOTHING;