# 一、项目结构
```
.
├── common-utils                      公共组件
│  └── src
│     └── main
│        ├── java
│        │  └── com
│        │     └── gcase
│        │        ├── feat            功能
│        │        ├── middleware      中间件
│        │        ├── model           数据模型
│        │        └── utils           工具类
│        └── resources                classpath
│
├── rpc-consumer                      rpc消费者
└── rpc-service                       rpc发布者
   ├── rpc-annotation-provider        rpc发布者注解式实现
   ├── rpc-api                        rpc服务暴露接口
   └── rpc-provider                   rpc发布者普通实现
```
# 二、项目规范
## 2.1 模块结构定义
### 1. 功能包
    目录结构：feat / <功能定义> / <实现方式> / <实现版本> / <具体实现>
### 2. 中间件包
    目录结构：middleware / <中间件定义> / <实现方式> / <实现版本> / <具体实现>
### 3. 数据模型包
    目录结构：model / <场景定义> / <实现版本> / <模型 | 状态码>
### 4. 工具类
    目录结构：utils / <工具定义> / <实现方式> / <实现版本> / <具体实现>
## 2.2 信息补充描述
- 方法的必要注释 
- 一个实现版本所需要的环境配置以及依赖说明
- 原则上一个实现版本对应实现一个案例