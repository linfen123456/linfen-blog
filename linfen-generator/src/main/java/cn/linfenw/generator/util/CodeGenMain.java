package cn.linfenw.generator.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import cn.linfenw.generator.domain.CodeGenConfig;
import org.springframework.beans.factory.annotation.Value;

import java.util.Scanner;

public class CodeGenMain {

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.username}")
    private static String username;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.driver-class-name}")
    private static String driverName;

    public static void main(String[] args) {
        CodeGenConfig codeGenConfig = new CodeGenConfig();
        Scanner scanner = new Scanner(System.in);
        codeGenConfig.setAuthor("linfen");
        codeGenConfig.setModuleName("blog");
        codeGenConfig.setPackageName("com.xd.pre.blog");
        System.out.println("请输入表名：\n");
        codeGenConfig.setTableName(scanner.nextLine());
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/linfen_blog?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        System.out.println(dataSourceConfig.toString());
        CodeGenUtil codeGenUtil = new CodeGenUtil();
        codeGenUtil.generateByTables(dataSourceConfig, codeGenConfig.getPackageName(), codeGenConfig.getAuthor(), codeGenConfig.getModuleName(), codeGenConfig.getTableName());
    }
}
