package cn.itcast.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String getBcryptPassword (String password){
        String encode = passwordEncoder.encode(password);
        return encode;
    }


    public static void main(String[] args) {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
