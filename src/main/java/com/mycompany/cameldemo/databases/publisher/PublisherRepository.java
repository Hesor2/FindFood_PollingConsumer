package com.mycompany.cameldemo.databases.publisher;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.cameldemo.model.*;

/**
 * Created by Emilo on 11-05-2017.
 */
public class PublisherRepository
{
    private static Sql2o sql2o;
    private final static String DB_URL = "mysql://80.255.6.114:3306/FindFood_Publisher"; // Use when developing to test on localhost
    private final static String DB_USER = "FF_Publisher";
    private final static String DB_PASS = "yQjS6yiA";

    public PublisherRepository()
    {
        if(PublisherRepository.sql2o == null)
        {
            PublisherRepository.sql2o = new Sql2o(DB_URL, DB_USER, DB_PASS);
        }
    }
    
    public Collection<Allergy> getAllergies(Date lastPoll)
    {
        Collection<Allergy> allergies;
        String sql =
                "SELECT allergyId, allergyName, allergyDescription " +
                        "FROM Allergies WHERE published = 1 AND createdDate >= :lastPoll";
        try{
            Connection con = sql2o.open();
            allergies = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(Allergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return allergies;
    }
    
    public Collection<Ingredient> getIngredients(Date lastPoll) {
        Collection<Ingredient> ingredients;
        String sql =
                "SELECT ingredientId, ingredientName, ingredientDescription " +
                    "FROM Ingredients WHERE published = 1 AND createdDate >= :lastPoll";
        try{
            Connection con = sql2o.open();
            ingredients = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(Ingredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return ingredients;
    }
    
    public Collection<IngredientAllergy> getIngredientAllergies(Date lastPoll)
    {
        Collection<IngredientAllergy> ingredientAllergies;
        String sql =
                "SELECT ingredientAllergyId, allergyId, ingredientId " +
                        "FROM IngredientAllergies";
        try{
            Connection con = sql2o.open();
            ingredientAllergies = con.createQuery(sql)
//                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(IngredientAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return ingredientAllergies;
    }
    
    public Collection<MealType> getMealTypes(Date lastPoll) {
        Collection<MealType> mealTypes;
        String sql =
                "SELECT mealTypeId, mealTypeName " +
                    "FROM MealTypes WHERE published = 1 AND createdDate >= :lastPoll";
        try{
            Connection con = sql2o.open();
            mealTypes = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(MealType.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return mealTypes;
    }
    
    public Collection<MeasuredIngredient> getMeasuredIngredients(Date lastPoll) {
        Collection<MeasuredIngredient> measuredIngredients;
        String sql =
                "SELECT measuredIngredientId, recipeId, ingredientId, amount, measure " +
                    "FROM MeasuredIngredients";
        try{
            Connection con = sql2o.open();
            measuredIngredients = con.createQuery(sql)
            		//.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(MeasuredIngredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return measuredIngredients;
    }
    
    public Collection<RecipeType> getRecipeTypes(Date lastPoll) {
        Collection<RecipeType> recipeTypes;
        String sql =
                "SELECT recipeTypeId, recipeTypeName " +
                    "FROM RecipeTypes";
        try{
            Connection con = sql2o.open();
            recipeTypes = con.createQuery(sql)
            		//.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(RecipeType.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return recipeTypes;
    }
    
    public Collection<Recipe> getRecipes(Date lastPoll) {
        Collection<Recipe> recipes;
        String sql =
                "SELECT recipeId, recipeName, recipeDescription, recipeImageFilePath, recipeTypeId, publisherName " +
                    "FROM Recipes WHERE published = 1 AND createdDate >= :lastPoll";
        try{
            Connection con = sql2o.open();
            recipes = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(Recipe.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return recipes;
    }
    
    public Collection<RecipeAllergy> getRecipeAllergies(Date lastPoll)
    {
        Collection<RecipeAllergy> recipeAllergies;
        String sql =
                "SELECT recipeAllergyId, allergyId, recipeId " +
                        "FROM RecipeAllergies";
        try{
            Connection con = sql2o.open();
            recipeAllergies = con.createQuery(sql)
//                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(RecipeAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return recipeAllergies;
    }
    
    public Collection<Menu> getMenus(Date lastPoll) {
        Collection<Menu> menus;
        String sql =
                "SELECT menuId, menuName, menuDescription, menuImageFilePath, mealTypeId, publisherName " +
                    "FROM Menus WHERE published = 1 AND createdDate >= :lastPoll";
        try{
            Connection con = sql2o.open();
            menus = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(Menu.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return menus;
    }
    
    public Collection<MenuAllergy> getMenuAllergies(Date lastPoll)
    {
        Collection<MenuAllergy> menuAllergies;
        String sql =
                "SELECT menuAllergyId, allergyId, menuId " +
                        "FROM MenuAllergies";
        try{
            Connection con = sql2o.open();
            menuAllergies = con.createQuery(sql)
//                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(MenuAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return menuAllergies;
    }
    
    public Collection<MenuIngredient> getMenuIngredients(Date lastPoll)
    {
        Collection<MenuIngredient> menuIngredients;
        String sql =
                "SELECT menuIngredientId, ingredientId, menuId " +
                        "FROM MenuIngredients";
        try{
            Connection con = sql2o.open();
            menuIngredients = con.createQuery(sql)
//                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(MenuIngredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return menuIngredients;
    }
    
    public Collection<MenuRecipe> getMenuRecipes(Date lastPoll)
    {
        Collection<MenuRecipe> menuRecipes;
        String sql =
                "SELECT menuRecipeId, recipeId, menuId " +
                        "FROM MenuRecipes";
        try{
            Connection con = sql2o.open();
            menuRecipes = con.createQuery(sql)
//                    .addParameter("lastPoll",lastPoll.getTime())
                    .executeAndFetch(MenuRecipe.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return menuRecipes;
    }
}
