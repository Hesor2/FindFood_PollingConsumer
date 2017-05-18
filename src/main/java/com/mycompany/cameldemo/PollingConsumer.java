/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

import com.mycompany.cameldemo.databases.publisher.PublisherRepository;
import com.mycompany.cameldemo.model.Allergy;
import com.mycompany.cameldemo.model.Ingredient;
import com.mycompany.cameldemo.model.IngredientAllergy;
import com.mycompany.cameldemo.model.MealType;
import com.mycompany.cameldemo.model.MeasuredIngredient;
import com.mycompany.cameldemo.model.Menu;
import com.mycompany.cameldemo.model.MenuAllergy;
import com.mycompany.cameldemo.model.MenuIngredient;
import com.mycompany.cameldemo.model.MenuRecipe;
import com.mycompany.cameldemo.model.Recipe;
import com.mycompany.cameldemo.model.RecipeAllergy;
import com.mycompany.cameldemo.model.RecipeType;

public class PollingConsumer extends ScheduledPollConsumer
{
    private PollingEndpoint endpoint;
    private static int count = 0;
    private static long lastUpdate = 0;

    public PollingConsumer(PollingEndpoint endpoint, Processor processor)
    {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.setDelay(endpoint.getConfiguration().getDelay());
    }

    @Override
    protected int poll() throws Exception 
    {
        count++;

        System.out.println("Poll #" + count + " begun");
        
        String operationPath = endpoint.getOperationPath();
        if(operationPath.equals("queryDatabase")) return processDatabaseQuery();
        

        // only one operation implemented for now !
        throw new IllegalArgumentException("Incorrect operation: " + operationPath);
    }

    private int processDatabaseQuery() throws Exception 
    {
//    	System.out.println(lastUpdate);
    	PublisherRepository repo = new PublisherRepository();
    	long tempLastUpdate = lastUpdate - 30*1000;
    	lastUpdate = System.currentTimeMillis();
        try {
        	ArrayList<ArrayList> collections = new ArrayList();
        	
        	Collection<Allergy> allergies = repo.getAllergies(tempLastUpdate);
            Collection<Ingredient> ingredients = repo.getIngredients(tempLastUpdate);
            Collection<IngredientAllergy> ingredientAllergies = repo.getIngredientAllergies(tempLastUpdate);
            Collection<MealType> mealTypes = repo.getMealTypes(tempLastUpdate);
            Collection<MeasuredIngredient> measuredIngredients = repo.getMeasuredIngredients(tempLastUpdate);
            Collection<RecipeType> recipeTypes = repo.getRecipeTypes(tempLastUpdate);
            Collection<Recipe> recipes = repo.getRecipes(tempLastUpdate);
            Collection<RecipeAllergy> recipeAllergies = repo.getRecipeAllergies(tempLastUpdate);
            Collection<Menu> menus = repo.getMenus(tempLastUpdate);
            Collection<MenuAllergy> menuAllergies = repo.getMenuAllergies(tempLastUpdate);
            Collection<MenuIngredient> menuIngredients = repo.getMenuIngredients(tempLastUpdate);
            Collection<MenuRecipe> menuRecipes = repo.getMenuRecipes(tempLastUpdate);
            
            collections.add((ArrayList) allergies);
            collections.add((ArrayList) ingredients);
            collections.add((ArrayList) ingredientAllergies);
            collections.add((ArrayList) mealTypes);
            collections.add((ArrayList) measuredIngredients);
            collections.add((ArrayList) recipeTypes);
            collections.add((ArrayList) recipes);
            collections.add((ArrayList) recipeAllergies);
            collections.add((ArrayList) menus);
            collections.add((ArrayList) menuAllergies);
            collections.add((ArrayList) menuIngredients);
            collections.add((ArrayList) menuRecipes);
            
            Exchange exchange = getEndpoint().createExchange();
            exchange.getIn().setBody(collections, ArrayList.class);
            getProcessor().process(exchange);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
