package uk.me.imprison.social.e2e;

import org.hamcrest.Matcher;
import org.junit.Test;
import uk.me.imprison.social.FakeConsole;
import uk.me.imprison.social.StaticClock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;
import static uk.me.imprison.social.FakeConsole.consoleYieldingCommands;

public class TestWall {

    @Test public void onlyUsersPostsAppearInTheirWall() {
        FakeConsole console =
                consoleYieldingCommands(
                        "Alice -> I love the weather today",
                        "Bob -> Damn! We lost!",
                        "Alice follows Bob",
                        "Alice wall");

        createConsoleSocialApplication(console, new StaticClock()).start();

        assertThat(console.lines(), containsInAnyOrder(
                entry("Alice - I love the weather today (0 seconds ago)"),
                entry("Bob - Damn! We lost! (0 seconds ago)")));
    }

    private Matcher<String> entry(String message) {
        return equalTo(message);
    }
}
