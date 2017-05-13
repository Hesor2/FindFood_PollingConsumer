package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;

import com.mycompany.cameldemo.model.MeasuredIngredient;

public class MeasuredIngredientsRepository extends UserRepository 
{
	@Override
	public boolean exists(int id)
	{
		MeasuredIngredient model;
        String sql =
        		"SELECT measuredIngredientId FROM MeasuredIngredients " +
                    "WHERE measuredIngredientId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(MeasuredIngredient.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(MeasuredIngredient model)
	{
        String sql = 
        		"INSERT INTO MeasuredIngredients (measuredIngredientId, recipeId, ingredientId, amount, measure) " +
                        "VALUES (:measuredIngredientId, :recipeId, :ingredientId, :amount, :measure)";
        executeUpdate(sql, model);
	}
	
	public void update(MeasuredIngredient model)
	{
		String sql = 
				"UPDATE MeasuredIngredients SET amount = :amount, measure = :measure " + 
				"WHERE measuredIngredientId = :measuredIngredientId";
		executeUpdate(sql, model);
	}
}
