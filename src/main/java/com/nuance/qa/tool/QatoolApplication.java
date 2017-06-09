package com.nuance.qa.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class QatoolApplication {

	final static String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	// Define the behavior that occurs when RabbitTemplate publishes to an exchange.
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	
	@Bean
	public JavaMailSender mailSender(Environment env) {  
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
		mailSender.setHost(env.getProperty("mailserver.host"));
		mailSender.setPort(Integer.parseInt(env.getProperty("mailserver.port")));
		mailSender.setUsername(env.getProperty("mailserver.username"));  
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.required", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.auth.mechanisms", "XOAUTH2");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		
		String accessToken = "ya29.GlxkBB_ZpZap7bEtGtUc3mVaZtvky-ucrwKEYL6mlokELGNCkC3Qs-vFRAfzutc3j0qz3fCLVTZcnnHvnoX4pEUToL2Nqxc3-np_8n6an0Sc0yAxsrh4Ab39YMM3gw";
		mailSender.setPassword(accessToken);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(QatoolApplication.class, args);
	}
}
