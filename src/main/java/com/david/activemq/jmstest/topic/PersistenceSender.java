package com.david.activemq.jmstest.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 持久订阅topic消息的生产者
 * @author david
 *
 */
public class PersistenceSender {
	
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		/*
		 * 持久订阅设置需要在连接之前便设置好
		 */
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic("mytopic_persistent");
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		conn.start();
	
		
		
		//发送消息
		for (int i = 0; i < 3; i++) {
			TextMessage message = session.createTextMessage("topic_persistent------>"+i);
			producer.send(message);
		}
		session.commit();
		session.close();
		conn.close();
	}
	
}
