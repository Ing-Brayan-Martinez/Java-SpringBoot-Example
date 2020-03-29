
INSERT INTO public.users(name, password, username, created_by, updated_by, is_account_non_expired,is_account_non_locked, is_credentials_non_expired, created, updated, is_active) VALUES('system', '$2a$11$Jnh7aU3ekq1Gd0fllYEmM.yDTNF.oTGYiDGL.9B5JNM7spmY4Kjwy', 'system', null,null,'Y', 'Y', 'Y', now(), now(), 'Y');

INSERT INTO public.role (created, is_active, updated, description, name, created_by, updated_by) VALUES ('2020-02-19 14:52:35.964184', 'Y', '2020-02-19 14:52:35.964184', 'este es un rol del sistem', 'ROLE_ADMIN', 1, 1);
INSERT INTO public.role (created, is_active, updated, description, name, created_by, updated_by) VALUES ('2020-02-19 14:52:35.964184', 'Y', '2020-02-19 14:52:35.964184', 'este es un rol del sistem', 'ROLE_USER', 1, 1);

INSERT INTO public.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_role (user_id, role_id) VALUES (1, 2);

