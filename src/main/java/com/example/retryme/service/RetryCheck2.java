package com.example.retryme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetryCheck2 {

    private final EmbeddedComponent embeddedComponent;

    public void startProcess() {
        embeddedComponent.startFirstEmbeddedProcess();
        doTheRetries();
    }

    @Retryable(
            exclude = IllegalArgumentException.class,
            maxAttempts = Integer.MAX_VALUE,
            listeners = "retryListener"
    )
    public void doTheRetries() {
        embeddedComponent.startSecondEmbeddedProcess();
    }
}
