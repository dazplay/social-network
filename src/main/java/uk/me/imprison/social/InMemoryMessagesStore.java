package uk.me.imprison.social;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryMessagesStore implements MessagesStore {
    List<Message> messages = Lists.newArrayList();

    @Override public void add(Message message) {
        messages.add(message);
    }

    @Override public List<Message> getMessagesBelongingTo(UserName user) {
        return messages.stream().filter(by(user)).collect(toList());
    }

    @Override public List<Message> getMessagesBelongingTo(Collection<UserName> users) {
        return messages.stream().filter(by(users)).collect(toList());
    }

    private Predicate<? super Message> by(Collection<UserName> users) {
        return users.stream().map(this::by).reduce(Predicate::or).get();
    }

    private Predicate<Message> by(UserName user) {
        return (message) -> Message.hasAuthor(equalTo(user)).matches(message);
    }
}
