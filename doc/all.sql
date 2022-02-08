drop table if exists `auth_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(64) NOT NULL COMMENT '登陆名',
  `name_cn` varchar(64) DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '盐',
  `locked` tinyint(1) DEFAULT '0' COMMENT '账号是否锁定，1：锁定，0未锁定',
  `account` varchar(64) DEFAULT NULL,
  `token` varchar(128) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists `project`;
CREATE TABLE `sys_project` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `project_name` varchar(100) NOT NULL COMMENT '项目名称',
  `project_type` int(4) DEFAULT '50' COMMENT '类型包括10业务线核心20中台核心30业务线其他40中台其他50测试项目',
  `auth_users_id` bigint NOT NULL COMMENT '用户权限',
  `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `belong_flag` char(1) DEFAULT '1' COMMENT '项目是公有还是私有,0私有1公有',
  `sequence` int(11) DEFAULT '0' COMMENT '排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_environment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '环境变量id',
  `project_id` bigint NOT NULL COMMENT '关联的项目ID',
  `environment_name` varchar(256) NOT NULL COMMENT '环境变量名称',
  `api_headers` mediumtext COMMENT '请求头配置',
  `api_urlParameter` mediumtext COMMENT 'url参数配置',
  `init_variables` mediumtext COMMENT '自定义变量',
  `variables_by_func` mediumtext COMMENT '自定义变量（函数）',
  `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_db_configs` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` bigint NOT NULL COMMENT '关联的项目ID',
  `name` varchar(256) NOT NULL,
  `type` varchar(64) NOT NULL,
  `ip` varchar(64) NOT NULL,
  `port` varchar(64) NOT NULL,
  `database` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_test_case` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_case_name` varchar(256) NOT NULL DEFAULT '' COMMENT '用例名称',
  `remarks` varchar(256) DEFAULT '' COMMENT '用例描述',
  `priority` int(2) DEFAULT 5 '优先级 1，2，3，4，5',
  `project_id` bigint NOT NULL COMMENT '关联的项目ID',
  `environment_id` bigint DEFAULT NULL,
  `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记'
  PRIMARY KEY (`id`)
  KEY `idx_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_test_case_result` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_case_id` bigint DEFAULT NULL COMMENT '测试用例ID',
  `collection_result_id` bigint DEFAULT NULL COMMENT '测试集执行结果ID',
  `environment_id` bigint DEFAULT NULL COMMENT '环境变量ID',
  `run_by` bigint DEFAULT NULL COMMENT '执行人ID',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `success` tinyint(1) DEFAULT '0' COMMENT '是否成功',
  `total_assertions` int(11) DEFAULT NULL COMMENT '总断言数量',
  `success_assertions` int(11) DEFAULT NULL COMMENT '成功断言数量',
  PRIMARY KEY (`id`),
  KEY `idx_test_case_id` (`test_case_id`) USING BTREE,
  KEY `idx_create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_request` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_case_id` bigint DEFAULT NULL COMMENT '测试用例ID',
  `main_type` int(1) NOT NULL DEFAULT 2 COMMENT '请求类型1 sql,2 http 3 redis' ,
  `db_config_id` bigint DEFAULT NULL COMMENT '数据库链接信息',
  `sql_str` mediumtext COMMENT 'sql语句',
  `request_name` varchar(256) DEFAULT NULL,
  `request_method` varchar(10) DEFAULT NULL,
  `request_path` varchar(1024) DEFAULT NULL,
  `request_body` mediumtext,
  `api_assertions` mediumtext,
  `api_url_parameter` mediumtext,
  `api_variables` mediumtext,
  `api_headers` mediumtext,
  `request_order` int(11) NOT NULL DEFAULT '0' COMMENT '排序值',
  `sleep_time` int(11) NOT NULL DEFAULT '0' COMMENT '等待时间',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_request_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请求';

