package com.david.activemq.jmstest.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	public static void main(String[] args) throws Exception {
		//创建连接工厂
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		//连接 activeMQ
		conn.start();
		//创建会话 Boolean.TRUE 表示需要会话 Session.AUTO_ACKNOWLEDGE 会话自动确认
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//创建目的地
		Destination destination = session.createQueue("david-queue");
		//创建生产者
		MessageProducer producer = session.createProducer(destination);
		//发送消息
		for (int i = 0; i < 3; i++) {
			TextMessage message = session.createTextMessage("message------>"+i);
			Thread.sleep(1000);
			producer.send(message);
		}
		session.commit();
		session.close();
		conn.close();
	}
}
