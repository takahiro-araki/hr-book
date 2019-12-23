CREATE TABLE USERS(
user_id serial primary key,
emp_id integer unique not null,
user_name varchar(20) not null,
mail_address varchar(300) not null,
passward varchar(20) not null,
user_roll integer not null,
act_status integer not null
);

CREATE TABLE HUMANS(
human_id serial primary key,
user_id integer references USERS(user_id),
emp_id integer unique not null,
human_name varchar(20) not null,
join_date date not null,
assign_company_name varchar(50),
icon_img varchar(200),
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);

CREATE TABLE ORDERS(
order_id serial primary key,
human_id integer references HUMANS(human_id),
order_status integer not null,
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);

CREATE TABLE BASE_SKILLS(
base_skill_id serial primary key,
base_skill_name varchar(20) not null,
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);


CREATE TABLE HUMAN_BASE_SKILLS(
human_base_skill_id serial primary key,
human_id integer references HUMANS(human_id),
base_skill_id integer references BASE_SKILLS(base_skill_id),
base_skill_score integer not null,
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);

CREATE TABLE SUB_SKILLS(
sub_skill_id serial primary key,
sub_skill_name varchar(20) not null,
description varchar(300),
sub_skill_status_type integer not null,
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);

CREATE TABLE HUMAN_SUB_SKILLS(
human_sub_skill_id serial primary key,
human_id integer references HUMANS(human_id),
sub_skill_id  integer references SUB_SKILLS(sub_skill_id),
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);

CREATE TABLE COMMON_SKILLS(
common_skill_id serial primary key,
common_skill_name varchar(20) not null,
description varchar(300),
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);


CREATE TABLE HUMAN_COMMON_SKILLS(
human_common_skill_id serial primary key,
human_id integer references HUMANS(human_id),
common_skill_id integer references COMMON_SKILLS(common_skill_id),
common_skill_score integer not null,
act_status integer not null,
version_num integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);


CREATE TABLE PRE_HUMAN_BASE_SKILLS(
pre_human_base_skill_id serial primary key,
order_id integer references ORDERS(order_id),
base_skill_id integer references BASE_SKILLS(base_skill_id),
base_skill_score integer not null,
act_status integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);


CREATE TABLE PRE_HUMAN_SUB_SKILLS(
pre_human_sub_skill_id serial primary key,
order_id integer references ORDERS(order_id),
sub_skill_id integer references SUB_SKILLS(sub_skill_id),
act_status integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);


CREATE TABLE PRE_HUMAN_COMMON_SKILLS(
pre_human_common_skill_id serial primary key,
order_id integer references ORDERS(order_id),
common_skill_id integer references COMMON_SKILLS(common_skill_id),
common_skill_score integer not null,
act_status integer not null,
register varchar(20) not null,
regist_date TIMESTAMP not null,
renewer varchar(20),
renew_date TIMESTAMP
);