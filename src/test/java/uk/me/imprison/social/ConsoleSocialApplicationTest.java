package uk.me.imprison.social;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static uk.me.imprison.social.FakeConsole.consoleYieldingCommands;

public class ConsoleSocialApplicationTest {
    public static final String FIRST_COMMAND = "First command";

    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    private final CommandParser parser = context.mock(CommandParser.class);

    @Test public void consoleInputIsPassedToParser() {
        final FakeConsole console = consoleYieldingCommands(FIRST_COMMAND);
        context.checking(new Expectations() {{
            oneOf(parser).parse(FIRST_COMMAND);
        }});

        new ConsoleSocialApplication(console, parser).start();
    }

}