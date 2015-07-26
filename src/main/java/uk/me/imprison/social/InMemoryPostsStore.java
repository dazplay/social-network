package uk.me.imprison.social;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryPostsStore implements PostsStore {
    List<Message> messages = Lists.newArrayList();

    @Override public void add(Message message) {
        messages.add(message);
    }

    @Override public List<Message> getPostsBelongingTo(UserName user) {
        return messages.stream().filter(by(user)).collect(toList());
    }

    private Predicate<Message> by(UserName user) {
        return (message) -> Message.hasUserName(equalTo(user)).matches(message);
    }
}
