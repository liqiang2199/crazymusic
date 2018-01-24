## 项目说明

#### 服务器地址配置
manifest配置：
```xml
     <!--服务器配置
        //正式服务器地址
        测试服务器地址
        RAP服务器地址
        HOST_TYPE // 0 正式服务器 ,1 测试服务器, 2 RAP服务器
        -->
    <meta-data
            android:name="HOST_URL"
            android:value="http://www.xxxx.com/" />
    <meta-data
            android:name="HOST_TEST_URL"
            android:value="http://yykj.vigorddns.com:8084/yikao/" />
    <meta-data
            android:name="HOST_RAP_URL"
            android:value="http://rap.taobao.org/mockjsdata/4849/" />
    <meta-data
            android:name="HOST_TYPE"
            android:value="2" />
```
获取HOST URL方法
```java
    XMeatData.getHostUrl(); //获取服务器地址
    XMeatData.getUrlAll(String url);//将一个无host的图片地址转换为完整地址
```

## 项目架构使用框架
1. xutils 的数据库及日志工具

```java
LogUtil.d("日志");//xutils日志打印

//数据库操作
daoConfig = new DbManager.DaoConfig()
.setDbName(DB_NAME)    //设置数据库名称
.setDbDir(new File(DB_PATH))  //数据库路径
.setDbVersion(DB_VERSION)  //数据库版本
.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
@Override
public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
// TODO: ...
// db.addColumn(...);
// db.dropTable(...);
  }
});
 x.getDb(daoConfig);
 //具体数据库操作参考xutils
```

2. jackson json 解析库
```groovy
 compile 'com.cqyanyu:jackson:1.0'
```

3. SpinKit 加载动画
https://github.com/ybq/Android-SpinKit
```groovy
    compile 'com.cqyanyu:SpinKit:1.0'
```
![](http://yykj.vigorddns.com:8084/web/UI%E6%A0%B7%E4%BE%8B/Android%E6%A0%B7%E4%BE%8B/%E5%8A%A0%E8%BD%BD%E5%8A%A8%E7%94%BB2.gif)
![](http://yykj.vigorddns.com:8084/web/UI%E6%A0%B7%E4%BE%8B/Android%E6%A0%B7%E4%BE%8B/%E5%8A%A0%E8%BD%BD%E5%8A%A8%E7%94%BB.gif)

4. eventbus3.0 事件通知
```groovy
    compile 'org.greenrobot:eventbus:3.0.0'
```

5. okhttp 网络请求
```groovy
    compile 'com.squareup.okhttp3:okhttp:3.4.1'
```

6. fresco图片加载
   http://www.fresco-cn.org/

7. 蒲公英bug 统计
```groovy
    compile 'com.pgyersdk:sdk:2.4.0'
```
8. 极光推送2.1.9

## 封装方法

javaDoc  http://yykj.vigorddns.com:8084/gradle/Android%20frameworkDoc/
```java
x.http()  //网络请求
x.user() //用户信息操作
```
![](http://oe8vx7mp5.bkt.clouddn.com//dcfa1a88b9e52e28836e46a1535e4326.png)
![](http://oe8vx7mp5.bkt.clouddn.com//84e1f6d1aecebf6bb8f5e5a381212a45.png)
![](http://oe8vx7mp5.bkt.clouddn.com//c35e3a456eddb504e0a65de2bf1a79e1.png)
