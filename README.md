
### 淋汾博客系统 基于Spring Boot +Vue前后端分离项目

前端项目地址  <a href='https://github.com/linfen123456/linfen-blog-ui'>https://github.com/linfen123456/linfen-blog-ui</a>

### 项目特点
- 前后端分离架构
- 采用Restfull API 规范开发
- 统一异常拦截，友好的错误提示
- 基于注解 + Aop切面实现全方位日记记录系统
- 基于Mybatis拦截器 + 策略模式实现数据权限控制
- Jwt Token 鉴权机制 
- 提供解决前后分离第三方社交登录方案 
- Spring Social集成Security实现第三方社交登录
- 基于Mybatis-Plus
- 基于注解实现数据脱敏,防隐私
- 七牛云文件存储
- 二次元图片选择

### 项目部署
后端项目打包：
修改application.yml的active为prod,然后执行<br>
 mvn package
 生成后将linfen-system-1.4.jar拷贝到服务端，切换到文件目录后执行：<br>
 nohup java -jar linfen-system-1.4.jar -Xms256m -Xmx1024m > linfen-blog.out 2>&1 &<br>

前端项目运行：<br>
 npm run dev<br>
前端项目打包：<br>
 npm run buld:prod<br>


在服务器可以使用nginx进行端口转发和代理配置

### 项目截图
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic1.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic2.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic3.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic4.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic5.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic6.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic7.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic8.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic9.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic10.png?raw=true)
![login](https://github.com/linfen123456/linfen-blog/blob/master/screenshot/run_pic11.png?raw=true)

