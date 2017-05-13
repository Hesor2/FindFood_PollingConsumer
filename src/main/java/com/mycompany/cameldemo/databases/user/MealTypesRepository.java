package com.mycompany.cameldemo.databases.user;


import org.sql2o.Connection;

import com.mycompany.cameldemo.model.MealType;

public class MealTypesRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		MealType model;
        String sql =
                "SELECT mealTypeId FROM MealTypes " +
                    "WHERE mealTypeId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(MealType.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(MealType model)
	{
        String sql =
                "INSERT INTO MealTypes (mealTypeId, mealTypeName) " +
                    "VALUES (:mealTypeId, :mealTypeName)";
        executeUpdate(sql, model);
	}
	
	public void update(MealType model)
	{
		String sql = "UPDATE MealTypes SET mealTypeName = :mealTypeName " + 
				"WHERE mealTypeId = :mealTypeId";
		executeUpdate(sql, model);
	}
}
