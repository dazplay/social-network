package uk.me.imprison.social.e2e;

import org.hamcrest.Matcher;
import org.junit.Test;
import uk.me.imprison.social.FakeConsole;
import uk.me.imprison.social.StaticClock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;

public class TestTimeline {
    @Test public void onlyUsersPostsAppearInTheirTimeline() {
        FakeConsole console =
                FakeConsole.consoleYieldingCommands(
                        "Alice -> I love the weather today",
                        "Bob -> Damn! We lost!",
                        "Bob -> Good game though.",
                        "Alice");

        createConsoleSocialApplication(console, new StaticClock()).start();

        assertThat(console.lines(), contains(entry("I love the weather today (0 seconds ago)")));
    }

    private Matcher<String> entry(String message) {
        return equalTo(message);
    }

}
