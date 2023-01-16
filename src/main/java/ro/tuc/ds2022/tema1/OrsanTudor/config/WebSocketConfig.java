package ro.tuc.ds2022.tema1.OrsanTudor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{
    //For configuration:
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config)
    {
        //Topic and App;
        //Carry messages to clients with destination /topic;

        //Din acestea 3 il ia pe ultimul:
        //config.enableSimpleBroker("/passingMaxValue"); //topic;
        //config.enableSimpleBroker("/passingMessageToAdmin");
        //config.enableSimpleBroker("/passingMessageToClient");

        //Mai multe deodata poate:
        config.enableSimpleBroker("/passingMaxValue", "/passingMessageToAdmin", "/passingMessageToClient"
                , "/passingTypingToAdmin", "/passingTypingToClient", "/passingReadToAdmin", "/passingReadToClient");

        //Pentru cand se primesc date de la client; Nu este nevoie de el
        //config.setApplicationDestinationPrefixes("/backend"); //app;
    }

    //For endpoint:
    //StompWebSocketEndpointRegistration
    //Varianta spring: v2.3.3;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        //SockJS for comunicare; FullDuplex api;
        //Endpoint wsSocket;
        //registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*");
        //registry.addEndpoint("/ws-message"); //.setAllowedOriginPatterns("*").withSockJS(); //* ca este inainte?
        //registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*").withSockJS();

        //1) Puteam sa ma uit la ce metode sunt available pentru endpoint, asa stiam pt version acesta ce merge:
        registry.addEndpoint("/webSocketMessage").setAllowedOrigins("*").withSockJS();

        registry.addEndpoint("/webSocketMessageClient").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/webSocketMessageAdmin").setAllowedOrigins("*").withSockJS();

        registry.addEndpoint("/webSocketMessageTypingClient").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/webSocketMessageTypingAdmin").setAllowedOrigins("*").withSockJS();

        registry.addEndpoint("/webSocketMessageReadClient").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/webSocketMessageReadAdmin").setAllowedOrigins("*").withSockJS();

        //2)
        //registry.addEndpoint("/webSocketMessage").withSockJS();
    }
}







