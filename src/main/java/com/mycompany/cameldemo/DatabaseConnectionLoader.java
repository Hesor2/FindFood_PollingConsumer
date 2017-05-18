package com.mycompany.cameldemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.mycompany.cameldemo.databases.publisher.PublisherRepository;
import com.mycompany.cameldemo.databases.user.UserRepository;

public class DatabaseConnectionLoader 
{	
	public void loadDatabaseConnections()
	{
		try (BufferedReader br = new BufferedReader(new FileReader(new File("PollingConsumer.config")))) 
		{
		    String line;
		    int lineCount = 0;
		    String publisherURL = null;
		    String publisherLogin = null;
		    String publisherPassword = null;
		    String userURL = null;
		    String userLogin = null;
		    String userPassword = null;
		    
		    while ((line = br.readLine()) != null) 
		    {
		       switch (lineCount)
		       {
		       case 0:
		    	   publisherURL = line;
		    	   break;
		       case 1:
		    	   publisherLogin = line;
		    	   break;
		       case 2:
		    	   publisherPassword = line;
		    	   break;
		       case 3:
		    	   userURL = line;
		    	   break;
		       case 4:
		    	   userLogin = line;
		    	   break;
		       case 5:
		    	   userPassword = line;
		    	   break;
		       }
		       lineCount++;
		    }
		    PublisherRepository.loadDatabaseConnection(publisherURL, publisherLogin, publisherPassword);
		    UserRepository.loadDatabaseConnection(userURL, userLogin, userPassword);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
