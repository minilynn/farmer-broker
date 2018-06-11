-- 创建farmerbroker数据库实例
create database if not exists farmerbroker default charset utf8 collate utf8_general_ci;

-- 农场主表
create table if not exists farmerbroker.farmer (
    id bigint unsigned not null auto_increment, -- 农场主编号
    primary key(id)
);

-- 区域代理商表
create table if not exsits farmerbroker.broker (

);

-- 商品信息表
create table if not exists farmerbroker.goods (
    id bigint unsigned not null auto_increment, -- 商品编号
    type char(5) not null, -- 商品类型，10001-有机蔬菜，10002-有机水果，00002--水果，10003--有机肉类，10004--有机鱼类
    status tinyint unsigned not null, -- 商品状态，0-无效商品，1-新建待审核，2-代理商审批通过，待终审，3-审批通过，待出售，4-暂停出售，5-已售罄，6-已删除
    unit_type tinyint unsigned not null, -- 销售单位，1-个，2-斤
    price decimal(8,2) not null, -- 商品单价
    unit_count smallint unsigned not null, -- 商品库存量
    name varchar(50) not null, -- 商品名称
    area varchar(80) not null, -- 商品所在区域
    farmer_id int unsigned not null, -- 商品所属农场主编号
    farmer_rank tinyint unsigned not null, -- 此刻农场主星级
    create_time datetime not null, -- 商品创建时间
    available_till date not null, -- 商品有效日期
    description varchar(1000), -- 商品描述信息
    primary key(id),
    key idx_area(area)
);

-- 用户表
create table if not exists farmerbroker.customer (

);
   