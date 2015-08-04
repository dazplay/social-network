package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.List;

public interface SocialFeed {
    void showTimeLineWith(List<Message> posts, LocalDateTime requestTime);

    void showWallWith(List<Message> messages, LocalDateTime requestTime);
}
