package uk.me.imprison.social;

public class ConsoleSocialApplication {
    public static ConsoleSocialApplication createConsoleSocialApplication(Console console, final ApplicationClock clock) {
        SocialFeed feed = new ConsolePrintingSocialFeed(console);
        MessagesStore messagesStore = new InMemoryMessagesStore();
        SocialNetwork network = new InMemorySocialNetwork();

        Social social = new SimpleSocial(feed, messagesStore, network);
        CommandParser commandSource = new SocialCommandParser(social, clock);


        return new ConsoleSocialApplication(console, commandSource);
    }

    private Console console;
    private CommandParser parser;

    public ConsoleSocialApplication(final Console console, final CommandParser parser) {
        this.console = console;
        this.parser = parser;
    }

    public void start() {
        while (console.hasCommand()) {
            String command = console.readCommand();
            parser.parse(command);
        }

    }
}
