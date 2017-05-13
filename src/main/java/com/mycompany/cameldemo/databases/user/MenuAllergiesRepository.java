package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.MenuAllergy;

public class MenuAllergiesRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		MenuAllergy model;
        String sql =
        		"SELECT menuAllergyId FROM MenuAllergies " +
                    "WHERE menuAllergyId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(MenuAllergy.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(MenuAllergy model)
	{
        String sql = 
        		"INSERT INTO MenuAllergies (menuAllergyId, allergyId, menuId) " +
                        "VALUES (:menuAllergyId, :allergyId, :menuId)";
        executeUpdate(sql, model);
	}
	
	public void update(MenuAllergy model)
	{
		String sql = 
				"UPDATE MenuAllergies SET allergyId = :allergyId, menuId = :menuId " + 
				"WHERE menuAllergyId = :menuAllergyId";
		executeUpdate(sql, model);
	}
}
