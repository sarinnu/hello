package kgym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(Model model) {
//      HTMLにトップページのリンクを送信
    	KgymHelper kgym=KgymHelper.getInstance();
    	String home=kgym.home();
    	model.addAttribute("home",home);
//		ログイン用にUserDataの新規インスタンスをHTMLに送信
    	model.addAttribute("userData", new UserData());
    	return "login";
    }
    
}
