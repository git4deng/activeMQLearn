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
	/**
	 * 事务性会话的消息接收确认，当在事务会话中，一个事务提交的时候，消息的确认自动发生。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//创建连接工厂
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61716");
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
			//事务提交的时候，消息自动确认
			session.commit();
		//	System.out.println("接收到消息了："+message.getText());
			System.out.println("收到消息："+message.getString("message---"+i)+",property:"+message.getStringProperty("hello"+i));
			i++;
		}
		session.close();
		conn.close();
	}
	/**
	 * 当消息处于非事务会话中，消息何时被确认取决于会话创建时候选择的应答模式 ,Session.AUTO_ACKNOWLEDGE，当客户端成功的从receive（）方法返回的时侯或者
	 * 从MessageListener.onMessage方法成功返回时，会话自动确认客户收到消息
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception {
//		//创建连接工厂
//		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
//		Connection conn = connFactory.createConnection();
//		//连接 activeMQ
//		conn.start();
//		//查看jms定义的属性
//		Enumeration names = conn.getMetaData().getJMSXPropertyNames();
//		while(names.hasMoreElements()){
//			String name = (String) names.nextElement();
//			System.out.println("jms name----->"+name);
//		}
//		Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
//		Destination destination = session.createQueue("david-queue_auto_acknowledge");
//		
//		MessageConsumer consumer = session.createConsumer(destination);
//		int i=0;
//		while(i<3){
//			MapMessage message=(MapMessage) consumer.receive();
//			System.out.println("收到消息："+message.getString("message---"+i)+",property:"+message.getStringProperty("hello"+i));
//			i++;
//		}
//		session.close();
//		conn.close();
//	}
	/**
	 * 当消息处于非事务会话中，消息何时被确认取决于会话创建时候选择的应答模式,Session.CLIENT_ACKNOWLEDGE，
	 * 客户端需要调用消息的aclnowlege方法确认消息，注意，这个种模式的消息确认是会话层面的，一但一个消息被确认
	 * 那么session下面的所有消息都会被确认。
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception {
//		//创建连接工厂（此地址为后面测试broker的时候填入的，实际需要天气activeMQ运行地址和端口)
//		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
//		Connection conn = connFactory.createConnection();
//		//连接 activeMQ
//		conn.start();
//		//查看jms定义的属性
//		Enumeration names = conn.getMetaData().getJMSXPropertyNames();
//		while(names.hasMoreElements()){
//			String name = (String) names.nextElement();
//			System.out.println("jms name----->"+name);
//		}
//		Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
//		Destination destination = session.createQueue("david-queue_client_acknowledge");
//		
//		MessageConsumer consumer = session.createConsumer(destination);
//		int i=0;
//		while(i<3){
//			MapMessage message=(MapMessage) consumer.receive();
//			System.out.println("收到消息："+message.getString("message---"+i)+",property:"+message.getStringProperty("hello"+i));
//			//通过消息本身来确认消息
//			if(i==2) message.acknowledge();
//			i++;
//		}
//		session.close();
//		conn.close();
//	}
}
