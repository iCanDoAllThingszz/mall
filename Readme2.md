## 10.缓存和分布式锁
### 10.1 什么是缓存?
缓存的目的是降低直接对数据库的访问频率, 从而提高接口性能。

![img_63.png](img_63.png)

### 10.2 缓存的分类

> 使用使用缓存 and 数据库时, 本地缓存 and 分布式缓存时。怎么保证数据的一致性很重要

> 读多写少的场景适合用缓存, 写多的场景得同时更新缓存和数据库

1. 本地缓存(一级缓存)
将缓存数据存在本地内存中, 比如HashMap。在分布式系统中, 每个节点都有本地缓存, 可能导致缓存数据冗余 缓存效率不高, 因此可以引入分布式缓存(二级缓存)

2. 分布式缓存(二级缓存)
将缓存数据存在分布式缓存服务器中, 比如Redis, 缓存数据很多的情况下, Redis可以横向扩展(主从), 也可以纵向扩展(集群)

![img_64.png](img_64.png)

### 10.3 整合Redis
新服务器中安装docker, redis; springboot项目整合redis:

1. mall-product服务中添加spring-boot-starter-data-redis依赖
2. 在application.yaml中进行配置
```yaml
spring:
  data:
    redis:
      host: 47.95.17.132
      port: 6379
```
3. 在SpringBootApplicationTest测试类中测试redis, MallProductApplicationTests # testRedis, RedisConfig中新建bean, 测试成功

