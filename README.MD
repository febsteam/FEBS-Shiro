### FEBS后台权限管理系统
![https://img.shields.io/badge/build-success-brightgreen.svg?longCache=true&style=flat-square](https://img.shields.io/badge/build-success-brightgreen.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/license-Apache%202-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/download-1k-green.svg?longCache=true&style=flat-square](https://img.shields.io/badge/download-1k-green.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square](https://img.shields.io/badge/springboot-2.0.4-yellow.svg?longCache=true&style=popout-square)
![https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square](https://img.shields.io/badge/apache%20shiro-1.4.0-green.svg?longCache=true&style=flat-square)

FEBS是一个简单高效的后台权限管理系统。项目基础框架采用全新的Java Web开发框架 —— Spring Boot2.0.4，消除了繁杂的XML配置，使得二次开发更为简单；数据访问层采用Mybatis，同时引入了通用Mapper和PageHelper插件，可快速高效的对单表进行增删改查操作，消除了大量传统XML配置SQL的代码；安全框架采用时下流行的Apache Shiro，可实现对按钮级别的权限控制；前端页面使用Bootstrap构建，主题风格为时下Google最新设计语言Material Design，并提供多套配色以供选择。FEBS意指：Fast，Easy use，Beautiful和Safe。

注：精力有限，Oracle分支于2018年8月21日停止更新并删除，如有需要可到群里获取。

[项目文档](https://shiro.mrbird.cc/)

### 功能模块
系统功能模块组成如下所示：
```
|-------------------------------------------|
|    ______ ______ ____   _____             |
|   |  ____|  ____|  _ \ / ____|            |
|   | |__  | |__  | |_) | (___              |
|   |  __| |  __| |  _ < \___ \             |
|   | |    | |____| |_) |____) |            |
|   |_|    |______|____/|_____/             |
|   Spring-Boot-Version: 2.0.4.RELEASE      |
|-------------------------------------------|
|
├─系统管理
│  ├─字典管理
│  ├─用户管理
│  ├─菜单管理
│  ├─角色管理
│  └─部门管理
├─系统监控
│  ├─在线用户
│  ├─系统日志
│  ├─Redis监控
│  └─Redis终端
│─网络资源
│  ├─One一个
│  │  ├─散文
│  │  ├─绘画
│  │  └─语文
│  ├─天气查询
│  ├─影视资讯
│  │  ├─即将上映
│  │  └─正在热映
│  └─每日一文
└─任务调度
   ├─定时任务
   └─调度日志
```
### 技术选型
#### 后端
- 基础框架：Spring Boot 2.0.4.RELEASE

- 持久层框架：Mybatis 3.4.5

- 安全框架：Apache Shiro 1.4.0

- 摸板引擎：Thymeleaf 3.0.9.RELEASE

- 数据库连接池：阿里巴巴Druid 1.1.10

- 缓存框架：Redis

- 日志打印：logback

- 其他：fastjson，poi，javacsv，quartz等。

#### 前端
 
- 基础框架：Bootstrap 4

- JavaScript框架：jQuery

- 消息组件：Bootstrap notify

- 提示框插件：SweetAlert2

- 树形插件：jsTree

- 树形表格插件：jqTreeGrid

- 表格插件：BootstrapTable

- 表单校验插件：jQuery-validate

- 多选下拉框插件：multiple-select

- 图表插件：Highcharts

- 时间插件：daterangepicker

#### 开发环境

- 语言：Java 8

- IDE：Eclipse Oxygen & IDEA 2018.1.4(Ultimate Edition)

- 依赖管理：Maven

- 数据库：MySQL5.7

- 版本管理：SVN，git

## 系统预览

![f47c1606680527619bc462afea4d068f.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/64420208.jpg)

![e3d9fc443686ec84003496d742c783ad.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/50183418.jpg)

![2911d8fa7bbe2b6674854a5c7f59de7a.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/57316459.jpg)

![48a18207aa79b0e75cfd7258d61dab1e.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/31072514.jpg)

![155af0ed234a7fe040666b1b7818d42e.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/38600911.jpg)

![57a6c25f7b616fcf4e452da673154960.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/5832650.jpg)

![d4973ba38ff475543eab95839b5314dc.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/99045302.jpg)

![](http://olwqftdzl.bkt.clouddn.com/18-6-29/66445963.jpg)

![](http://olwqftdzl.bkt.clouddn.com/18-6-29/18024225.jpg)

### 主题预览

![2bbae66a0cc36ea96ca1ffe66951921d.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/21305793.jpg)

![a9f57431583b8ed9a5d18b7cf37bd053.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/22217970.jpg)

![d8cb6313fd2ee8eb793d443b83a78ce4.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/6580523.jpg)

![2bd554ce2511cd1e7e3e7b3b56216ed6.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/86512301.jpg)

![5e6d35e4390cf7f0752d7864ad701e2b.png](http://olwqftdzl.bkt.clouddn.com/18-4-18/81830531.jpg)
