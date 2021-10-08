# 工程简介
## 使用静态资源的访问方式访问指定文件

### 背景

为公司某个测试环境排查bug时发现问题：公司有定时生成报表的业务，测试环境发现文件夹下已经生成文件，但是下载时却访问404。

排查发现``` static-locations:```缺少文件夹的映射，以前未使用过这种方式下载文件，所以写一篇简单的入门文章学习一下。

### 优点：

1. 可以直接通过URL访问图片（配合文件上传，然后返回URL给页面展示使用十分方便）
2. 需要下载文件时只用知道文件名就可以通过URL下载，不许要单独写接口
3. 一些静态数据可以以json的格式存放，使用时直接访问不用写接口

### 使用方法

#### **添加配置**

```yaml
spring:
  application:
    name: demo006

  mvc:
    static-path-pattern: /**

  resources:
    static-locations: classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:${spring.imagePath},file:${spring.excelPath},file:${spring.jsonPath}
    
#路径需要按需求修改
  imagePath: /Users/zhangyuhang/Git_Repository/exercise-spring/demo006/upload-file/images/
  excelPath: /Users/zhangyuhang/Git_Repository/exercise-spring/demo006/upload-file/excel/
  jsonPath: /Users/zhangyuhang/Git_Repository/exercise-spring/demo006/upload-file/json/
```



1. spring.mvc.static-path-pattern代表的含义是我们应该以什么样的路径来访问静态资源，换句话说，只有静态资源满足什么样的匹配条件，Spring Boot才会处理静态资源请求，以官方配置为例：

   ```yaml
   #   这表示只有静态资源的访问路径为/resources/**时，才会处理请求
   spring.mvc.static-path-pattern=/resources/**
   ```

   假定采用默认的配置端口，那么只有请求地址类似于“http://localhost:8080/resources/123.png”时，Spring Boot才会处理此请求，处理方式是将根据模式匹配后的文件名查找本地文件，那么应该在什么地方查找本地文件呢？这就是“spring.resources.static-locations”的作用了。

2. spring.resources.static-locations用于告诉Spring Boot应该在何处查找静态资源文件，这是一个列表性的配置，查找文件时会依赖于配置的先后顺序依次进行，默认的官方配置如下：

   ```yaml
   spring.resources.static-locations=classpath:/static,classpath:/public,classpath:/resources,classpath:/META-INF/resources
   ```

   spring.resources.static-locations在这里配置静态资源路径，前面说了这里的配置是覆盖默认配置，所以需要将默认的也加上否则static、public等这些路径将不能被当作静态资源路径，在这个最末尾的file:${web.upload-path}之所有要加file:是因为指定的是一个具体的硬盘路径，其他的使用classpath指的是系统环境变量。



#### **核心代码**

啥都不说，直接上代码。

完整代码：https://github.com/Yu-Hang-Z/Exercise-Spring.git中demo006项目

流程：上传图片 --> 返回url --> 使用URL显示图片

 			上传文件（Excel其他文件也行）--> 返回 URL --> 使用 URL 下载文件

1. Controller层核心代码展示

   ```java
       @RequestMapping("/images")
       public WebResponse uploadImage(MultipartFile image){
           if (image == null){
               return WebResponse.ofError("上传文件为空");
           }
           return uploadService.saveImage(image);
       }
   
       @RequestMapping("/excel")
       public WebResponse uploadExcel(MultipartFile file){
           if (file == null){
               return WebResponse.ofError("上传文件为空");
           }
           return uploadService.saveExcel(file);
       }
   
   ```

2. Service层核心代码展示

   ```java
   @Service
   public class UploadService {
   
       @Value(value = "${spring.imagePath}")
       private String imagePath;
   
       @Value(value = "${spring.excelPath}")
       private String excelPath;
   
       public WebResponse saveImage(MultipartFile multipartFile ){
           WebResponse webResponse = new WebResponse();
           try {
               // 保存图片,使用UUID防止重名
               String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
               File file = new File(imagePath + fileName);
               multipartFile.transferTo(file);
               String url = "http://localhost:9999/" + fileName;
               FileInfo fileInfo = new FileInfo(fileName,url);
               webResponse.setData(fileInfo);
               webResponse.setMsg("上传成功");
           } catch (IOException e) {
               e.printStackTrace();
               webResponse.setMsg("上传失败");
               return webResponse;
           }
           webResponse.setSuccess(true);
           return webResponse;
       }
   
       public WebResponse saveExcel(MultipartFile multipartFile ){
           WebResponse webResponse = new WebResponse();
           try {
               // 保存图片,使用UUID防止重名
               String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
               File file = new File(excelPath + fileName);
               multipartFile.transferTo(file);
               String url = "http://localhost:9999/" + fileName;
               FileInfo fileInfo = new FileInfo(fileName,url);
               webResponse.setData(fileInfo);
               webResponse.setMsg("上传成功");
           } catch (IOException e) {
               e.printStackTrace();
               webResponse.setMsg("上传失败");
               return webResponse;
           }
           webResponse.setSuccess(true);
           return webResponse;
       }
   }
   
   ```

   白天上班，晚上时间必较紧，代码其实还有很多可以优化的地方（例如：文件拷贝方法可以复用，文件url的路径设计不合理等），望大家见谅。

#### **效果图**

1. 上传图片效果图

   ![CleanShot 2021-09-30 at 10.58.46@2x](http://typoraimages.oss-cn-beijing.aliyuncs.com/img/CleanShot%202021-09-30%20at%2010.58.46@2x.png)

2. URL展示图片

   ![CleanShot 2021-09-30 at 11.00.34@2x](http://typoraimages.oss-cn-beijing.aliyuncs.com/img/CleanShot%202021-09-30%20at%2011.00.34@2x.png)

3. 上传Excel文件

   ![CleanShot 2021-09-30 at 11.15.08@2x](http://typoraimages.oss-cn-beijing.aliyuncs.com/img/CleanShot%202021-09-30%20at%2011.15.08@2x.png)

4. URL下载文件

   ![CleanShot 2021-09-30 at 11.15.56@2x](http://typoraimages.oss-cn-beijing.aliyuncs.com/img/CleanShot%202021-09-30%20at%2011.15.56@2x.png)

## 参考

- [1] [Springboot之静态资源路径配置](https://blog.csdn.net/zsl129/article/details/52906762)
- [2] [Spring Boot(六)：如何配置静态资源的地址与访问路径](https://blog.csdn.net/yiifaa/article/details/78299052)

### 使用方法


# 延伸阅读

