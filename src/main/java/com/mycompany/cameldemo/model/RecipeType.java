package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.RecipeTypesRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class RecipeType implements Model
{
    private int recipeTypeId;
    private String recipeTypeName;

    public RecipeType(){
    }

    public RecipeType(String name){
        this.recipeTypeId = 0;
        this.recipeTypeName = name;
    }

    public RecipeType(int id, String name)
    {
        this.recipeTypeId = id;
        this.recipeTypeName = name;
    }

    public int getRecipeTypeId() {
        return recipeTypeId;
    }

    public void setRecipeTypeId(int recipeTypeId) {
        this.recipeTypeId = recipeTypeId;
    }

    public String getRecipeTypeName() {
        return recipeTypeName;
    }

    public void setRecipeTypeName(String recipeTypeName) {
        this.recipeTypeName = recipeTypeName;
    }

	@Override
	public void send() 
	{
		RecipeTypesRepository repo = new RecipeTypesRepository();
		String message = "RecipeType " + recipeTypeId + " was ";
		if(repo.exists(recipeTypeId)) 
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