package com.hqt.happyhostel.utils;


import java.security.SecureRandom;

public class RandomStringGenerator {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++){
            sb.append(AB.charAt(rnd.nextInt( AB.length() )));

        }
        return sb.toString();
    }

    public static String randomToken(int len, String username){
        StringBuilder sb = new StringBuilder(len);
        sb.append(username);
        for (int i = 0; i < len; i++){
            sb.append(AB.charAt(rnd.nextInt( AB.length() )));
        }
        return sb.toString();
    }

    public static String randomInviteCode(int len, String roomId){
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++){
            sb.append(AB.charAt(rnd.nextInt( AB.length() )));
        }
        sb.append(roomId);
        return sb.toString();
    }
}
