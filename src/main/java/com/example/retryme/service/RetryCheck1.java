package com.example.retryme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetryCheck1 {

    private final EmbeddedComponent embeddedComponent;

    @Retryable(
            exclude = IllegalArgumentException.class,
            maxAttempts = Integer.MAX_VALUE,
            listeners = "retryListener"
    )
    public void startProcess() {
        embeddedComponent.startFirstEmbeddedProcess();
        embeddedComponent.startSecondEmbeddedProcess();
    }
}
