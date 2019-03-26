package com.david.activemq.jmstest.spring.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

	public void onMessage(Message message) {
		TextMessage txMsg=(TextMessage) message;
		try {
			System.out.println("spring配置的消费者接收到消息："+txMsg.getText());
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}

}
