## 说明
B站课程，动力节点SpringBoot全套教程[最详细的SpringBoot教程](https://www.bilibili.com/video/BV1PZ4y1j7QK)

1. 如何创建一个springboot框架web项目
2. 使用springboot框架集成springmvc
3. 使用springboot框架的核心配置文件application.properties
4. 使用springboot框架的核心配置文件application.yml或者application.yaml
5. springboot框架的核心配置application.properties和application.yml或者application.yaml同时存在？(无)
6. 多环境下核心配置文件的使用(properties)，工作中开发的环境有哪些：开发环境、测试环境、准生产环境、生成环境
7. 多环境下核心配置文件的使用(yaml或yml)(无)
8. 自定义配置(@Value一个一个获取)
9. 将自定义配置映射到一个对象
10. springboot集成jsp
11. springboot集成MyBatis
    a. 添加mybatis依赖，mysql驱动
    b. 使用mybatis提供的逆向工程生成实体bean，映射文件，DAO接口
    
    springboot集成MyBatis,主要的是两个注解@Mapper，@MapperScan
    @Mapper 需要在每一个Mapper接口类上添加，作用是扫描dao接口
    @MapperScan 是在springboot启动入口类上添加的，它是扫描所有包
    
    关于Mapper映射文件存放的位置的写法有以下两种
    1. 将Mapper接口和Mapper映射文件存放到src/main/java同一目录下，
        还需要在pom文件中手动指定资源文件夹路径
    2. 将Mapper接口和Mapper映射文件分开存放，Mapper接口存放到src/main/java目录下
        Mapper映射文件存放到resources(类路径)在springboot核心配置文件中指定mapper映射文件存放位置

16. SpringBoot项目下使用事务
    事务是完整的功能，也叫完整的业务。事务只和增删改有关系