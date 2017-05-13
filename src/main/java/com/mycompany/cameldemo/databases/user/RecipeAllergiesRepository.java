package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.RecipeAllergy;

public class RecipeAllergiesRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		RecipeAllergy model;
        String sql =
        		"SELECT recipeAllergyId FROM RecipeAllergies " +
                    "WHERE recipeAllergyId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(RecipeAllergy.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(RecipeAllergy model)
	{
        String sql = 
        		"INSERT INTO RecipeAllergies (recipeAllergyId, allergyId, recipeId) " +
                        "VALUES (:recipeAllergyId, :allergyId, :recipeId)";
        executeUpdate(sql, model);
	}
	
	public void update(RecipeAllergy model)
	{
		String sql = 
				"UPDATE RecipeAllergies SET allergyId = :allergyId, recipeId = :recipeId " + 
				"WHERE recipeAllergyId = :recipeAllergyId";
		executeUpdate(sql, model);
	}
}
