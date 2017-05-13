package com.mycompany.cameldemo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.mycompany.cameldemo.model.*;

public class SendingProcessor implements Processor 
{

	@Override
	public void process(Exchange exchange) throws Exception 
	{
		Model model = exchange.getIn().getBody(Model.class);
		model.send();
	}

}
