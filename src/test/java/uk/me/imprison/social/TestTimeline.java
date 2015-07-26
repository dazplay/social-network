package uk.me.imprison.social;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;
import static uk.me.imprison.social.FakeConsoleIn.consoleInputWithCommands;

public class TestTimeline {
    private final FakeConsoleOutput consoleOut = new FakeConsoleOutput();

    @Test public void onlyUsersPostsAppearInTheirTimeline() {
        ConsoleInput consoleIn =
                consoleInputWithCommands(
                        "Alice -> I love the weather today",
                        "Bob -> Damn! We lost!",
                        "Bob -> Good game though.",
                        "Alice");

        createConsoleSocialApplication(consoleIn, consoleOut, new StaticClock()).start();

        assertThat(consoleOut.lines(), contains(entry("I love the weather today (0 seconds ago)")));
    }

    private Matcher<String> entry(String message) {
        return equalTo(message);
    }

}
