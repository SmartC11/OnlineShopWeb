-- 插入用户数据
-- 用户密码均为 "admin" 的 bcrypt 加密值：$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.
-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code)
values(1, 'chengzijian68@gmail.com', 'Zijian', 'Cheng', 'Guangzhou', '广州市番禺区小谷围街道华南理工大学大学城校区生活区C12-440宿舍', '(86) 135-9050-0263', '510000', null, true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', null);

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code)
values(2, 'test123@test.com', 'John', 'Doe', 'New York', 'Wall Street1', '1234567890', '1234567890', null, true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', null);

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code)
values(3, 'ivan123@test.com', 'Ivan', 'Ivanov', 'Moscow', 'Tverskaya street 1', '1234567890', '1234567890', null, true, '$2a$08$eApn9x3qPiwp6cBVRYaDXed3J/usFEkcZbuc3FDa74bKOpUzHR.S.', null);

-- 插入用户角色数据
-- 用户角色的映射
insert into user_role (user_id, roles) values (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'USER');
insert into user_role (user_id, roles) values (3, 'USER');
