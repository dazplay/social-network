package uk.me.imprison.social;

import java.io.PrintStream;

public class StreamingConsoleOutput implements ConsoleOutput {
    public static final String AWAITING_COMMAND_TOKEN = "> ";

    private PrintStream out;

    public StreamingConsoleOutput(PrintStream out) {
        this.out = out;
    }

    @Override public void println(String content) {
        out.println(content);
    }

    @Override public void awaitingCommand() {
        out.print(AWAITING_COMMAND_TOKEN);
    }
}
