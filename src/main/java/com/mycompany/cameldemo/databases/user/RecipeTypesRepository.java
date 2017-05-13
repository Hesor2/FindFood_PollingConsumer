package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.RecipeType;

public class RecipeTypesRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		RecipeType model;
        String sql =
        		"SELECT recipeTypeId FROM RecipeTypes " +
                    "WHERE recipeTypeId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(RecipeType.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(RecipeType model)
	{
        String sql = 
        		"INSERT INTO RecipeTypes (recipeTypeId, recipeTypeName) " +
                        "VALUES (:recipeTypeId, :recipeTypeName)";
        executeUpdate(sql, model);
	}
	
	public void update(RecipeType model)
	{
		String sql = 
				"UPDATE RecipeTypes SET recipeTypeName = :recipeTypeName " + 
				"WHERE recipeTypeId = :recipeTypeId";
		executeUpdate(sql, model);
	}
}
