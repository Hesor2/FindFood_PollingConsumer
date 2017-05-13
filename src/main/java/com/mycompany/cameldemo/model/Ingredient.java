package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.IngredientsRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class Ingredient implements Model{
    private int ingredientId;
    private String ingredientName;
    private String ingredientDescription;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

	@Override
	public void send() {
		IngredientsRepository repo = new IngredientsRepository();
		String message = "Ingredient " + ingredientId + " was ";
		if(repo.exists(ingredientId)) 
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
