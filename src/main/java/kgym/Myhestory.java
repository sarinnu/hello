package kgym;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import base.DBManager;

@Controller
public class Myhestory{
	
   @RequestMapping(value = "/myhestory", method = RequestMethod.GET)
   public String myhestory(HttpSession session,Model model) {

	   String name=session.getAttribute("user").toString();
   	   UserData data =(UserData)session.getAttribute(name);
   	   
	   	Connection con = null;
	    PreparedStatement st = null;
	    try{
	        con = DBManager.getConnection();
	
	        st =  con.prepareStatement("SELECT userID FROM user_t WHERE name like ? AND password like?");
	        st.setString(1, data.getName());
	        st.setString(2, data.getPassword());
	        ResultSet rs = st.executeQuery();
	        rs.next();
	        System.out.println(rs.getInt(1));
	        
	        st =  con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?");
	        st.setInt(1, rs.getInt(1));
	        rs = st.executeQuery();
	        
            ArrayList<ProductDataBeans> pdbList = new ArrayList<ProductDataBeans>();

	        while (rs.next()) {
	        	  String str = rs.getString("itemCode");
	  	   		  String url="https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPVF2NnkxbWpIeTBPQSZzPWNvbnN1bWVyc2VjcmV0Jng9ZmU-&itemcode="+str;
	  	   		  URL con_url=new URL(url);
	  	   		  HttpURLConnection conn=(HttpURLConnection)con_url.openConnection();
	  	   		  conn.connect();
	  	   		
	  	   		  BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	  	   		
	  	   		  String json="";
	  	   		  String receiver="";
	  	   		  while((receiver=br.readLine())!=null) {
	  	   			json+=receiver;
	  	   		  }
	  	   		
	  	   		  ObjectMapper mapper =new ObjectMapper();
	  	   		  JsonNode root=mapper.readTree(json);
	  	   		  String itemName=root.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue();
	  	   		  int price = root.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt();
	  	          String imageURL =root.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small").textValue();
	  	          
	  	          ProductDataBeans pdb = new ProductDataBeans();
	  	          String itemURL="/Item?code="+str;
		          pdb.setItemURL(itemURL);
		          pdb.setImageURL(imageURL);
		          pdb.setProductName(itemName);
		          pdb.setPrice(price);  
		          
		          pdbList.add(pdb);
		          br.close();
	        	}
	       
		        con.close();
		        
		        model.addAttribute("pdbList",pdbList);
		        model.addAttribute("name",name);
		        return "myhestory";
		    }catch(SQLException e){
		    	System.out.println("sql"+e.getMessage());
		    	return "error";
		    }catch(NullPointerException e) {
				System.out.println("null"+e.getMessage());
				return "error";
			}catch(Exception e) {
				System.out.println("e"+e.getMessage());
				return "error";
			}
        

   }
    
}
