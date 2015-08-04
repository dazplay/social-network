package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.List;

public interface SocialFeed {
    void showTimeLineWith(List<Message> messages, LocalDateTime requestTime);

    void showWallWith(List<Message> messages, LocalDateTime requestTime);
}
