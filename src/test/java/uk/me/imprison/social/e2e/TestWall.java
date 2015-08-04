package uk.me.imprison.social.e2e;

import org.hamcrest.Matcher;
import org.junit.Test;
import uk.me.imprison.social.ConsoleInput;
import uk.me.imprison.social.FakeConsoleOutput;
import uk.me.imprison.social.StaticClock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;
import static uk.me.imprison.social.FakeConsoleIn.consoleInputWithCommands;

public class TestWall {
    private final FakeConsoleOutput consoleOut = new FakeConsoleOutput();

    @Test public void onlyUsersPostsAppearInTheirWall() {
        ConsoleInput consoleIn =
                consoleInputWithCommands(
                        "Alice -> I love the weather today",
                        "Bob -> Damn! We lost!",
                        "Alice follows Bob",
                        "Alice wall");

        createConsoleSocialApplication(consoleIn, consoleOut, new StaticClock()).start();

        assertThat(consoleOut.lines(), containsInAnyOrder(
                entry("Alice - I love the weather today (0 seconds ago)"),
                entry("Bob - Damn! We lost! (0 seconds ago)")));
    }

    private Matcher<String> entry(String message) {
        return equalTo(message);
    }
}
