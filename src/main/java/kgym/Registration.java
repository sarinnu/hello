package kgym;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Registration{
	
   @RequestMapping(value = "/Registration", method = RequestMethod.GET)
   public String registration(Model model) {
       model.addAttribute("userData", new UserData()); // 検索フォーム用インスタンス
       return "registration";

   }
}
