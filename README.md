# API测试

>使用Maven管理项目,整合线上线下测试，使用filter区分环境。

***请确认是否本地配置maven环境，否则导入不成功***


使用说明
----
### 目录结构
* src/main/filters: 线上/线下区分环境的配置文件。
* src/main/resources/config: 配置文件目录
  * config.properties：定义变量后，通过前面filters中的值来赋值
  * server.yaml:定义测试用的host,port,protocol,headers,params，如果具体的case的yaml中也定义，会覆盖，定义格式请参考case具体yaml。
* src/main/resources/data：存放测试参数，其中`test-data`文件夹中是内部测试环境数据，`product-data`是线上数据，文件和case是对应的。
* src/main/java：不多说，case脚本


#### 怎么写Http接口的case

>例如,LoginTest

继承`AbstractTest`，然后正常写脚本就ok

最后实现`testDataFileName`方法，返回参数配置的文件，就是上面`test-data`或则`product-data`
中的文件名（不要带后缀），比如`LoginTest`的参数配置文件是`login.yaml`，此函数的返回值写`return "login"`
就好。

#### 怎么配置参数
大家了解一下Yaml的使用。
```#模块名
   packageName: login
   #测试接口任务列表
   taskList:
   -   id: 1
       #测试方法名
       testName: testLogin
       #服务uri，注意带/
       uri: /login/one
       methodType: get
       #如果需要设置header，就写，不需要可以去掉
       headers:
         cookie: key=value
       #请求参数
       params:
         IMEI: 42424242safsd
         userId: 2342523452345
   -   id: 2
       testName: login-2
       uri: /login/two
   ```
#### 怎么写PSF接口case

继承`AbstractPSFTest`,实现`testDataFileName`方法，返回参数配置的文件，就是上面`test-data`或则`product-data`中的文件名（不要带后缀），比如`LoginTest`的参数配置文件是`login.yaml`，此函数的返回值写`return "login"`就好。


* PSF的server center，在config下server.yaml中配置`psfCenter: 10.0.1.1,10.0.0.2`，逗号隔开。

* 在test(product)-data中的具体文件中，配置`serviceType`与`taskList`同级，代表所有下面case均使用这个serviceType。另外PSF接口中，不用配置method。

参考：`com.yd.psf.Test`类，与`test-data`中`psf.yaml`写法


* login.properties 属性配置文件可以修改对应的cellphone、imei来获取司机端token device_type设置为1为调用Android登陆接口，device_type设置为2调用ios登陆接口
