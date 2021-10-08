# 工程简介
## 1.多文件配置多个环境

Profile 是 Spring 对不同环境提供不同配置功能的支持，可以通过激活、指定参数等方式快速切换环境
springboot当中，application是我们的主配置文件，至于是yml结尾的文件还是properties结尾的文件，这两种都可以的，没有实质区别，都是更改springboot全局默认值。唯一的区别就是语法区别。
在实际开发当中一般我们会分为以下几种环境，所谓的环境最主要的就是库，测试他们一般会有自己专门的测试库，而线上会有专门的线上库，这些是不能混淆使用的。
环境区分：
dev: 开发环境
test: 测试环境
prod: 生产环境

用法详解：
这里以properties文件为例：创建三个配置文件，其中application.properties用来指定环境
application-环境名称 这个是多配置文件的命名规则。这个后缀环境名称，在指定的时候会用到的。

![CleanShot 2021-10-08 at 10.27.26@2x](http://typoraimages.oss-cn-beijing.aliyuncs.com/img/202110081027241.png)

# 延伸阅读

