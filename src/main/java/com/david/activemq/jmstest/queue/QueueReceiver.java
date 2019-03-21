package com.david.activemq.jmstest.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueReceiver {
	public static void main(String[] args) throws Exception {
		//创建连接工厂
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		//连接 activeMQ
		conn.start();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("david-queue");
		
		MessageConsumer consumer = session.createConsumer(destination);
		int i=0;
		while(i<3){
			i++;
			TextMessage message=(TextMessage) consumer.receive();
			session.commit();
			System.out.println("接收到消息了："+message.getText());
		}
		session.close();
		conn.close();
	}
}
