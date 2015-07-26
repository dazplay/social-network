package uk.me.imprison.social;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static uk.me.imprison.social.Message.message;

public class SocialCommandParserTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    private final Social social = context.mock(Social.class);

    private final StaticClock clock = new StaticClock();
    private final SocialCommandParser parser = new SocialCommandParser(social, clock);

    @Test public void postActionIfHasPostSyntax() {
        final Message parsedMessage = message().by("Bob").withContent("I like hamburgers!").timestamped(clock.now()).build();

        context.checking(new Expectations() {{
            oneOf(social).post(parsedMessage);
        }});

        parser.parse("Bob -> I like hamburgers!");
    }

    @Test public void readActionIfOnlyUserNamePassedIn() {
        final UserName bob = UserName.fromString("Bob");

        context.checking(new Expectations() {{
            oneOf(social).showTimelineFor(bob, clock.now());
        }});

        parser.parse("Bob");
    }

}