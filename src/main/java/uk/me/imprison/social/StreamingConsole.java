package uk.me.imprison.social;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StreamingConsole implements Console {
    public static final String AWAITING_COMMAND_TOKEN = "> ";

    private final Scanner scanner;
    private final PrintStream out;

    public StreamingConsole(final InputStream inputStream, PrintStream out) {
        scanner = new Scanner(inputStream);
        this.out = out;
    }

    @Override public void println(String content) {
        out.println(content);
    }

    @Override public boolean hasCommand() {
        return scanner.hasNextLine();
    }

    @Override public String readCommand() {
        out.print(AWAITING_COMMAND_TOKEN);
        return scanner.nextLine();
    }

}
