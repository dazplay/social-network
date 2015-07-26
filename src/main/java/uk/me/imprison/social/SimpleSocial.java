package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleSocial implements Social {
    private final SocialFeed feed;
    private final PostsStore posts;

    public SimpleSocial(final SocialFeed feed, final PostsStore posts) {
        this.feed = feed;
        this.posts = posts;
    }

    @Override public void showTimelineFor(UserName user, LocalDateTime withRequestTime) {
        final List<Message> usersPosts = posts.getPostsBelongingTo(user);
        feed.showTimeLineWith(usersPosts, withRequestTime);
    }

    @Override public void post(Message message) {
        posts.add(message);
    }
}
