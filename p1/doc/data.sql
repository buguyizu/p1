--2017/11/17 password: a
insert into "USERS" values('admin', '$2a$10$RUcan6t4ezVbiXb7.uhzL.E3d810ATWVRZaT5lYhWtFrGdWW5jWWS', 'TRUE', default, default, default, default, default, default, 'admin', default, 'admin', default, default);
insert into "AUTHORITIES" values('admin', 'ADMIN');

--type是-1用于记录type名称
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('-1', '01', '部门', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('-1', '02', '性别', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('-1', '03', '可用状态', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('01', '01', '财务', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('01', '02', '产品', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('02', '1', '男', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('02', '2', '女', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('03', 'true', '有效', 0);
insert into "M_CODE" ("F_TYPE", "F_CODE", "F_VALUE", "VERSION") values('03', 'false', '无效', 0);

--menu
insert into "T_MENU" values('10', '当前用户', '/user/',              'USER',  '1-0', 100, '');
insert into "T_MENU" values('11', '用户信息', '/user/info/',         'USER',  '1-1', 110, '');
insert into "T_MENU" values('12', '用户设定', '/user/setting/',      'USER',  '1-2', 120, '');
insert into "T_MENU" values('13', '用户主页', '/user/homepage/',     'USER',  '1-3', 130, '');
insert into "T_MENU" values('20', '用户管理', '/hr/',                'ADMIN', '2-0', 200, '');
insert into "T_MENU" values('21', '用户管理', '/hr/userlist/',       'ADMIN', '2-1', 210, '');
insert into "T_MENU" values('22', '权限管理', '/hr/authority/',      'ADMIN', '2-2', 220, '');
insert into "T_MENU" values('23', '责任管理', '/hr/responsibility/', 'ADMIN', '2-3', 230, '');
insert into "T_MENU" values('30', '业务管理', '/biz/',               'STAFF', '3-0', 300, '');
insert into "T_MENU" values('31', '财务管理', '/biz/finance/',       'STAFF', '3-1', 310, '');
insert into "T_MENU" values('32', '产品管理', '/biz/product/',       'STAFF', '3-2', 320, '');
insert into "T_MENU" values('90', '系统管理', '/site/',              'ADMIN', '9-0', 900, '');
insert into "T_MENU" values('91', '系统设定', '/site/setting/',      'ADMIN', '9-1', 910, '');
insert into "T_MENU" values('92', '基础数据', '/site/data/',         'ADMIN', '9-2', 920, '');
insert into "T_MENU" values('93', '流程管理', '/site/flow/',         'ADMIN', '9-3', 930, '');
insert into "T_MENU" values('94', '日志管理', '/site/log/',          'ADMIN', '9-4', 940, '');
