<h1><center>物联网设备管理平台设计报告</center></h1>

## 1 编写目的

​		本项目为2023-2024 秋冬学期《B/S体系软件设计》课程项目，旨在利用课程学习的Web开发技术实现一个用于物联网设备管理平台。本文档为该项目设计文档，编写的主要目的是阐述明白平台的整体架构以及设计方案，包括平台组成部分、各部分之间关系、主要功能等。为后续开发提供一个较为清晰的指导。



## 2 项目概述

​		本项目旨在实现一个简易的物联网设备管理平台，需要接收物联网终端模拟器发送的数据并整合、显示，以帮助使用者掌握设备信息。具体的功能需求如下：

1. 用户管理：即支持用户的注册、登录功能。
2. 设备管理：支持物联网设备的创建和修改，要有对物联网设备必要信息的管理——ID，设备类型，名称，告警，不同类型的设备支持的可调节参数……
3. 设备信息管理：设备会报告不同信息，要支持对这些信息的查询
4. 数据可视化：对于物联网设备，需要提供地图界面进行设备的展示，另外，对于所有设备数据进行必要统计并通过图表进行可视化展示，方便更好地监控管理。

额外功能：由于现阶段手机才是大多数人都离不开的设备，在开发时我们需要考虑到平台对于手机浏览器的适配。



## 3 开发环境

#### 前端

​		项目前端预计采用`Vue3`，并使用`element-plus`UI库进行设计。Vue3 具有较完整的社区文档以及资源，通过`Vite` 进行快速简单的配置并启动服务，对于各种功能有较好的支持。插件市场也十分丰富。

#### 后端

​		项目后端框架拟采用`SpringBoot`.`SpringBoot` 作为基于`Spring` 的开源框架，不仅继承了`Spring` 控制反转（`IOC`），面向切面（`AOP`）等优秀思想，简化了应用的开发和部署，还简化了`Spring`应用的创建，运行，调试，部署等，使得程序员可以专注于`Spring`应用的开发而无需过多关注`XML`的配置。同样是易于初学者上手的后端开发框架。

​		另外使用`MySQL`作为本系统的数据库。本次开发系统使用的数据格式比较简单，而且`MySQL`在之前已经有相关经验，此次也能快速熟悉项目的相关开发。

​		结合`Mybatis`,`lombok`等项目可以大大简化代码和开发流程。

#### 开发工具

​		前端开发使用`vscode `配合官方插件`Volar`。

​		后端开发使用`idea` 。



## 4 项目模块设计

项目总体可分为三大模块

#### 用户模块

提供用户数据访问

* 登入登出
  * 需要有对访问权限的检查
* 注册
* 对于基本信息的增删改查
* 修改密码

#### 设备模块

主要是对设备分类以及设备本身两种数据的增删改查

#### 可视化模块

利用其余模块的数据进行可视化的显示, 例如设备状态统计, 接收信息状态统计

#### 技术细节

* 数据库: 设计数据结构, 建立表单. 使用触发器管理分类下的设备数. 建立事件定时删除过期`token`

* `mqtt`数据库客户端: 搭建一个`mqtt`客户端,接收模拟器通过`mqtt broker` 传递的信息并存储进数据库.
* 平台后端 :  为前端提供数据接口, 操作, 传递数据.
* 平台前端: 搭建用户可视界面, 展示和操作数据. 使用`e-charts`进行图表绘制,使用高德地图的接口进行地图绘制.

## 数据结构设计

```sql
-- 建表
-- 用户表
create table users (
        id int unsigned primary key auto_increment comment 'ID',
        username varchar(20) not null unique comment '用户名',
        password varchar(32)  comment '密码',
        nickname varchar(10)  default '' comment '昵称',
        email varchar(128) default '' comment '邮箱',
        description varchar(100) default '' comment '个人描述',
        create_time datetime not null comment '创建时间',
        update_time datetime not null comment '修改时间'
) comment '用户表';
insert into users value (1,'admin','admin','','','',now(),now());

-- 设备分类表
create table categories(
    id int unsigned primary key  auto_increment comment 'ID',
    name varchar(32) not null comment '分类名称',
    description varchar(100) default '' comment '分类描述',
    count int unsigned default 0 comment '该种类设备数量',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) comment '设备分类表';
insert into categories value (1,'默认分类','未分类项目',0,now(),now());

-- 设备表
create table devices(
    id int unsigned primary key  auto_increment comment 'ID',
    name varchar(30) not null comment '设备名称',
    state int unsigned not null default 2 comment '0-正常 1-警告 2-断线',
    category_id int unsigned not null comment '设备分类',
    description varchar(100) default '' comment '设备描述',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间'
)comment '设备表';


-- mqtt设备信息表
create table messages(
    alert boolean not null comment '是否警告',
    client_id varchar(20)  not null,
    info varchar(200) not null ,
    lat decimal(12,8) not null comment '纬度',
    lng decimal(12,8) not null comment '经度',
    timestamp long not null comment '时间戳',
    value int not null comment '设备数值, 嗯~无效测试数据'
);

-- token表
create table tokens(
    token varchar(160) not null primary key ,
    expire datetime not null
);

```



## 5 总结

​		本文提出了物联网设备管理平台的设计报告，该项目主要为了搭建一个简易的物联网设备管理平台，使用户对设备拥有监控管理的能力。报告确定了项目使用的前后端以及数据库使用的相关开发技术，对该项目的设计和开发提供了具体的指导和参考。

