package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;

import com.mycompany.cameldemo.model.Recipe;

public class RecipesRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		Recipe model;
        String sql =
        		"SELECT recipeId FROM Recipes " +
                    "WHERE recipeId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Recipe.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(Recipe model)
	{
        String sql = 
        		"INSERT INTO Recipes (recipeId, recipeName, recipeDescription, recipeImageFilePath, recipeTypeId, publisherName) " +
                        "VALUES (:recipeId, :recipeName, :recipeDescription, :recipeImageFilePath, :recipeTypeId, :publisherName)";
        executeUpdate(sql, model);
	}
	
	public void update(Recipe model)
	{
		String sql = 
				"UPDATE Recipes SET recipeName = :recipeName, recipeDescription = :recipeDescription, recipeImageFilePath = :recipeImageFilePath, recipeTypeId = :recipeTypeId, publisherName = :publisherName " + 
				"WHERE recipeId = :recipeId";
		executeUpdate(sql, model);
	}
}
