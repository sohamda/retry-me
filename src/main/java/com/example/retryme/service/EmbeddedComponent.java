package com.example.retryme.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmbeddedComponent {

    public void startFirstEmbeddedProcess() {
        log.info("Embedded Component Invoked");
    }

    public void startSecondEmbeddedProcess() {
        log.info("Embedded Component Invoked");
    }
}
