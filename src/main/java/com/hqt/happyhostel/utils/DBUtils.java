package com.hqt.happyhostel.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection makeConnection() throws Exception{
        Connection cn=null;
        String IP="localhost";
        String instanceName="CUTEPHOMAIQUE";
        String port="1433";
        String uid="sa";
        String pwd="OIUOiu12";
        String db="HostelManagement";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                +";databasename="+db+";user="+uid+";password="+pwd+ ";encrypt=true;trustServerCertificate=true;";
        Class.forName(driver);
        cn= DriverManager.getConnection(url);
        return cn;
    }
}
