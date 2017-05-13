package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;
import com.mycompany.cameldemo.model.IngredientAllergy;

public class IngredientAllergiesRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		IngredientAllergy model;
        String sql =
        		"SELECT ingredientAllergyId FROM IngredientAllergies " +
                    "WHERE ingredientAllergyId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(IngredientAllergy.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(IngredientAllergy model)
	{
        String sql = 
        		"INSERT INTO IngredientAllergies (ingredientAllergyId, allergyId, ingredientId) " +
                        "VALUES (:ingredientAllergyId, :allergyId, :ingredientId)";
        executeUpdate(sql, model);
	}
	
	public void update(IngredientAllergy model)
	{
		String sql = 
				"UPDATE IngredientAllergies SET allergyId = :allergyId, ingredientId = :ingredientId " + 
				"WHERE ingredientAllergyId = :ingredientAllergyId";
		executeUpdate(sql, model);
	}
}
