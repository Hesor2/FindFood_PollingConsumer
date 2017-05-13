package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.MenuIngredient;

public class MenuIngredientsRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		MenuIngredient model;
        String sql =
        		"SELECT menuIngredientId FROM MenuIngredients " +
                    "WHERE menuIngredientId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(MenuIngredient.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(MenuIngredient model)
	{
        String sql = 
        		"INSERT INTO MenuIngredients (menuIngredientId, ingredientId, menuId) " +
                        "VALUES (:menuIngredientId, :ingredientId, :menuId)";
        executeUpdate(sql, model);
	}
	
	public void update(MenuIngredient model)
	{
		String sql = 
				"UPDATE MenuIngredients SET ingredientId = :ingredientId, menuId = :menuId " + 
				"WHERE menuIngredientId = :menuIngredientId";
		executeUpdate(sql, model);
	}
}
