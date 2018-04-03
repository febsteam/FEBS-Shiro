数据库名字: mrbird
需要设置数据库的字符编码为utf8,而不是utf8mb4，具体设置方式可以参考下面几个文档

https://blog.csdn.net/qiyuexuelang/article/details/9049985
https://scottlinux.com/2017/03/04/mysql-mariadb-set-character-set-and-collation-to-utf8/
https://stackoverflow.com/questions/22572558/how-to-set-character-set-database-and-collation-database-to-utf8-in-my-ini?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

只有设置完编码后才能建立数据库，若一开始建立了数据库，character_set_database 的编码不会变。

用$ mysql -u root -p  -D mrbird < init.sql 初始化数据库。

linux使用1024以下的端口需要root权限，所以可以在配置文件中将端口改为8080。
https://blog.csdn.net/ycpanda/article/details/12201851

开发时直接使用Spring Boot的入口类启动即可，访问地址localhost。部署时建议打包成war包，访问地址localhost:(端口号)/febs。账号mrbird，密码123456。
