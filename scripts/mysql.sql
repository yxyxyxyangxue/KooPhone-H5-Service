-- 139.196.180.61
-- dell-/v0Q61f0
create database if not exists kp_freetraffic_db character set utf8 collate utf8_general_ci;

drop table if exists kp_freetraffic_order;
create table if not exists kp_freetraffic_order (
    id bigint(20) unsigned not null auto_increment comment '主键',
    telephone char(32) not null comment '手机号',
    order_no bigint(20) not null comment '订单号',
    source_order_no char(32) not null comment '来源订单id',
    activity_id bigint(20) comment '活动ID',
    idx int(10) comment '履约次数',
    sku_code varchar(32) comment '商品编码',
    sku_name varchar(32) comment '商品名称',
    status int(4) not null comment '订单状态 50-成功，60-失败',
    expected_execute_time datetime comment '期望执行时间',
    actual_execute_time datetime comment '实际执行时间',
    biz_code varchar(32) comment '业务码',
    biz_desc varchar(32) comment '业务码描述',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    primary key (id) using btree,
    unique index `uq_telephone_source_order_no`(`telephone`, `source_order_no`) using btree,
    unique index `uq_telephone`(`telephone`) using btree
) engine = InnoDB character set = utf8 collate = utf8_general_ci comment = '免流订单表' row_format = dynamic;