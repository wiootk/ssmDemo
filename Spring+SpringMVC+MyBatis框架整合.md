[TOC]
## Spring+SpringMVC+MyBatis框架整合
### 1、基本概念
Spring 是一个轻量级的控制反转（ IoC ）和面向切面（ AOP ）的容器框架。
Spring MVC  分离了 控制器 、模型 对象 、分派器以及处理程序对象的角色。
MyBatis 是一个基于 Java 的 持久层 框架。使用简单的  XML 或注解用于配置和原始映射，将接口和  Java  的 POJOs 映射成数据库中的记录。
### 2、开发环境搭建
#### 1.安装jdk
新建：JAVA_HOME  ：E:\Java\jdk1.6.0_43
编辑：Path  ：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin
新建：CLASSPATH： .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
测试，打开“运行”-->输入“CMD"-->java，javac，java -version
#### 2.MyEclipse的安装
       window-->preference输入jdk
#### 3.Tomcat8的安装
#### 4.MyEclipse配置Tomcat
window-->preference输入tomcat
#### 5.Maven安装配置
##### 5.1 maven配置环境变量 
 下载maven解压：
      5.1 新建：MAVEN_HOME  ：D:\server\apache-maven-3.2.1
      5.2 编辑：Path ：%MAVEN_HOME%\bin;
测试：mvn -version
##### 5.2 Maven数据仓库的配置     
        conf/settings.xml:<localRepository>d:/maven_jar</localRepository>
##### 5.3 MyEclipse中集成Maven
 在preferences中进行配置
验证：File-->new-->other-->MyEclipse-->Maven4MyEclipse-->Maven Project
#### 6.SVN安装
     SVN是代码版本管理器，首先在本地安装一个SVN管理器，然后配置MyEclipse的SVN插件，将插件下载下来解压后，直接复制到MyEclipse安装目录下dropins中即可。任意建立一个项目，右键项目-->team-->share Project..，打开后如果看到了SVN代表插件安装成功。
#### 7. JS代码提示安装（Spket插件）
Spket插件：http://download.csdn.net/detail/u012909091/7335891      
解压后复制到MyEclipse的dropins包，重启（SVN的安装只需要将文件解压，然后放在dropins目录下即可，无需配置）
配置:Windows-->Preferences-->Speket-->New:jQuery--> Add Library : jQuery-->Add File:本地jquery文件-->Default
最后Window-->General-->Editors-->File Associatior : *.js -->Spket JavaScript Editor。右键项目-->Reload JavaScript Profile再打开JS文件可用看到代码提示了
#### 8. JAVA代码提示       

Preferences->java->editor->content assist->auto activation->..for java :.abc

### 3、在IDEA 新建maven工程：maven-archetype-webapp

**构建maven项目结构**

- src
    * main
        *  java
        *  resources
        *  webapp
            - WEB-INF
            - index.jsp
    * test
        *  java
        *  resources
**File->modeues->更改新建包类别** 

### 4、SSM整合
#### 4.1、Maven引入需要的JAR包
**pom.xml**
``` xml
<properties>
    <!-- spring版本号 -->
    <spring.version>4.0.2.RELEASE</spring.version>
    <!-- mybatis版本号 -->
    <mybatis.version>3.2.6</mybatis.version>
    <!-- log4j日志文件管理包版本 -->
    <slf4j.version>1.7.7</slf4j.version>
    <log4j.version>1.2.17</log4j.version>
</properties>
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <!-- 表示开发的时候引入，发布的时候不会加载此包 -->
        <scope>test</scope>
    </dependency>
    <!-- spring核心包 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-oxm</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- mybatis核心包 -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>${mybatis.version}</version>
    </dependency>
    <!-- mybatis/spring包 -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.2</version>
    </dependency>
    <!-- 导入java ee jar 包 -->
    <dependency>
        <groupId>javax</groupId>
        <artifactId>javaee-api</artifactId>
        <version>7.0</version>
    </dependency>
    <!-- 导入Mysql数据库链接jar包 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.30</version>
    </dependency>
    <!-- 导入dbcp的jar包，用来在applicationContext.xml中配置数据库 -->
    <dependency>
        <groupId>commons-dbcp</groupId>
        <artifactId>commons-dbcp</artifactId>
        <version>1.2.2</version>
    </dependency>
    <!-- JSTL标签类 -->
    <dependency>
        <groupId>jstl</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!-- 日志文件管理包 -->
    <!-- log start -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
    </dependency>
    <!-- 格式化对象，方便输出日志 -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.1.41</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>${slf4j.version}</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>${slf4j.version}</version>
    </dependency>
    <!-- log end -->
    <!-- 映入JSON -->
    <dependency>
        <groupId>org.codehaus.jackson</groupId>
        <artifactId>jackson-mapper-asl</artifactId>
        <version>1.9.13</version>
    </dependency>
    <!-- 上传组件包 -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.3.1</version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.4</version>
    </dependency>
    <dependency>
        <groupId>commons-codec</groupId>
        <artifactId>commons-codec</artifactId>
        <version>1.9</version>
    </dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.7.5</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.7.5</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.7.5</version>
</dependency>
</dependencies>
    <!--  <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.3</version>
                <configuration> 
                     <encoding>utf8</encoding>
                    <source>${java-version}</source>
                    <target>${java-version}</target>
                    <encoding>UTF-8</encoding>
                    <compilerArguments>
                        <verbose />
                        <bootclasspath>${java.home}\jre\lib\rt.jar;${java.home}\jre\lib\jce.jar</bootclasspath>
                    </compilerArguments>
                </configuration>
            </plugin>
              <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-source-plugin</artifactId>
                <version>2.2.1</version>
                <executions>
                    <execution>
                        <id>attach-sources</id>
                        <goals>
                            <goal>jar-no-fork</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>  

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.4</version>
                 <configuration>
                    <warName>${project.artifactId}</warName>
                    <warSourceExcludes>nodejs/**</warSourceExcludes>
                    <webResources>
                        <resource>
                            <filtering>true</filtering>
                            <directory>src/main/profile</directory>
                            <targetPath>WEB-INF</targetPath>
                            <includes>
                                <include>**/web.xml</include>
                            </includes>
                        </resource>
                        <resource>
                            <filtering>true</filtering>
                            <directory>src/main/profile/pages</directory>
                            <targetPath>pages</targetPath>
                            <includes>
                                <include>**</include>
                            </includes>
                        </resource>
                    </webResources>
                    <warSourceDirectory>src/main/webapp</warSourceDirectory>
                    <webXml>src/main/webapp/WEB-INF/web.xml</webXml>
                </configuration>
            </plugin>                 
        </plugins>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
   <distributionManagement>
        <repository>
            <id>dcsoft_releases</id>
            <url>http://172.168.2.160/content/repositories/releases/</url>
        </repository>
        <snapshotRepository>
            <id>dcsoft_snapshots</id>
            <url>http://172.168.2.160/content/repositories/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>-->
```
#### 4.2、Spring与MyBatis的整合

##### 4.2.1、建立JDBC属性文件

``` xml
jdbc.properties （文件编码修改为 utf-8 ）
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/demo
username=root
password=
#定义初始连接数
initialSize=0
#定义最大连接数
maxActive=20
#定义最大空闲
maxIdle=20
#定义最小空闲
minIdle=1
#定义最长等待时间
maxWait=60000
```

##### 4.2.2、建立spring-mybatis.xml配置文件
**spring-mybatis.xml**
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans  
   http://www.springframework.org/schema/beans/spring-beans-3.1.xsd  
   http://www.springframework.org/schema/context  
   http://www.springframework.org/schema/context/spring-context-3.1.xsd  
   http://www.springframework.org/schema/mvc  
   http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">
    <!-- 自动扫描 -->
    <context:component-scan base-package="com.jun"/>
    <!-- 引入配置文件 -->
    <bean id="propertyConfigurer"
          class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:jdbc.properties"/>
    </bean>

    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
          destroy-method="close">
        <property name="driverClassName" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
        <!-- 初始化连接大小 -->
        <property name="initialSize" value="${initialSize}"></property>
        <!-- 连接池最大数量 -->
        <property name="maxActive" value="${maxActive}"></property>
        <!-- 连接池最大空闲 -->
        <property name="maxIdle" value="${maxIdle}"></property>
        <!-- 连接池最小空闲 -->
        <property name="minIdle" value="${minIdle}"></property>
        <!-- 获取连接最大等待时间 -->
        <property name="maxWait" value="${maxWait}"></property>
    </bean>

    <!-- spring和MyBatis完美整合，不需要mybatis的配置映射文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 自动扫描mapping.xml文件 -->
        <property name="mapperLocations" value="classpath:com/jun/mapping/*.xml"></property>
    </bean>

    <!-- DAO接口所在包名，Spring会自动查找其下的类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.jun.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

    <!-- (事务管理)transaction manager, use JtaTransactionManager for global tx -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
    <!--<bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <prop key="com.jason.exception.SystemException">error/500</prop>
            <prop key="com.jason.exception.BusinessException">error/errorpage</prop>
            <prop key="java.lang.exception">error/500</prop>
        </props>
    </property>
</bean>-->
```

##### 4.2.3、Log4j的配置
**log4j.properties**
``` xml
#定义LOG输出级别
log4j.rootLogger=INFO,Console,File
#定义日志输出目的地为控制台
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.Target=System.out
#可以灵活地指定日志输出格式，下面一行是指定具体的格式
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=[%c] - %m%n
#文件大小到达指定尺寸的时候产生一个新的文件
log4j.appender.File=org.apache.log4j.RollingFileAppender
#指定输出目录
log4j.appender.File.File=logs/ssm.log
#定义文件最大大小
log4j.appender.File.MaxFileSize=10MB
# 输出所以日志，如果换成DEBUG表示输出DEBUG以上级别日志
log4j.appender.File.Threshold=ALL
log4j.appender.File.layout=org.apache.log4j.PatternLayout
log4j.appender.File.layout.ConversionPattern=[%p] [%d{yyyy-MM-dd HH\:mm\:ss}][%c]%m%n
```

##### 4.2.4、JUnit测试
######4.2.4.1、创建测试用表
**在数据库建立测试表：**
``` sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*Data for the table `user_t` */
insert  into `user`(`id`,`name`,`password`,`age`) values (1,'测试','abc',24);
```
###### 4.2.4.2、利用MyBatis Generator自动创建代码
*mybatis-3.2.6.jar、mybatis-generator-core-1.3.2.jar、mysql-connector-java-5.1.30.jar*
**generatorConfig.xml：**
*文件以ANSI格式存储*
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>

 <!-- 配置文件路径 
    <properties url="${mybatis.generator.generatorConfig.properties}"/>
    -->
    <!-- 数据库驱动-->
    <classPathEntry  location="mysql-connector-java-5.1.30.jar"/>
    <context id="DB2Tables"  targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接URL，用户名、密码 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost:3306/demo" userId="root" password="">
        </jdbcConnection>
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!-- 生成模型的包名和位置-->
        <javaModelGenerator targetPackage="com.jun.entity" targetProject="src">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- 生成映射文件的包名和位置-->
        <sqlMapGenerator targetPackage="resources.com.jun.mapping" targetProject="src">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!-- 生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.jun.dao" targetProject="src">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!-- 要生成的表 tableName是数据库中的表名或视图名 domainObjectName是实体类名-->
        <table tableName="user" domainObjectName="User" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
    </context>
</generatorConfiguration>
```

**Start.bat**
`Java -jar mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite`
完成后将文件复制到工程中(mapper.xml文件放到resources 文件夹内)。
###### 4.2.4.2（2）利用MyBatis Generator自动创建代码（maven插件）
1. 依赖 pom.xml
```xml
  <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
            <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
            <verbose>true</verbose>
            <overwrite>true</overwrite>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>cn.doity</groupId>
                <artifactId>mybatis-generator-bySweb</artifactId>
                <version>1.0.1</version>
                <scope>system</scope>
                <systemPath>${project.basedir}/lib/mybatis-generator-bySweb.jar</systemPath>
            </dependency>
             <!--通过 Maven 引入-->
                    <!--<dependency>-->
                    <!--<groupId>cn.doity</groupId>-->
                    <!--<artifactId>mybatis-generator-bySweb</artifactId>-->
                    <!--<version>1.0.1</version>-->
                    <!--</dependency>-->
        </dependencies>
    </plugin>
```
2. 配置文件 mybatisGenerator.properties
```properties
## 数据库信息
jdbc.dr=com.mysql.jdbc.Driver
jdbc.uip=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF8
jdbc.un=root
jdbc.pw=root

### Generator 相关设置
## 表名
tableName=%
## 模块名
moduleName=abc
# 包路径配置
model.package=com.jun.entity
dao.package=com.jun.dao
xml.mapper.package=mapper
target.resources=src/main/resources
target.project=src/main/java
```
3. 配置文件 generatorConfig.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<!-- 配置Run As Maven build : Goals 参数 : mybatis-generator:generate -Dmybatis.generator.overwrite=true -->
<!-- 配置 tableName,使用 Run As Maven build 生成 dao model 层 -->
<generatorConfiguration>
    <!--修改config.properties中的模块名称、表名即可-->
    <properties resource="mybatisGenerator.properties"/>
    <context id="mysqlTables" targetRuntime="MyBatis3">
        <!--<property name="daoPackage" value="com.jun.${moduleName}.persistence.custom"/>-->
        <property name="daoPackage" value="com.jun.dao"/>
        <!--序列化-->
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>
        <!--插入成功后返回ID-->
        <plugin type="cn.doity.common.generator.plugin.InsertAndReturnKeyPlugin"/>
        <!--分页查询功能-->
        <plugin type="cn.doity.common.generator.plugin.SelectByPagePlugin"/>
        <!--生成带有for update后缀的select语句插件-->
        <plugin type="cn.doity.common.generator.plugin.SelectForUpdatePlugin"/>
        <commentGenerator>
            <!-- 代码合并时需要用到 -->
            <property name="suppressAllComments" value="false"/>
            <!--关闭注释-->
            <property name="suppressDate" value="true"/>
        </commentGenerator>
        <!-- 数据库连接方式 -->
        <jdbcConnection driverClass="${jdbc.dr}" connectionURL="${jdbc.uip}"
                        userId="${jdbc.un}" password="${jdbc.pw}">
        </jdbcConnection>
        <javaTypeResolver type="cn.doity.common.generator.plugin.MyTypeResolver">
            <property name="forceBigDecimals" value="true"/>
        </javaTypeResolver>
        <!--生成的model 包路径 -->
        <javaModelGenerator targetPackage="${model.package}" targetProject="${target.project}">
            <property name="enableSubPackages" value="ture"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--生成xml mapper文件 路径 -->
        <sqlMapGenerator targetPackage="${xml.mapper.package}" targetProject="${target.resources}">
            <property name="enableSubPackages" value="ture"/>
        </sqlMapGenerator>
        <!-- 生成的Dao接口 的包路径 -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="${dao.package}" targetProject="${target.project}">
            <property name="enableSubPackages" value="ture"/>
        </javaClientGenerator>
        <!--表别名在mysql下面，删除语句会有问题，alias="t"-->
        <table tableName="${tableName}" alias="t">
        </table>
    </context>
</generatorConfiguration>
```



###### 4.2.4.3、建立Service接口和实现类

**com.jun.service.UserService.java**
``` java
public interface UserService {
    public User getUserById(int userId);
}
```

**com.jun.service.impl.UserServiceImpl.java**
``` java
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }
}
```

###### 4.2.4.4、建立测试类

**注释掉的部分是不使用 Spring 时，一般情况下的一种测试方法**
**TestMyBatis**
``` java 
@RunWith(SpringJUnit4ClassRunner.class)    //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    // private ApplicationContext ac = null;
    @Resource
    private UserService userService = null;
// @Before
//     public void before() {
//        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        userService = (IUserService) ac.getBean("userService");
//     }
    @Test
    public void test1() {
        User user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }
}
```
#### 4.3、整合SpringMVC
##### 4.3.1、配置spring-mvc.xml
*配置里面的注释也很详细，在此就不说了，主要是 自动扫描控制器，视图模式，注解的启动 这三个*
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
   http://www.springframework.org/schema/context
   http://www.springframework.org/schema/context/spring-context-3.1.xsd
   http://www.springframework.org/schema/mvc
   http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd">
   <!--默认注解映射的支持 -->
    <mvc:annotation-driven/>
    <!-- 自动扫描该包，使SpringMVC认为包下用了@controller注解的类是控制器 -->
    <context:component-scan base-package="com.jun.controller"/>
    <!--避免IE执行AJAX时，返回JSON出现下载文件 -->
    <bean id="mappingJacksonHttpMessageConverter"
          class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
        <property name="supportedMediaTypes">
            <list>
                <value>text/html;charset=UTF-8</value>
            </list>
        </property>
    </bean>

    <!-- 静态资源处理-->
    <mvc:resources mapping="/css/**" location="/css/"/>
    <mvc:default-servlet-handler/>

    <!-- 启动SpringMVC的注解功能，完成请求和注解POJO的映射 -->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter ">
        <property name="messageConverters">
            <list>
                <ref bean="mappingJacksonHttpMessageConverter"/>    <!-- JSON转换器 -->
                <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter"/>
                <bean class="org.springframework.http.converter.xml.SourceHttpMessageConverter"/>
                <bean class="org.springframework.http.converter.FormHttpMessageConverter"/>
                <bean class="org.springframework.http.converter.StringHttpMessageConverter"/>
            </list>
        </property>
    </bean>
    <!-- 定义跳转的文件的前后缀 ，视图模式配置-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 这里的配置我的理解是自动给后面action的方法return的字符串加上前缀和后缀，变成一个 可用的url地址 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!-- 配置文件上传，如果没有使用文件上传可以不用配置，当然如果不配，那么配置文件中也不必引入上传组件包 -->
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 默认编码 -->
        <property name="defaultEncoding" value="utf-8"/>
        <!-- 文件大小最大值 -->
        <property name="maxUploadSize" value="10485760000"/>
        <!-- 内存中的最大值 -->
        <property name="maxInMemorySize" value="40960"/>
    </bean>
</beans>
```
##### 4.3.2、配置web.xml文件
**web.xml**
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
         version="3.0">
    <display-name>SSMDemo</display-name>
    <!-- Spring和mybatis的配置文件 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mybatis.xml</param-value>
    </context-param>
    <!-- 编码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <async-supported>true</async-supported>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!-- Spring监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- 防止Spring内存溢出监听器 -->
    <listener>
        <listener-class>org.springframework.web.util.IntrospectorCleanupListener</listener-class>
    </listener>

    <!-- Spring MVC servlet -->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
        <async-supported>true</async-supported>
    </servlet>
    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <!-- 此处可以可以配置成*.do，对应struts的后缀习惯 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>/index.jsp</welcome-file>
    </welcome-file-list>

</web-app>
```
##### 4.3.3、测试
######4.3.3.1、新建jsp页面
**WEB-INF/jsp/showUser.jsp**

```html  
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
<head>
    <title>测试</title>
</head>
<body>
${user.name}
</body>
</html>
```

###### 4.3.3.2、建立UserController类
**com.jun.controller.UserController.java  控制器**
``` java
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }
}
```
######4.3.3.3、部署项目
输入地址： localhost:8080/user/showUser?id=1
###分页插件PageHelper使用：
**添加依赖 pom.xml**
```xml
<dependency>
    <groupId>com.github.jsqlparser</groupId>
    <artifactId>jsqlparser</artifactId>
    <version>0.9.6</version>
</dependency>
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.0.0</version>
    <!-- 4.0.0 和5.0.0 配置不同 -->
</dependency>
```
**spring-mybaties.xml 配置PageHelper插件**
``` xml
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 自动扫描mapping.xml文件 -->
        <property name="mapperLocations" value="classpath:com/jun/mapping/*.xml"></property>
        <!-- <property name="typeAliasesPackage" value="com.isea533.mybatis.model"/>-->
        <property name="plugins">
            <array>
            <!-- 拦截器-->
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <value>
                            helperDialect=mysql
                            reasonable=true
                            supportMethodsArguments=true
                            params=count=countSql
                            autoRuntimeDialect=true
                        </value>
                    </property>
                </bean>
            </array>
        </property>
    </bean>
```
**PageHelper只对紧跟着的第一个SQL语句起作用**

``` java
@Controller
@RequestMapping("/pageHelper")
public class PageHelperController {
    @Resource
    private UserService userService;
    @RequestMapping("/getPage")
    @ResponseBody
    public Map<String, Object> getAll(HttpServletRequest request, Model model){
    int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        boolean isCount=true;
        //获取第pageNum页，pageSize条内容，默认查询总数count
        //PageHelper.startPage(1,0); 不分页
        PageHelper.startPage(pageNum , pageSize,isCount);
        //PageHelper.orderBy("age desc"); 4.0 的方法
        List<User> list =userService.findAll();
        PageInfo page = new PageInfo(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",list);
        map.put("count",page.getTotal());
        map.put("pagelist",page.getList());
        return  map;
    } 
}
```
1. 在UserService添加 `public List<User> findAll();`
2. 在UserServiceImpl添加 
```java
 public List<User> findAll() {
        return this.userDao.findAll();
    }
```
3. 在UserMapper添加 `List<User> findAll();`
4. 在UserMapper.xml添加
```xml
  <select id="findAll" resultMap="BaseResultMap" parameterType="java.lang.Integer" >
    select
    <include refid="Base_Column_List" />
    from user
  </select>
```

**部署项目 访问： localhost:8080/pageHelper/getPage?pageNum=1&pageSize=2 **

###动态语句，多参数
UserMapper.xml
```xml
<select id="selectDynamic" resultMap="BaseResultMap" parameterType="java.lang.Integer">
    SELECT <include refid="Base_Column_List" />  from user
    <where>
       1=1
      <if test="name !=null">
        AND  `name` LIKE CONCAT(CONCAT('%', #{name, jdbcType=VARCHAR}),'%')
      </if>
      <if test="id != null and id>0">
        AND  id = #{id, jdbcType=INTEGER}
      </if>
    </where>
  </select>
  <select id="selectIfTrim" resultMap="BaseResultMap">
    SELECT <include refid="Base_Column_List" />    FROM user
    <trim prefix="WHERE" prefixOverrides="AND|OR">
      <if test="name !=null ">
        `name` LIKE CONCAT(CONCAT('%', #{name, jdbcType=VARCHAR}),'%')
      </if>
      <if test="id != null and id != '' ">
        AND id = #{id, jdbcType=INTEGER}
      </if>
    </trim>
  </select>
  <select id="selectChoose" resultMap="BaseResultMap" parameterType="java.lang.Integer">
    SELECT <include refid="Base_Column_List" />    FROM user
    <where>
      <choose>
        <when test="name !=null ">
          name LIKE CONCAT(CONCAT('%', #{name, jdbcType=VARCHAR}),'%')
        </when >
        <when test="id != null and id != '' ">
          id = #{id, jdbcType=INTEGER}
        </when >
        <otherwise>
        </otherwise>
      </choose>
    </where>
  </select>
<!--  public List<User> selectByIdsForeach(String[] ids);-->
  <select id="selectByIdsForeach" resultMap="BaseResultMap">
    SELECT <include refid="Base_Column_List" />    FROM user
    WHERE id IN
    <foreach collection="array" item="ids"  open="(" separator="," close=")">
      #{ids}
    </foreach>
  </select>
  <!--Public User selectByParms1(String name,String age);-->
  <select id="selectByParms1" resultMap="BaseResultMap">
    select  *  from user   where name = #{0} and age=#{1}
  </select>
  <!--Public User selectUser(Map paramMap);-->
  <select id="selectByParms2" resultMap="BaseResultMap" parameterType="hashmap">
    select  *  from user   where name = #{name,jdbcType=VARCHAR} and age=#{age,jdbcType=INTEGER}
  </select>
  <!--Public User selectUser(@param(“userName”)Stringname,@param(“userArea”)String area);-->
  <select id="selectByParms3" resultMap="BaseResultMap">
    select  *  from user   where name = #{name,jdbcType=VARCHAR} and age=#{age,jdbcType=INTEGER}
  </select>
<update id="updateByPrimaryKeySelective" parameterType="com.jun.entity.User" >
    update user
    <set >
      <if test="name != null" >
        name = #{name,jdbcType=VARCHAR},
      </if>
      <if test="password != null" >
        password = #{password,jdbcType=VARCHAR},
      </if>
      <if test="age != null" >
        age = #{age,jdbcType=INTEGER},
      </if>
    </set>
    where id = #{id,jdbcType=INTEGER}
  </update>
  <update id="updateIfTrim" parameterType="com.jun.entity.User" >
    UPDATE user
    <trim prefix="SET" suffixOverrides=",">
      <if test="name != null" >
        name = #{name,jdbcType=VARCHAR},
      </if>
      <if test="password != null" >
        password = #{password,jdbcType=VARCHAR},
      </if>
      <if test="age != null" >
        age = #{age,jdbcType=INTEGER},
      </if>
    </trim>
    where id = #{id,jdbcType=INTEGER}
  </update>
```
UserMapper.java
```java
    /*name id*/
    List<User> selectDynamic(User record);
    List<User> selectIfTrim(User record);
    List<User> selectChoose(User record);
    List<User> selectByIdsForeach(Integer[] ids);


    /*age id*/
    int updateIfTrim(User record);
    int updateByPrimaryKeySelective(User record);
    /*动态参数*/
    User selectByParms1(String name,Integer age);
    User selectByParms2(Map paramMap);
    User selectByParms3(@Param("name")String name,@Param("age")Integer age);
```
UserService.java
```java
    /*name id*/
    List<User> selectDynamic(User record);
    List<User> selectIfTrim(User record);
    List<User> selectChoose(User record);
    /*age id*/
    int updateIfTrim(User record);
    int updateByPrimaryKeySelective(User record);
    /*动态参数*/
    User selectByParms1(User record);
    User selectByParms2(User record);
    User selectByParms3(User record);
    List<User> selectByIdsForeach(Integer[] ids);
```
UserServiceImpl.java
```java
    public List<User> selectDynamic(User record) {
        return this.userDao.selectDynamic(record);
    }
    public List<User> selectIfTrim(User record) {
        return this.userDao.selectIfTrim(record);
    }
    public List<User> selectChoose(User record) {
        return this.userDao.selectChoose(record);
    }
    public int updateIfTrim(User record) {
        return this.userDao.updateIfTrim(record);
    }
    public int updateByPrimaryKeySelective(User record) {
        return this.userDao.updateByPrimaryKeySelective(record);
    }
    public User selectByParms1(User record) {
        return this.userDao.selectByParms1(record.getName(), record.getAge());
    }
    public User selectByParms2(User record) {
        Map paramMap = new HashMap();
        paramMap.put("name", record.getName());
        paramMap.put("age", record.getAge());
        return this.userDao.selectByParms2(paramMap);
    }
    public User selectByParms3(User record) {
        return this.userDao.selectByParms3(record.getName(), record.getAge());
    }
    public List<User> selectByIdsForeach(Integer[] ids) {
        return this.userDao.selectByIdsForeach(ids);
    }

```
UserController.java
```java
    /*name id*/
    @RequestMapping("/selectDynamic")
    @ResponseBody
    public List<User> selectDynamic(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectDynamic(user);
        return list;
    }

    @RequestMapping("/selectIfTrim")
    @ResponseBody
    public List<User> selectIfTrim(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectIfTrim(user);
        return list;
    }

    @RequestMapping("/selectChoose")
    @ResponseBody
    public List<User> selectChoose(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectChoose(user);
        return list;
    }

    /*age id*/
    @RequestMapping("/updateIfTrim")
    @ResponseBody
    public int updateIfTrim(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setId(Integer.parseInt(request.getParameter("age")));
        }
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        int result = this.userService.updateIfTrim(user);
        return result;
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setId(Integer.parseInt(request.getParameter("age")));
        }
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        int result = this.userService.updateByPrimaryKeySelective(user);
        return result;
    }

    /*动态参数 name age*/
    @RequestMapping("/selectByParms1")
    @ResponseBody
    public User selectByParms1(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms1(user);
        return result;
    }

    @RequestMapping("/selectByParms2")
    @ResponseBody
    public User selectByParms2(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms2(user);
        return result;
    }

    @RequestMapping("/selectByParms3")
    @ResponseBody
    public User selectByParms3(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms3(user);
        return result;
    }

    @RequestMapping("/selectByIdsForeach")
    @ResponseBody
    public List<User> selectByIdsForeach(Integer[] ids) {
        Integer[] idss = new Integer[]{2, 3};
        return this.userService.selectByIdsForeach(idss);
    }

```

###Jstl 标签编写（组件化）
http://blog.csdn.net/lovesummerforever/article/details/16880791

###与FreeMarker整合
1. pom.xml添加依赖
``` xml
    <dependency>
      <groupId>org.freemarker</groupId>
      <artifactId>freemarker</artifactId>
      <version>2.3.20</version>
    </dependency>
```
2. spring-mvc.xml 配置视图解析器
```xml
    <!--JSP视图解析器-->
    <bean id="viewResolverJsp" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 这里的配置我的理解是自动给后面action的方法return的字符串加上前缀和后缀，变成一个 可用的url地址 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
        <property name="contentType"><value>text/html;charset=UTF-8</value></property>
        <property name="viewClass" value="org.springframework.web.servlet.view.InternalResourceView"/>
        <property name="order" value="1"/>
    </bean>      
 <!--freeMarker视图解析器-->
  <bean id="viewResolverFtl" class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
      <property name="viewClass" value="org.springframework.web.servlet.view.freemarker.FreeMarkerView"/>
      <property name="contentType" value="text/html; charset=UTF-8"/>
      <property name="exposeRequestAttributes" value="true" />
      <property name="exposeSessionAttributes" value="true" />
      <property name="exposeSpringMacroHelpers" value="true" />
      <property name="cache" value="true" />
      <property name="suffix" value=".ftl" />
      <property name="order" value="0"/>
  </bean>
 <!--freeMarker模板位置-->
    <bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <property name="templateLoaderPath" value="/WEB-INF/ftl/"/>
        <!--设置FreeMarker环境属性-->
        <property name="freemarkerVariables">
            <map>
                <entry key="xml_escape"  value-ref="fmXmlEscape" />
            </map>
        </property>
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="freemarkerSettings">
            <props>
                <!--刷新模板周期（秒）-->
                <prop key="template_update_delay">3600</prop>
                <prop key="locale">zh_CN</prop>
                <prop key="datetime_format">yyyy-MM-dd HH:mm:ss</prop>
                <prop key="date_format">yyyy-MM-dd</prop>
                <prop key="time_format">HH:mm:ss</prop>
                <prop key="number_format">#.##</prop>
                <!--模板编码格式-->
                <prop key="default_encoding">UTF-8</prop>
                <!--本地化设置-->
                <prop key="locale">UTF-8</prop>
                <prop key="boolean_format">true,false</prop>
                <prop key="whitespace_stripping">true</prop>
                <prop key="tag_syntax">auto_detect</prop>
                <prop key="url_escaping_charset">UTF-8</prop>
            </props>
        </property>
    </bean>
<bean id="fmXmlEscape" class="freemarker.template.utility.XmlEscape"/>
```

3. FreeMarkerController.java
```java
@Controller
public class FreeMarkerController {
    @RequestMapping("/get/usersInfo")
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("1234");
        List<User> users = new ArrayList<User>();
        users.add(user);
        return new ModelAndView("usersInfo", "users", users);
    }
    @RequestMapping("/get/allUsers")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = new ArrayList<User>();
        User u1 = new User();
        u1.setName("王五");
        u1.setPassword("123");
        users.add(u1);
        User u2 = new User();
        u2.setName("张三");
        u2.setPassword("2345");
        users.add(u2);
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("userList", users);
        Map<String, String> product = new HashMap<String, String>();
        rootMap.put("lastProduct", product);
        product.put("url", "http://www.baidu.com");
        product.put("name", "green hose");
        String result = JSON.toJSONString(rootMap);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JsonUtil.getMapFromJson(result);
        return new ModelAndView("allUsers", "resultMap", resultMap);
    }
}
```

```java
public class JsonUtil {
    public static Map<String, Object> getMapFromJson(String jsonString) {
        if (checkStringIsEmpty(jsonString)) {
            return null;
        }
        return JSON.parseObject(jsonString);
    }
    private static boolean checkStringIsEmpty(String str) {
        if (str == null || str.trim().equals("") || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}

```

4. ftl模板文件
*WEB-INF/ftl/usersInfo.ftl*
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>usersInfo</title>
</head>
<body>
<#list users as user>
<div>
    username : ${user.name},
    password : ${user.password}
</div>
</#list>
</body>
</html>
```

*WEB-INF/ftl/allUsers.ftl*
```html
<html>
<head>
    <title>allUsers</title>
</head>
<body>
<#list resultMap.userList as user>
Welcome ${user.name}!&nbsp;&nbsp;id:${user.password}<br/>
</#list>
<p>Our latest product:
    <a href="${resultMap.lastProduct.url}">${resultMap.lastProduct.name}  </a>!
</body>
</html>
```
6. 启动服务器 访问*http://localhost:8080/get/usersInfo*  *http://localhost:8080/get/allUsers* *http://localhost:8080/user/showUser?id=1*

### FreeMarker 基本语法

FreeMarker模板文件主要由如下4个部分组成: 
1,文本:直接输出的部分 
2,注释:<#-- ... -->格式部分,不会输出 
3,插值:即${...}或#{...}格式的部分,将使用数据模型中的部分替代输出 
4,FTL指令:FreeMarker指定,和HTML标记类似,名字前加#予以区分,不会输出 
<#list animals as being><br> 
   <li>${being.name} for ${being.price} Euros<br> 
<#list><br> 
如果该指令是一个用户指令而不是系统内建指令时,应将#符号改成@符号
FreeMarker会忽略FTL标签中的空白字符.值得注意的是< , /> 和指令之间不允许有空白字符. 
1,通用插值${expr};2,数字格式化插值:#{expr}或#{expr;format} 

设置、赋值
<#settion number_format="currency"/> 
<#assign answer=42/> 
${answer} 
<#-- 格式化 --> 
${answer?string} 
${answer?string.number} 
${answer?string.currency} 
${answer?string.percent} 
${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")} 
${foo?string("yes", "no")} 
mX:小数部分最小X位 
MX:小数部分最大X位 
```
#{x; M2} <#-- 输出2.58 --> 
#{y; M2} <#-- 输出4 --> 
#{expr;format}格式化数字
```

3, 表达式 
1,字符串 ：单引号或双引号，转义
${"我的文件保存在C:\\盘"} 
${'我名字是\"annlee\"'} 
转义字符: 
\";双引号(u0022) \';单引号(u0027) \\;反斜杠(u005C) \n;换行(u000A) \r;回车(u000D) 
\t;Tab(u0009) \b;退格键(u0008) \f;Form feed(u000C)  \l;<  \g;>  \a;&  \{;{ 
${r"${foo}"} 输出 ${foo} 
${r"C:\foo\bar"} 输出 C:\foo\bar 
布尔值 ：true和false,不使用引号. 
<#list ["星期一", "星期二", "星期三"] + ["星期四", "星期五", "星期六", "星期天"] as x> 
${x} //${week[2]}   week[3..5]
</#list> 
用数字范围定义数字集合,如2..5等同于[2, 3, 4, 5]
{"语文":78, "数学":80} 
字符串操作 
${"hello, ${user}!"}   //使用第一种语法来连接 
${"hello, " + user + "!"} //使用+号来连接 
<#if isBig>Wow!</#if> 
截取子串: 
${book[0]}${book[4]}   //结果是su 
${book[1..4]}     //结果是tru 
算术运算符  +, - , * , / , % 
round, floor, ceiling
${ 1.1?int } 取整
比较运算符 =或者==   !=: >或者gt >=或者gte <或者lt <=或者lte 
逻辑运算符  &&  ||  ! 
内建函数 :变量?内建函数
html:对字符串进行HTML编码 
cap_first:使字符串第一个字母大写 
lower_case:将字符串转换成小写 
upper_case:将字符串转换成大写 
trim:去掉字符串前后的空白字符 
size:获取序列中元素的个数 
int:取得数字的整数部分,结果带符号 
3.10 空值处理运算符 
![defaultValue]:指定缺失变量的默认值 
??:判断某个变量是否存在 布尔值
3.11 运算符的优先级 
1,一元运算符:! 
2,内建函数:? 
3,乘除法:*, / , % 
4,加减法:- , + 
5,比较:> , < , >= , <= (lt , lte , gt , gte) 
6,相等:== , = , != 
7,逻辑与:&& 
8,逻辑或:|| 
9,数字范围:.. 
应该使用括号来严格区分,这样的可读性好,出错少 
<#if (age>60)>老年人 
<#elseif (age>40)>中年人 
<#elseif (age>20)>青年人 
<#else> 少年人 
</#if> 

<#switch value> 
<#case refValue>...<#break> 
<#case refValue>...<#break> 
<#default>... 
</#switch> 

<#list sequence as item> 
item_index:当前变量的索引值 
item_has_next:是否存在下一个对象 
<#break>指令跳出迭代 
</#list> 
<#include filename [options]>  options包含encoding和parse（true）
<#import "/lib/common.ftl" as com>导入/lib/common.ftl模板文件中的所有变量,放置com的
<#noparse>FreeMarker不处理的内容</#noparse> 
escape指令导致body区的插值都会被自动加上escape表达式 
<#escape x as x?html> 
First name:${firstName} 
<#noescape>...</#noescape> 
</#escape> 
等同于: 
First name:${firstName?html} 
创建或替换顶层变量
<#assign x> <#list ["星期一", "星期二", "星期三"] as n> ${n} </#list> </#assign> ${x} 
<#assign x="Hello ${user}!"> 

设置FreeMarker的运行环境:<#setting name=value>
name的取值范围: 
locale:该选项指定该模板所用的国家/语言选项 
number_format:指定格式化输出数字的格式 
boolean_format:指定两个布尔值的语法格式,默认值是true,false 
date_format,time_format,datetime_format:指定格式化输出日期的格式 
time_zone:设置格式化输出日期时所使用的时区 

自定义指令 
><#macro book booklist>    //定义自定义指令booklist是参数 
<#list booklist as book> 
   ${book} 
</#list> 
<#return> //随时结束该自定义指令
</#macro> 
<@book booklist=["spring","j2ee"] />   //使用刚刚定义的指令 


><#macro page title> 
<html> 
<head> 
   <title></title> 
</head> 
<body> 
   <h1>${title?html}</h1> 
   <#nested>      //用于引入用户自定义指令的标签体 
</body> 
</html> 
</#macro> 

上面的代码将一个HTML页面模板定义成一个page指令,则可以在其他页面中如此page指令: 
<#import "/common.ftl" as com>     //假设上面的模板页面名为common.ftl,导入页面 
><@com.page title="book list"> 
<u1> 
<li>spring</li> 
<li>j2ee</li> 
</ul> 
></@com.page> 


从上面的例子可以看出,使用macro和nested指令可以非常容易地实现页面装饰效果,此外,还可以在使用nested指令时,指定一个或多个循环变量,看如下代码: 
<#macro book> 
<#nested 1>      //使用book指令时指定了一个循环变量值 
<#nested 2> 
</#macro> 
<@book ;x> ${x} .图书</@book> 
输出文本如下: 
1 .图书    2 .图书 

在nested指令中使用循环变量时,可以使用多个循环变量,看如下代码: 
<#macro repeat count> 
<#list 1..count as x>     //使用nested指令时指定了三个循环变量 
   <#nested x, x/2, x==count> 
</#list> 
</#macro> 
<@repeat count=4 ; c halfc last> 
${c}. ${halfc}<#if last> Last! </#if> 
</@repeat> 
输出结果为: 
1. 0.5   2. 1   3. 1.5   4. 2 Last; 

内置函数
Sequence的内置函数
1.sequence?first 返回sequence的第一个值。
2.sequence?last 返回sequence的最后一个值。
3.sequence?reverse 将sequence的现有顺序反转，即倒序排序
4.sequence?size 返回sequence的大小
5.sequence?sort 将sequence中的对象转化为字符串后顺序排序
6.sequence?sort_by(value) 按sequence中对象的属性value进行排序
注意：Sequence不能为null
Hash的内置函数
1.hash?keys 返回hash里的所有key,返回结果为sequence
2.hash?values 返回hash里的所有value,返回结果为sequence
操作字符串内置函数
${"str"?substring(0,2)}结果为st
${"str"?cap_first}结果为Str
${"Str"?cap_first}结果为str
${"str"?capitalize}结果为STR
<#assign date1="2009-10-12"?date("yyyy-MM-dd")>
<#assign date2="9:28:20"?time("HH:mm:ss")>
<#assign date3="2009-10-12 9:28:20"?time("HH:mm:ss")>
${"string"?ends_with("ing")?string},是否由某个子串结尾 返回结果为true
注意：布尔值必须转换为字符串才能输出
7. html 用于将字符串中的<、>、&和"替换为对应得<>&quot:&amp
${"string"?index_of("in")结果为3
${"string"?index_of("ab")结果为-1
 length返回字符串的长度 ${"string"?length}结果为6
${"STRING"?lower_case}结果为string
${"string"?upper_case}结果为STRING
${"string"?contains("ing")?string}结果为true
${"111.11"?number}结果为111.11
${"strabg"?replace("ab","in")}结果为string
<#list "This|is|split"?split("|") as s>${s}</#list>
trim 删除字符串首尾空格 ${"String"?trim} 结果为String
操作数字内置函数
1.c 用于将数字转换为字符串
2.string用于将数字转换为字符串：number,currency（货币）和percent(百分比)其中number为默认的数字格式转换
${tempNum?string.number}或${tempNum?string("number")} 结果为20
操作布尔值内置函数
string用于将布尔值转换为字符串输出:true转为"true"，false转换为"false"
foo?string("yes","no")如果布尔值是true,那么返回"yes",否则返回no

### demo
``` java 
@Controller
public class FreeMarkerDemoController {
    @RequestMapping("/freeMarkerDemo")
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response, Model model) {
      
        //1.简单字符串
        model.addAttribute("textDemo","HelloWorld!!");
        //2.计算||条件判断||输出特殊字符
        int a=1;
        int b=2;
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        //3.遍历List
        List<String>list=new ArrayList<String>();
        list.add("早餐");
        list.add("中餐");
        list.add("晚餐");
        model.addAttribute("list",list);
        //4.遍历map
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        model.addAttribute("map",map);
        //5.遍历复杂map
        Map<String,Object>userInfo=new HashMap<String,Object>();
        userInfo.put("username","username");
        Map<String,Object>cMap=new HashMap<String,Object>();
        cMap.put("cKey1","cValue1");
        cMap.put("cKey2","cValue2");
        userInfo.put("cMap",cMap);
        model.addAttribute("userinfo",userInfo);
        //6.遍历嵌套map
        Map<String,Object>outerMap=new HashMap<String,Object>();
        Map<String,String>innerMap=new HashMap<String,String>();
        innerMap.put("innerKey1","innerValue1");
        innerMap.put("innerKey2","innerValue2");
        outerMap.put("key1","value1");
        outerMap.put("key2","value2");
        outerMap.put("innerMap",innerMap);
        model.addAttribute("outerMap",outerMap);
        //7.Map、List嵌套
        Map<String,Object>mMap=new HashMap<String,Object>();
        List<String>innerList=new ArrayList<String>();
        innerList.add("吃早餐");
        innerList.add("吃中餐");
        innerList.add("吃晚餐");
        mMap.put("key1","value1");
        mMap.put("key2","value2");
        mMap.put("innerList",innerList);
        model.addAttribute("mMap",mMap);
        return new ModelAndView("freeMarkerDemo");
    }
}
```
freeMarkerDemo.ftl
``` xml
一：简单输出 ${textDemo}
二：计算||条件判断||输出特殊字符
a == ${a};b == ${b}
${a} + ${b} = ${a + b}
<#if (a > b)>
a 大于 b
<#elseif (a == b)>
a 等于 b
<#elseif (a < b)>
a 小于 b
</#if>
<#assign c = a + b>
<#noparse><#assign c = a + b>; ${c} = </#noparse>${c}
${r'<#assign c = a + b>'}; ${r'${c}'} = ${c}

    三：list 遍历
<#list list as item>
${item_index + 1}.${item}
</#list>
    四：map 遍历
<#list map?keys as key>
${key} == ${map[key]}
</#list>
    五：复杂map遍历
<#assign cmap = userinfo.cMap>
<#list cmap?keys as key>
${key} == ${cmap[key]}
</#list>
<br>

<#assign stu={"name":"zhangsan","age":"21","sex":"man"}>
<#list stu?keys as key>
${key} = ${stu[key]};
</#list>
    <br>
    六：map嵌套遍历

<#list outerMap?keys as key>
    <#assign innerMap = outerMap[key]>
    <#if innerMap?is_hash>
        <#list innerMap?keys as _key>
        ${_key} == ${innerMap[_key]}
        </#list>
    <#elseif innerMap?is_string>
    ${key} == ${innerMap}
    <#-- ${key} == ${outerMap[key]} -->
    </#if>
</#list>
<br>
 七：map list 嵌套遍历
<#list mMap?keys as key>
    <#assign list = mMap[key]>
<#--<#if key=="innerList">-->
<#--${"string"?ends_with("List")?string}-->
    <#if key?string?ends_with("List")>
        <#list list as item>
        ${item_index + 1}.${item}
        </#list>
    <#else>
    ${key} == ${list}
    </#if>
</#list>
<#--
    十 调用静态方法
${T.staticMethod()}
    十一 枚举
${E.Summer.getSeason()}
${E.Autumn.getSeason()}
-->
```

**访问 http://localhost:8080/freeMarkerDemo**

### 与Velocity 整合

1. 依赖 pom.xml  
``` xml
    <!-- Velocity模板 -->
    <dependency>
      <groupId>org.apache.velocity</groupId>
      <artifactId>velocity</artifactId>
      <version>1.7</version>
    </dependency>
    <dependency>
      <groupId>org.apache.velocity</groupId>
      <artifactId>velocity-tools</artifactId>
      <version>2.0</version>
    </dependency>
```
2. 配置视图解析器 spring-mvc.xml
```xml
   <!-- Velocity视图解析器-->
    <bean id="velocityConfig" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
        <property name="resourceLoaderPath" value="/WEB-INF/vm/" />
        <property name="configLocation" value="classpath:velocity.properties" />
        <property name="velocityProperties">
            <props>
                <prop  key="input.encoding">UTF-8</prop>
                <prop  key="output.encoding">UTF-8</prop>
            </props>
        </property>
    </bean>
    <!-- 配置后缀 -->
    <bean id="velocityViewResolver" class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">
        <property name="suffix" value=".vm" />
        <property name="contentType"><value>text/html;charset=UTF-8</value></property>
        <!-- 顺序要在jsp 之前 -->
        <property name="order" value="2"/>
    </bean>
```
3. 配置文件 velocity.properties
```properties
#encoding  
#input.encoding=UTF-8
#output.encoding=UTF-8  
#autoreload when vm changed  
file.resource.loader.cache=false
file.resource.loader.modificationCheckInterval=2
velocimacro.library.autoreload=false 
```
4. 模板文件 vm/showAllUser.vm
```vm
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>show all users</title>
</head>
<body>
<table >
    #foreach($user in $userList)
        <tr >
            <td >$user.name</td>
            <td >$user.age</td>
        </tr>
    #end
</table>
</body>
</html>
```
5. controller
```java
@Controller
@RequestMapping("/VMuser")
public class VMUserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @RequestMapping("/showAllUser")
    public String showAllUser(HttpServletRequest request, Model model){
        List<User> userList = userService.findAll();
        logger.info("userList's size==============" + userList.size());
        model.addAttribute("userList",userList);
        return "showAllUser";
//        return new ModelAndView("showAllUser", "userList", userList);
    }
}
```

**访问 http://localhost:8080/VMuser/showAllUser**



<meta http-equiv="refresh" content="10">