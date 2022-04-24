package me.changwoo.kafkachatserver.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.changwoo.kafkachatserver.constants.KafkaConstants;
import me.changwoo.kafkachatserver.repository.entity.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final SimpMessagingTemplate template;

//    @Autowired
//    public MessageListener(SimpMessagingTemplate template) { // basic constructor injection
//        this.template = template;
//    }

    @KafkaListener( // 해당 토픽에 대해, 해당 group으로 listening 하겠다.
            topics = KafkaConstants.PROD_TOPIC,
            groupId =  KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        log.info("listen from kafka broker ");
        log.info("who did you send from : \n" + message.toString());
        template.convertAndSend("/topic/group", message);
    }

}
