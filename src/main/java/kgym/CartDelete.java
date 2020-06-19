package kgym;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartDelete {

    @RequestMapping(value="/cartDelete",method=RequestMethod.GET)
    public String cartDelete(Model model,HttpSession session,@RequestParam("index") int index) {
//		�Z�b�V�������烆�[�U�[�f�[�^�����o��
    	String user=session.getAttribute("user").toString();
	    UserData data=(UserData)session.getAttribute(user);
	    ArrayList<ProductDataBeans> items=data.getItem();

//		�J�[�g�f�[�^���珤�i���폜���X�V
	    int i=0;
	    for(ProductDataBeans item:items) {
	    	if(item.getIndex()==index) {
	    		items.remove(i);
	    	    data.setItem(items);
	    	    session.setAttribute(user, data);
	    	    break;
	    	}
	    	i++;
	    }
	   
	    return "redirect:/cart";
    }
 }