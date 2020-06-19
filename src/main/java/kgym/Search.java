package kgym;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class Search {
	
	@RequestMapping("/Search")
    public String search(HttpSession session,@RequestParam("item")String item,Model model) {
    	KgymHelper kgym=KgymHelper.getInstance();
    	String home=kgym.home();
    	model.addAttribute("home",home);
    	
    	if(session.getAttribute("user")==null) {
    		model.addAttribute("null", "ログインしてください");
    		return "redirect:top";
    	}
    	
    	if(item.equals("")) {
    		model.addAttribute("null","入力してください");
    		return "redirect:top";
    	}
    			
    	try {
    		String item2=URLEncoder.encode(item,"UTF-8");
    		String url="https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPVF2NnkxbWpIeTBPQSZzPWNvbnN1bWVyc2VjcmV0Jng9ZmU-&query="+item2;
    		URL con_url=new URL(url);
    		HttpURLConnection conn=(HttpURLConnection)con_url.openConnection();
    		conn.connect();
    		
    		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    		
    		String json="";
    		String receiver="";
    		while((receiver=br.readLine())!=null) {
    			json+=receiver;
    		}
    		
    		
    		System.out.println(json);
    		ObjectMapper mapper =new ObjectMapper();
    		JsonNode root=mapper.readTree(json);
    		
    		
    		  //Beansを格納するArrayListを生成
            ArrayList<ProductDataBeans> pdbList = new ArrayList<ProductDataBeans>();
            
            int SearchResultNum = root.get("ResultSet").get("totalResultsAvailable").asInt();
            model.addAttribute("results",SearchResultNum);

			  //JSONから10件分の要素を取り出し、String型に格納
			  //UserDataBeansにセットし、BeansをArrayListに格納
			  for(int i = 0; i <= 19; i++) {
			        String hitNum = String.valueOf(i);
			        String imageURL =root.get("ResultSet").get("0").get("Result").get(hitNum).get("Image").get("Medium").textValue();
			        String productName = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Name").textValue();
			        int price = root.get("ResultSet").get("0").get("Result").get(hitNum).get("Price").get("_value").asInt();
			        String ID=root.get("ResultSet").get("0").get("Result").get(hitNum).get("Url").textValue();
					
			        System.out.println(SearchResultNum);
			        String rating=root.get("ResultSet").get("0").get("Result").get(hitNum).get("Review").get("Rate").textValue();
					String description=root.get("ResultSet").get("0").get("Result").get(hitNum).get("Description").textValue();
			        String availability=root.get("ResultSet").get("0").get("Result").get(hitNum).get("Availability").textValue();
			        if(availability.equals("instock")) {
			        	availability="購入可能";
			        }else {
			        	availability="在庫切れ";
			        }
			     
			        ProductDataBeans pdb = new ProductDataBeans();
			        pdb.setImageURL(imageURL);
			        pdb.setProductName(productName);
			        pdb.setPrice(price);  
			        pdb.setQuery(item2);
			        pdb.setSearchResultNum(SearchResultNum);
			        
			        String code=root.get("ResultSet").get("0").get("Result").get(hitNum).get("Code").textValue();
			        pdb.setCode(code);
			        model.addAttribute("code","https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLockup?appid=dj00aiZpPVF2NnkxbWpIeTBPQSZzPWNvbnN1bWVyc2VjcmV0Jng9ZmU-&itemcode="+code);
			        
			        String itemURL="/Item?code="+code;
			        pdb.setItemURL(itemURL);
			        pdb.setRating(rating);
			        pdb.setDescription(description);
			        pdb.setAvailability(availability);
			        session.setAttribute(code,pdb);
			
			        pdbList.add(pdb);
			   }
			
			  //ArrayListをリクエストスコープに格納
			model.addAttribute("resultData", pdbList);
			model.addAttribute("keyword", item);
			
            br.close();
            
    		return "search";
    	}catch(NullPointerException e) {
    		System.out.println("null"+e.getMessage());
    		return "error";
    	}catch(Exception e) {
    		System.out.println("e"+e.getMessage());
    		return "error";
    	}
    }
}
