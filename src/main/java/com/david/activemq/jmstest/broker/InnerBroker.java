package com.david.activemq.jmstest.broker;

import java.net.URI;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class InnerBroker {
	public static void main(String[] args) throws Exception {
		//启动broker方式一
//		BrokerService broker=new BrokerService();
//		broker.setUseJmx(true);
//		broker.addConnector("tcp://localhost:61616");
//		broker.start();
		//启动方式二：BrokerFactory来创建broker
		String uri="properties:broker.properties";
		BrokerService broker = BrokerFactory.createBroker(new URI(uri));
		broker.addConnector("tcp://localhost:61616");
		broker.start();
	}
}
