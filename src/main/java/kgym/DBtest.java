package kgym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//MySQL�ւ̐ڑ��e�X�g
public class DBtest {
    public static Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db","root","");
            System.out.println("DBConnected!!");
            return con;
        }catch(ClassNotFoundException e){
            throw new IllegalMonitorStateException();
        } catch (SQLException e) {
            throw new IllegalMonitorStateException();
        }
    }
}

