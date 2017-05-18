package com.mycompany.cameldemo;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * A Camel Application
 */
public class MainApp 
{
    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception 
    {
        System.out.println("Starting polling consumer, press CTRL+C to terminate");
        DatabaseConnectionLoader dbcl = new DatabaseConnectionLoader();
        dbcl.loadDatabaseConnections();

        org.apache.camel.main.Main main = new org.apache.camel.main.Main();
        main.enableHangupSupport();

        main.addRouteBuilder(new RouteBuilder() 
        {
            @Override
            public void configure() throws Exception 
            {
                PropertiesComponent properties = new PropertiesComponent();
                properties.setLocation("classpath:polling.properties");
                getContext().addComponent("properties", properties);
                SendingProcessor processor = new SendingProcessor();
                
                from("polling://queryDatabase").split(body()).split(body()).process(processor);
            }
        });

        main.run();
    }
}

