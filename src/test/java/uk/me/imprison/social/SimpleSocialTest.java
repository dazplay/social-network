package uk.me.imprison.social;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static uk.me.imprison.social.Message.message;

public class SimpleSocialTest {
    @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

    private final SocialFeed feed = context.mock(SocialFeed.class);
    private final PostsStore posts = context.mock(PostsStore.class);

    private final SimpleSocial social = new SimpleSocial(feed, posts);

    @Test public void timelineBelongingToUserIsShown() {
        final UserName bob = UserName.fromString("Bob");
        final List<Message> bobsPosts = newArrayList();
        context.checking(new Expectations() {{
            allowing(posts).getPostsBelongingTo(bob); will(returnValue(bobsPosts));
            oneOf(feed).showTimeLineWith(bobsPosts);
        }});
        social.showTimelineFor(bob);
    }

    @Test public void postsAreStored() {
        Message message = message().build();
        context.checking(new Expectations() {{
            oneOf(posts).add(message);
        }});
        social.post(message);
    }

}