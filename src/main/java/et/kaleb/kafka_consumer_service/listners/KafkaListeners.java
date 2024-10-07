package et.kaleb.kafka_consumer_service.listners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import et.kaleb.kafka_consumer_service.dto.KafkaObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {"spring.json.value.default.type=et.kaleb.kafka_consumer_service.dto.KafkaObject"}
    )
    void listener(KafkaObject kafkaObject) throws JsonProcessingException {
        log.info("message received , {}", new ObjectMapper().writeValueAsString(kafkaObject));

    }
}
