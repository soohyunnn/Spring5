package com.example.demo.activemqApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.apress.prospring5.ch9.entities.Singer;

import static com.example.demo.activemqApp.ActiveMQConfig.ORDER_QUEUE;

@Service
public class OrderSender {

    private static Logger log = LoggerFactory.getLogger(OrderSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Singer myMessage) {
        log.info("sending with convertAndSend() to queue <" + myMessage + ">");
        jmsTemplate.convertAndSend(ORDER_QUEUE, myMessage);
    }
}