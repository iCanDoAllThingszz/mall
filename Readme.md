# 分布式三高商城系统
# 一、系统架构演变
### 1. 服务架构的演变
##### 1.1 单体架构
单体架构中使用经典的三层模型:表现层、业务逻辑层、数据访问层，所有模块都耦合在一起。

简单，高效，开发速度快，缺点是性能容易遇到瓶颈，扩展性差。

![img.png](img.png)

##### 1.2 单体架构-集群
将单体项目部署在多台服务器上, 使用nginx做负载均衡, 提高系统的并发处理能力。

缺点是维护成本高， 扩展性差， 难以进行水平扩展。

![img_1.png](img_1.png)

##### 1.3 垂直化
将单体项目按照业务进行拆分, 每个业务模块使用独立的服务器, 实现业务模块的解耦和独立部署。比如将一个商城系统拆分成订单系统、用户系统和商品系统

缺点是冗余代码多, 用户系统和商品系统中可能都有对商品的crud操作

![img_4.png](img_4.png)

##### 1.4 服务化
SOA(面向服务的架构), 将业务模块再拆分成更小的服务, 每个服务都提供特定的功能, 实现业务模块的解耦和独立部署。

ESB: 企业服务总线, 用于连接不同的服务, 实现服务之间的通信和集成。

![img_5.png](img_5.png)

##### 1.5 微服务化
在SOA的基础上继续演进就是我们讲的微服务了, SOA的服务进行更细粒度的拆分就是微服务。

随着服务拆分之后, 微服务架构下的 服务的注册、服务之间的发现、服务之间的调用、服务的治理等都需要做相应的处理。

并且对基础运维的能力要求会越来越高, 虚拟化、容器化等。

![img_6.png](img_6.png)

**微服务和SOA的区别:**
1. 思想上: 微服务的目的是解耦, 而SOA的目的是实现数据的互通和共享性。
2. 协议: 微服务会使用一些轻量级的通信协议(Restful API)
3. 基础设施要求: 微服务更加强调开发运维的持续交付(CICD)

# 二、项目介绍
### 1.三高介绍
三高指的是: 高性能(响应快)、高并发(并发能力强)、高可用(某个节点/服务宕机后, 整个应用的容灾能力还是强的)

![img_7.png](img_7.png)

### 2.项目架构
##### 2.1 项目架构图:

![img_8.png](img_8.png)

##### 2.2 业务组成:

![img_9.png](img_9.png)

##### 2.3 RPC框架

在微服务架构中，服务与服务之间要实现接口的调用我们肯定要通过相关的RPC(Remote Procedure Call)框架来实现。

常用的RPC框架有:Dubbo,Google的GRPC，Apache的Thrift，微博的Motan，京东的EasyRPC等。我们通过RPC框架可以取调用服务提供者提供的服务，但有一个前提是我们要能找到这个服务。通常我们的服务部署都是集群多节点的部署，所以在消费者这端就不可能直接写死在代码里面，这时就涉及到了服务的发现问题，这时就需要另一个组件注册中心了

![img_11.png](img_11.png)

#####  2.4 注册中心

注册中心实现服务地址管理的功能，解决服务动态感知(上线，下线)。

![img_12.png](img_12.png)

#####  2.5 负载均衡

在服务注册中心的介绍中我们可以看到负载均衡的应用。我们可以通过Ribbon来实现客户端的负载均衡，负载均衡的策略可以是：轮询，随机，根据响应时间来计算权重的轮询等。

![img_13.png](img_13.png)

#####  2.6 配置中心

在微服务架构中我们有很多个服务，而每个服务中是都会有单独的配置文件的。里面有很多的配置信息的有关联的，而且对于后期的更新维护也会非常的不方便，这时配置中心就上场了。常用的配置中心有：apollo/Nacos/disconf/zookeeper/diamond/Spring Cloud Config

![img_14.png](img_14.png)

#####  2.7 网关

网关可以帮助我们完成用户请求的入口，路由。完成统一授权，日志的记录，权限的认证和限流及熔断操作。

![img_15.png](img_15.png)

#####  2.8 限流、降级、缓存

在现实的微服务架构中的性能是很难满足所有的用户请求，这时我们就可以通过一些措施来保证我们的核心服务的正常运转。

限流：sentinel、hystrix

降级：主动降级(订单评论、广告关闭)、被动降级

缓存：降低数据源访问频率、Redis等

容错机制：服务出现挂机，宕机之后的处理机制。

![img_16.png](img_16.png)

##### 2.9 Bus

Bus消息总线，实现异步化的通信机制。

![img_17.png](img_17.png)

##### 2.10 链路监控

因为微服务中的服务实在是太多了，为了能更好的监控个服务的情况，肯定就需要链路监控服务，我们可以通过sleuth+zipkin来实现，应用层监控，系统级监控

![img_18.png](img_18.png)

# 三、环境准备
##### 3.1 虚拟机准备
这里我们准备的是阿里云服务器: https://swasnext.console.aliyun.com/servers/cn-beijing

##### 3.2 Docker安装
CentOS7安装Docker: https://docs.docker.com/engine/install/centos/

1. 卸载原有环境
```shell
sudo yum remove docker \ 
                  docker-client \ 
                  docker-client-latest \ 
                  docker-common \ 
                  docker-latest \ 
                  docker-latest-logrotate \ 
                  docker-logrotate \ 
                  docker-engine
```

2. 安装依赖环境和镜像地址
```shell
sudo yum install -y yum-utils
```

设置官方镜像地址
```shell
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

设置阿里云镜像地址
```shell
sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

安装docker CE
```shell
sudo yum install -y docker-ce docker-ce-cli containerd.io
```

启动docker
```shell
systemctl start docker
```

查询docker中正在运行的容器信息
```shell
# docker ps命令用于列出当前正在运行的Docker容器。它会显示容器的ID、名称、状态、端口映射等信息
docker ps

# CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
```

查询docker中所有容器信息, 不管有没有在运行
```shell
docker ps -a
```

删除docker容器
```shell
docker rm 容器ID
```

查看docker版本
```shell
docker version
```

设置docker服务开机自动启动
```shell
systemctl enable docker
```

##### 3.3 Docker中安装mysql数据库
为了后续做CICD, 我们在docker中安装必要软件

拉取mysql5.7镜像
```shell
docker pull mysql:5.7
```
> 因为docker国内被墙了, 所以我们需要使用国内的镜像仓库, 配置方法参考: https://blog.csdn.net/Lichen0196/article/details/137355517 <br/>
> 但是这个blog中给出的镜像数据源也不太行, 镜像数据源可以使用:

```json
{
  "registry-mirrors": [
    "https://docker.fxxk.dedyn.io",
    "https://docker.m.daocloud.io",
    "https://docker.1panel.live"
  ]
}
```

查看docker镜像信息
```shell
docker images

# REPOSITORY   TAG       IMAGE ID       CREATED         SIZE
# mysql        5.7       5107333e08a8   11 months ago   501MB
```

基于mysql5.7镜像启动容器:
```shell
# 基于mysql5.7镜像启动容器, 容器中端口为3306(第二个), 宿主机端口也为3306(第一个), 容器名称为mysql, -v 数据挂载信息(容器中哪些目录下的信息挂载到宿主机的哪些目录下) -e: 设置root账号的默认密码为root
docker run -p 3306:3306 --name mysql -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=199741Zy1! -d mysql:5.7
```

> 怎么修改mysql密码: https://blog.csdn.net/m0_37482190/article/details/133250569 (记一次狗黑客攻击)

docker启动mysql容器时, 发生了问题 容器一启动就挂, 可以通过以下命令查看容器日志
```shell
docker logs <container_id>
```

docker启动mysql容器立马挂掉, 通过日志看是不能读取'/etc/mysql/conf.d/目录的解决方法: https://www.cnblogs.com/wxxf/p/17854020.html

再次执行docker ps命令后, 确认mysql容器已经启动
```shell
[root@iZ2ze9afrj51kl498kmdwkZ ~]# docker ps
CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS          PORTS                               NAMES
2b138bd7cdd3   mysql:5.7   "docker-entrypoint.s…"   41 minutes ago   Up 41 minutes   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql
```

在宿主机的/mydata/mysql/conf目录下设置配置文件my.cnf:
```xml
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
init_connect="SET collation_connection = utf8_unicode_ci"
init_connect="SET NAMES utf8"
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```
重启docker-mysql容器:
```shell
docker restart <container_id>

[root@iZ2ze9afrj51kl498kmdwkZ conf]# docker ps
CONTAINER ID   IMAGE       COMMAND                  CREATED       STATUS       PORTS                               NAMES
2b138bd7cdd3   mysql:5.7   "docker-entrypoint.s…"   4 hours ago   Up 4 hours   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql
[root@iZ2ze9afrj51kl498kmdwkZ conf]# docker restart 2b
2b
```
连接数据库(root:root)

##### 3.4 Docker中安装Redis
拉取Redis最新版本镜像
```shell
docker pull redis
```

根据Redis镜像文件创建容器
```shell
# -d:后台运行 -p:端口映射 -v:数据挂载 --name:容器名称
docker run -d -p 6379:6379 --name mall-redis -v /mydata/redis/data:/data -v /mydata/redis/conf:/etc/redis  redis redis-server /etc/redis/redis.conf
```
因为配置了目录挂载, 所以如果报错 `Fatal error, can't open config file '/etc/redis/redis.conf': No such file or directory`, 我们在/mydata/redis/conf下创建redis.conf文件即可

重新启动容器即可:
```shell
[root@iZ2ze9afrj51kl498kmdwkZ conf]# docker ps
CONTAINER ID   IMAGE       COMMAND                  CREATED         STATUS          PORTS                               NAMES
83bffad12ab5   redis       "docker-entrypoint.s…"   2 seconds ago   Up 1 second     0.0.0.0:6379->6379/tcp              mall-redis
2b138bd7cdd3   mysql:5.7   "docker-entrypoint.s…"   5 hours ago     Up 11 minutes   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql
```

进入redis容器内部:
```shell
docker exec -it <container_id> /bin/bash
```

在redis容器内部尝试连接redis:
```shell
root@83bffad12ab5:/data# redis-cli
127.0.0.1:6379> set name zhangsan
OK
127.0.0.1:6379> get zhangsan
(nil)
127.0.0.1:6379> get name
"zhangsan"
```

设置mysql容器和redis容器开机自启动:
```shell
docker update --restart=always <container_id>
```

重要: 修改/mydata/redis/conf/redis.conf文件中的bind 127.0.0.1为bind 0.0.0.0(从只允许本机访问改成允许所有ip访问), 修改protected-mode为no。参考 https://developer.aliyun.com/article/792302

idea连接redis, ok

##### 3.5 本地开发环境准备
1. jdk 17
2. idea + web storm
3. git

##### 3.6 父工程创建
mall_zy(pom项目, 做版本控制用)

##### 3.7 相关微服务模块创建
- 商品 mall-product
- 订单 mall-order
- 会员 mall-member
- 库存 mall-ware
- 优惠活动 mall-coupon

> 解决git将子模块误认为子系统模块的问题(github文件夹有向右的白色箭头且不能打开): https://www.jianshu.com/p/28e61a24d847

##### 3.8 gitignore文件配置

##### 3.9 数据库表创建
- mall_pms 商品数据库
- mall_oms 订单数据库
- mall_ums 会员数据库
- mall_wms 库存数据库
- mall_sms 优惠活动数据库

执行./sql/下对应数据库的.sql文件, 在对应数据库下创建表

##### 3.10 服务创建和数据库配置
前端: Vue + ElementUI <br/>
后端: SpringBoot + MyBatisPlus

我们在本项目中使用人人开源中提供的模版项目来实现快速的项目构建: https://github.com/renrenio  https://gitee.com/renrenio

使用到的模版项目有:
- renren-fast: 后端模版
- renren-generator: 代码生成器
- renren-fast-vue: 前端模版

1. renren-fast: <br/>
修改renren-fast中application.yml配置文件, 创建对应的数据库 renren_fast

根据renren-fast/db/下的.sql文件创建数据库表

##### 3.11 后端服务启动配置
启动renren-fast服务, 启动类: RenrenApplication

##### 3.12 前端服务搭建
前端服务: renren-fast-vue

安装node.js

启动renren-fast-vue参考: https://github.com/renrenio/renren-fast-vue/wiki/Getting-started

> ps: 这个前端中用到的依赖 如node-sass等不支持mac的arm64架构, 前端项目放弃了

##### 3.13 generator系统搭建
使用renren-generator生成代码(通过模版文件生成)

修改renren-generator的application.yml, generator.properties配置文件, 生成我们各个数据库下的模版代码

启动renren-generator, 访问localhost:9999, 生成对应数据库的非业务crud代码

##### 3.14 common模块搭建
mall-common模块中存放一些公共的代码, 比如异常处理类, 工具类等

对renren-generator生成的代码修改导入的依赖, 修改报错的代码, 使项目能够正常启动

##### 3.15 mybatis-plus整合
添加mybatis-plus依赖, mysql数据库依赖
```xml
<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.2</version>
    <scope>compile</scope>
</dependency>
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.11</version>
</dependency>
```

完成mybatis-plus的配置, mall-product: application.yaml

配置完成后在MallProductApplicationTests中测试成功

##### 3.16 各微服务代码生成
为mall_oms(9991), mall_pms(9990), mall_sms(9992), mall_ums(9993), mall_wms(9994)分别生成代码

##### 3.17 SpringCloud Netflix介绍
![img_10.png](img_10.png)

前端不要直接调用后端, 要经过nginx -> 网关

netflix已经不再维护了, 也没有springcloud alibaba好用, 不推荐使用

##### 3.18 SpringCloud Alibaba介绍
注意: springboot和spring cloud alibaba的版本要对应, 否则可能会有问题(官网有版本对应), 这里我们使用的是springboot 2.4.12, spring cloud alibaba 2021.1

我们会使用到的微服务组件:
- 注册中心: Nacos
- 配置中心: Nacos
- 负载均衡: Ribbon
- 声明式服务调用: OpenFeign
- 服务容错: Sentinel
- 网关: Gateway
- 链路监控: Sleuth
- 分布式事务: Seata
- 消息队列: RabbitMQ

##### 3.19 下载nacos服务
本机的nacos放在了/opt/nacos

linux/unix/mac下单机启动naocs服务(是一个springboot服务):
```shell
sh startup.sh -m standalone
```
服务启动之后访问localhost:8848/nacos即可

![img_19.png](img_19.png)

##### 3.20 nacos 微服务注册
1. 修改pom.xml文件 添加nacos依赖
```xml
<!--nacos服务注册-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency> 
```

2. 启动类上加 @EnableDiscoveryClient 注解

3. 修改application.yml文件, 添加nacos配置
```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 
```

注意, 我们还需要导入spring-cloud-starter-bootstrap依赖, 否则报错ClassNotFoundException
```xml
  <dependencies>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-bootstrap</artifactId>
      <version>3.0.3</version>
    </dependency>
  </dependencies>
```
完成其他微服务模块的注册中心配置(1.pom.xml 2.启动类 3.application.yaml)

##### 3.21 Doker容器中安装
在docker中安装nacos

拉取nacos镜像文件
```shell
docker pull nacos/nacos-server:latest
```

> 这里我们nacos的版本是2.4.3

启动nacos容器:
```shell
# -d 后台运行模式 -e 设置环境变量 -v 设置挂载目录 --name 设置容器名称 -p 设置端口映射 nacos/nacos-server:1.4.2 镜像名称
docker run -d -e MODE=standalone -v /mydata/nacos/conf:/home/nacos/conf --name nacos -p 8848:8848 -p 8849:8849 -p 9848:9848 -p 9849:9849 nacos/nacos-server:latest
```

然后需要:
1. 在/mydata/nacos/conf下新增配置文件
2. 修改nacos的数据源为mysql
3. 修改nacos的账号密码, 参考: https://nacos.io/docs/latest/manual/admin/auth/
```shell
(base) zhaoyu@MBP-W12T7NRJFQ-1817 ~ % curl -X POST '8.152.0.119:8848/nacos/v1/auth/users/admin' -d 'password=nacos' 
{"username":"nacos","password":"nacos"}
```
4. 配置nacos开机启动
```shell
docker update --restart=always nacos
```
5. 修改我们微服务模块的nacos配置

```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848
        username: nacos
        password: nacos
```
##### 3.22 OpenFeign服务调用
OpenFeign是一个声明式的服务调用组件, 本质上是封装了Ribbon和RestTemplate, 实现了负载均衡和Rest调用, 我们用它来实现服务间的调用

1. 在服务提供者模块中定义服务提供接口
2. 在服务消费者中调用服务提供者的服务

我们用 mall-order(服务调用者) 和 mall-product(服务提供者) 来做服务调用测试

1. mall-product -> BrandController (服务提供者):
```java
    //OpenFeign服务提供接口
    @GetMapping("/all")
    public R queryAllBrand(){
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("huawei");
        brandEntity.setBrandId(100L);
        return R.ok().put("brands", brandEntity);
    }
```
2. mall-order (服务调用)
- 引入相关的依赖
```xml
<!--openfeign-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<!--load balancer组件 openfeign调用者必备-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    <version>3.1.0</version>
</dependency>
```
- 创建对应的Feign接口: ProductOpenFeignService
```java
@FeignClient(name="mall-product") // 说明要调用的服务名称
public interface ProductOpenFeignService {

    //需要访问的远程方法
    @GetMapping("mallproduct/brand/all")
    public R queryAllBrand();
}
```
- 启动类新增@EnableFeignClients注解， 开启远程调用功能
```java
@SpringBootApplication
@MapperScan("com.zy.mallorder.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zy.mallorder.feign")
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }

}
```
3. 测试 访问localhost:9991/malloms/order/feign
```java
@RestController
@RequestMapping("malloms/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOpenFeignService productOpenFeignService;

    //测试openFeign服务调用
    @GetMapping("feign")
    public R testFeign() {
        return productOpenFeignService.queryAllBrand();
    }
}
```

**测试:** 
0. 启动mall-product和mall-order服务
1. mall-product和mall-order都注册到了nacos
2. 访问 http://localhost:9991/malloms/order/feign, 成功返回结果

##### 3.23 Nacos配置中心基本使用
在mall-order测试nacos配置中心使用

1. 引入nacos-config依赖
```xml
<!--nacos配置中心-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>2021.1</version>
</dependency>
```

2. 创建bootstrap.yaml文件, 添加nacos配置中心配置(bootstrap加载顺序优先于application)
```yaml
server:
  port: 9991

spring:
  application:
    name: mall-order
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 TEST_GROUP or DEV_GROUP
        # namespace: 70f309cd-313e-47ab-85ed-23e713b79a31 # 指定服务在哪个命名空间下面
```

3. 添加@RefreshScope注解打开配置信息动态刷新功能
```java
@RestController
@RequestMapping("malloms/order")
@RefreshScope
public class OrderController {
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    //测试nacos配置中心
    @GetMapping("/config")
    public R queryConfig() {
        return R.ok().put("username", username).put("password", password);
    }
}
```

4. 在nacos控制台中添加配置文件, 发布配置文件 (ps:配置中心中的配置信息优先于application.yaml中的配置信息)
![img_20.png](img_20.png)

5. 测试: 启动mall-order服务 访问 localhost:9991/malloms/order/config, 成功返回 `{"msg":"success","password":"123456","code":0,"username":"zhaoyu93"}`

6. 为其他微服务新增bootstrap配置文件

##### 3.24 命名空间和配置组
可以通过dataId、group、namespace 三种方式进行配置文件隔离

为每个微服务创建一个配置命名空间

![img_21.png](img_21.png)

然后通过配置分组来区分 dev, test, prod环境

##### 3.25 配置拆分
我们现在是将某个服务中的所有配置都写在了同一个配置文件中, 这样会导致配置文件过大, 难以维护, 配置拆分后, 配置文件就变小了, 也方便维护了

mybatis相关配置:

![img_22.png](img_22.png)

datasource相关配置:

![img_23.png](img_23.png)

bootstrap.yaml配置:

```yaml
server:
  port: 9991

spring:
  application:
    name: mall-order
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 eg: TEST_GROUP or DEV_GROUP
        namespace: 56f80f04-c3b2-440a-a28b-4cb24426fd83 # 指定服务在哪个命名空间下面
        ext-config[0]:date-id: mall-order-mybatis # 指定多个配置文件
        ext-config[0].group: test
        ext-config[0]:refresh: false

        ext-config[1]:date-id: mall-order-datasource # 指定多个配置文件
        ext-config[1].group: test
        ext-config[1]:refresh: false
```
##### 3.26 Gateway核心概念介绍
客户端不可能记下每一个微服务的地址, 因此我们需要网关来路由, 此外网关还可以实现限流、鉴权、统一日志记录等功能

Spring Cloud Gateway 最重要的三个概念:
- 路由(route): 路由是网关最基础的部分, 路由信息包括ID、目标URI、匹配条件、过滤器信息等
- 断言(predicate): 路由条件, 例如请求方法、请求路径等
- 过滤器(filter): 过滤器用于修改请求和响应的信息

![img_24.png](img_24.png)

> spring cloud gateway 为我们提供了一些现成的predicates(断言)和filters(过滤器)
> 请求到网关 -> 断言 -> 过滤器 -> 路由到对应微服务 -> 过滤器 -> 返回结果

![img_25.png](img_25.png)

示例配置文件:
```yaml
server:
  port: 9999

spring:
  main:
    web-application-type: reactive
  application:
    name: cloud-gateway-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    gateway:
      discovery:
        locator:
          enabled: true # 开启注册中心路由功能, 把网关服务注册到naocs中
      routes:
        # 路由1
        - id: nacos-provider
          uri: http://localhost:9001/nacos-provider # 匹配提供服务的路由地址
          predicates:
            - Path=/gateway/** # 断言, 路径相匹配则进行路由, 访问http://localhost:9001/nacos-provider/gateway/** 时匹配
        # 路由2
        - id: nacos-provider2
          uri: http://localhost:9001/nacos-provider # 匹配提供服务的路由地址
          predicates:
            - Path=/route/** # 断言, 路径相匹配则进行路由
```

##### 3.27 Gateway网关服务搭建
创建网关服务模块 mall-gateway(9995)

1. pom.xml中添加对应的依赖:
```xml
        <!--nacos服务注册-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--gateway依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
            <version>${spring-cloud-gateway.version}</version>
        </dependency>
        <!--loadbalancer负载均衡组件, 必须加 否则gateway无法实现负载均衡-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
            <version>3.0.1</version>
        </dependency>
```
2. 完成配置
bootstrap.yaml:

```yaml
server:
  port: 9995

spring:
  application:
    name: mall-gateway
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 eg: TEST_GROUP or DEV_GROUP
        # namespace: 56f80f04-c3b2-440a-a28b-4cb24426fd83 # 指定服务在哪个命名空间下面
        #        ext-config[0]:date-id: mall-order-mybatis # 指定多个配置文件
        #        ext-config[0].group: test
        #        ext-config[0]:refresh: false
        #
        #        ext-config[1]:date-id: mall-order-datasource # 指定多个配置文件
        #        ext-config[1].group: test
        #        ext-config[1]:refresh: false

    # 配置路由
    gateway:
      routes:
        # 如果请求的url中包含baidu，则路由到百度
        - id: route1
          uri: http://www.baidu.com
          predicates:
            - Query=url,baidu
        # 如果请求的url中包含taobao，则路由到淘宝
        - id: route2
          uri: http://www.taobao.com
          predicates:
            - Query=url,taobao


```

3. 启动mall-gateway服务 测试网关服务路由规则
- 网关服务成功注册到了nacos
- 访问 localhost:9995/?url=baidu, 成功路由到百度; 访问 localhost:9995/?url=taobao, 成功路由到淘宝

# 四、业务开发
前端模版服务: renren-ui (需要使用node v16.0.0 -> nvm use 16.0.0 否则报错)

后端模版服务: renren-security

### 0.1 业务开发方向拆解
1. 维护基础数据 - 商品分类、商品品牌、商品规格、商品属性、商品SPU、商品SKU

基于renren-security模版和renren-ui模版开发

## 1. 后台管理系统

## 1. 三级类目操作

### 1.1 商品分类后台查询
执行pms_category.sql 初始化商品分类数据

> 接口测试记录参考postman

mall-product -> CategoryController
- 新增listTree接口, postman测试接口 通过

> compareTo方法升降序是如何确定的: https://blog.csdn.net/weixin_45505313/article/details/130705913

### 1.2 菜单维护
1. 在renren-ui前端页面上新增【商品系统】一级菜单
2. 在【商品系统】下新增【类别管理】菜单, 路由填写 mallproduct/category。ps: 菜单信息维护在renren-security的sys_menu表中
3. 修改renren-ui, 新增mall/product/category.vue页面, 确保页面能够正确跳转, 页面内容参考renren-ui

![img_26.png](img_26.png)

### 1.3 数据展示 & renren-security 服务注册
vue + element ui 实现数据展示

element ui 组件: https://element.eleme.cn/#/zh-CN/component/installation

模版服务中, 前端默认请求的url是人人模版后端: http://localhost:9998/renren-admin

但是现在我们的服务架构如下图:

![img_27.png](img_27.png)

因此需要引入网关服务(mall-gateway 9995), 前端只请求网关服务, 网关服务完成后续服务的路由 (修改renren-ui的.env文件)

修改renren-ui的.env文件, 将后端请求地址改为网关服务

接下来我们需要让renren-security注册到nacos中, 这样网关才能将其路由到。

##### renren-security服务注册
1. renren-security使用的是较高版本的springboot 3.2.6, 因此我们要用spring cloud alibaba 2023.x 来与其兼容
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <version>2023.0.1.0</version>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <version>2023.0.1.0</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
    <version>4.0.0</version>
</dependency> 
```
2. 高版本的nacos默认使用grpc的方式和服务器通信(低版本用http), 因此我们需要把阿里云服务器以及docker中打开8848, 8849, 9848(8848+1000), 9849(8848+1001)端口

docker 指定8848, 8849, 9848, 9849映射端口启动容器:
```shell
docker run -d -e MODE=standalone -v /mydata/nacos/conf:/home/nacos/conf --name nacos -p 8848:8848 -p 8849:8849 -p 9848:9848 -p 9849:9849 nacos/nacos-server:latest
```

### 1.4 网关路由
通过配置网关服务的路由, 将前端请求路由到对应的服务

mall-gateway -> bootstrap.yaml

```yaml
spring:
  application:
    name: mall-gateway
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 eg: TEST_GROUP or DEV_GROUP

    # 配置路由
    gateway:
      routes:
        # renren-admin路由
        - id: renren-admin
          uri: lb://renren-admin
          predicates:
            - Path=/renren-admin/**
```
但是此时还是请求不到renren-admin服务, 因为mall-gateway服务没有配置允许跨域请求

报错 `:8001/#/login:1 Access to XMLHttpRequest at 'http://localhost:9995/renren-admin/login' from origin 'http://172.18.141.186:8001' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.`

> 跨域: 协议 or 域名 or 端口

浏览器访问非同源服务器流程: <br/>

![img_28.png](img_28.png)

##### 解决跨域请求流程
**问题原因:** 跨域根本原因是由同源策略引起的。所谓同源是指域名，协议，端口相同，当页面在执行一个脚本时会检查访问的资源是否同源，如果非同源，在请求数据的时候浏览器会在控制台报一个异常，提示拒绝访问。注意：跨域限制访问，其实是浏览器的限制。理解这一点很重要！！！

**解决跨域问题的方法 - CORS方式解决跨域(常见, 仅修改服务端即可):**

CORS 全称是"跨域资源共享"（Cross-origin resource sharing）。CORS需要浏览器和服务器同时支持，但是目前基本上浏览器都支持，所以我们只要保证服务器端服务器实现了 CORS 接口，就可以跨域通信

实现方式：在响应头配置 Access-Control-Allow-Origin 字段以后,数据包发送给浏览器后，浏览器就会根据这里配置的白名单 “放行” 允许白名单的服务器对应的网页来用 ajax 跨域访问 。

- Access-Control-Allow-Origin             允许请求的域
- Access-Control-Allow-Methods            允许请求的方法
- Access-Control-Allow-Headers            预检请求后，告知发送请求需要有的头部
- Access-Control-Allow-Credentials        表示是否允许发送cookie，默认false；
- Access-Control-Max-Age                  本次预检的有效期，单位：秒；

> 在解决跨域问题的路途上, 我先尝试了WebMvcConfigurer, 发现无法生效, 更换为CorsWebFilter后生效

删除renren-security的跨域配置, 否则报错`Access to XMLHttpRequest at 'http://localhost:9995/renren-admin/login' from origin 'http://172.18.141.186:8001&#39;  has been blocked by CORS policy: The 'Access-Control-Allow-Origin' header contains multiple values 'http://172.18.141.186:8001,  http://172.18.141.186:8001&#39;,  but only one is allowed.`

这是因为有不止一个地方添加了响应头的Access-Control-Allow-Origin属性, Access-Control-Allow-Origin包含了多个值, 根据CORS规范, 这个响应头只能有一个值

### 1.5 查看类别数据
配置网关的商品服务路由(**注意路由配置的顺序**):

```yaml
spring:
  application:
    name: mall-gateway
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 eg: TEST_GROUP or DEV_GROUP
        # namespace: 56f80f04-c3b2-440a-a28b-4cb24426fd83 # 指定服务在哪个命名空间下面

    # 配置路由
    gateway:
      routes:
        # 商品系统路由, 注意路由顺序 别让Path=/renren-admin/**把前面的路由给拦截了        
        - id: mall-product-route
          uri: lb://mall-product
          predicates:
            - Path=/renren-admin/mallproduct/**
          filters:
            - StripPrefix=1 # 去掉路径上的前缀1层 renren-admin
        # renren-admin路由
        - id: renren-admin-route
          uri: lb://renren-admin
          predicates:
            - Path=/renren-admin/**
```
启动网关服务, renren-admin服务(后端基础功能), mall-product服务(商品服务)

前端 -> 网关 -> 路由 -> mall-product / renren-admin 成功跑通

修改renren-ui的category.vue页面, 使得返回的三级品类数据能够正确显示:

![img_29.png](img_29.png)

### 1.6 删除类别数据
1. 修改renren-ui 的category.vue页面, 新增: 添加删除按钮(附带展示逻辑); 新增复选框

2. CategoryController -> delete 批量删除接口, 使用Postman测试/mallproduct/category/delete接口 <br/>
为CategoryEntity类新增逻辑删除判断字段isDeleted, 默认值为0, 删除时更新为1, 同时为pms_category表新增is_Deleted字段

3. 修改 CategoryController -> delete 接口逻辑

4. 修改renren-ui, 新增点击 添加/删除按钮后的逻辑 **element-ui组件库只兼容vue2, 我们使用的是vue3, 所以需要安装element-plus 为了使用其中的elmessagebox, elmessage组件; node版本适用v16.0.0**

5. 完成类别删除功能

### 1.7 新增类别信息

> element-plus组件库地址: https://element-plus.org/zh-CN

1. 修改renren-ui 的category.vue页面, 新增: 点击添加按钮 跳出添加类目详情对话框(el-dialog), 添加完成后调用 mallproduct/category/save接口保存数据

2. 完成类别新增功能

### 1.8 更新类别
1. 修改renren-ui 的category.vue页面, 新增: 点击编辑按钮 跳出编辑类目详情对话框(el-dialog), 编辑完成后调用 mallproduct/category/update接口更新数据

2. 完成类别编辑功能

### 1.9 拖拽页面效果
实现拖拽三级类目的效果

1. 修改renren-ui 的category.vue页面, 新增: 实现三级类目拖拽效果(拖拽后不能破坏最大分类层级为三级的规则)

2. 修改后端逻辑, 使得前端拖拽类目后, 后端对应类目的parentCid, catLevel, sort字段也更新。在CategoryController接口中新增updateBatch接口, 批量更新类目数据

3. 完成拖拽功能

4. 修改renren-ui 的category.vue页面, 新增switch控件, 控制拖拽功能开关

### 1.10 批量删除类目数据
1. 修改renren-ui 的category.vue页面, 新增: 批量删除按钮, 批量删除后调用/mallproduct/category/delete接口批量删除数据

2. 完成批量删除功能

## 2. 品牌管理
### 2.1 品牌crud基本操作

> 重要: 模版服务前端renren-ui和后端renren-security是配套的, 我们之前的前端模版用的是renren-ui, 后端用的是renren-fast。这次把后端模版替换为renren-security, 在renren-security分支上进行开发

1. 在前端页面上新增【品牌管理】菜单, 其父菜单为【商品系统】, 路由为/mallproduct/brand

2. 生成模版前端页面 brand.vue

3. 实现品牌管理crud基本功能

### 2.2 品牌管理 - 显示状态优化

### 2.3 阿里云OSS服务开通和使用
1. 我们将图片存放在阿里云OSS服务器上, 方便各个微服务进行访问。 阿里云oss控制台: https://oss.console.aliyun.com/overview



> oss学习路径: https://help.aliyun.com/zh/oss/

> 阿里云oss服务 api文档: https://help.aliyun.com/zh/oss/developer-reference/description?spm=a2c4g.11186623.help-menu-31815.d_3_2_0.7ec3b930QfGlyk

相关术语介绍:

![img_30.png](img_30.png)

2. 新建 `mall-bucket-zy` bucket

> 配置环境变量secret-id,secret-key等, 详情见阿里云oss java sdk 【快速入门】 https://help.aliyun.com/zh/oss/developer-reference/getting-started?spm=a2c4g.11186623.help-menu-31815.d_3_3_0_4.72a15c719jlR9V&scm=20140722.H_32011._.OR_help-V_1#f033b3702421e

3. 使用阿里云oss api上传文件 mall-product -> MallProductApplicationTests -> testUploadFile() `参考[阿里云API文档](https://help.aliyun.com/zh/oss/developer-reference/list-of-operations-by-function?spm=a2c4g.11186623.help-menu-31815.d_3_2_1.6f2354b5wgWyLJ)`

参考阿里云oss java sdk文档, 导入依赖:

```xml
        <!--阿里云oss sdk-->
        <dependency>
            <groupId>com.aliyun.oss</groupId>
            <artifactId>aliyun-sdk-oss</artifactId>
            <version>3.17.4</version>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
        <!-- no more than 2.3.3-->
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-runtime</artifactId>
            <version>2.3.3</version>
        </dependency>
```

参考阿里云oss java sdk 上传文件 -> 上传文件流demo代码, 修改testUploadFile()方法 `注意需要设置access-key-id和access-key-secret环境变量`

```java
@SpringBootTest
public class Test{
    /**
     * 阿里云oss 上传文件测试
     * */
    @Test
    public void testUploadFile() throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "mall-bucket-zy";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "exampledir/表格.pdf";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        String filePath= "/Users/zhaoyu/Desktop/表格.pdf";
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        String region = "cn-beijing";

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException | FileNotFoundException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
```

上传成功:

![img_31.png](img_31.png)

### 2.4 SpringCloud Alibaba OSS 操作
alibaba的Springboot starter好像有点问题, 不一定能支持到springboot 3.2这么高的版本, 还是使用上述的方法完成springboot和oss的整合吧

可以参考: [【案例实战】SpringBoot整合阿里云文件上传OSS](https://developer.aliyun.com/article/1227235)

### 2.5 图片上传的各种方式介绍
1. 浏览器 -> 商品服务 -> oss服务器(大文件上传时, 后端服务压力大, 影响正常的业务流程)

要做两次大文件上传操作

![img_32.png](img_32.png)

2. 浏览器 -> 商品服务
   浏览器 -> oss服务器 (前端上传图片到oss, 把图片在oss上的地址传给后端; access-key, access-secret需要存到前端 不安全)

![img_33.png](img_33.png)

3. 浏览器 -> 商品服务 (获得图片的阿里云防伪签名)
   浏览器 -> oss服务器 (根据防伪签名把图片上传到oss服务器)

既提高了效率, 有保证了安全

![img_36.png](img_36.png)

### 2.6 third-party服务创建
创建mall-third-party服务 用于处理第三方服务, 比如阿里云oss防伪签名获取, 短信发送服务等第三方服务

测试Springboot整合阿里云oss操作 -> MallThridPartyApplicationTests :: aliyunOssUploadFile

### 2.7 服务端签名服务
服务端生成签名参考: [阿里云oss web端直传实践 - 服务端签名直传](https://help.aliyun.com/zh/oss/use-cases/obtain-signature-information-from-the-server-and-upload-data-to-oss?scm=20140722.S_help%40%40文档%40%4031926.S_BB1%40bl%2BRQW%40ag0%2BBB2%40ag0%2Bhot%2Bos0.ID_31926-RL_签名-LOC_doc%7EUND%7Eab-OR_ser-V_4-P0_0&spm=a2c4g.11186623.0.i4)

![img_37.png](img_37.png)

整体步骤:
1. 配置oss服务的跨域规则
2. 配置用户权限, 获取access-key和access-secret, 并将其设置到服务段代码的环境变量中
3. 服务器接收到Web端发送的用户上传文件的请求后，使用自己的长期凭证（accessKeyId和accessKeySecret）向阿里云STS请求一个临时授权凭证，这个临时凭证将授权Web端在限定的时间内对特定资源进行操作。临时访问凭证的使用应限制在最小必要范围内。
4. 服务端构建上传策略, 您可以根据您的业务需求在请求中构建请求策略，这些策略可能包括但不限于文件的最大尺寸、文件存储的具体位置以及请求的有效期限等。
5. 服务端计算签名, 在Web端能够发起上传请求之前，服务器需要基于前面定义的策略生成一个数字签名。这个签名用于验证请求的合法性和完整性。您可以通过控制台直接生成签名，也可以参考以下内容在您的代码中实现。
6. 服务端将所有必要的信息（包括但不限于签名、STS Token、Policy等）组织成适合HTTP POST请求的数据格式，以便于Web端使用这些数据构建上传请求。

新增OSSController以及getOssToken接口, 接口测试见postman

配置网关路由:
```yaml
server:
  port: 9995

spring:
  application:
    name: mall-gateway
  cloud:
    # ncaos配置
    nacos:
      discovery:
        server-addr: 8.152.0.119:8848 # Nacos服务注册中心地址
      config:
        server-addr: 8.152.0.119:8848 # Nacos配置中心地址
        file-extension: yaml # 指定加载yaml后缀的配置文件
        group: DEFAULT_GROUP # 指定服务所在的分组 eg: TEST_GROUP or DEV_GROUP

    # 配置路由
    gateway:
      routes:
        # 商品系统路由, 注意路由顺序 别让Path=/renren-admin/**把前面的路由给拦截了
        - id: mall-product-route
          uri: lb://mall-product
          predicates:
            - Path=/renren-admin/mallproduct/**
          filters:
            - StripPrefix=1 # 去掉路径上的前缀1层 renren-admin
        # 第三方服务路由
        - id: mall-third-party-route
          uri: lb://mall-third-party
          predicates:
            - Path=/renren-admin/oss/**
          filters:
            - StripPrefix=1 # 去掉路径上的前缀1层 renren-admin
        # renren-admin路由
        - id: renren-admin-route
          uri: lb://renren-admin
          predicates:
            - Path=/renren-admin/**

#          filters:
#            - RewritePath=/renren-admin/(?<segment>.*),/renren-admin/$\{segment}
```

### 2.8 上传图片功能实现
整体流程:
1. 阿里云OSS配置跨域
2. 后端服务签名实现(这里没有用角色扮演, 直接用子账号zhaoyu93生成的签名) 实现细节见 OssServiceImpl#getOssToken
3. 前端向阿里云oss提交表单, 携带签名信息, 具体见 singleUpload.vue

### 2.9 添加和修改功能完善
配置品牌管理页面显示我们上传到阿里云oss的图片, 需要打开bucket的权限, 并且不要开启阻止公共访问 否则无法访问 参考:https://developer.aliyun.com/article/1052776

代码参考 brand.vue

![img_38.png](img_38.png)

![img_39.png](img_39.png)

### 2.10 前端表单数据校验
前端校验: 对前端添加修改品牌的dialog进行表单校验(主要使用表单的rules属性完成), 参考 brand-add-or-update.vue

### 2.11 JSR-303校验实现
后端校验(前端校验不能覆盖所有情况 eg:直接请求接口就可以跳过前端校验): 利用aop的思想 + JSR303规范 完成校验

JSR是Java Specification Requests的缩写, 意思是Java规范提案, 是指向JCP(Java Community Process)提出新增一个标准化技术规范的正式请求, 任何人都可以提交JSR 以向Java平台增添新的API和服务。JSR已成为Java的一个重要标准。

JSR-303是Java EE 6的一项子规范, 叫做Bean Validation。Hibernate Validator是JSR-303的参考实现, Hibernate Validator提供了JSR-303规范中所有内置Constraint的实现, 除此以外还有一些附加的Constraint。

jakarta.validation-api 关注于定义验证规范和注解，而 spring-boot-starter-validation 关注于在 Spring Boot 应用程序中自动配置和集成验证功能。

- jakarta.validation-api 提供了数据验证的标准 API 和注解，是一个更为通用的 Java 规范，可以在任何遵循该规范的 Java 应用程序中使用。它定义了一系列的标准验证注解（如 @NotNull, @Size, @Min, 等）和API，用于在应用程序中进行数据验证。 作为一个规范，它可以被任何遵循 Jakarta Bean Validation 规范的验证框架实现，如 Hibernate Validator
- spring-boot-starter-validation 是一个针对 Spring Boot 应用程序的 Starter，它自动配置了 Bean Validation 的实现（如 Hibernate Validator），并提供了与 Spring 框架特性的集成，使得在 Spring Boot 应用程序中使用数据验证变得更加简单和方便

添加 `spring-boot-starter-validation`依赖, 使用相关注解完成校验, 在实体类上添加校验注解
- @NotNull: 用于校验字段是否为null
- @NotBlank: 用于校验字段是否为null或只包含空格
- @NotEmpty: 用于校验字段是否为null或字符串长度/集合大小为0

在函数行参的位置加@Valid注解, 开启校验 eg (通过BindingResult对象获取校验的结果信息):

```java
@PutMapping
@Operation(summary = "修改")
@LogOperation("修改")
//@RequiresPermissions("mallproduct:brand:update")
public Result update(@Valid @RequestBody BrandDTO dto, BindingResult result){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        //JSR303校验出了非法字段
        if (result.hasErrors()) {
        return new Result().error(400, result.getAllErrors().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        brandService.update(dto);

        return new Result();
        }
```

1. 给BrandDTO添加校验注解
```java
/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "品牌")
public class BrandDTO implements Serializable {
   private static final long serialVersionUID = 1L;

   @SchemaProperty(name = "品牌id")
   private Long brandId;

   @SchemaProperty(name = "品牌名")
   @NotEmpty(message = "品牌名称字段不能为空")
   private String name;

   @SchemaProperty(name = "品牌logo地址")
   @URL(message = "logo的格式必须是url合法地址")
   @NotEmpty(message = "logo字段不能为空")
   private String logo;

   @SchemaProperty(name = "介绍")
   private String descript;

   @SchemaProperty(name = "显示状态[0-不显示；1-显示]")
   private Integer showStatus = 1;

   @SchemaProperty(name = "检索首字母")
   @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是单个字母")
   @NotEmpty(message = "检索首字母字段不能为空")
   private String firstLetter;

   @SchemaProperty(name = "排序")
   @Min(value = 0, message = "排序字段不能小于0")
   @Max(value = 9999, message = "排序字段不能大于9999")
   //@Pattern(regexp = "^[0-9]+$", message = "排序字段必须是数字") 这里不能用@Pattern注解, @Pattern注解只能用于字符串校验 用到整型就报错
   private Integer sort = 0;
}

```
2. 给BrandController的update方法的入参dto加@Valid注解
3. 测试update接口, 不满足校验条件 则报错 400 Bad Request :

![img_41.png](img_41.png)

`控制台日志: 2024-11-21T22:35:33.352+08:00  WARN 96577 --- [mall-product] [nio-9990-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public io.renren.common.utils.Result com.zy.mallproduct.controller.BrandController.update(com.zy.mallproduct.dto.BrandDTO): [Field error in object 'brandDTO' on field 'name': rejected value []; codes [NotEmpty.brandDTO.name,NotEmpty.name,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [brandDTO.name,name]; arguments []; default message [name]]; default message [品牌名称字段不能为空]] ]`

> [git命令大全](https://blog.csdn.net/qq_43727392/article/details/137741796)


```markdown
在 Spring MVC 中，当你在控制器（Controller）的方法参数中使用 @Valid 或 @Validated 注解来启用 Spring 的验证机制时，可以紧跟着在方法参数中添加一个 BindingResult 参数。Spring MVC 会自动检测到这个参数，并将验证过程中产生的错误填充到这个 BindingResult 对象中。
这是如何做到的呢？主要是通过 Spring MVC 的参数解析机制实现的。当一个 HTTP 请求到达 Spring MVC 控制器的一个处理方法时，Spring 的 DispatcherServlet 会根据配置的 HandlerMapping 找到对应的处理器（Controller），然后根据 HandlerAdapter 的实现类来调用具体的方法。
在这个过程中，方法的参数需要被解析和填充。Spring MVC 有一套参数解析的策略，这套策略由一系列的 HandlerMethodArgumentResolver 组成，每个 Resolver 负责解析一种类型的参数。对于 @Valid 或 @Validated 注解和紧随其后的 BindingResult 参数，Spring MVC 使用特定的 Resolver 来处理它们。
当 @Valid 或 @Validated 注解的参数被解析时，Spring MVC 会调用配置的验证器（Validator）对参数对象进行验证。验证的结果，无论是成功还是失败，都会被封装到一个 BindingResult 对象中。如果方法参数中紧跟着有一个 BindingResult 类型的参数，Spring MVC 就会将这个验证结果对象传递给它。
这样，你就可以在方法体内通过检查 BindingResult 对象来判断验证是否通过，以及在验证失败时获取失败的详细信息。
这个机制使得在控制器方法中处理验证结果变得非常方便。你可以根据验证结果来决定是否继续执行方法的剩余部分，或者是直接返回一个错误响应给客户端。
示例代码中，BindingResult result 参数就是这样注入的。通过检查 result.hasErrors() 来判断是否有验证错误，如果有，可以进一步处理这些错误，例如，将错误信息返回给客户端。
```

### 2.12 统一异常处理
定义全局异常处理器: ValidatorExceptionHandler

定义错误响应编码枚举类(5位数字, 前两位约定为业务场景, 后三位约定为错误码): ErrorCode

### 2.13 分组异常
分组校验: 利用JSR303的分组校验功能, 实现添加和修改的校验分组, 参考 BrandDTO, AddGroups, UpdateGroups

分组校验的使用场景: 例如有些业务场景需要校验某些字段(比如新增品牌时, 不需要校验brandId; 但是更新品牌时 需要保证brandId不为空), 就可以使用分组校验

使用@Validated注解开启分组校验(@Valid注解不支持分组校验)

add接口按照AddGroups分组校验:
```java
@PostMapping
@Operation(summary = "保存")
@LogOperation("保存")
//@RequiresPermissions("mallproduct:brand:save")
public Result save(@Validated({AddGroups.class}) @RequestBody BrandDTO dto, BindingResult result){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        if(result.hasErrors()){
        return new Result().error(400, result.getAllErrors().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        brandService.save(dto);

        return new Result();
}   
```

update接口按照UpdateGroups分组校验:
```java
    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    //@RequiresPermissions("mallproduct:brand:update")
    public Result update(@Validated({UpdateGroups.class}) @RequestBody BrandDTO dto, BindingResult result){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        //JSR303校验出了非法字段
        if (result.hasErrors()) {
            return new Result().error(400, result.getAllErrors().stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

        brandService.update(dto);

        return new Result();
    }
```

> 注意: 当使用分组校验时, 不在对应分组中的校验都会失效

BrandDTO: 

```java
/**
 * 品牌
 *
 * @author zhaoyu93 zhaoyu93@meituan.com
 * @since 1.0.0 2024-11-17
 */
@Data
@Schema(name = "品牌")
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "品牌id")
	@NotNull(message = "更新数据品牌时, brandId字段不能为空", groups = {UpdateGroups.class})
	@Null(message = "添加品牌信息时, brandId字段可为空", groups = {AddGroups.class})
	private Long brandId;

	@SchemaProperty(name = "品牌名")
	@NotEmpty(message = "品牌名称字段不能为空", groups = {AddGroups.class})
	private String name;

	@SchemaProperty(name = "品牌logo地址")
	@URL(message = "logo的格式必须是url合法地址", groups = {AddGroups.class, UpdateGroups.class})
	@NotEmpty(message = "logo字段不能为空", groups = {AddGroups.class})
	private String logo;

	@SchemaProperty(name = "介绍")
	private String descript;

	@SchemaProperty(name = "显示状态[0-不显示；1-显示]")
	private Integer showStatus = 1;

	@SchemaProperty(name = "检索首字母")
	@Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是单个字母", groups = {AddGroups.class, UpdateGroups.class})
	@NotEmpty(message = "检索首字母字段不能为空", groups = {AddGroups.class})
	private String firstLetter;

	@SchemaProperty(name = "排序")
	@Min(value = 0, message = "排序字段不能小于0", groups = {AddGroups.class, UpdateGroups.class})
	@Max(value = 9999, message = "排序字段不能大于9999", groups = {AddGroups.class, UpdateGroups.class})
	private Integer sort = 0;
}
```






## 2. 基础业务
## 3. 商城业务


# 五、上线部署
## 1. K8S
## 2. Kubesphere
## 3. 集群部署

