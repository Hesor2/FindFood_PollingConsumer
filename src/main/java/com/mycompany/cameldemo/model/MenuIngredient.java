package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MenuIngredientsRepository;

public class MenuIngredient implements Model
{
	private int menuIngredientId;
	private int ingredientId;
	private int menuId;
	
	public int getMenuIngredientId() {
		return menuIngredientId;
	}
	
	public void setMenuIngredientId(int menuIngredientId) {
		this.menuIngredientId = menuIngredientId;
	}
	
	public int getIngredientId() {
		return ingredientId;
	}
	
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
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
		MenuIngredientsRepository repo = new MenuIngredientsRepository();
		String message = "MenuIngredient " + menuIngredientId + " was ";
		if(repo.exists(menuIngredientId)) 
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
