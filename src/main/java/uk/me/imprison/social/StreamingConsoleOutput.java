package uk.me.imprison.social;

import java.io.PrintStream;

public class StreamingConsoleOutput implements ConsoleOutput {
    private PrintStream out;

    public StreamingConsoleOutput(PrintStream out) {
        this.out = out;
    }

    @Override public void println(String content) {
        out.println(content);
    }
}
