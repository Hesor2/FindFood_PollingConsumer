package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.IngredientAllergiesRepository;

public class IngredientAllergy implements Model
{
	private int ingredientAllergyId;
	private int allergyId;
	private int ingredientId;
	
	public int getIngredientAllergyId() {
		return ingredientAllergyId;
	}
	
	public void setIngredientAllergyId(int ingredientAllergyId) {
		this.ingredientAllergyId = ingredientAllergyId;
	}
	
	public int getAllergyId() {
		return allergyId;
	}
	
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}
	
	public int getIngredientId() {
		return ingredientId;
	}
	
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Override
	public void send() 
	{
		IngredientAllergiesRepository repo = new IngredientAllergiesRepository();
		String message = "IngredientAllergy " + ingredientAllergyId + " was ";
		if(repo.exists(ingredientAllergyId)) 
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
