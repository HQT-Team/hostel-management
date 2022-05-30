package com.hqt.happyhostel.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection makeConnection() throws Exception{
        Connection cn=null;
        String IP="localhost";
        String instanceName="DESKTOP-0D7MP1Q\\TANLOC";
//        String instanceName="KHOAHD7621\\KHOAHD";
        String port="1433";
        String uid="sa";
        String pwd="Tanloc162";
//        String pwd="0792596763";
        String db="HostelManagement2";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                +";databasename="+db+";user="+uid+";password="+pwd+ ";encrypt=true;trustServerCertificate=true;";
        Class.forName(driver);
        cn= DriverManager.getConnection(url);
        return cn;
    }
}
