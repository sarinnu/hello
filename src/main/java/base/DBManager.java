package base;
/**
 *
 * @author hayashi-s
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBManager {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3308/kagoyume_db?characterEncoding=UTF-8&serverTimezone=JST","root","");
//            con=DriverManager.getConnection("mysql://root:48487845@localhost:3306/kagoyume_db?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=JST");
            System.out.println("DBConnected!!");
            return con;
        }catch(ClassNotFoundException e){
        	System.out.println("class"+e.getMessage());
           throw new IllegalMonitorStateException();
 //        	 return con;
        } catch (SQLException e) {
       	    System.out.println("SQL"+e.getMessage());
            throw new IllegalMonitorStateException();
//        	 return con;
        }
    }
}
