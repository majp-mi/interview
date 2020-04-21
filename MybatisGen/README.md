1.修改pom.xml文件,引入mybatis-generator-maven-plugin;
2.编写application.properties文件，定义数据库连接等;
3.配置generatorConfig.xml;
4.mven-->Plugins-->mybatis-generator，右击mybatis-generator，执行 "Run Maven Build"，仅运行一次；
5.在java文件夹下则会自动生成User.java,UserMapper.java,UserMapper.xml三个文件。