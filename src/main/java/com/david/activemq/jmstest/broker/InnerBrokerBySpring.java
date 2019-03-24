package com.david.activemq.jmstest.broker;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class InnerBrokerBySpring {
	public static void main(String[] args) throws Exception {
		ApplicationContext at=new ClassPathXmlApplicationContext("applicationContext.xml");
		
	}
}
