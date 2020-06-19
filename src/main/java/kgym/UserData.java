package kgym;
    
import java.util.ArrayList;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 	ユーザデータを格納するデータビーンズ
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Scope
public class UserData{
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private ProductDataBeans pdb;
    private ArrayList<ProductDataBeans> item=new ArrayList<ProductDataBeans>();
    private int cartTotal;
    
//  入力チェック用のリスト
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<String>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }

        return chkList;
    }

//  DB操作用のマッピングするメソッド
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setPassword(this.password);
        udd.setMail(this.mail);
        udd.setAddress(this.address);
        udd.setTotal(this.total);
    }
}
