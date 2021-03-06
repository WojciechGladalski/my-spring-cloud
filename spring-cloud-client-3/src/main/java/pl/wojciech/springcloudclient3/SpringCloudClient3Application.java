package pl.wojciech.springcloudclient3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.SendTo;
import pl.wojciech.springcloudclient3.converter.TextPlainMessageConverter;
import pl.wojciech.springcloudclient3.model.LogMessage;

@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class SpringCloudClient3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClient3Application.class, args);
    }

    @Bean
    public MessageConverter providesTextPlainMessageConverter() {
        return new TextPlainMessageConverter();
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public LogMessage enrichLogMessage(LogMessage logMessage) {
        log.info(logMessage.getMessage());
        return new LogMessage(String.format("[1]: %s", logMessage.getMessage()));
    }

}
