package uk.me.imprison.social;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class TestTimeline {
    private final FakeConsoleOutput consoleOut = new FakeConsoleOutput();

    @Test public void onlyUsersPostsAppearInTheirTimeline() {
        ConsoleInput consoleIn =
                consoleInputWithCommands(
                        "Alice -> I love the weather today",
                        "Bob -> Damn! We lost!",
                        "Bob -> Good game though.",
                        "Alice",
                        "exit");

        ConsoleSocialApplication app = createConsoleSocialApplication(consoleIn, consoleOut);
        app.start();

        assertThat(consoleOut.lines(), contains(entryWithMessage("I love the weather today")));
    }

    private Matcher<String> entryWithMessage(String message) {
        return equalTo(message);
    }

    private ConsoleInput consoleInputWithCommands(String... commands) {
        final StringBuilder commandsStream = new StringBuilder();
        for (String command : commands) {
            commandsStream.append(command).append("\n");
        }
        return new StreamingConsoleInput(toInputStream(commandsStream.toString()));
    }


    public ConsoleSocialApplication createConsoleSocialApplication(ConsoleInput consoleIn, ConsoleOutput consoleOut) {
        SocialFeed feed = new ConsolePrintingSocialFeed(consoleOut);
        PostsStore postsStore = new InMemoryPostsStore();

        Social social = new SimpleSocial(feed, postsStore);
        CommandParser commandSource = new SocialCommandParser(social);

        return new ConsoleSocialApplication(consoleIn, commandSource);
    }
}
