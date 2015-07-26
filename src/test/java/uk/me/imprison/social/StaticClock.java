package uk.me.imprison.social;

import java.time.LocalDateTime;

public class StaticClock implements ApplicationClock {
    private final LocalDateTime referenceTime = LocalDateTime.now();

    @Override public LocalDateTime now() {
        return referenceTime;
    }

}
