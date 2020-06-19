package kgym;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Top {

    @RequestMapping(value="/top",method=RequestMethod.GET)
    public String top(HttpSession session,HttpServletResponse hsr,@ModelAttribute UserData data,Model model) {
    	
    		System.out.println("update");
    		// セッションを用いたログインチェック
    		if(session.getAttribute("user")==null) {
	    		UserDataDTO UDD=new UserDataDTO();
	    		UDD.setName(data.getName());
	    		UDD.setPassword(data.getPassword());
	    		
	    		UserDataDAO DAO=UserDataDAO.getInstance();
	    		try{
	    			UserDataDTO user=DAO.search(UDD);
	    			if(user.getName()!=null) {
	    				session.setAttribute("user", data.getName());
	    				session.setAttribute(data.getName(), data);
	    				model.addAttribute("name","ようこそ！"+user.getName()+"様！！！");
	    				model.addAttribute("mydata", "/mydata");
	    				model.addAttribute("cart","/cart");
	    				
	    			}else {
	    				model.addAttribute("null",model.getAttribute("null"));
	    				
	    			}
	    		}catch(SQLException e) {
	    			System.out.println(e.getMessage());
	    			return "/error";
	    		}
    		}else {
    			String name=session.getAttribute("user").toString();
    			model.addAttribute("name","ようこそ！"+name+"様！！！");
    			
    		}
    		model.addAttribute("null", model.getAttribute("null"));
    		
    		
    	
    	
    	KgymHelper kgym=KgymHelper.getInstance();
    	String home=kgym.home();
    	model.addAttribute("logout","/logout");
    	model.addAttribute("login","/login");
       	return "top";
    }
    
}
