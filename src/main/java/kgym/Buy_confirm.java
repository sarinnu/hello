package kgym;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Buy_confirm{
	
	@RequestMapping(value="/buy_confirm",method=RequestMethod.GET)
	public String buy_confirm(HttpSession session,Model model) {
	
		String user=session.getAttribute("user").toString();
	    UserData data=(UserData)session.getAttribute(user);
	    
        model.addAttribute("user", user+"�l");
		model.addAttribute("datas",data.getItem());
		model.addAttribute("total",data.getCartTotal());

		return "buy_confirm";
	}
}
