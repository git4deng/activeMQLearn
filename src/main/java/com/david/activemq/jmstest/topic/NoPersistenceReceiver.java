package com.david.activemq.jmstest.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class NoPersistenceReceiver {
	
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		conn.start();
	
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("mytopic");
		
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = consumer.receive();
		while(message!=null){
			TextMessage txtMsg=(TextMessage)message;
			System.out.println("收到消息："+txtMsg.getText());
			message=consumer.receive(1000L);
		}
		session.commit();
		session.close();
		conn.close();
	}
}
