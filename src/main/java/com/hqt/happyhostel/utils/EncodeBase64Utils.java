package com.hqt.happyhostel.utils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class EncodeBase64Utils {
    private static final int BUFFER_SIZE = 8192;



    public static String imageToBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        ImageIO.write(image, "png", out);
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }
}
