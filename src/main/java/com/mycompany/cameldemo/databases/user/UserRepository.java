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

    public static void loadDatabaseConnection(String DB_URL, String DB_USER, String DB_PASS)
    {
    	sql2o = new Sql2o(DB_URL, DB_USER, DB_PASS);
    }

    protected static Sql2o getSql2o() {
        return sql2o;
    }
    
    protected void executeUpdate(String sql, Model model)
    {
    	Connection con = null;
    	try{
            con = sql2o.open();
            con.createQuery(sql, true)
                    .bind(model)
                    .executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    	finally
    	{
    		con.close();
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
