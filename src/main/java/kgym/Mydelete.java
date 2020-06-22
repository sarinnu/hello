package kgym;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Mydelete {

    @RequestMapping(value="/mydelete",method=RequestMethod.GET)
    public String mydelete(Model model,HttpSession session) {

    	String name=session.getAttribute("user").toString();
    	UserData data=(UserData)session.getAttribute(name);
    	

    	UserDataDTO UDD=new UserDataDTO();
		UDD.setName(data.getName());
		UDD.setPassword(data.getPassword());
		try {
			UserDataDTO user=UserDataDAO.getInstance().search(UDD);
			model.addAttribute("user",user);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return "/error";
		}
    	
    	return "mydelete";
    }
 }