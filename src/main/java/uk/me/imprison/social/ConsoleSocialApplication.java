package uk.me.imprison.social;

public class ConsoleSocialApplication {
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
