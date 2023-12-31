package com.yks.cmt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitmqConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    //自动装配消息监听器所在的容器工厂配置类实例
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
    /**
     * 下面为单一消费者实例的配置
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        //定义消息监听器所在的容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //设置容器工厂所用的实例
        factory.setConnectionFactory(connectionFactory);
        //设置消息在传输中的格式，在这里采用JSON的格式进行传输
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者实例的初始数量。在这里为1个
        factory.setConcurrentConsumers(1);
        //设置并发消费者实例的最大数量。在这里为1个
        factory.setMaxConcurrentConsumers(5);
        //设置并发消费者实例中每个实例拉取的消息数量-在这里为1个
        factory.setPrefetchCount(1);
        return factory;
    }
    //自定义配置RabbitMQ发送消息的操作组件RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //设置“发送消息后进行确认”
        connectionFactory.setPublisherConfirms(true);
        //设置“发送消息后返回确认信息”
        connectionFactory.setPublisherReturns(true);
        //构造发送消息组件实例对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        //发送消息后，如果发送成功，则输出“消息发送成功”的反馈信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData,ack,cause));
        //发送消息后，如果发送失败，则输出“消息发送失败-消息丢失”的反馈信息
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message));
        //定义消息传输的格式为JSON字符串格式
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //最终返回RabbitMQ的操作组件实例RabbitTemplate
        return rabbitTemplate;
    }
    // 创建队列
    @Bean
    public Queue queue() {
        return new Queue("queue", false);
    }
    @Bean
    public Queue queue2(){
        return new Queue("queue2",false);
    }
    // 创建交换机
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("exchange");
    }
    @Bean
    public DirectExchange exchange2(){
        return new DirectExchange("exchange2");
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("routingKey");
    }
    @Bean
    public Binding binding2(Queue queue2,DirectExchange exchange2){
        return BindingBuilder.bind(queue2).to(exchange2).with("routingKey2");
    }


}
