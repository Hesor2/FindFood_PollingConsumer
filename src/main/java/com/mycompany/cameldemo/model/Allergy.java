package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.AllergiesRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class Allergy implements Model {
    private int allergyId;
    private String allergyName;
    private String allergyDescription;

    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyDescription() {
        return allergyDescription;
    }

    public void setAllergyDescription(String allergyDescription) {
        this.allergyDescription = allergyDescription;
    }

    @Override
    public String toString()
    {
        return '\n' + "ID: " + allergyId + '\n' + "Name: " + allergyName + '\n' + "Description: " + allergyDescription;
    }

	@Override
	public void send() 
	{
		AllergiesRepository repo = new AllergiesRepository();
		String message = "Allergy " + allergyId + " was ";
		if(repo.exists(allergyId)) 
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
