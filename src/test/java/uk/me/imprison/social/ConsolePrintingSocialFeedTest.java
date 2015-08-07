package uk.me.imprison.social;

import java.time.LocalDateTime;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static uk.me.imprison.social.Message.message;

public class ConsolePrintingSocialFeedTest {
    private final LocalDateTime requestTime = LocalDateTime.now();
    private final Message message1 = message().by("Bob").withContent("Say cheese!").timestamped(requestTime.minusMinutes(2)).build();
    private final Message message2 = message().by("Bob").withContent("Say it better!").timestamped(requestTime.minusSeconds(10)).build();

    private final FakeConsole console = FakeConsole.fakeConsole();

    private final ConsolePrintingSocialFeed socialFeed = new ConsolePrintingSocialFeed(console);

    @Test public void wallmessagesArePrintedToConsole() {
        socialFeed.showWallWith(asList(message1, message2), requestTime);
        assertThat(console.lines(), contains("Bob - Say it better! (10 seconds ago)", "Bob - Say cheese! (2 minutes ago)"));
    }

    @Test public void timelineMessagesArePrintedToConsole() {
        socialFeed.showTimeLineWith(asList(message1, message2), requestTime);

        assertThat(console.lines(), contains("Say it better! (10 seconds ago)", "Say cheese! (2 minutes ago)"));
    }
}