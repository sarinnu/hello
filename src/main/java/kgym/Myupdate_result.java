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
//	   �Z�b�V�������烆�[�U�[�f�[�^���擾
	   model.addAttribute("user",user);
	   UserDataDTO userdata = new UserDataDTO();
       user.UD2DTOMapping(userdata);

//	   DB�փf�[�^�̑}��
       UserDataDAO .getInstance().update(userdata,session.getAttribute("user").toString());

//	   �Z�b�V�����ɕύX�ς݂̃��[�U�f�[�^��ݒu
	   session.setAttribute(user.getName(),user);

	   return "myupdate_result";
   }
}
