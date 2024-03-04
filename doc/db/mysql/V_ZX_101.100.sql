-- demo.t_shop definition

CREATE TABLE `t_shop` (
  `id` bigint(20) NOT NULL COMMENT '店铺id',
  `name` varchar(255) NOT NULL COMMENT '商铺名称',
  `address` varchar(255) NOT NULL COMMENT '商铺地址',
  `cost` bigint(20) DEFAULT '0' COMMENT '店铺成本',
  `category` varchar(50) DEFAULT NULL COMMENT '商铺分类',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态;1:启用;0:禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- demo.t_shop_item definition

CREATE TABLE `t_shop_item` (
  `id` bigint(20) NOT NULL COMMENT '商品id',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `price` bigint(20) NOT NULL COMMENT '商品价格',
  `item_desc` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品说明',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态;1:启用;0:禁用',
  `pic_id` bigint(20) DEFAULT NULL COMMENT '图片id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;