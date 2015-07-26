package uk.me.imprison.social;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.String.format;

public class ConsolePrintingSocialFeed implements SocialFeed {
    private ConsoleOutput out;

    public ConsolePrintingSocialFeed(ConsoleOutput out) {
        this.out = out;
    }


    @Override public void showTimeLineWith(List<Message> posts, LocalDateTime requestTime) {
        posts.stream().sorted(mostRecentFirst()).forEach(addToTimelineUsing(requestTime));
    }

    private Consumer<Message> addToTimelineUsing(LocalDateTime requestTime) {
        return (post) -> out.println(format("%s (%s ago)", post.content(), formattedTimeElapsedFor(post, requestTime)));
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
