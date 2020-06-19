package kgym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
         try {
        	 Connection con=null;
             Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/kagoyume_db?characterEncoding=UTF-8&serverTimezone=JST","root","");
             System.out.println("DBConnected!!");
            
         }catch(ClassNotFoundException e){
 	        System.out.println("class"+e.getMessage());
         } catch (SQLException e) {
        	 System.out.println("sql"+e.getMessage());
         } 
    	return "index";
    }
}
