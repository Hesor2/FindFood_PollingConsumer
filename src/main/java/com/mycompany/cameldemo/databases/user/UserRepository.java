package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.cameldemo.model.Model;

/**
 * Created by Emilo on 11-05-2017.
 */
public class UserRepository
{
    private static Sql2o sql2o;
    private final static String DB_URL = "mysql://80.255.6.114:3306/FindFood_User";
    //private final static String DB_URL = "mysql://localhost:3306/FindFood_User";
    private final static String DB_USER = "FF_User";
    private final static String DB_PASS = "Dr4X8gvT";

    public UserRepository()
    {
        if(UserRepository.sql2o == null)
        {
            UserRepository.sql2o = new Sql2o(DB_URL, DB_USER, DB_PASS);
        }
    }

    protected static Sql2o getSql2o() {
        return sql2o;
    }
    
    protected void executeUpdate(String sql, Model model)
    {
    	try{
            Connection con = sql2o.open();
            con.createQuery(sql, true)
                    .bind(model)
                    .executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean exists(int pk)
    {
    	return false;
    }
    
//    public boolean exists(int pk1, int pk2)
//    {
//    	return false;
//    }
}
