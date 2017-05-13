package com.mycompany.cameldemo.databases.user;

import org.sql2o.Connection;

import com.mycompany.cameldemo.model.Menu;

public class MenusRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		Menu model;
        String sql =
        		"SELECT menuId FROM Menus " +
                    "WHERE menuId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Menu.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(Menu model)
	{
        String sql = 
        		"INSERT INTO Menus (menuId, menuName, menuDescription, menuImageFilePath, mealTypeId, publisherName) " +
                        "VALUES (:menuId, :menuName, :menuDescription, :menuImageFilePath, :mealTypeId, :publisherName)";
        executeUpdate(sql, model);
	}
	
	public void update(Menu model)
	{
		String sql = 
				"UPDATE Menus SET menuName = :menuName, menuDescription = :menuDescription, menuImageFilePath = :menuImageFilePath, mealTypeId = :mealTypeId, publisherName = :publisherName " + 
				"WHERE menuId = :menuId";
		executeUpdate(sql, model);
	}
}
