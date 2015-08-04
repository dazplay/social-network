package uk.me.imprison.social;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.String.format;

public class ConsolePrintingSocialFeed implements SocialFeed {
    public static final String USING_WALL_FORMATTING = "%s - %s (%s ago)";
    public static final String USING_TIMELINE_FORMATTING = "%s (%s ago)";

    private final ConsoleOutput out;

    public ConsolePrintingSocialFeed(ConsoleOutput out) {
        this.out = out;
    }

    @Override public void showTimeLineWith(List<Message> posts, LocalDateTime requestTime) {
        posts.stream().sorted(mostRecentFirst()).forEach(addToTimelineUsing(requestTime));
    }

    @Override public void showWallWith(final List<Message> messages, final LocalDateTime requestTime) {
        messages.stream().sorted(mostRecentFirst()).forEach(addToWallUsing(requestTime));
    }

    private Consumer<Message> addToWallUsing(final LocalDateTime requestTime) {
        return (message) -> out.println(format(USING_WALL_FORMATTING, message.author(), message.content(), formattedTimeElapsedFor(message, requestTime)));
    }

    private Consumer<Message> addToTimelineUsing(LocalDateTime requestTime) {
        return (post) -> out.println(format(USING_TIMELINE_FORMATTING, post.content(), formattedTimeElapsedFor(post, requestTime)));
    }

    private String formattedTimeElapsedFor(Message post, LocalDateTime requestTime) {
        Duration elapsedTime = Duration.between(post.timestamp(), requestTime);
        if (elapsedTime.compareTo(Duration.ofMinutes(1)) < 0) {
            return format("%s seconds", elapsedTime.getSeconds());
        }
        if (elapsedTime.compareTo(Duration.ofHours(1)) < 0) {
            return format("%s minutes", elapsedTime.toMinutes());
        }

        return format("%s hours", elapsedTime.toHours());
    }

    private Comparator<? super Message> mostRecentFirst() {
        return (o1, o2) -> o2.timestamp().compareTo(o1.timestamp());
    }

}
