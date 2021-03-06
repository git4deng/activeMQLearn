package com.david.activemq.jmstest.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
@Service
public class QueueSender {
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jt;
	
	public static void main(String[] args) {
		ApplicationContext at=new ClassPathXmlApplicationContext("applicationContext.xml");
		QueueSender qs = at.getBean("queueSender", QueueSender.class);
		qs.jt.send(new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				TextMessage txMsg = session.createTextMessage("spring msg==========>helloworld!!");
				return txMsg;
			}
		});
	}
	
}
