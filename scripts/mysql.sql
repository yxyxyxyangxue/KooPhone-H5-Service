-- 139.196.180.61
-- dell-/v0Q61f0
create database if not exists kp_freetraffic_db character set utf8 collate utf8_general_ci;

drop table if exists kp_freetraffic_order;
create table if not exists kp_freetraffic_order (
    id bigint(20) unsigned not null auto_increment comment '主键',
    telephone varchar(32) comment '手机号',
    user_pseudo_code varchar(64) not null comment '用户伪码',
    order_id varchar(20) comment '移动内部订购唯一标识',
    channel_seq_id varchar(64) comment '外部交易ID',
    price int(10) comment '资费（单位-分）',
    action_time char(14) comment '操作时间',
    action_id int(4) comment '1-订购，2-退订，3-暂停，4-激活',
    effective_time varchar(14) comment '生效时间',
    expire_time varchar(14) comment '失效时间',
    effective_real_time char(14) comment '生效时间',
    expire_real_time char(14) comment '失效时间',
    pay_type int(4) comment '计费类型，1-自动续费，2-到期关闭',
    channel_id varchar(32) comment '渠道合作方编码',
    product_id varchar(32) comment '产品编码',
    order_type int(4) comment '订购类型，0-测试，1-正式',
    return_status int(4) comment '订购结果状态值，0-成功，1-失败',
    prov_code char(4) comment '省编码',
    order_time char(14) comment '用户发起的订购时间',
    app_id varchar(32) comment '应用id',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    primary key (id) using btree,
    key `idx_telephone`(`telephone`) using btree,
    unique index `uq_user_pseudo_code_product_id`(`user_pseudo_code`, `product_id`) using btree
) engine = InnoDB character set = utf8 collate = utf8_general_ci comment = '免流订单表' row_format = dynamic;