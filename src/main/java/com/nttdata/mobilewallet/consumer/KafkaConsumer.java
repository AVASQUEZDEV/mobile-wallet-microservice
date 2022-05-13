package com.nttdata.mobilewallet.consumer;

import com.nttdata.mobilewallet.dto.request.P2PRequest;
import com.nttdata.mobilewallet.enums.BuySaleStatus;
import com.nttdata.mobilewallet.events.KafkaEvent;
import com.nttdata.mobilewallet.repository.IP2PRepository;
import com.nttdata.mobilewallet.service.IP2PService;
import com.nttdata.mobilewallet.util.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@EnableKafka
@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final IP2PRepository ip2PRepository;

    public KafkaConsumer(IP2PRepository ip2PRepository) {
        this.ip2PRepository = ip2PRepository;
    }

    @KafkaListener(topics = Topic.REQUESTED_BUY, containerFactory = "kafkaListenerContainerFactory", groupId = "group_1")
    public void requestBuyConsumer(KafkaEvent<Map<String, Object>> event) {
        LOGGER.info("[KafkaConsumer][requestBuyConsumer][start]" + event.getData());
        String fromUserId = event.getData().get("fromUserId").toString();
        ip2PRepository.findById(fromUserId)
                .doOnNext(p -> LOGGER.error("[KafkaConsumer][requestBuyConsumer][P2P]" + p.toString()))
                .flatMap(p -> {
                    p.setSaleStatus(BuySaleStatus.PENDING);
                    return ip2PRepository.save(p);
                }).subscribe();
        LOGGER.info("[KafkaConsumer][requestBuyConsumer][end]");
    }

}
