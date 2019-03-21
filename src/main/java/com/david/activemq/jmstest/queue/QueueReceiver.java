package com.david.activemq.jmstest.queue;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
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
		//查看jms定义的属性
		Enumeration names = conn.getMetaData().getJMSXPropertyNames();
		while(names.hasMoreElements()){
			String name = (String) names.nextElement();
			System.out.println("jms name----->"+name);
		}
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("david-queue");
		
		MessageConsumer consumer = session.createConsumer(destination);
		int i=0;
		while(i<3){
			
			//TextMessage message=(TextMessage) consumer.receive();
			MapMessage message=(MapMessage) consumer.receive();
			
			session.commit();
		//	System.out.println("接收到消息了："+message.getText());
			System.out.println("收到消息："+message.getString("message---"+i)+",property:"+message.getStringProperty("hello"+i));
			i++;
		}
		session.close();
		conn.close();
	}
}
