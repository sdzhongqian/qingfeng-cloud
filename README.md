# 青锋Cloud+VUE前后端分离后台管理系统-SpringCloud+Alibaba+Nacas+OAuth2Jwt+Gateway+skywalking等技术

#### 介绍
青锋Cloud+VUE前后端分离后台管理系统，后端采用：SpringCloud+Alibaba+Nacas+OAuth2Jwt+Gateway+skywalking+Feign+Spring Boot Admin等技术；前端采用VUE+ant design。整合了菜单功能权限、数据权限、代码生成器、quartz定时器等业务功能，是拿来即用的微服务架构。

#### 青锋生态介绍
1、[Thymeleaf版（开源）](https://gitee.com/msxy/qingfengThymeleaf)
2、[Shiro版（开源）](https://gitee.com/msxy/qingfeng)
3、[VUE版（开源）](https://gitee.com/msxy/qingfeng-vue)
4、[cloud+vue版（开源）](https://gitee.com/msxy/qingfeng-cloud)
5、activiti工作流版 
预览地址
[http://xinlingge.cn:8181/qingfeng](http://xinlingge.cn:8181/qingfeng)

#### 交流群
为了大家的交流，特建立青锋项目交流群，欢迎大家的加入讨论， **项目中数据库脚本需要加群获取** 。
 **QQ交流群：青锋产品交流1群(已满)：826025670   青锋产品交流2群：772893019  青锋产品交流3群：315978117** 
 **微信号：QF_qingfeng1024 (加微信、群主拉进微信交流群)** 

#### 软件架构
 **微服务端口约定** 
qingfeng-Register	8001
qingfeng-Auth	        8101
qingfeng-Server-System	8201
qingfeng-Server-Test	8202
qingfeng-Server-Gencode	8203
qingfeng-Server-Job	8204
qingfeng-Gateway	8301

 **微服务项目目录结构** 
├─ant-design-cloud                    ------ VUE前端代码
├─qingfeng-auth                       ------ 微服务认证服务器
├─qingfeng-common                     ------ 通用模块
│  ├─qingfeng-common-core             ------ 系统核心依赖包
├─qingfeng-gateway                    ------ 微服务网关
├─qingfeng-server                     ------ 微服务子系统
│  ├─qingfeng-server-system           ------ 微服务子系统系统核心模块
│  ├─qingfeng-server-test             ------ 微服务子系统demo模块
│  ├─qingfeng-server-gencode          ------ 微服务子系统代码生成模块
│  └─qingfeng-server-job              ------ 微服务子系统任务调度模块
└─qingfeng-monitor                    ------ 微服务系统监控
   ├─qingfeng-monitor-admin           ------ 微服务Spring Boot Admin

![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/235341_f997a654_395948.png "微信图片_20210403235333.png")

#### 安装教程

1.  安装nocos

### 1、Nacos安装
Nacos是Alibaba提供的服务管理软件，可以无缝地和Spring Cloud结合，现已经整合到了Spring Cloud Alibaba模块中。这一节我们将使用Spring Cloud Alibaba Nacos来代替Spring Cloud Eureka。
官网地址：https://nacos.io/zh-cn/
Nacos下载及安装：https://nacos.io/zh-cn/docs/quick-start.html
Nacos配置
    1、修改conf/application.properties配置文件
    `server.port=8001`
    2、修改nacos解压包目录下conf/application.properties配置文件，添加如下配置：
    `spring.datasource.platform=mysql
    db.num=1
    db.url.0=jdbc:mysql://localhost:3306/qingfeng_cloud_nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
    db.user=root
    db.password=Root@123`
    3、启动、进入Nacos下的bin，执行：
    `cmd startup.cmd -m standalone`
    ![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/233048_393f499e_395948.png "微信图片_20210403233031.png")
    浏览器输入：http://localhost:8001/nacos ，账号密码为：nacos 
    ![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/233148_4d601e78_395948.png "微信图片_20210403233143.png")

### 2、安装Redis
Redis 是一个开源的使用 ANSI C 语言编写、遵守 BSD 协议、支持网络、可基于内存、分布式、可选持久性的键值对(Key-Value)存储数据库，并提供多种语言的 API。
官网地址：https://redis.io/
安装教程地址：https://www.runoob.com/redis/redis-install.html
打开一个 cmd 窗口 使用 cd 命令切换目录到 redis安装目录 运行：
redis-server.exe redis.windows.conf
![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/233459_84a8bdab_395948.png "微信图片_20210403233453.png")

### 3、导入数据脚本
    1、导入qingfeng_cloud.sql (青锋cloud业务数据表)
    2、导入qingfeng_cloud_job.sql (青锋cloud定时器数据表)
    3、导入qingfeng_cloud_nacos.sql (青锋cloud Nacos数据表)

### 4、登录Nacos修改数据源配置
    以qingfeng-server-system为例：
    ![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/233912_37e38bed_395948.png "微信图片_20210403233900.png")

### 5、项目后台服务启动
    以此启动服务
    1、qingfeng-gateway （必须）
    2、qingfeng-Auth （必须）
    3、qingfeng-Server-System （必须）
    4、qingfeng-Server-Gencode （可选）
    5、qingfeng-server-job （可选）
    6、qingfeng-Server-Test （可选）

### 6、vue前端启动
    打开cmd框（或者导入到Visual Studio）
    执行安装命令：
    npm install 
    安装完毕执行启动命令：
    npm run serve 
    ![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/234723_641e02a2_395948.png "微信图片_20210403234717.png")

### 7、启动完毕在浏览器输入：
    http://localhost:8000/ 
    账号密码： admin/123456 
![输入图片说明](https://images.gitee.com/uploads/images/2021/0403/235150_ef448391_395948.png "微信图片_20210403235140.png")


#### 使用说明

待更新，更多资源请加群

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
