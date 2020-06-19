package kgym;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Cart{
	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String cart(HttpSession session,Model model) {
		
//		セッションからユーザーデータを取り出す
		String user=session.getAttribute("user").toString();
	    UserData data=(UserData)session.getAttribute(user);
	    ArrayList<ProductDataBeans> items=data.getItem();
	    
//	 	ユーザーのカートデータから商品データを取り出す
	    int i=0;
	    int total=0;
	    for(ProductDataBeans item:items) {
	    	item.setIndex(i);
	    	items.set(i,item);
	    	total+=item.getPrice();
	    	i++;
	    }

//		ユーザーデータの更新
	    data.setItem(items);
	    data.setCartTotal(total);
	    session.setAttribute(user,data);
    
//		HTMLにカートデータを送信	    
	    model.addAttribute("user", user+"様");
		model.addAttribute("datas",data.getItem());
		model.addAttribute("total",data.getCartTotal());
		
		return "cart";
	}
}
