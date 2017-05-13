package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.RecipesRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class Recipe implements Model {

    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String recipeImageFilePath;
    private int recipeTypeId;
    private String publisherName;

    public Recipe(){}

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeImageFilePath() {
        return recipeImageFilePath;
    }

    public void setRecipeImageFilePath(String recipeImageFilePath) {
        this.recipeImageFilePath = recipeImageFilePath;
    }

    public int getRecipeTypeId() {
        return recipeTypeId;
    }

    public void setRecipeTypeId(int recipeTypeId) {
        this.recipeTypeId = recipeTypeId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

	@Override
	public void send() 
	{
		RecipesRepository repo = new RecipesRepository();
		String message = "Recipe " + recipeId + " was ";
		if(repo.exists(recipeId)) 
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