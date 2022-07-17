package org.bozdgn.reportservice.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSender {

    public void sendPropertyUpdateRequest(Long propertyID) {
        log.info("Property information request send, i guess..");
        log.info("SEND UPDATE REQUEST " + propertyID);
    }
}
