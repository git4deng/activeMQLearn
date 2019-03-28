package com.david.activemq.jmstest.staticnetwork;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueReceiver2 {
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory connFactory=new ActiveMQConnectionFactory("tcp://47.107.184.235:61716");
		for(int i=0;i<30;i++){
			new MyThread(connFactory).start();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
class MyThread extends Thread{
	private ConnectionFactory connFactory;
	public MyThread(ConnectionFactory connFactory) {
		this.connFactory=connFactory;
	}
	@Override
	public void run() {
		try {
			final Connection conn = connFactory.createConnection();
			conn.start();
			final Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("david-queue2");
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					TextMessage tm=(TextMessage) message;
					try {
						System.out.println("61716接收到消息："+tm.getText()+"==="+new Date().getTime());
					} catch (JMSException e) {
						e.printStackTrace();
					}
					try {
						session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
					try {
						session.close();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						conn.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}