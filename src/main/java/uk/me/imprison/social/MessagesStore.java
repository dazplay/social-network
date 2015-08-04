package uk.me.imprison.social;

import java.util.Collection;
import java.util.List;

public interface MessagesStore {
    List<Message> getMessagesBelongingTo(UserName userName);

    List<Message> getMessagesBelongingTo(Collection<UserName> userNames);

    void add(Message message);
}
