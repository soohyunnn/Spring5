package com.apress.prospring5.ch12;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class);
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(final String message) {
        jmsTemplate.setDeliveryDelay(5000L);
        System.out.println("보내기1");
        this.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
            	System.out.println("보내기2");
                TextMessage jmsMessage = session.createTextMessage(message);
                logger.info(">>> 보내기: " + jmsMessage.getText());
                System.out.println("보내기3");
                return jmsMessage;
            }
        });
    }
}
