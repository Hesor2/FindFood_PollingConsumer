package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MenusRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class Menu implements Model
{
    private int menuId;
    private String menuName;
    private String menuDescription;
    private String menuImageFilePath;
    private int mealTypeId;
    private String publisherName;

    public Menu(){}

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuImageFilePath() {
        return menuImageFilePath;
    }

    public void setMenuImageFilePath(String menuImageFilePath) {
        this.menuImageFilePath = menuImageFilePath;
    }

    public int getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
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
		MenusRepository repo = new MenusRepository();
		String message = "Menu " + menuId + " was ";
		if(repo.exists(menuId)) 
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
