package uk.me.imprison.social;

public class ConsoleSocialApplication {
    public static ConsoleSocialApplication createConsoleSocialApplication(ConsoleInput consoleIn, ConsoleOutput consoleOut) {
        SocialFeed feed = new ConsolePrintingSocialFeed(consoleOut);
        PostsStore postsStore = new InMemoryPostsStore();

        Social social = new SimpleSocial(feed, postsStore);
        CommandParser commandSource = new SocialCommandParser(social);

        return new ConsoleSocialApplication(consoleIn, commandSource);
    }

    private ConsoleInput consoleIn;
    private CommandParser parser;

    public ConsoleSocialApplication(final ConsoleInput consoleIn, final CommandParser parser) {
        this.consoleIn = consoleIn;
        this.parser = parser;
    }

    public void start() {
        for (String line : consoleIn) {
            parser.parse(line);
        }
    }
}
