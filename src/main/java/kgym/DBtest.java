package kgym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBtest {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("mysql://root:48487845@localhost:3306/kagoyume_db?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=JST");
            System.out.println("DBConnected!!");
            return con;
        }catch(ClassNotFoundException e){
            throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            throw new IllegalMonitorStateException();
        }
    }
}


