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
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db?characterEncoding=UTF-8&serverTimezone=JST","root","");
            System.out.println("DBConnected!!");
            return con;
        }catch(ClassNotFoundException e){
           throw new IllegalMonitorStateException();
//        	 System.out.println(e.getMessage());
//        	 return con;
        } catch (SQLException e) {
            throw new IllegalMonitorStateException();
//        	 System.out.println(e.getMessage());
//        	 return con;
        }
    }
}
