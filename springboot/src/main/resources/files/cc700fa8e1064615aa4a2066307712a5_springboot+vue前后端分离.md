### springboot+vue前后端分离

环境配置：nodejs+mysql+navicat(可选，用于可视化数据库)+springboot（安装）

#### 前端vue

管理员模式进入文件：命令行

1. `npm install`
2. `npm run serve`

request封装类、跨域

分页查询

hutool工具类:File工具类

lombok：简化java开发的插件

mybatis-plus：分页插件；不用get和post

浅拷贝问题

访问到后台主题之后需要二次嵌套路由

vue router

#### springboot+mybatis

spring官方定制网站：start.spring.io

当前台收到数据，编程java的实体，然后插入数据库。

常用的api：adjax等三个。

2237120



![image-20220617150353276](C:\Users\GDHS\AppData\Roaming\Typora\typora-user-images\image-20220617150353276.png)

持久化框架：hibernate

跨域CORS

#部署

#连接

springsecurity

文件上传：spring自带的

```
    #连接池配置
    initial-size: 5
    min-idle: 5
    max-active: 20
    max-wait: 30000
jpa:
  show-sql: true
redis:
  jedis:
    pool:
      max-wait: 3000 #连接池最大阻塞等待时间
      max-active: 100 #连接池最大连接数
      max-idle: 20 #连接池中的最大空闲数
      min-idle: 0 #连接池中的最小空闲数
  timeout: 3000 #连接超时
```



