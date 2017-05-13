/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cameldemo;

import java.util.Map;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;

/**
 *
 * @author Markus
 */
public class PollingComponent extends UriEndpointComponent
{

    public PollingComponent()
    {
        super(PollingEndpoint.class);
    }
    
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> map) throws Exception 
    {
        PollingEndpoint endpoint = new PollingEndpoint(uri, remaining, this);
        PollingConfiguration configuration = new PollingConfiguration();

        // use the built-in setProperties method to clean the camel parameters map
        setProperties(configuration, map);

        endpoint.setConfiguration(configuration);
        return endpoint;
    }
}
