package kgym;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Item{
	
	@ModelAttribute
    UserData setUpForm() {
	   return new UserData();
    }
	
   @RequestMapping(value = "/Item", method = RequestMethod.GET)
   public String item(HttpSession session,@RequestParam("code") String code,Model model) {

	   model.addAttribute("pdb", (ProductDataBeans)session.getAttribute(code));
	   model.addAttribute("add", "/add?code="+code);
	   return "item";
	  }
}
