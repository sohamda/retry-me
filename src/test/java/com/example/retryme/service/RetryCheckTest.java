package com.example.retryme.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RetryCheckTest {

    @MockBean
    private EmbeddedComponent embeddedComponent;

    @SpyBean
    private RetryCheck1 retryCheck1;
    @SpyBean
    private RetryCheck2 retryCheck2;

    @Test
    @DisplayName("verify retry performed 3 times")
    public void retryCheck1Test() {
        doThrow(NullPointerException.class)
                .doThrow(NullPointerException.class)
                .doThrow(IllegalArgumentException.class)
                .when(embeddedComponent).startSecondEmbeddedProcess();

        assertThrows(RuntimeException.class, () -> {
            retryCheck1.startProcess();
        });

        verify(embeddedComponent, times(3)).startSecondEmbeddedProcess();
    }

    @Test
    @DisplayName("verify no retry performed with nested method calls")
    public void retryCheck2Test() {
        doThrow(NullPointerException.class)
                .doThrow(NullPointerException.class)
                .doThrow(IllegalArgumentException.class)
                .when(embeddedComponent).startSecondEmbeddedProcess();

        assertThrows(RuntimeException.class, () -> {
            retryCheck2.startProcess();
        });

        verify(embeddedComponent, times(1)).startSecondEmbeddedProcess();
    }
}
