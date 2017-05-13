/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mycompany.cameldemo.databases.publisher.PublisherRepository;
import com.mycompany.cameldemo.model.*;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class PollingConsumer extends ScheduledPollConsumer
{
    private PollingEndpoint endpoint;
    private static int count = 0;
    private Date now;

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
        java.util.Date utilDate = new java.util.Date();
        if(now == null)
        {
            now = new Date(0);
        }
        else
        {
            now = new Date(utilDate.getTime());
        }

        System.out.println("Poll #" + count + " begun");
        
        String operationPath = endpoint.getOperationPath();
        if(operationPath.equals("queryDatabase")) return processDatabaseQuery();

        // only one operation implemented for now !
        throw new IllegalArgumentException("Incorrect operation: " + operationPath);
    }

    private int processDatabaseQuery() throws Exception 
    {
    	PublisherRepository repo = new PublisherRepository();
        try {
        	ArrayList<ArrayList> collections = new ArrayList();
        	
        	Collection<Allergy> allergies = repo.getAllergies(now);
            Collection<Ingredient> ingredients = repo.getIngredients(now);
            Collection<IngredientAllergy> ingredientAllergies = repo.getIngredientAllergies(now);
            Collection<MealType> mealTypes = repo.getMealTypes(now);
            Collection<MeasuredIngredient> measuredIngredients = repo.getMeasuredIngredients(now);
            Collection<RecipeType> recipeTypes = repo.getRecipeTypes(now);
            Collection<Recipe> recipes = repo.getRecipes(now);
            Collection<RecipeAllergy> recipeAllergies = repo.getRecipeAllergies(now);
            Collection<Menu> menus = repo.getMenus(now);
            Collection<MenuAllergy> menuAllergies = repo.getMenuAllergies(now);
            Collection<MenuIngredient> menuIngredients = repo.getMenuIngredients(now);
            Collection<MenuRecipe> menuRecipes = repo.getMenuRecipes(now);
            
            
                        
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
