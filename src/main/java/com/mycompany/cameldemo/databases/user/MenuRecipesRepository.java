package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.MenuRecipe;

public class MenuRecipesRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		MenuRecipe model;
        String sql =
        		"SELECT menuRecipeId FROM MenuRecipes " +
                    "WHERE menuRecipeId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(MenuRecipe.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(MenuRecipe model)
	{
        String sql = 
        		"INSERT INTO MenuRecipes (menuRecipeId, recipeId, menuId) " +
                        "VALUES (:menuRecipeId, :recipeId, :menuId)";
        executeUpdate(sql, model);
	}
	
	public void update(MenuRecipe model)
	{
		String sql = 
				"UPDATE MenuRecipes SET recipeId = :recipeId, menuId = :menuId " + 
				"WHERE menuRecipeId = :menuRecipeId";
		executeUpdate(sql, model);
	}
}
