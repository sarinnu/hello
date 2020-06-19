package kgym;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Registration_confirm{
	
  

   @RequestMapping(value = "/Registration_confirm", method = RequestMethod.POST)
   public String registration_confirm(HttpSession session,@ModelAttribute UserData data,Model model) {
	 
	   String list="";
	   if(data.chkproperties().size()!=0) {
		   for(String chk:data.chkproperties()) {
			   list+=chk;
		   }
		   list+="入力してください";
		   System.out.println(list);
		   model.addAttribute("chk",list);
		   return "Registration";
	   }
//	   セッションに送信データを格納
	   session.setAttribute("name",data.getName());
	   session.setAttribute("password",data.getPassword());
	   session.setAttribute("mail",data.getMail());
	   session.setAttribute("address",data.getAddress());
	   
	   model.addAttribute("userData",data);
	   
	   return "/registration_confirm";
   }
}