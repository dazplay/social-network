package uk.me.imprison.social;

import java.time.LocalDateTime;

public interface ApplicationClock {
    LocalDateTime now();
}
