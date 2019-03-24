package com.david.activemq.jmstest.spring;

import javax.jms.JMSException;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
@Service
public class QueueReceiver {
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jt;
	public static void main(String[] args) throws JMSException {
		ApplicationContext at=new ClassPathXmlApplicationContext("applicationContext.xml");
		QueueReceiver qr = at.getBean("queueReceiver", QueueReceiver.class);
		String txMsg =(String) qr.jt.receiveAndConvert();
		System.out.println("=================》接收到消息："+txMsg);
		
	}
}
