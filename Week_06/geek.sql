CREATE TABLE `geek_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(50) NOT NULL DEFAULT '' COMMENT '手机号',
  `age` int(3) NOT NULL DEFAULT '0' COMMENT '年龄',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 1 正常 0 删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `geek_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '商品类型',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '商品描述',
  `amount` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0 下线  1 上线',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `geek_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `mobile` varchar(50) NOT NULL COMMENT '手机号',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `pay_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '支付价格',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0 未支付 1 已支付 2 已发货 3已收货 4 退货',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

