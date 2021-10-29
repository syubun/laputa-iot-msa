/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.laputa.iot.common.core.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Password encoder tool
 *
 * @author sommer
 */
public class BCryptCoderUtil {


//    public static void main(String[] args) {
////        System.setProperty("jasypt.encryptor.password", "laputa");
////        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
//
//        //加密方法
////        System.out.println(stringEncryptor.encrypt("laputa"));
//        //解密方法
////        System.out.println(stringEncryptor.decrypt("ltJPpR50wT0oIY9kfOe1Iw=="));
//        System.out.println(new BCryptPasswordEncoder().encode("laputa"));
//    }

    public static Boolean matches(String raw, String encoded) {
        return new BCryptPasswordEncoder().matches(raw, encoded);
    }

    public static String encode(String raw) {
        return new BCryptPasswordEncoder().encode(raw);
    }
}
