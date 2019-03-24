package com.david.activemq.jmstest.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	/**
	 * 事务性会话的消息接收确认，当在事务会话中，一个事务提交的时候，消息的确认自动发生。
	 * @param args
	 * @throws Exception
	 */
//	public static void main(String[] args) throws Exception {
//		//创建连接工厂
//		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
//		Connection conn = connFactory.createConnection();
//		//连接 activeMQ
//		conn.start();
//		//创建会话 Boolean.TRUE 表示需要会话 Session.AUTO_ACKNOWLEDGE 会话自动确认
//		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//		//创建目的地
//		Destination destination = session.createQueue("david-queue");
//		//创建生产者
//		MessageProducer producer = session.createProducer(destination);
//		//发送消息
//		for (int i = 0; i < 3; i++) {
//			/*TextMessage message = session.createTextMessage("message------>"+i);
//			Thread.sleep(1000);
//			*/
//			// 测试message相关结构属性
//			MapMessage mapMessage = session.createMapMessage();
//			//设置属性
//			mapMessage.setStringProperty("hello"+i, "world");
//			//设置值
//			mapMessage.setString("message---"+i, "my map message++++++++"+i);
//			producer.send(mapMessage);
//		}
//		session.commit();
//		session.close();
//		conn.close();
//	}
	/**
	 * 当消息处于非事务会话中，消息何时被确认取决于会话创建时候选择的应答模式,Session.AUTO_ACKNOWLEDGE，当客户端成功的从receive（）方法返回的时侯或者
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
//		Session session = conn.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
//		//创建目的地
//		Destination destination = session.createQueue("david-queue_auto_acknowledge");
//		//创建生产者
//		MessageProducer producer = session.createProducer(destination);
//		//发送消息
//		for (int i = 0; i < 3; i++) {
//			/*TextMessage message = session.createTextMessage("message------>"+i);
//			Thread.sleep(1000);
//			 */
//			// 测试message相关结构属性
//			MapMessage mapMessage = session.createMapMessage();
//			//设置属性
//			mapMessage.setStringProperty("hello"+i, "world");
//			//设置值
//			mapMessage.setString("message---"+i, "my map message++++++++"+i);
//			producer.send(mapMessage);
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
	public static void main(String[] args) throws Exception {
		//（此地址为后面测试broker的时候填入的，实际需要天气activeMQ运行地址和端口)
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection conn = connFactory.createConnection();
		conn.start();
		Session session = conn.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("david-queue_client_acknowledge");
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 3; i++) {
			MapMessage mapMessage = session.createMapMessage();
			mapMessage.setStringProperty("hello"+i, "world");
			mapMessage.setString("message---"+i, "my map message++++++++"+i);
			producer.send(mapMessage);
		}
		session.close();
		conn.close();
	}
	
}
