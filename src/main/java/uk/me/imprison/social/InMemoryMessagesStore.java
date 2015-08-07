package uk.me.imprison.social;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;

public class InMemoryMessagesStore implements MessagesStore {
    private final List<Message> messages = newArrayList();

    @Override public void add(Message message) {
        messages.add(message);
    }

    @Override public List<Message> getMessagesBelongingTo(UserName user) {
        return getMessagesBelongingTo(singletonList(user));
    }

    @Override public List<Message> getMessagesBelongingTo(Collection<UserName> users) {
        return messages.stream().filter(by(users)).collect(toUnmodifiableList());
    }

    private Collector<Message, ?, List<Message>> toUnmodifiableList() {
        return collectingAndThen(toList(), Collections::unmodifiableList);
    }

    private Predicate<? super Message> by(Collection<UserName> users) {
        return users.stream().map(this::by).reduce(Predicate::or).get();
    }

    private Predicate<Message> by(UserName user) {
        return (message) -> Message.hasAuthor(equalTo(user)).matches(message);
    }
}
