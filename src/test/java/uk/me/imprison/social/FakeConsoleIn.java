package uk.me.imprison.social;

import static org.apache.commons.io.IOUtils.toInputStream;

public class FakeConsoleIn {

    public static ConsoleInput consoleInputWithCommands(String... commands) {
        final StringBuilder commandsStream = new StringBuilder();
        for (String command : commands) {
            commandsStream.append(command).append("\n");
        }
        return new StreamingConsoleInput(toInputStream(commandsStream.toString()));
    }

}
