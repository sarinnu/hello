package kgym;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Add {
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,@RequestParam("code") String code,Model model) {
		
//		セッションからカートに追加する商品のデータを取り出す
		String user=session.getAttribute("user").toString();
	    UserData data=(UserData)session.getAttribute(user);
		ProductDataBeans item=(ProductDataBeans)session.getAttribute(code);

//      ユーザーのカートデータを更新
		ArrayList<ProductDataBeans> items=data.getItem();
		items.add(item);
	    data.setItem(items);
	    
//      追加した商品の名前をHTMLに送信
		model.addAttribute("name",item.getProductName());
		return "add";
	}
}
