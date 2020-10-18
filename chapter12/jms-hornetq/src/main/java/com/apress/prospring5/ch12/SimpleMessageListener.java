package com.apress.prospring5.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component("messageListener")
public class SimpleMessageListener{
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageListener.class);

    @JmsListener(destination = "prospring5", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(Message message) {
    	System.out.println("수신됨1");
        TextMessage textMessage = (TextMessage) message;
        System.out.println("수신됨2");
        try {
            logger.info(">>> 수신됨: " + textMessage.getText());
            System.out.println("수신됨3");
        } catch (JMSException ex) {
            logger.error("JMS 에러", ex);
        }
    }
}
