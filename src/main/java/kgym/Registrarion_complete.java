package kgym;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Registrarion_complete{

   @RequestMapping(value = "/Registrarion_complete",method=RequestMethod.GET)
   public String registrarion_complete(HttpSession session,Model model) throws Exception {

	   UserData user=new UserData();
	   user.setName(session.getAttribute("name").toString());
	   user.setPassword(session.getAttribute("password").toString());
	   user.setMail(session.getAttribute("mail").toString());
	   user.setAddress(session.getAttribute("address").toString());
	   model.addAttribute("user",user);
	   
	   UserDataDTO userdata = new UserDataDTO();
       user.UD2DTOMapping(userdata);
       UserDataDAO.getInstance().insert(userdata);

	   session.removeAttribute("name");
	   session.removeAttribute("password");
	   session.removeAttribute("mail");
	   session.removeAttribute("address");

	   return "registrarion_complete";
   }
}
