**一、基本说明**
1. 本项目基于Spring、SpringMVC、MyBatis框架搭建
2. RPC框架使用Dubbo
3. 分布式缓存使用Redis
4. 加入百度AI等第三方api
5. 代码结构、代码规范遵循阿里巴巴开发文档
6. 项目在探索的过程中会不定期修改、维护

**二、模块说明**
1. template-common： 界面实体VO等
2. template-core： 数据库操作DAO等
3. template-service： 核心业务实现层
4. temlate-manager： 对template-service层公共部分的提取
5. template-dubbo-client： 对外服务接口定义层
6. template-dubbo-service： 对外服务接口实现层
7. template-web： web控制器层
8. template-monitor： 监控层
9. template-test： 单元测试等