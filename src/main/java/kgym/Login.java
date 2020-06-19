package kgym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(Model model) {
//      HTML�Ƀg�b�v�y�[�W�̃����N�𑗐M
    	KgymHelper kgym=KgymHelper.getInstance();
    	String home=kgym.home();
    	model.addAttribute("home",home);
//		���O�C���p��UserData�̐V�K�C���X�^���X��HTML�ɑ��M
    	model.addAttribute("userData", new UserData());
    	return "login";
    }
    
}
