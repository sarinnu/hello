package kgym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import base.DBManager;


public class UserDataDAO {

    // DBを介したユーザデータの操作
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }

//  ユーザデータの新規登録
    public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());//謖�螳壹�ｮ繧ｿ繧､繝�繧ｹ繧ｿ繝ｳ繝怜�､縺九ｉSQL譬ｼ邏咲畑縺ｮDATE蝙九↓螟画峩
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

//  ユーザデータの検索
    public UserDataDTO search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "SELECT * FROM user_t WHERE name like ? AND password like?";

            st =  con.prepareStatement(sql);
            st.setString(1, "%"+ud.getName()+"%");
            st.setString(2,  ud.getPassword());

            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setPassword(rs.getString(3));
            resultUd.setMail(rs.getString(4));
            resultUd.setAddress(rs.getString(5));

            System.out.println("search completed");

            return resultUd;
        }catch(SQLException e){
        	System.out.println(e.getMessage());
        	UserDataDTO nul=new UserDataDTO();
        	return nul;
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

//  ユーザデータの更新
    
    public void update(UserDataDTO ud,String name) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "UPDATE user_t SET name=?,password=?,mail=?,address=? WHERE name =?";

            st =  con.prepareStatement(sql);
            st.setString(1, ud.getName().trim());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4,ud.getAddress().trim());
            st.setString(5, name);
            st.executeUpdate();

            System.out.println("update completed");
         
        }catch(SQLException e){
            System.out.println("sql"+e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
//  ユーザデータの削除
    public void delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();

            String sql = "UPDATE user_t SET deleteFlg=? WHERE name =?";

            st =  con.prepareStatement(sql);
            int flg=ud.getDeleteFlg()+1;
            st.setInt(1, flg);
            st.setString(2, ud.getName().trim());
            st.executeUpdate();

            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println("sql"+e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }

}
