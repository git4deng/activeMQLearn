package com.david.activemq.jmstest.staticnetwork;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		conn.start();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("david-queue2");
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 30; i++) {
			TextMessage message = session.createTextMessage("message------>"+i);
			producer.send(message);
		}
		session.commit();
		session.close();
		conn.close();
	}
}
