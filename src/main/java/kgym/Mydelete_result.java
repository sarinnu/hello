package kgym;


import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Mydelete_result{
	
  

   @RequestMapping(value = "/mydelete_result",method=RequestMethod.GET)
   public String registrarion_complete(HttpSession session,Model model)  {
//	      セッションからユーザーデータを取得
	   String name =session.getAttribute("user").toString();
	   UserData data=(UserData)session.getAttribute(name);
	   UserDataDTO userdata = new UserDataDTO();
       data.UD2DTOMapping(userdata);

//	   MySQLに接続しDBからユーザーデータを削除
       try {
    	   UserDataDTO user=UserDataDAO.getInstance().search(userdata);
    	   UserDataDAO.getInstance().delete(user);
       }catch(SQLException e) {
    	   System.out.println(e.getMessage());
    	   return "error";
       }

//	   セッションからユーザーデータを削除
	   session.removeAttribute(name);

	   return "mydelete_result";
   }
}
