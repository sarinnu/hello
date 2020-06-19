package kgym;

import java.util.ArrayList;

/**
 * 画面系の処�?�?表示を簡略化するため�?�ヘルパ�?�クラス。定数なども保存されま�?
 * @author hayashi-s
 */
public class KgymHelper {

    //トップへのリンクを定数として設�?
    private String homeURL = "/login";

    public static KgymHelper getInstance(){
        return new KgymHelper();
    }

    //トップへのリンクを返却
    public String home(){
        return homeURL;
    }

    /**
     * 入力された�?ータの�?ち未入力�??目がある�?�合�?�チェ�?クリストにしたがいどの�?目�?
     * 未入力なのか�?�html�?を返却する
     * @param chkList�?UserDataBeansで生�?�されるリスト�?�未入力要�?の名前が�?�納されて�?�?
     * @return 未入力�?��?目に対応する文字�??
     */
    public String chkinput(ArrayList<String> chkList){
        String output = "";
        for(String val : chkList){
                if(val.equals("name")){
                    output += "名前";
                }
                if(val.equals("password")){
                    output +="パスワー�?";
                }
                if(val.equals("mail")){
                    output +="メール";
                }
                if(val.equals("address")){
                    output +="住所";
                }
                output +="が未記�?�で�?<br>";
            }
        return output;
    }
    
    
}