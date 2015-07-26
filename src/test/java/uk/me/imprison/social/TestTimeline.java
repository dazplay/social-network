package uk.me.imprison.social;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.apache.commons.io.IOUtils.toInputStream;
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

        createConsoleSocialApplication(consoleIn, consoleOut).start();

        assertThat(consoleOut.lines(), contains(entryWithMessage("I love the weather today")));
    }

    private Matcher<String> entryWithMessage(String message) {
        return equalTo(message);
    }

}
