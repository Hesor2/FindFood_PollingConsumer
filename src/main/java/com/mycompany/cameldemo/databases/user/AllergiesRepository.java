package com.mycompany.cameldemo.databases.user;

import com.mycompany.cameldemo.model.Allergy;
import org.sql2o.Connection;

public class AllergiesRepository extends UserRepository
{
	@Override
	public boolean exists(int id)
	{
		Allergy model;
        String sql =
                "SELECT allergyId FROM Allergies " +
                    "WHERE allergyId = :id";
        try{
            Connection con = getSql2o().open();
            model = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Allergy.class);
            if (model != null) return true;
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
	}
	
	public void create(Allergy model)
	{
        String sql =
                "INSERT INTO Allergies (allergyId, allergyName, allergyDescription) " +
                    "VALUES (:allergyId, :allergyName, :allergyDescription)";
        executeUpdate(sql, model);
	}
	
	public void update(Allergy model)
	{
		String sql = "UPDATE Allergies SET allergyName = :allergyName, allergyDescription = :allergyDescription " + 
				"WHERE allergyId = :allergyId";
		executeUpdate(sql, model);
	}
}
