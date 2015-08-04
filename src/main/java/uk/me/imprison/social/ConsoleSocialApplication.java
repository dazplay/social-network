package uk.me.imprison.social;

public class ConsoleSocialApplication {
    public static ConsoleSocialApplication createConsoleSocialApplication(
            ConsoleInput consoleIn, ConsoleOutput consoleOut, final ApplicationClock clock) {
        SocialFeed feed = new ConsolePrintingSocialFeed(consoleOut);
        PostsStore postsStore = new InMemoryPostsStore();
        SocialNetwork network = new InMemorySocialNetwork();

        Social social = new SimpleSocial(feed, postsStore, network);
        CommandParser commandSource = new SocialCommandParser(social, clock);


        return new ConsoleSocialApplication(consoleIn, consoleOut, commandSource);
    }

    private ConsoleInput consoleIn;
    private ConsoleOutput consoleOut;
    private CommandParser parser;

    public ConsoleSocialApplication(final ConsoleInput consoleIn, final ConsoleOutput consoleOut, final CommandParser parser) {
        this.consoleIn = consoleIn;
        this.consoleOut = consoleOut;
        this.parser = parser;
    }

    public void start() {
        consoleOut.awaitingCommand();
        for (String line : consoleIn) {
            parser.parse(line);
            consoleOut.awaitingCommand();
        }
    }
}
