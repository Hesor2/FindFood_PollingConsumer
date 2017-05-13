package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.RecipeAllergiesRepository;;

public class RecipeAllergy implements Model
{
	private int recipeAllergyId;
	private int allergyId;
	private int recipeId;
	
	public int getRecipeAllergyId() {
		return recipeAllergyId;
	}
	
	public void setRecipeAllergyId(int recipeAllergyId) {
		this.recipeAllergyId = recipeAllergyId;
	}
	
	public int getAllergyId() {
		return allergyId;
	}
	
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	@Override
	public void send() 
	{
		RecipeAllergiesRepository repo = new RecipeAllergiesRepository();
		String message = "RecipeAllergy " + recipeAllergyId + " was ";
		if(repo.exists(recipeAllergyId)) 
		{
			repo.update(this);
			message += "updated";
		}
		else
		{
			repo.create(this);
			message += "created";
		}
		
		System.out.println(message);
	}
	
	
}
