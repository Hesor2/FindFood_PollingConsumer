package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MenuRecipesRepository;

public class MenuRecipe implements Model
{
	private int menuRecipeId;
	private int recipeId;
	private int menuId;
	
	public int getMenuRecipeId() {
		return menuRecipeId;
	}
	
	public void setMenuRecipeId(int menuRecipeId) {
		this.menuRecipeId = menuRecipeId;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Override
	public void send() 
	{
		MenuRecipesRepository repo = new MenuRecipesRepository();
		String message = "MenuRecipe " + menuRecipeId + " was ";
		if(repo.exists(menuRecipeId)) 
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
