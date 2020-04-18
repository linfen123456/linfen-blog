package cn.linfenw.common.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class StringEncryptorUtil {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("EbfYkitulv73I2p0mXI50JMXoaxZTKJ1");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("linfenw@qq.com");
        String password = textEncryptor.encrypt("lxdqwicdr-kaiwanxiao");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
