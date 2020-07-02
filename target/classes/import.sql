insert into user (nome, login, senha, email) values ('jose silva', 'jose', '$2a$10$p5Q7d6ld0y5tG.t9pR3s3uWFecBW1UgZwCHkShMCMvdQ5yGonKkKC', 'jose.silva@qqemail.com');
insert into user (nome, login, senha, email) values ('maria santos', 'maria', '$2a$10$p5Q7d6ld0y5tG.t9pR3s3uWFecBW1UgZwCHkShMCMvdQ5yGonKkKC', 'maria.santos@qqemail.com');

insert into role(nome) values ('ROLE_ADMIN');
insert into role(nome) values ('ROLE_USER');

insert into user_roles(user_id,role_id) values(1, 1);
insert into user_roles(user_id,role_id) values(2, 2);
