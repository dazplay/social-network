package uk.me.imprison.social;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

public class FakeConsole implements Console {

    private final Iterator<String> commandIterator;

    public static FakeConsole consoleYieldingCommands(final String... commands) {
        List<String> commandsList = Lists.newArrayList();
        for (String command : commands) {
            commandsList.add(command);
        }
        return new FakeConsole(commandsList);
    }

    public static FakeConsole fakeConsole() {
        return consoleYieldingCommands();
    }

    private final List<String> receivedLines = Lists.newArrayList();

    public FakeConsole(final List<String> commands) {
        this.commandIterator = commands.iterator();
    }

    @Override public void println(final String content) {
        receivedLines.add(content);
    }

    @Override public boolean hasCommand() {
        return commandIterator.hasNext();
    }

    @Override public String readCommand() {
        return commandIterator.next();
    }

    public List<String> lines() {
        return receivedLines;
    }
}
