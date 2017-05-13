package com.mycompany.cameldemo;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultPollingEndpoint;
import org.apache.camel.spi.UriEndpoint;

@UriEndpoint(scheme= "polling", title= "Polling", syntax= "polling://operationPath", consumerOnly= true, consumerClass= PollingConsumer.class, label= "polling")
public class PollingEndpoint extends DefaultPollingEndpoint
{
    private String operationPath;
    private PollingConfiguration configuration;
    
    public PollingEndpoint(String uri, String operationPath, PollingComponent component)
    {
        super(uri, component);
        this.operationPath = operationPath;
    }

    @Override
    public Producer createProducer() throws Exception 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Consumer createConsumer(Processor processor) throws Exception 
    {
        PollingConsumer consumer = new PollingConsumer(this, processor);
        return consumer;
    }

    @Override
    public boolean isSingleton() 
    {
        return true;
    }
    
    public String getOperationPath() {
        return operationPath;
    }

    public void setOperationPath(String operationPath) 
    {
        this.operationPath = operationPath;
    }

    public PollingConfiguration getConfiguration()
    {
        return configuration;
    }

    public void setConfiguration(PollingConfiguration configuration)
    {
        this.configuration = configuration;
    }

}
