package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MealTypesRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class MealType implements Model
{
    private int mealTypeId;
    private String mealTypeName;

    public int getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(int mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public String getMealTypeName() {
        return mealTypeName;
    }

    public void setMealTypeName(String mealTypeName) {
        this.mealTypeName = mealTypeName;
    }

	@Override
	public void send() {
		MealTypesRepository repo = new MealTypesRepository();
		String message = "MealType " + mealTypeId + " was ";
		if(repo.exists(mealTypeId)) 
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