package kgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.DBManager;

@Controller
public class Mydata {

    @RequestMapping(value="/mydata",method=RequestMethod.GET)
    public String mydata(HttpSession session,Model model) {

    	String name=session.getAttribute("user").toString();
    	UserData data =(UserData)session.getAttribute(name);
    	
    	Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("SELECT userID FROM user_t WHERE name like ? AND password like?");
            st.setString(1, data.getName());
            st.setString(2, data.getPassword());
            ResultSet rs = st.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
            
            st =  con.prepareStatement("SELECT * FROM user_t WHERE userID = ?");
            st.setInt(1, rs.getInt(1));
            rs = st.executeQuery();
            rs.next();
            
            UserDataDTO mydata = new UserDataDTO();
            mydata.setName(rs.getString(2));
            mydata.setPassword(rs.getString(3));
            mydata.setMail(rs.getString(4));
            mydata.setAddress(rs.getString(5));
            mydata.setTotal(rs.getInt(6));
            mydata.setNewDate(rs.getTimestamp(7));
            mydata.setDeleteFlg(rs.getInt(8));
            
            model.addAttribute("mydata",mydata);
            

            System.out.println("search completed");
            con.close();

        }catch(SQLException e){
        	System.out.println(e.getMessage());
        }

    	KgymHelper kgym=KgymHelper.getInstance();
    	String home=kgym.home();
    	model.addAttribute("home",home);
    	return "mydata";
    }
}
