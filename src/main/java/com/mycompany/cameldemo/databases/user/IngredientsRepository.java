package com.mycompany.cameldemo.databases.user;

import com.mycompany.cameldemo.model.Ingredient;

import org.sql2o.Connection;

public class IngredientsRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		Ingredient model;
        String sql =
                "SELECT ingredientId FROM Ingredients " +
                    "WHERE ingredientId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Ingredient.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(Ingredient model)
	{
        String sql =
                "INSERT INTO Ingredients (ingredientId, ingredientName, ingredientDescription) " +
                    "VALUES (:ingredientId, :ingredientName, :ingredientDescription)";
        executeUpdate(sql, model);
	}
	
	public void update(Ingredient model)
	{
		String sql = "UPDATE Ingredients SET ingredientName = :ingredientName, ingredientDescription = :ingredientDescription " + 
				"WHERE ingredientId = :ingredientId";
		executeUpdate(sql, model);
	}
}
