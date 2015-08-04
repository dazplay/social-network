package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.List;

import com.google.common.collect.Lists;

public class SimpleSocial implements Social {
    private final SocialFeed feed;
    private final PostsStore posts;
    private SocialNetwork network;

    public SimpleSocial(final SocialFeed feed, final PostsStore posts, final SocialNetwork network) {
        this.feed = feed;
        this.posts = posts;
        this.network = network;
    }

    @Override public void showTimelineFor(UserName user, LocalDateTime withRequestTime) {
        final List<Message> usersPosts = posts.getPostsBelongingTo(user);
        feed.showTimeLineWith(usersPosts, withRequestTime);
    }

    @Override public void post(Message message) {
        posts.add(message);
    }

    @Override public void follow(UserName user, UserName followee) {
        network.follows(user, followee);
    }

    @Override public void showWallFor(UserName user, LocalDateTime withRequestTime) {
        List<UserName> users = Lists.newArrayList(user);
        users.addAll(network.followeesFor(user));

        final List<Message> wallPosts = posts.getPostsBelongingTo(users);
        feed.showWallWith(wallPosts, withRequestTime);
    }
}
