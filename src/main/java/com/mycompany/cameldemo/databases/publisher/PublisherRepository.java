package com.mycompany.cameldemo.databases.publisher;

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
    
    public static void loadDatabaseConnection(String DB_URL, String DB_USER, String DB_PASS)
    {
    	sql2o = new Sql2o(DB_URL, DB_USER, DB_PASS);
    }
    
    public Collection<Allergy> getAllergies(long lastPoll)
    {
        Collection<Allergy> allergies;
        String sql =
                "SELECT allergyId, allergyName, allergyDescription " +
                        "FROM Allergies WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            allergies = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(Allergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return allergies;
    }
//    published = 1 AND 
    public Collection<Ingredient> getIngredients(long lastPoll) {
        Collection<Ingredient> ingredients;
        String sql =
                "SELECT ingredientId, ingredientName, ingredientDescription " +
                    "FROM Ingredients WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            ingredients = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(Ingredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return ingredients;
    }
    
    public Collection<IngredientAllergy> getIngredientAllergies(long lastPoll)
    {
        Collection<IngredientAllergy> ingredientAllergies;
        String sql =
                "SELECT ingredientAllergyId, allergyId, ingredientId " +
                        "FROM IngredientAllergies WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            ingredientAllergies = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(IngredientAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return ingredientAllergies;
    }
    
    public Collection<MealType> getMealTypes(long lastPoll) {
        Collection<MealType> mealTypes;
        String sql =
                "SELECT mealTypeId, mealTypeName " +
                    "FROM MealTypes WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            mealTypes = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(MealType.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return mealTypes;
    }
    
    public Collection<MeasuredIngredient> getMeasuredIngredients(long lastPoll) {
        Collection<MeasuredIngredient> measuredIngredients;
        String sql =
                "SELECT measuredIngredientId, recipeId, ingredientId, amount, measure " +
                    "FROM MeasuredIngredients WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            measuredIngredients = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(MeasuredIngredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return measuredIngredients;
    }
    
    public Collection<RecipeType> getRecipeTypes(long lastPoll) {
        Collection<RecipeType> recipeTypes;
        String sql =
                "SELECT recipeTypeId, recipeTypeName " +
                    "FROM RecipeTypes WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            recipeTypes = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(RecipeType.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return recipeTypes;
    }
    
    public Collection<Recipe> getRecipes(long lastPoll) {
        Collection<Recipe> recipes;
        String sql =
                "SELECT recipeId, recipeName, recipeDescription, recipeImageFilePath, recipeTypeId, publisherName " +
                    "FROM Recipes WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            recipes = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(Recipe.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return recipes;
    }
    
    public Collection<RecipeAllergy> getRecipeAllergies(long lastPoll)
    {
        Collection<RecipeAllergy> recipeAllergies;
        String sql =
                "SELECT recipeAllergyId, allergyId, recipeId " +
                        "FROM RecipeAllergies WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            recipeAllergies = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(RecipeAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return recipeAllergies;
    }
    
    public Collection<Menu> getMenus(long lastPoll) {
        Collection<Menu> menus;
        String sql =
                "SELECT menuId, menuName, menuDescription, menuImageFilePath, mealTypeId, publisherName " +
                    "FROM Menus WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            menus = con.createQuery(sql)
            		.addParameter("lastPoll",lastPoll)
                    .executeAndFetch(Menu.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return menus;
    }
    
    public Collection<MenuAllergy> getMenuAllergies(long lastPoll)
    {
        Collection<MenuAllergy> menuAllergies;
        String sql =
                "SELECT menuAllergyId, allergyId, menuId " +
                        "FROM MenuAllergies WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            menuAllergies = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(MenuAllergy.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return menuAllergies;
    }
    
    public Collection<MenuIngredient> getMenuIngredients(long lastPoll)
    {
        Collection<MenuIngredient> menuIngredients;
        String sql =
                "SELECT menuIngredientId, ingredientId, menuId " +
                        "FROM MenuIngredients WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            menuIngredients = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(MenuIngredient.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return menuIngredients;
    }
    
    public Collection<MenuRecipe> getMenuRecipes(long lastPoll)
    {
        Collection<MenuRecipe> menuRecipes;
        String sql =
                "SELECT menuRecipeId, recipeId, menuId " +
                        "FROM MenuRecipes WHERE createdDate >= :lastPoll";
        Connection con = null;
        try{
            con = sql2o.open();
            menuRecipes = con.createQuery(sql)
                    .addParameter("lastPoll",lastPoll)
                    .executeAndFetch(MenuRecipe.class);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
        finally
        {
        	con.close();
        }
        return menuRecipes;
    }
}
