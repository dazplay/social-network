package uk.me.imprison.social;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static uk.me.imprison.social.Message.message;

public class ConsolePrintingSocialFeedTest {
    private final Message message1 = message().by("Bob").withContent("Say cheese!").build();
    private final Message message2 = message().by("Bob").withContent("Say it better!").build();

    private final FakeConsoleOutput console = new FakeConsoleOutput();

    private final ConsolePrintingSocialFeed socialFeed = new ConsolePrintingSocialFeed(console);

    @Test public void messagesArePrintedToConsole() {
        socialFeed.showTimeLineWith(asList(message1, message2));

        assertThat(console.lines(), contains("Say cheese!", "Say it better!"));
    }
}