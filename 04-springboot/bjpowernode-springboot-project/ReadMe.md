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
    1. 添加mybatis依赖，mysql驱动
    2. 使用mybatis提供的逆向工程生成实体bean，映射文件，DAO接口
    
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
17. SpringMvc中的其他注解
18. SpringBoot使用RESTful风格
19. SpringBoot集成Redis
    a.添加操作redis数据类型的依赖
    b.在springboot核心配置文件中添加redis的配置
20. SpringBoot集成Dubbo分布式框架
    1. 接口工程：存放实体bean和业务接口
    2. 服务提供者：业务接口的实现类并将服务暴露且注册到注册中心，调用数据持久层
        - 添加依赖(dubbo,注册中心,接口工程)
        - 配置服务提供者核心配置文件
    3. 服务消费者：处理浏览器客户端发送的请求，从注册中心调用服务提供者所提供的服务
        - 添加依赖(dubbo,注册中心,接口工程)
        - 配置服务消费者核心配置文件
23. SpringBoot集成Dubbo、Redis、MyBatis、Spring、SpringMvc、JSP
    1. 接口工程：存放实体bean和业务接口
    2. 服务提供者：它是一个SpringBoot框架web项目，集成MyBatis,Redis
        - 添加依赖：MyBatis依赖、MySql驱动依赖、Dubbo依赖、zookeeper依赖、Redis依赖、接口工程
        - 配置SpringBoot核心配置文件：
            - 配置连接数据库
            - 配置连接Redis
            - 配置Dubbo
    3. 服务消费者：它是一个SpringBoot框架web项目，集成JSP、Dubbo、接口工程
        - 添加依赖：Dubbo依赖、zookeeper依赖、解析JSP页面的依赖
        - 配置SpringBoot核心配置文件
            - 配置视图解析器
            - 配置Dubbo
30. SpringBoot使用拦截器
    1. 定义一个拦截器，实现HandlerInterceptor接口
    2. 创建一个配置类(即：在SpringMvc配置文件中使用mvc:interceptors)
31. SpringBoot框架下使用Servlet
    - 创建一个Servlet它要继承HttpServlet
    - 在web.xml配置文件中使用servlet servlet-mapping
    1. 注解方式：@WebServlet @ServletCompanScan
    2. 配置类
33. SpringBoot框架下使用过滤器Filter



 