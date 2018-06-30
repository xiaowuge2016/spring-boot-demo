# Spring Boot Demo

 个人练手项目，熟悉 spring boot 的各种用法。

## 使用说明
 1. 构建命令  # mvn clean package (跳过测试为  # mvn clean package -Dmaven.test.skip=true)

### 整合 docker 使用说明
 1. linux 环境下运行（安装好docker、maven）
 2. 构建命令  # mvn docker:build
 3. 启动命令  # docker run -d -p 58080:8080 -m 512m com.lifujian/demo:1.0.0
 4. 浏览器访问  http://127.0.0.1:58080/
