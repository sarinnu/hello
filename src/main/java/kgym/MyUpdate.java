package kgym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyUpdate {

    @RequestMapping(value="/myupdate",method=RequestMethod.GET)
    public String myupdate(Model model) {   	
//		�X�V�p��UserData�C���X�^���X��HTML�ɑ��M
    	model.addAttribute("userdata",new UserData());
    	return "myupdate";
    }
 }