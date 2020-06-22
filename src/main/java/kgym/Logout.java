package kgym;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logout {

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String login(Model model,HttpSession session) {

    	session.removeAttribute("user");
    	return "logout";
    }
 }