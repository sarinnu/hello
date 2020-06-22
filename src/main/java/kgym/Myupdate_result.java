package kgym;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Myupdate_result{
	
  

   @RequestMapping(value = "/myupdate_result",method=RequestMethod.POST)
   public String myupdate_result(HttpSession session,@ModelAttribute UserData user,Model model) throws Exception {

	   model.addAttribute("user",user);
	   UserDataDTO userdata = new UserDataDTO();
       user.UD2DTOMapping(userdata);
       
       UserDataDAO .getInstance().update(userdata,session.getAttribute("user").toString());
       session.setAttribute(user.getName(),user);

	   return "myupdate_result";
   }
}
