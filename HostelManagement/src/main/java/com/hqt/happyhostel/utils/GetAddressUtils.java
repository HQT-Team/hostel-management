package com.hqt.happyhostel.utils;

public class GetAddressUtils {

    public static String getDefaultAddress(String address){
        int lastIndex = address.lastIndexOf("-");
        String defaultAddress = address.substring(lastIndex + 1);
        return defaultAddress;
    }
}
