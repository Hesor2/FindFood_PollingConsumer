package com.mycompany.cameldemo.model;

import com.mycompany.cameldemo.databases.user.MeasuredIngredientsRepository;

/**
 * Created by Emilo on 11-05-2017.
 */
public class MeasuredIngredient implements Model
{
    private int measuredIngredientId;
	private int recipeId;
    private int ingredientId;
    private double amount;
    private String measure;

//    public MeasuredIngredient(){}
    
    public int getMeasuredIngredientId() {
        return measuredIngredientId;
    }

    public void setMeasuredIngredientId(int measuredIngredientId) {
        this.measuredIngredientId = measuredIngredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

	@Override
	public void send() 
	{
		MeasuredIngredientsRepository repo = new MeasuredIngredientsRepository();
		String message = "MeasuredIngredient " + measuredIngredientId + " was ";
		if(repo.exists(measuredIngredientId)) 
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
	
	@Override
	public String toString()
	{
		return "" + measuredIngredientId + " " + recipeId + " " + ingredientId + " " + amount + " " + measure;
	}
}
