package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static uk.me.imprison.social.Message.message;

public class SimpleSocialTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    private final SocialFeed feed = context.mock(SocialFeed.class);
    private final MessagesStore messages = context.mock(MessagesStore.class);
    private final SocialNetwork network = context.mock(SocialNetwork.class);

    private final LocalDateTime requestTime = LocalDateTime.now();

    private final SimpleSocial social = new SimpleSocial(feed, messages, network);

    @Test public void showWallActionResultsInAllPostsBelongingToUserAndFollowersBeingSentToFeedOrderedByTimestamp() {
        final UserName bob = UserName.fromString("Bob");
        final UserName alice = UserName.fromString("Alice");
        final List<Message> bobAndAllicesPosts = ImmutableList.of();
        final Collection<UserName> followees = ImmutableList.of(alice);

        context.checking(new Expectations() {{
            allowing(network).followeesFor(bob); will(returnValue(followees));
            allowing(messages).getMessagesBelongingTo(asList(bob, alice)); will(returnValue(bobAndAllicesPosts));
            oneOf(feed).showWallWith(bobAndAllicesPosts, requestTime);
        }});

        social.showWallFor(bob, requestTime);
    }

    @Test public void followActionsAreReportedToSocialNetwork() {
        final UserName bob = UserName.fromString("Bob");
        final UserName alice = UserName.fromString("Alice");

        context.checking(new Expectations() {{
            oneOf(network).follows(bob, alice);
        }});

        social.follow(bob, alice);
    }

    @Test public void timelineBelongingToUserIsShown() {
        final UserName bob = UserName.fromString("Bob");
        final List<Message> bobsMessages = newArrayList();
        context.checking(new Expectations() {{
            allowing(messages).getMessagesBelongingTo(bob); will(returnValue(bobsMessages));
            oneOf(feed).showTimeLineWith(bobsMessages, requestTime);
        }});
        social.showTimelineFor(bob, requestTime);
    }

    @Test public void postedMessagesAreStored() {
        Message message = message().build();
        context.checking(new Expectations() {{
            oneOf(messages).add(message);
        }});
        social.post(message);
    }

}