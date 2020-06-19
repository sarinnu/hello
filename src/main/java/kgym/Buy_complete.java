package kgym;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import base.DBManager;

@Controller
public class Buy_complete{
	
  

   @RequestMapping(value = "/buy_complete",method=RequestMethod.GET)
   public String buy_complete(HttpSession session,@RequestParam("type") int type,Model model) throws Exception {
//	   セッションからユーザーデータを取り出す
	   String name=session.getAttribute("user").toString();
	   UserData data=(UserData)session.getAttribute(name);
	   ArrayList<ProductDataBeans> items=data.getItem();
	   
//		MySQLに接続しbuy_tとuser_tを編集
	   Connection con = null;
       PreparedStatement st = null;
       for(ProductDataBeans item:items) {
	       try{
	           con = DBManager.getConnection();
	           st =  con.prepareStatement("select * from user_t where name=? and password=?");
	           st.setString(1,data.getName());
	           st.setString(2,data.getPassword());
	           ResultSet rs = st.executeQuery();
	           rs.next();
	    	   System.out.println(data.getCartTotal());

	           
	           st =  con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
	           st.setInt(1, rs.getInt(1));
	           st.setString(2, item.getCode());
	           st.setInt(3, type);
	           st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
	           st.executeUpdate();
	    	   System.out.println(data.getCartTotal());

	           st =  con.prepareStatement("update user_t set total = ? where userID = ?");
	           int total=rs.getInt(6);
	           total+=data.getCartTotal();
	           System.out.println(rs.getInt(6));
	           st.setInt(1, total);
	           st.setInt(2, rs.getInt(1));
	           st.executeUpdate();
	       }catch(SQLException e){
	           System.out.println(e.getMessage());
	           throw new SQLException(e);
	       }finally{
	           if(con != null){
	               con.close();
	           }
	       }
       }
       
//	   購入額をHTMLに送信
       model.addAttribute("total", data.getCartTotal());
       
//		ユーザーデータのカート情報をリセット・更新
       data.setItem(null);
       data.setCartTotal(0);
       session.setAttribute(name,data);
	   return "buy_complete";
   }
}
   
