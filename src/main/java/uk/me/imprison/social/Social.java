package uk.me.imprison.social;

import java.time.LocalDateTime;

public interface Social {
    void showTimelineFor(UserName userName, LocalDateTime requestTime);

    void post(Message message);
}
