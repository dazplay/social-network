package uk.me.imprison.social;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static uk.me.imprison.social.ConsoleSocialApplication.createConsoleSocialApplication;
import static uk.me.imprison.social.FakeConsoleIn.consoleInputWithCommands;

public class ConsoleSocialApplicationTest {
    public static final String FIRST_COMMAND = "First command";
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    private final ConsoleOutput consoleOutput = context.mock(ConsoleOutput.class);
    private final CommandParser parser = context.mock(CommandParser.class);

    @Test public void consoleNotifiedOfAwaitingUserInput() {

        ConsoleInput consoleIn = consoleInputWithCommands(FIRST_COMMAND);

        final Sequence order = context.sequence("order");
        context.checking(new Expectations() {{
            oneOf(consoleOutput).awaitingCommand(); inSequence(order);
            oneOf(parser).parse(FIRST_COMMAND); inSequence(order);
            oneOf(consoleOutput).awaitingCommand(); inSequence(order);
        }});

        new ConsoleSocialApplication(consoleIn, consoleOutput, parser).start();
    }

}