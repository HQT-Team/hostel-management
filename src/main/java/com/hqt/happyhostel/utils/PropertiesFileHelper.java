package com.hqt.happyhostel.utils;


import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesFileHelper {
    public static Properties getProperties(ServletContext context, String fileRelativePath){
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(input);
        }catch(IOException ex) {
                Logger.getLogger(PropertiesFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }
}
