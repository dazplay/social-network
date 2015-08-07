package uk.me.imprison.social;

public class ConsoleSocialApplication {
    public static ConsoleSocialApplication createConsoleSocialApplication(Console console, final ApplicationClock clock) {
        SocialFeed feed = new ConsolePrintingSocialFeed(console);
        MessagesStore messagesStore = new InMemoryMessagesStore();
        SocialNetwork network = new InMemorySocialNetwork();

        Social social = new SimpleSocial(feed, messagesStore, network);
        CommandDispatcher dispatcher = new CommandDispatcher(social, clock);


        return new ConsoleSocialApplication(console, dispatcher);
    }

    private Console console;
    private CommandDispatcher dispatcher;

    public ConsoleSocialApplication(final Console console, final CommandDispatcher dispatcher) {
        this.console = console;
        this.dispatcher = dispatcher;
    }

    public void start() {
        while (console.hasCommand()) {
            String command = console.readCommand();
            dispatcher.execute(command);
        }

    }
}
