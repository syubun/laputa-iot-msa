package com.laputa.iot.common.core.util;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.env.StandardEnvironment;

public class EncryptorUtil {

    public static void main(String[] args) {
        System.setProperty("jasypt.encryptor.password", "laputa");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());

        //加密方法
        System.out.println(stringEncryptor.encrypt("laputa"));
        //解密方法
//        System.out.println(stringEncryptor.decrypt("ltJPpR50wT0oIY9kfOe1Iw=="));

    }
}
