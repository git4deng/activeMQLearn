package com.david.activemq.jmstest.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 持久订阅的topic消息的消费者
 * @author david
 *
 */
public class PersistenceReceiver {
	/**
	 * 持久订阅一定要先运行一次，相当于向中间件注册这个消费者，这个时候无论消费者是否在线，消息都会收到
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61616");
		Connection conn = connFactory.createConnection();
		//设置消费者id,用来识别消费者
		conn.setClientID("cc_01");
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Topic destination = session.createTopic("mytopic_persistent");
		//创建TopicSubscriber来订阅
		TopicSubscriber ts = session.createDurableSubscriber(destination, "T1");
		conn.start();
		Message message = ts.receive();
		while(message!=null){
			TextMessage txtMsg=(TextMessage)message;
			System.out.println("收到消息："+txtMsg.getText());
			message=ts.receive(1000L);
		}
		session.commit();
		session.close();
		conn.close();
	}
}
