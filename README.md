# guyu-TradingDesk

[作者毕设产品，版权所有，仅供个人学习借鉴]

系统版本号:1.0.0

系统说明

谷雨农副产品交易平台使用分为两大部分：前台商品交易界面，后台商品管理界面。

谷雨农副产品交易平台的使用者可以划分为四类，分别是游客，普通用户，商户，管理员。前台主要使用人群为游客和普通用户，后台主要使用人群为商家和管理员。

游客和普通用户可以浏览前台，查看商品信息、商品搜索、查看公告等。普通用户经过登录以后，可以将商品添加到购物车、购买商品、收藏商品、查看个人信息、完善个人信息、完善收货地址、查看订单、发表商品评论等。

商家和管理员可以通过登录账号进入本系统后台。商家通过后台可以进行商品管理，店铺管理，订单管理等。管理员通过后台可以进行前台首页轮播图管理，用户管理，公告管理，商品管理，店铺管理订单管理，销售信息管理，评论管理。

配置说明

后端开发框架用的是Springboot框架，存储数据使用的是数据库是MySql7.7.27，数据库连接池是DBCP，jdk版本为1.8.0_241，使用的开发工具书idea2020.1版本，若要运行项目，则需按步骤进行系统配置：
1.	使用idea导入项目，配置jdk和所需jar包。
2.	打开Navicat For Mysql，创建guyu数据库，并导入guyu.sql文件。
3.	http://localhost:8080/#/为首页。默认账号为：zhangsan 默认密码为:123456
4.	http://localhost:8080/#/login为后台登录页面。管理员账号为：admin 密码为：admin

未完成部分：

1.销售信息功能

2.历史浏览功能

3.用户购买历史清单

4.自动创建用户数据表

待优化部分：

1.当前系统后端代码尚未进行封装集成（代码量较多）

2.部分bug尚未修复

图片展示

1.前端首页

![image](https://github.com/DingJiaXiaoXuan/guyu-TradingDesk/blob/master/img/%E5%89%8D%E7%AB%AF%E9%A6%96%E9%A1%B5.png)


2.所有商品界面

![image](https://github.com/DingJiaXiaoXuan/guyu-TradingDesk/blob/master/img/%E6%89%80%E6%9C%89%E5%95%86%E5%93%81%E7%95%8C%E9%9D%A2.png)


3.商品详情界面

![image](https://github.com/DingJiaXiaoXuan/guyu-TradingDesk/blob/master/img/%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85.png)


4.购物车界面

![image](https://github.com/DingJiaXiaoXuan/guyu-TradingDesk/blob/master/img/%E8%B4%AD%E7%89%A9%E8%BD%A6%E7%95%8C%E9%9D%A2.png)


5.后台用户管理界面

![image](https://github.com/DingJiaXiaoXuan/guyu-TradingDesk/blob/master/img/%E5%90%8E%E5%8F%B0%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)
