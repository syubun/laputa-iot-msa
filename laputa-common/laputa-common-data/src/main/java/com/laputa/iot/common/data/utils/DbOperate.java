package com.laputa.iot.common.data.utils;
 
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
 
/**
 * @Author: sommer.jiang
 * 数据库备份与还原
 * @Date: 2018/4/28 19:56
 */
public class DbOperate {
 
    /**
     * 备份数据库db
     * @param root
     * @param pwd
     * @param dbName
     * @param backPath
     * @param backName
     */
    public static void dbBackUp(String url,String root,String pwd,String dbName,String backPath,String backName) throws Exception {
        String pathSql = backPath+backName;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -h"+url);
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份："+dbName);
        Process process = runtime.exec("cmd /c"+sb.toString());
        System.out.println("备份成功!");
    }
 
    /**
     * 恢复数据库
     * @param root
     * @param pwd
     * @param dbName
     * @param filePath
     * mysql -hlocalhost -uroot -p123456 db < /home/back.sql
     */
    public static void dbRestore(String root,String pwd,String dbName,String filePath){
        StringBuilder sb = new StringBuilder();
        sb.append("mysql");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" <");
        sb.append(filePath);
        System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始还原数据");
        try {
            Process process = runtime.exec("cmd /c"+sb.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,"utf8"));
            String line = null;
            while ((line=bf.readLine())!=null){
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("还原成功！");
    }
 
 
 
    public static void main(String[] args) throws Exception {
        String dbname = "laputa_iot_upms";
        String backName = dbname+ new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".sql";
        DbOperate.dbBackUp("106.13.63.126","root","Laputa@123456",dbname,"F:/",backName);
//        dbRestore("root","123456","db","F://2018-04-30-19-28-28.sql");
    }
 
}