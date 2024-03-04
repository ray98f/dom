-- ---------------- magic_api相关 ----------------
CREATE TABLE `magic_api_file` (
  `file_path` varchar(512) NOT NULL,
  `file_content` mediumtext,
  PRIMARY KEY (`file_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `magic_api_backup` (
  `id` varchar(32) NOT NULL COMMENT '原对象ID',
  `create_date` bigint(13) NOT NULL COMMENT '备份时间',
  `tag` varchar(32) DEFAULT NULL COMMENT '标签',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `name` varchar(64) DEFAULT NULL COMMENT '原名称',
  `content` blob COMMENT '备份内容',
  `create_by` varchar(64) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`,`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 审计日志
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL COMMENT 'id',
  `business_name` varchar(32) DEFAULT NULL COMMENT '项目名',
  `function_name` varchar(64) DEFAULT NULL COMMENT '功能名',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `method_name` varchar(128) DEFAULT NULL COMMENT '方法名',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名',
  `thread_user` json DEFAULT NULL COMMENT '当前用户对象JSON',
  `parameters` json DEFAULT NULL COMMENT '请求参数对象JSON',
  `tid` varchar(128) DEFAULT '--' COMMENT 'tid',
  `version` varchar(32) DEFAULT NULL COMMENT '版本号',
  `consuming_time` int(11) DEFAULT NULL COMMENT '耗时',
  `return_obj` json DEFAULT NULL COMMENT '返回对象JSON',
  `status` tinyint(1) DEFAULT '0' COMMENT '返回状态是否是成功',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求IP地址',
  `elements` longtext COMMENT '异常数据JSON',
  `exception_message` text COMMENT '异常消息数据',
  `env` varchar(255) DEFAULT NULL COMMENT '环境参数',
  `api` text COMMENT 'api地址',
  `type` tinyint(1) DEFAULT '1' COMMENT '日志类型:1-AOP拦截,2-过程中的日志,3-自定义日志',
  `user_name` varchar(255) DEFAULT '--' COMMENT '用户名称',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operation_type` char(50) DEFAULT '--' COMMENT '操作类型',
  `client_id` char(200) DEFAULT '--' COMMENT '终端',
  `client_name` varchar(255) DEFAULT NULL COMMENT '终端名称',
  `user_id` char(50) DEFAULT NULL COMMENT '用户ID',
  `create_by`         varchar(64)     default ''                 comment '创建者',
  `create_time`       datetime                                   comment '创建时间',
  `update_by`         varchar(64)     default ''                 comment '更新者',
  `update_time`       datetime                                   comment '更新时间',
  `tenant_id` char(50) DEFAULT '--' COMMENT '租户id',
  `row_version` int DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `start_time` (`start_time`) USING BTREE,
  KEY `client_id` (`client_id`(191)) USING BTREE,
  KEY `function_name` (`function_name`) USING BTREE,
  KEY `tid` (`tid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='请求日志';

create table sys_file
(
    id          bigint       not null comment 'id'
        primary key,
    file_name   varchar(255) null comment '文件名称',
    file_url    varchar(255) null comment '资源url',
    create_by   bigint       null comment '创建用户',
    create_time datetime     null comment '创建时间',
    update_by   bigint       null comment '创建用户',
    update_time datetime     null comment '创建时间',
    is_delete   int   DEFAULT 0  null comment '0正常，1删除'
);