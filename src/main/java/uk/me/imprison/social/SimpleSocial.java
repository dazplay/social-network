package uk.me.imprison.social;

import java.time.LocalDateTime;
import java.util.List;

import com.google.common.collect.Lists;

public class SimpleSocial implements Social {
    private final SocialFeed feed;
    private final MessagesStore messages;
    private SocialNetwork network;

    public SimpleSocial(final SocialFeed feed, final MessagesStore messages, final SocialNetwork network) {
        this.feed = feed;
        this.messages = messages;
        this.network = network;
    }

    @Override public void showTimelineFor(UserName user, LocalDateTime withRequestTime) {
        final List<Message> usersMessages = messages.getMessagesBelongingTo(user);
        feed.showTimeLineWith(usersMessages, withRequestTime);
    }

    @Override public void post(Message message) {
        messages.add(message);
    }

    @Override public void follow(UserName user, UserName followee) {
        network.follows(user, followee);
    }

    @Override public void showWallFor(UserName user, LocalDateTime withRequestTime) {
        List<UserName> users = Lists.newArrayList(user);
        users.addAll(network.followeesFor(user));

        final List<Message> wallMessages = messages.getMessagesBelongingTo(users);
        feed.showWallWith(wallMessages, withRequestTime);
    }
}
