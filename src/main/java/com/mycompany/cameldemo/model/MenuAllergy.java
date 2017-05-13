package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MenuAllergiesRepository;

public class MenuAllergy implements Model
{
	private int menuAllergyId;
	private int allergyId;
	private int menuId;
	
	public int getMenuAllergyId() {
		return menuAllergyId;
	}
	
	public void setMenuAllergyId(int menuAllergyId) {
		this.menuAllergyId = menuAllergyId;
	}
	
	public int getAllergyId() {
		return allergyId;
	}
	
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
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
		MenuAllergiesRepository repo = new MenuAllergiesRepository();
		String message = "MenuAllergy " + menuAllergyId + " was ";
		if(repo.exists(menuAllergyId)) 
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
